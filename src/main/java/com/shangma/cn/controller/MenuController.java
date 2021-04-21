package com.shangma.cn.controller;

import com.shangma.cn.common.http.AxiosResult;
import com.shangma.cn.common.page.PageResult;
import com.shangma.cn.common.utils.TreeUtils;
import com.shangma.cn.controller.base.BaseController;
import com.shangma.cn.domin.criteria.BrandCriteria;
import com.shangma.cn.domin.criteria.MenuCriteria;
import com.shangma.cn.domin.entity.Brand;
import com.shangma.cn.domin.entity.Menu;
import com.shangma.cn.domin.vo.BrandVo;
import com.shangma.cn.domin.vo.MenuVo;
import com.shangma.cn.service.BrandService;
import com.shangma.cn.service.MenuService;
import com.shangma.cn.transfer.BrandTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/20 11:28
 * 文件说明：
 */
@RestController
@RequestMapping("menu")
@RequiredArgsConstructor
public class MenuController extends BaseController {

    private final MenuService menuService;


    /**
     * 分页条件查询 tree
     *
     * @param menuCriteria
     * @return
     */
    @GetMapping
    public AxiosResult<PageResult<MenuVo>> list(MenuCriteria menuCriteria) {
        return AxiosResult.success(menuService.getMenuTree(menuCriteria));
    }

    /**
     * 查询所有菜单的Tree
     *
     * @return
     */
    @GetMapping("tree")
    public AxiosResult<List<MenuVo>> getMenuTree() {
        List<MenuVo> rootfolder = new ArrayList<>();
        List<MenuVo> list = menuService.getAllMenuTree();
        MenuVo menuVo = new MenuVo();
        menuVo.setId(0L);
        menuVo.setMenuTitle("主目录");
        menuVo.setChildren(list);
        rootfolder.add(menuVo);
        return AxiosResult.success(rootfolder);
    }


    /**
     * 添加功能
     */

    @PostMapping
    public AxiosResult<Void> add(@RequestBody Menu menu) {
        System.out.println(menu);
        return toAxios(menuService.save(menu));
    }


    /**
     * 修改功能
     */

    @PutMapping
    public AxiosResult<Void> update(@RequestBody Menu menu) {
        return toAxios(menuService.update(menu));
    }


    @GetMapping("{id}")
    public AxiosResult<Menu> findById(@PathVariable Long id) {
        return AxiosResult.success(menuService.getById(id));
    }


    @DeleteMapping("{id}")
    public AxiosResult<Void> deleteById(@PathVariable Long id) {
        return toAxios(menuService.deleteById(id));
    }
}
