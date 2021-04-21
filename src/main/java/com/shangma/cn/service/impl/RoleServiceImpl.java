package com.shangma.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.cn.common.page.PageResult;
import com.shangma.cn.domin.criteria.RoleCriteria;
import com.shangma.cn.domin.entity.AdminRole;
import com.shangma.cn.domin.entity.Role;
import com.shangma.cn.domin.vo.RoleVo;
import com.shangma.cn.mapper.AdminRoleMapper;
import com.shangma.cn.mapper.RoleMapper;
import com.shangma.cn.service.RoleService;
import com.shangma.cn.service.base.impl.BaseServiceImpl;
import com.shangma.cn.transfer.RoleTransfer;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/20 9:56
 * 文件说明：
 */
@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {


    private final RoleMapper roleMapper;

    private final RoleTransfer roleTransfer;

    private final AdminRoleMapper adminRoleMapper;

    /**
     * 分页条件查询
     *
     * @param roleCriteria
     * @return
     */

    @Override
    public PageResult<RoleVo> searchPage(RoleCriteria roleCriteria) {
        PageHelper.startPage(roleCriteria.getCurrentPage(), roleCriteria.getPageSize());
        LambdaQueryWrapper<Role> lambda = new QueryWrapper<Role>().lambda();

        if (!StringUtils.isEmpty(roleCriteria.getRoleName())) {
            lambda.like(Role::getRoleName, roleCriteria.getRoleName());
        }
        if (!StringUtils.isEmpty(roleCriteria.getStartTime())) {
            lambda.between(Role::getCreateTime, roleCriteria.getStartTime(), roleCriteria.getEndTime());
        }
        List<Role> roles = roleMapper.selectList(lambda);
        PageInfo<Role> pageInfo = new PageInfo<>(roles);
        List<RoleVo> roleVos = roleTransfer.toVO(roles);
        return new PageResult<RoleVo>(pageInfo.getTotal(), roleVos);
    }

    @Override
    public RoleVo findById(Long id) {
        return roleTransfer.toVO(getById(id));
    }


}
