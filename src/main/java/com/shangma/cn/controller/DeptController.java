package com.shangma.cn.controller;

import com.shangma.cn.common.http.AxiosResult;
import com.shangma.cn.controller.base.BaseController;
import com.shangma.cn.domin.entity.Dept;
import com.shangma.cn.service.DeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/14 16:56
 * 文件说明：
 *
 */
@RestController
@RequestMapping("dept")
@RequiredArgsConstructor
public class DeptController extends BaseController {


    private final  DeptService deptService;


    @GetMapping
    public AxiosResult<List<Dept>> list() {
        List<Dept> list = deptService.list();
        return AxiosResult.success(list);
    }

    @GetMapping("{id}")
    public AxiosResult<Dept> findById(@PathVariable Long id) {
        Dept byId = deptService.getById(id);
        return AxiosResult.success(byId);
    }


    @PostMapping
    public AxiosResult<Void> add(@RequestBody Dept Dept) {
        return  toAxios(deptService.save(Dept));

    }


    @PutMapping
    public AxiosResult<Void> update(@RequestBody Dept Dept) {
        return  toAxios(deptService.update(Dept));
    }

    @DeleteMapping("{id}")
    public AxiosResult<Void> deleteById(@PathVariable Long id) {
        return  toAxios(deptService.deleteById(id));
    }
}
