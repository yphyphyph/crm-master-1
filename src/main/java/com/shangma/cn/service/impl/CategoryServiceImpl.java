package com.shangma.cn.service.impl;

import com.shangma.cn.domin.entity.Brand;
import com.shangma.cn.domin.entity.Category;
import com.shangma.cn.service.BrandService;
import com.shangma.cn.service.CategoryService;
import com.shangma.cn.service.base.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/14 16:54
 * 文件说明：
 */
@Service
@Transactional
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {
}
