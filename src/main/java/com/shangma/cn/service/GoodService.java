package com.shangma.cn.service;

import com.shangma.cn.common.page.PageResult;
import com.shangma.cn.domin.criteria.GoodCriteria;
import com.shangma.cn.domin.entity.Good;
import com.shangma.cn.domin.vo.GoodVo;
import com.shangma.cn.service.base.BaseService;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/14 16:54
 * 文件说明：
 */
public interface GoodService extends BaseService<Good> {
    PageResult<GoodVo> searchPage(GoodCriteria goodCriteria);
}
