package com.shangma.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.cn.common.page.PageResult;
import com.shangma.cn.common.utils.TreeUtils;
import com.shangma.cn.domin.criteria.MenuCriteria;
import com.shangma.cn.domin.entity.Admin;
import com.shangma.cn.domin.entity.AdminRole;
import com.shangma.cn.domin.entity.Menu;
import com.shangma.cn.domin.entity.RoleMenu;
import com.shangma.cn.domin.vo.MenuVo;
import com.shangma.cn.mapper.AdminRoleMapper;
import com.shangma.cn.mapper.MenuMapper;
import com.shangma.cn.mapper.RoleMenuMapper;
import com.shangma.cn.service.MenuService;
import com.shangma.cn.service.base.impl.BaseServiceImpl;
import com.shangma.cn.transfer.MenuTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/20 11:28
 * 文件说明：
 */
@Service
@Transactional
@RequiredArgsConstructor
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {


    private final MenuTransfer menuTransfer;

    private final AdminRoleMapper adminRoleMapper;


    private final RoleMenuMapper roleMenuMapper;


    private final MenuMapper menuMapper;


    @Override
    public PageResult<MenuVo> getMenuTree(MenuCriteria menuCriteria) {
        PageHelper.startPage(menuCriteria.getCurrentPage(), menuCriteria.getPageSize());
        List<Menu> root = this.search(new QueryWrapper<Menu>().lambda().eq(Menu::getParentId, 0));
        PageInfo<Menu> pageInfo = new PageInfo<>(root);
        List<MenuVo> rootVo = menuTransfer.toVO(root);
        Collections.sort(rootVo, (t, t1) -> t.getMenuSort() - t1.getMenuSort());
        List<Menu> other = this.search(new QueryWrapper<Menu>().lambda().ne(Menu::getParentId, 0));
        List<MenuVo> otherVo = menuTransfer.toVO(other);
        rootVo.forEach(item -> getChildren(item, otherVo));
        return new PageResult<MenuVo>(pageInfo.getTotal(), rootVo);
    }

    @Override
    public List<MenuVo> getAllMenuTree() {
        List<Menu> list = this.list();
        List<Menu> root = list.stream().filter(item -> item.getParentId().longValue() == 0).sorted((t, t1) -> t.getMenuSort() - t1.getMenuSort()).collect(Collectors.toList());
        list.removeAll(root);
        List<MenuVo> menuVos = menuTransfer.toVO(list);
        List<MenuVo> rootVo = menuTransfer.toVO(root);
        rootVo.forEach(item -> getChildren(item, menuVos));
        return rootVo;
    }

    @Override
    public List<Menu> getMenusByAdminId(Long adminId) {
        List<Long> roleIds = adminRoleMapper.selectList(new QueryWrapper<AdminRole>().lambda().eq(AdminRole::getAdminId, adminId)).stream().map(AdminRole::getRoleId).collect(Collectors.toList());
        Set<Long> menuIds = roleMenuMapper.selectList(new QueryWrapper<RoleMenu>().lambda().in(RoleMenu::getRoleId, roleIds)).stream().map(RoleMenu::getMenuId).collect(Collectors.toSet());
        List<Menu> menus = menuMapper.selectBatchIds(menuIds);
        return menus;
    }


    /**
     * 找孩子
     */
    public void getChildren(MenuVo menuVo, List<MenuVo> list) {
        List<MenuVo> second = list.stream().filter(item -> item.getParentId().longValue() == menuVo.getId().longValue()).sorted((t, t1) -> t.getMenuSort() - t1.getMenuSort()).collect(Collectors.toList());
        if (second != null && second.size() > 0) {
            menuVo.setChildren(second);
            list.removeAll(second);
            second.forEach(item -> {
                getChildren(item, list);
            });

        }

    }


}
