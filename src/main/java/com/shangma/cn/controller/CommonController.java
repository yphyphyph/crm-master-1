package com.shangma.cn.controller;

import com.shangma.cn.common.async.AsyncMananger;
import com.shangma.cn.common.async.RunnableFactory;
import com.shangma.cn.common.cache.CacheService;
import com.shangma.cn.common.cache.KeyUtils;
import com.shangma.cn.common.http.AxiosResult;
import com.shangma.cn.common.http.EnumStatus;
import com.shangma.cn.common.utils.ServletUtils;
import com.shangma.cn.common.utils.TokenService;
import com.shangma.cn.common.utils.UploadUtils;
import com.shangma.cn.controller.base.BaseController;
import com.shangma.cn.domin.entity.Admin;
import com.shangma.cn.domin.entity.LoginAdmin;
import com.shangma.cn.service.AdminService;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/19 15:57
 * 文件说明：
 */
@RestController
@RequestMapping("common")
@RequiredArgsConstructor
public class CommonController extends BaseController {

    private final CacheService cacheService;


    private final AdminService adminService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;



    @GetMapping("getCaptcha/{uuid}")
    public AxiosResult<String> getCaptcha(@PathVariable String uuid) throws IOException {
        System.out.println(uuid);
        GifCaptcha specCaptcha = new GifCaptcha(130, 48, 6);
        String verCode = specCaptcha.text().toLowerCase();
        cacheService.setCacheWithDefaultTime(KeyUtils.CODE_PREFIX + uuid, verCode);
        return AxiosResult.success(specCaptcha.toBase64());
    }


    private final  TokenService tokenService;
    @PostMapping("doLogin")
    public AxiosResult<String> doLogin(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        String uuid = map.get("uuid");
        String captcha = map.get("captcha");
        //验证码是否正确
        String cache = cacheService.getCache(KeyUtils.CODE_PREFIX + uuid);
        if (!cache.equalsIgnoreCase(captcha)) {
            //验证码不正确
//            AsyncMananger.getInstance().executeByAsync(RunnableFactory.insertLoginLog(username, 1, EnumStatus.CODE_ERROR.getMessage()));
            return AxiosResult.error(EnumStatus.CODE_ERROR);
        }
        //判断用户不存在
        Admin admin = adminService.doLogin(username);
        if (admin == null) {
//            AsyncMananger.getInstance().executeByAsync(RunnableFactory.insertLoginLog(username, 1, EnumStatus.ACCOUNT_ERROR.getMessage()));
            return AxiosResult.error(EnumStatus.ACCOUNT_ERROR);
        }
        boolean matches = bCryptPasswordEncoder.matches(password, admin.getAdminPwd());
        if (!matches) {
            //密码错误
//            AsyncMananger.getInstance().executeByAsync(RunnableFactory.insertLoginLog(username, 1, EnumStatus.PASSWOED_ERROR.getMessage()));
            return AxiosResult.error(EnumStatus.PASSWOED_ERROR);
        }
        //登录成功
        cacheService.clearCache(KeyUtils.CODE_PREFIX + uuid);
//        AsyncMananger.getInstance().executeByAsync(RunnableFactory.insertLoginLog(username, 0, "登录成功"));


        LoginAdmin loginAdmin = new LoginAdmin();
        loginAdmin.setAdmin(admin);

        return AxiosResult.success(tokenService.setLoginAdminAndCreateToken(loginAdmin));

    }


    @GetMapping("getAdminInfo")
    public AxiosResult<Map<String,Object>> getUserInfo() {
        Map<String,Object> map  =     adminService.getAdminInfo();
        return AxiosResult.success(map);
    }


    /**
     * 这么判断图片是不是图片？
     * 后缀名的判断
     * //图片的大小
     *
     * @param file
     * @return
     */
    @PostMapping("upload")
    public AxiosResult<String> upload(@RequestPart Part file) throws IOException {
        //判断是不是图片
        BufferedImage read = ImageIO.read(file.getInputStream());
        if (read == null) {
            return AxiosResult.error(EnumStatus.Upload_NOT_IMAGE);
        }
        String filenameExtension = StringUtils.getFilenameExtension(file.getSubmittedFileName());
        if (!"jpg".equals(filenameExtension) && !"png".equals(filenameExtension)) {
            return AxiosResult.error(EnumStatus.IMG_EXT_ERROR);
        }
        long size = file.getSize() / 1024;
        if (size > 200) {
            return AxiosResult.error(EnumStatus.UPLoad_FILE_TOO_LANGE);
        }
        String upload = UploadUtils.upload(file);

        return AxiosResult.success(upload);
    }

}
