package com.shangma.cn.controller;

import com.shangma.cn.common.http.AxiosResult;
import com.shangma.cn.common.utils.UploadUtils;
import com.shangma.cn.controller.base.BaseController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Part;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/19 15:57
 * 文件说明：
 */
@RestController
@RequestMapping("common")
public class UpLoadController extends BaseController {
    @PostMapping("upload")
    public AxiosResult<String> upload(@RequestPart Part file) {
        String upload = UploadUtils.upload(file);
        return AxiosResult.success(upload);
    }

}
