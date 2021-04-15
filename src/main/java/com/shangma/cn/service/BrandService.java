package com.shangma.cn.service;

import com.shangma.cn.domin.criteria.BrandCriteria;
import com.shangma.cn.domin.entity.Brand;
import com.shangma.cn.domin.vo.BrandVo;
import com.shangma.cn.service.base.BaseService;

import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/14 16:54
 * 文件说明：
 */
public interface BrandService  extends BaseService<Brand> {

    /**
     * 分页条件查询
     * @param brandCriteria
     * @return
     */
    List<BrandVo> searchPage(BrandCriteria brandCriteria);
}
