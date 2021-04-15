package com.shangma.cn.controller;

import com.shangma.cn.common.http.AxiosResult;
import com.shangma.cn.controller.base.BaseController;
import com.shangma.cn.domin.entity.Brand;
import com.shangma.cn.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/14 16:56
 * 文件说明：
 */
@RestController
@RequestMapping("brand")
@RequiredArgsConstructor

public class BrandController extends BaseController {


    private  final  BrandService brandService;

    @GetMapping
    public AxiosResult<List<Brand>> list() {
        List<Brand> list = brandService.list();
        return AxiosResult.success(list);
    }

    @GetMapping("{id}")
    public AxiosResult<Brand> findById(@PathVariable Long id) {
        Brand byId = brandService.getById(id);
        return AxiosResult.success(byId);
    }


    @PostMapping
    public AxiosResult<Void> add(@RequestBody Brand brand) {
        return toAxios(brandService.save(brand));

    }


    @PutMapping

    public AxiosResult<Void> update(@RequestBody Brand brand) {
        return toAxios(brandService.update(brand));
    }

    @DeleteMapping("{id}")

    public AxiosResult<Void> deleteById(@PathVariable Long id) {
        return toAxios(brandService.deleteById(id));
    }
}
