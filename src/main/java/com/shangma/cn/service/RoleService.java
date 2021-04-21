package com.shangma.cn.service;

import com.shangma.cn.common.page.PageResult;
import com.shangma.cn.domin.criteria.RoleCriteria;
import com.shangma.cn.domin.entity.Role;
import com.shangma.cn.domin.vo.RoleVo;
import com.shangma.cn.service.base.BaseService;

import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/20 9:55
 * 文件说明：
 */
public interface RoleService extends BaseService<Role> {
    PageResult<RoleVo> searchPage(RoleCriteria roleCriteria);

    RoleVo findById(Long id);




}
