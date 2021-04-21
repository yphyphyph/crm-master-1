package com.shangma.cn.common.http;

import lombok.Data;

import java.awt.geom.Area;
import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/19 15:22
 * 文件说明：
 */
@Data
public class AreaResult {

    private String reason;

    private List<AreaItem> result;

}
