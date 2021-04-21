package com.shangma.cn.controller;

import com.shangma.cn.common.http.AxiosResult;
import com.shangma.cn.common.page.PageResult;
import com.shangma.cn.controller.base.BaseController;
import com.shangma.cn.domin.criteria.AdminCriteria;
import com.shangma.cn.domin.entity.Admin;
import com.shangma.cn.domin.vo.AdminVo;
import com.shangma.cn.domin.vo.DeptVo;
import com.shangma.cn.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
    public AxiosResult<Void> add(@RequestBody Admin admin) {
        //彩虹表撞库  ： 把常用的格式的密码 MD5加密好  保存起来
        admin.setAdminPwd(bCryptPasswordEncoder.encode("123456"));
        admin.setIsAdmin(false);
        return toAxios(adminService.saveAdminAndRoles(admin));
    }

    @PutMapping
    public AxiosResult<Void> update(@RequestBody Admin Admin) {
        return toAxios(adminService.updateAdminAndRoles(Admin));
    }

    @DeleteMapping("{id}")
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
    public AxiosResult<Void> deleteById(@PathVariable List<Long> ids) {
        return toAxios(adminService.batchDeleteByIds(ids));
    }


}
