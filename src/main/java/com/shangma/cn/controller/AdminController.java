package com.shangma.cn.controller;

import com.shangma.cn.common.http.AxiosResult;
import com.shangma.cn.controller.base.BaseController;
import com.shangma.cn.domin.entity.Admin;
import com.shangma.cn.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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



    @GetMapping
    public AxiosResult<List<Admin>> list() {
        List<Admin> list = adminService.list();
        return AxiosResult.success(list);
    }

    @GetMapping("{id}")
    public AxiosResult<Admin> findById(@PathVariable Long id) {
        Admin byId = adminService.getById(id);
        return AxiosResult.success(byId);
    }


    @PostMapping
    public AxiosResult<Void> add(@RequestBody Admin Admin) {
        return  toAxios(adminService.save(Admin));

    }
    @PutMapping
    public AxiosResult<Void> update(@RequestBody Admin Admin) {
        return  toAxios(adminService.update(Admin));
    }

    @DeleteMapping("{id}")
    public AxiosResult<Void> deleteById(@PathVariable Long id) {
        return  toAxios(adminService.deleteById(id));
    }
}
