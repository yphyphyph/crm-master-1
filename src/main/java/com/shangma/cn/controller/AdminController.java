package com.shangma.cn.controller;

import com.alibaba.excel.EasyExcel;
import com.shangma.cn.common.http.AxiosResult;
import com.shangma.cn.common.page.PageResult;
import com.shangma.cn.common.perm.HasPerm;
import com.shangma.cn.common.valid.AddGroup;
import com.shangma.cn.common.valid.UpdateGroup;
import com.shangma.cn.controller.base.BaseController;
import com.shangma.cn.domin.criteria.AdminCriteria;
import com.shangma.cn.domin.entity.Admin;
import com.shangma.cn.domin.excel.AdminExcel;
import com.shangma.cn.domin.vo.AdminVo;
import com.shangma.cn.service.AdminService;
import com.shangma.cn.transfer.AdminExcelTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/14 16:56
 * 文件说明：
 */
@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController extends BaseController {


    private final AdminService adminService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping
    public AxiosResult<PageResult<AdminVo>> list(AdminCriteria adminCriteria) {
        PageResult<AdminVo> pageResult = adminService.searchPage(adminCriteria);
        return AxiosResult.success(pageResult);
    }

    @GetMapping("{id}")
    public AxiosResult<Admin> findById(@PathVariable Long id) {
        Admin byId = adminService.getAdminAndRoleIdsById(id);
        return AxiosResult.success(byId);
    }

    @PostMapping
    @HasPerm(perm = "admin:add")
    public AxiosResult add(@Validated(AddGroup.class) @RequestBody Admin admin) {
        //彩虹表撞库  ： 把常用的格式的密码 MD5加密好  保存起来
        admin.setAdminPwd(bCryptPasswordEncoder.encode("123456"));
        admin.setIsAdmin(false);
        return toAxios(adminService.saveAdminAndRoles(admin));
    }

    @PutMapping
    @HasPerm(perm = "admin:edit")
    public AxiosResult<Void> update(@Validated(UpdateGroup.class) @RequestBody Admin Admin) {
        return toAxios(adminService.updateAdminAndRoles(Admin));
    }

    @DeleteMapping("{id}")
    @HasPerm(perm = "admin:delete")
    public AxiosResult<Void> deleteById(@PathVariable Long id) {
        return toAxios(adminService.deleteById(id));
    }

    /**
     * 批量删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("batch/{ids}")
    @HasPerm(perm = "admin:batch")
    public AxiosResult<Void> deleteById(@PathVariable List<Long> ids) {
        return toAxios(adminService.batchDeleteByIds(ids));
    }


    private final AdminExcelTransfer adminExcelTransfer;

    @GetMapping("export")
    @HasPerm(perm = "admin:export")
    public ResponseEntity<byte[]> export() throws UnsupportedEncodingException {
        List<Admin> list = adminService.list();
        List<AdminExcel> adminExcels = adminExcelTransfer.toVO(list);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        EasyExcel.write(out, AdminExcel.class).sheet("员工信息表").doWrite(adminExcels);
        byte[] bytes = out.toByteArray();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDispositionFormData("attachement", URLEncoder.encode("员工信息表.xlsx", "utf-8"));
        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
    }


}
