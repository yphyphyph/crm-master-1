package com.shangma.cn.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.cn.common.http.AxiosResult;
import com.shangma.cn.common.page.PageResult;
import com.shangma.cn.controller.base.BaseController;
import com.shangma.cn.domin.criteria.BrandCriteria;
import com.shangma.cn.domin.entity.Brand;
import com.shangma.cn.domin.vo.BrandVo;
import com.shangma.cn.service.BrandService;
import com.shangma.cn.transfer.BrandTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    private final BrandService brandService;

    private final BrandTransfer brandTransfer;


    @GetMapping
    public AxiosResult<PageResult<BrandVo>> list(BrandCriteria brandCriteria) {
        return AxiosResult.success(brandService.searchPage(brandCriteria));
    }


    @GetMapping("findAll")
    public AxiosResult<List<BrandVo>> findAll() {
        List<Brand> list = brandService.list();
        List<BrandVo> brandVos = brandTransfer.toVO(list);
        return AxiosResult.success(brandVos);
    }

    @GetMapping("{id}")
    public AxiosResult<BrandVo> findById(@PathVariable Long id) {
        BrandVo brandVo = brandService.findById(id);
        return AxiosResult.success(brandVo);
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

    @DeleteMapping("batch/{ids}")
    public AxiosResult<Void> batchDelete(@PathVariable List<Long> ids) {
        return toAxios(brandService.batchDeleteByIds(ids));
    }
}
