package com.shangma.cn.common.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/1 11:50
 * 文件说明：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {

    private long total;

    private List<T> data;
}
