package com.shangma.cn.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.cn.common.http.AxiosResult;
import com.shangma.cn.common.page.PageResult;
import com.shangma.cn.common.perm.HasPerm;
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
import java.util.Map;

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
    @HasPerm(perm = "role:add")
    public AxiosResult<Void> add(@RequestBody Role role) {
        return toAxios(roleService.save(role));

    }


    @PutMapping
    @HasPerm(perm = "role:edit")
    public AxiosResult<Void> update(@RequestBody Role role) {
        return toAxios(roleService.update(role));
    }

    @DeleteMapping("{id}")
    @HasPerm(perm = "role:delete")
    public AxiosResult<Void> deleteById(@PathVariable Long id) {
        return toAxios(roleService.deleteById(id));
    }

    @DeleteMapping("batch/{ids}")
    @HasPerm(perm = "role:batch")
    public AxiosResult<Void> batchDelete(@PathVariable List<Long> ids) {
        return toAxios(roleService.batchDeleteByIds(ids));
    }

    /**
     * 给用户赋予权限
     */

    @PutMapping("{roleId}/menu")
    public AxiosResult<Void> setRoleMenu(@PathVariable Long roleId, @RequestBody List<Long> menuIds) {
        int row = roleService.setRoleMenu(roleId, menuIds);
        return toAxios(row);
    }

    /**
     * 获取角色的权限（不全的） 只用于前端展示而已
     */

    @GetMapping("{roleId}/treeMenu")
    public AxiosResult<List<Long>> getRoleTreeMenuForShow(@PathVariable Long roleId) {
        List<Long> list = roleService.getRoleTreeMenuForShow(roleId);
        return AxiosResult.success(list);
    }
}
