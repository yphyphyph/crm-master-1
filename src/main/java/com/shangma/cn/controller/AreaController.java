package com.shangma.cn.controller;

import com.shangma.cn.common.http.AreaItem;
import com.shangma.cn.common.http.AreaResult;
import com.shangma.cn.common.http.AxiosResult;
import com.shangma.cn.controller.base.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/19 15:15
 * 文件说明：
 */
@RequestMapping("area")
@RequiredArgsConstructor
@RestController
public class AreaController extends BaseController {

    private final RestTemplate restTemplate;

    @GetMapping("{id}/children")
    public AxiosResult<List<AreaItem>> getChildById(@PathVariable String id) {
        String path = "http://apis.juhe.cn/xzqh/query?key=59db7e044462bff7394d4eb67541e7af&fid=" + id;
        AreaResult areaResult = restTemplate.getForObject(path, AreaResult.class);
        return AxiosResult.success(areaResult.getResult());
    }



}
