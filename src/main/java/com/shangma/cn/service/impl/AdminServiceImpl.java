package com.shangma.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.cn.common.page.PageResult;
import com.shangma.cn.common.utils.TokenService;
import com.shangma.cn.common.utils.TreeUtils;
import com.shangma.cn.domin.criteria.AdminCriteria;
import com.shangma.cn.domin.entity.*;
import com.shangma.cn.domin.vo.AdminVo;
import com.shangma.cn.domin.vo.MenuVo;
import com.shangma.cn.mapper.AdminMapper;
import com.shangma.cn.mapper.AdminRoleMapper;
import com.shangma.cn.mapper.MenuMapper;
import com.shangma.cn.service.AdminService;
import com.shangma.cn.service.CategoryService;
import com.shangma.cn.service.MenuService;
import com.shangma.cn.service.base.impl.BaseServiceImpl;
import com.shangma.cn.transfer.AdminTransfer;
import com.shangma.cn.transfer.MenuTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/14 16:54
 * 文件说明：
 */
@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {
    /**
     * 分页条件查询
     *
     * @param adminCriteria
     * @return
     */


    private final AdminMapper adminMapper;
    private final AdminTransfer adminTransfer;
    private final AdminRoleMapper adminRoleMapper;


    private final MenuMapper menuMapper;

    private final MenuTransfer menuTransfer;

    @Override
    public PageResult<AdminVo> searchPage(AdminCriteria adminCriteria) {
        PageHelper.startPage(adminCriteria.getCurrentPage(), adminCriteria.getPageSize());
        LambdaQueryWrapper<Admin> lambda = new QueryWrapper<Admin>().lambda();

        if (!StringUtils.isEmpty(adminCriteria.getAdminName())) {
            lambda.like(Admin::getAdminName, adminCriteria.getAdminName());
        }
        if (!StringUtils.isEmpty(adminCriteria.getAdminPhone())) {
            lambda.eq(Admin::getAdminPhone, adminCriteria.getAdminPhone());
        }

        if (adminCriteria.getDeptId() != null && adminCriteria.getDeptId() != 0) {
            lambda.eq(Admin::getDeptId, adminCriteria.getDeptId());
        }
        if (!StringUtils.isEmpty(adminCriteria.getStartTime())) {
            lambda.between(Admin::getCreateTime, adminCriteria.getStartTime(), adminCriteria.getEndTime());
        }
        List<Admin> admins = adminMapper.selectList(lambda);
        PageInfo<Admin> pageInfo = new PageInfo<>(admins);
        List<AdminVo> adminVos = adminTransfer.setSex(admins);
        return new PageResult<AdminVo>(pageInfo.getTotal(), adminVos);
    }

    /**
     * 保存员工和员工的角色
     *
     * @param admin
     * @return
     */
    @Override
    public int saveAdminAndRoles(Admin admin) {
        int save = this.save(admin);
        Set<Long> roleIds = admin.getRoleIds();
        if (roleIds != null) {
            roleIds.forEach(roleId -> {
                adminRoleMapper.insert(new AdminRole(admin.getId(), roleId));
            });
        }

        return save;
    }

    @Override
    public Admin getAdminAndRoleIdsById(Long id) {
        Admin byId = this.getById(id);
        List<AdminRole> adminRoles = adminRoleMapper.selectList(new QueryWrapper<AdminRole>().lambda().eq(AdminRole::getAdminId, id));
        byId.setRoleIds(adminRoles.stream().map(AdminRole::getRoleId).collect(Collectors.toSet()));
        return byId;
    }

    /**
     * 更新用户和用户的角色
     *
     * @param admin
     * @return
     */
    @Override
    public int updateAdminAndRoles(Admin admin) {
        adminRoleMapper.delete(new UpdateWrapper<AdminRole>().lambda().eq(AdminRole::getAdminId, admin.getId()));
        admin.getRoleIds().forEach(roleId -> adminRoleMapper.insert(new AdminRole(admin.getId(), roleId)));
        return this.update(admin);
    }

    @Override
    public Admin doLogin(String username) {
//        return adminMapper.selectOne(new QueryWrapper<Admin>().lambda().eq(Admin::getAdminName, username));
        List<Admin> admins = adminMapper.selectList(new QueryWrapper<Admin>().lambda().eq(Admin::getAdminName, username));
        if (admins.isEmpty()) {
            return null;
        }
        return admins.get(0);
    }

    final TokenService tokenService;

    final MenuService menuService;

    @Override
    public Map<String, Object> getAdminInfo() {
        long startTime = System.currentTimeMillis();

        Admin admin = tokenService.getLoginAdmin().getAdmin();
        Map<String, Object> map = new HashMap<>();
        map.put("admin", admin);
        List<Menu> list = null;
        //拿菜单
        if (tokenService.isAdmin()) {
            list = menuMapper.selectList(null);
            System.out.println("所有的权限："+list);
        } else {
            list = menuService.getMenusByAdminId(tokenService.getAdminId());
        }
        LoginAdmin loginAdmin = tokenService.getLoginAdmin();
        loginAdmin.setPerms(list);
        tokenService.setLoginAdmin(loginAdmin);
        //干掉按钮级别权限
        List<Menu> collect = list.stream().filter(menu -> menu.getMenuType() != 2).collect(Collectors.toList());
        System.out.println("菜单列表"+collect);
        List<MenuVo> menuVos = menuTransfer.toVO(collect);
        System.out.println("vo列表"+menuVos);
        List<MenuVo> menuVos1 = TreeUtils.buildTree(menuVos);
        System.out.println("tree列表"+menuVos1);
        map.put("huige", menuVos1);
        //按钮级别
        List<Menu> btnPerm = list.stream().filter(menu -> menu.getMenuType() != 0).collect(Collectors.toList());
        map.put("huigeBtn", btnPerm);
        long endTime = System.currentTimeMillis();
        System.out.println("查询用户详情的用时时间:"+ (endTime-startTime));
        return map;
    }
}
