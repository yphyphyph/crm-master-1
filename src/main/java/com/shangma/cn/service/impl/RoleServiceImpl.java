package com.shangma.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.cn.common.page.PageResult;
import com.shangma.cn.domin.criteria.RoleCriteria;
import com.shangma.cn.domin.entity.AdminRole;
import com.shangma.cn.domin.entity.Menu;
import com.shangma.cn.domin.entity.Role;
import com.shangma.cn.domin.entity.RoleMenu;
import com.shangma.cn.domin.vo.RoleVo;
import com.shangma.cn.mapper.AdminRoleMapper;
import com.shangma.cn.mapper.MenuMapper;
import com.shangma.cn.mapper.RoleMapper;
import com.shangma.cn.mapper.RoleMenuMapper;
import com.shangma.cn.service.RoleService;
import com.shangma.cn.service.base.impl.BaseServiceImpl;
import com.shangma.cn.transfer.RoleTransfer;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private final RoleMenuMapper roleMenuMapper;


    private final MenuMapper menuMapper;

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

    /**
     * 给角色赋予权限
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    @Override
    public int setRoleMenu(Long roleId, List<Long> menuIds) {
        roleMenuMapper.delete(new UpdateWrapper<RoleMenu>().lambda().eq(RoleMenu::getRoleId, roleId));
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setRoleId(roleId);
        menuIds.forEach(menuId -> {
            roleMenu.setMenuId(menuId);
            roleMenuMapper.insert(roleMenu);
        });

        return 1;
    }

    /**
     * 获取角色的权限（不全的） 只用于前端展示而已
     */

    @Override
    public List<Long> getRoleTreeMenuForShow(Long roleId) {
        List<Long> list = new ArrayList<>();
        List<RoleMenu> roleMenus = roleMenuMapper.selectList(new QueryWrapper<RoleMenu>().lambda().eq(RoleMenu::getRoleId, roleId));
        //获得所有的menuIds
        List<Long> menuIds = roleMenus.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        if (menuIds.size() > 0) {
            List<Menu> menus = menuMapper.selectBatchIds(menuIds);
            //实在不会写就这样写
            //获得所有的按钮级别
            List<Menu> menuAndBtnList = menus.stream().filter(menu -> menu.getMenuType() != 0).collect(Collectors.toList());

            menuAndBtnList.forEach(menu -> {
                if (!menuAndBtnList.stream().anyMatch(menu1 -> menu.getId().equals(menu1.getParentId()))) {
                    list.add(menu.getId());
                }
            });


        }
        return list;

    }


}
