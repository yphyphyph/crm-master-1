package com.shangma.cn.controller;

import com.shangma.cn.common.http.AxiosResult;
import com.shangma.cn.controller.base.BaseController;
import com.shangma.cn.domin.entity.Good;
import com.shangma.cn.service.GoodService;
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
@RequestMapping("good")
@RequiredArgsConstructor
public class GoodController extends BaseController {


    private final GoodService goodService;


    @GetMapping
    public AxiosResult<List<Good>> list() {
        List<Good> list = goodService.list();
        return AxiosResult.success(list);
    }

    @GetMapping("{id}")
    public AxiosResult<Good> findById(@PathVariable Long id) {
        Good byId = goodService.getById(id);
        return AxiosResult.success(byId);
    }


    @PostMapping
    public AxiosResult<Void> add(@RequestBody Good Good) {
        return toAxios(goodService.save(Good));

    }


    @PutMapping
    public AxiosResult<Void> update(@RequestBody Good Good) {
        return toAxios(goodService.update(Good));
    }

    @DeleteMapping("{id}")
    public AxiosResult<Void> deleteById(@PathVariable Long id) {
        return toAxios(goodService.deleteById(id));
    }
}
