package com.shangma.cn.service;

import com.shangma.cn.common.page.PageResult;
import com.shangma.cn.domin.criteria.RoleCriteria;
import com.shangma.cn.domin.entity.Role;
import com.shangma.cn.domin.vo.RoleVo;
import com.shangma.cn.service.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/20 9:55
 * 文件说明：
 */
public interface RoleService extends BaseService<Role> {
    PageResult<RoleVo> searchPage(RoleCriteria roleCriteria);

    RoleVo findById(Long id);


    /**
     * 给角色赋予权限
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    int setRoleMenu(Long roleId, List<Long> menuIds);

    /**
     * 获取角色的权限（不全的） 只用于前端展示而已
     */

    List<Long> getRoleTreeMenuForShow(Long roleId);


}
