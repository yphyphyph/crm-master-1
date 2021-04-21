package com.shangma.cn.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.cn.common.http.AxiosResult;
import com.shangma.cn.common.page.PageResult;
import com.shangma.cn.controller.base.BaseController;
import com.shangma.cn.domin.criteria.RoleCriteria;
import com.shangma.cn.domin.entity.Role;
import com.shangma.cn.domin.vo.BrandVo;
import com.shangma.cn.domin.vo.RoleVo;
import com.shangma.cn.service.RoleService;
import com.shangma.cn.transfer.BrandTransfer;
import com.shangma.cn.transfer.RoleTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/14 16:56
 * 文件说明：
 */
@RestController
@RequestMapping("role")
@RequiredArgsConstructor
public class RoleController extends BaseController {

    private final RoleService roleService;

    private final RoleTransfer roleTransfer;


    @GetMapping
    public AxiosResult<PageResult<RoleVo>> list(RoleCriteria roleCriteria) {
        PageResult<RoleVo> pageResult = roleService.searchPage(roleCriteria);
        return AxiosResult.success(pageResult);
    }


    @GetMapping("findAll")
    public AxiosResult<List<RoleVo>> findAll() {
        List<Role> list = roleService.list();
        List<RoleVo> brandVos = roleTransfer.toVO(list);
        return AxiosResult.success(brandVos);
    }

    @GetMapping("{id}")
    public AxiosResult<RoleVo> findById(@PathVariable Long id) {
        RoleVo roleVo = roleService.findById(id);
        return AxiosResult.success(roleVo);
    }


    @PostMapping
    public AxiosResult<Void> add(@RequestBody Role role) {
        return toAxios(roleService.save(role));

    }


    @PutMapping
    public AxiosResult<Void> update(@RequestBody Role role) {
        return toAxios(roleService.update(role));
    }

    @DeleteMapping("{id}")
    public AxiosResult<Void> deleteById(@PathVariable Long id) {
        return toAxios(roleService.deleteById(id));
    }

    @DeleteMapping("batch/{ids}")
    public AxiosResult<Void> batchDelete(@PathVariable List<Long> ids) {
        return toAxios(roleService.batchDeleteByIds(ids));
    }
}
