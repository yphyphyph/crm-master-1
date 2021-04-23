package com.shangma.cn.service;

import com.shangma.cn.common.page.PageResult;
import com.shangma.cn.domin.criteria.AdminCriteria;
import com.shangma.cn.domin.entity.Admin;
import com.shangma.cn.domin.entity.Dept;
import com.shangma.cn.domin.vo.AdminVo;
import com.shangma.cn.service.base.BaseService;

import java.util.Map;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/14 16:54
 * 文件说明：
 */
public interface AdminService extends BaseService<Admin> {
    /**
     * 分页条件查询
     *
     * @param adminCriteria
     * @return
     */
    PageResult<AdminVo> searchPage(AdminCriteria adminCriteria);

    /**
     * 保存员工和员工的角色
     *
     * @param admin
     * @return
     */
    int saveAdminAndRoles(Admin admin);

    /**
     * 添加用户和用户的角色
     *
     * @param admin
     * @return
     */

    Admin getAdminAndRoleIdsById(Long id);

    /**
     * 更新用户和用户的角色
     *
     * @param admin
     * @return
     */
    int updateAdminAndRoles(Admin admin);


    /**
     * 登录功能 通过用户名查询用户
     */
    Admin doLogin(String username);

    /**
     * 获得用户信息
     * 菜单 本身信息 按钮级别权限
     *
     * @return
     */

    Map<String, Object> getAdminInfo();
}
