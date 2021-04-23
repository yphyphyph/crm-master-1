package com.shangma.cn.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.cn.common.http.AxiosResult;
import com.shangma.cn.common.page.PageResult;
import com.shangma.cn.common.perm.HasPerm;
import com.shangma.cn.common.utils.TreeUtils;
import com.shangma.cn.controller.base.BaseController;
import com.shangma.cn.domin.criteria.CategoryCriteria;
import com.shangma.cn.domin.entity.Category;
import com.shangma.cn.domin.vo.CategoryVo;
import com.shangma.cn.service.CategoryService;
import com.shangma.cn.transfer.CategoryTransfer;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/14 16:56
 * 文件说明：
 */
@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController extends BaseController {


    private final CategoryService categoryService;


    private final CategoryTransfer categoryTransfer;


    /**
     * 分页查询一级分类
     *
     * @return
     */
    @GetMapping
    public AxiosResult<PageResult<CategoryVo>> list(CategoryCriteria categoryCriteria) {
        return AxiosResult.success(categoryService.findAllFirstLevelCategory(categoryCriteria));
    }

    /**
     * 通过父id找孩子
     */
    @GetMapping("{id}/children")
    public AxiosResult<List<CategoryVo>> getChildrenById(@PathVariable Long id) {
        List<CategoryVo> list = categoryService.getChildrenById(id);
        return AxiosResult.success(list);
    }


    /**
     * 根据前端勾选的等级来查询 这个等级的上级数据
     *
     * @return
     */
    @GetMapping("choose/{chooseLevel}")
    public AxiosResult<List<CategoryVo>> getByChooseLevel(@PathVariable int chooseLevel) {
        List<CategoryVo> list = categoryService.getByChooseLevel(chooseLevel);
        return AxiosResult.success(list);
    }


    /**
     * 添加功能
     */
    @PostMapping
    @HasPerm(perm = "category:add")
    public AxiosResult<Void> addEntity(@RequestBody Category category) {
        return toAxios(categoryService.save(category));
    }


    /**
     * 修改功能
     */
    @PutMapping
    @HasPerm(perm = "category:edit")
    public AxiosResult<Void> updateEntity(@RequestBody Category category) {
        return toAxios(categoryService.update(category));
    }

    /**
     * 删除
     */
    @DeleteMapping("cascade/{id}/{level}")
    @HasPerm(perm = "category:delete")
    public AxiosResult<Void> cascadeDelete(@PathVariable long id, @PathVariable int level) {
        return toAxios(categoryService.cascadeDelete(id, level));
    }


    @GetMapping("{id}")
    public AxiosResult<Category> findById(@PathVariable long id) {
        return AxiosResult.success(categoryService.getById(id));
    }


    @GetMapping("testTime")
    public AxiosResult<List<CategoryVo>> testTime() {

        long startTime = System.currentTimeMillis();
        List<CategoryVo> childrenById = categoryService.getChildrenById(0L);
        childrenById.forEach(categoryVo -> {
            List<CategoryVo> childrenById1 = categoryService.getChildrenById(categoryVo.getId());
            categoryVo.setChildren(childrenById1);
            childrenById1.forEach(categoryVo1 -> {
                categoryVo1.setChildren(categoryService.getChildrenById(categoryVo1.getId()));
            });
        });
        long endTime = System.currentTimeMillis();

        System.out.println((endTime - startTime) / 1000);

        return  AxiosResult.success(childrenById);
    }


}
