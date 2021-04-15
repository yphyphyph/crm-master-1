package com.shangma.cn.controller;

import com.shangma.cn.common.http.AxiosResult;
import com.shangma.cn.controller.base.BaseController;
import com.shangma.cn.domin.entity.Category;
import com.shangma.cn.service.CategoryService;
import lombok.RequiredArgsConstructor;
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


    @GetMapping
    public AxiosResult<List<Category>> list() {
        List<Category> list = categoryService.list();
        return AxiosResult.success(list);
    }

    @GetMapping("{id}")
    public AxiosResult<Category> findById(@PathVariable Long id) {
        Category byId = categoryService.getById(id);
        return AxiosResult.success(byId);
    }


    @PostMapping
    public AxiosResult<Void> add(@RequestBody Category Category) {
        return  toAxios(categoryService.save(Category));

    }


    @PutMapping
    public AxiosResult<Void> update(@RequestBody Category Category) {
        return  toAxios(categoryService.update(Category));
    }

    @DeleteMapping("{id}")
    public AxiosResult<Void> deleteById(@PathVariable Long id) {
        return  toAxios(categoryService.deleteById(id));
    }
}
