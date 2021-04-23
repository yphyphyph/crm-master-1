package com.shangma.cn.service;

import com.shangma.cn.common.page.PageResult;
import com.shangma.cn.domin.criteria.MenuCriteria;
import com.shangma.cn.domin.entity.Menu;
import com.shangma.cn.domin.vo.MenuVo;
import com.shangma.cn.service.base.BaseService;

import java.util.List;
import java.util.Set;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/20 11:27
 * 文件说明：
 */
public interface MenuService extends BaseService<Menu> {

    /**
     * 获得所有的菜单通过树型展示
     *
     * @return
     */
    PageResult<MenuVo> getMenuTree(MenuCriteria menuCriteria);

    List<MenuVo> getAllMenuTree();


    List<Menu> getMenusByAdminId(Long adminId);


}
