package com.shangma.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.cn.common.page.PageResult;
import com.shangma.cn.domin.criteria.BrandCriteria;
import com.shangma.cn.domin.entity.Brand;
import com.shangma.cn.domin.vo.BrandVo;
import com.shangma.cn.mapper.BrandMapper;
import com.shangma.cn.service.BrandService;
import com.shangma.cn.service.base.impl.BaseServiceImpl;
import com.shangma.cn.transfer.BrandTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/14 16:54
 * 文件说明：
 */
@Service
@RequiredArgsConstructor
@Transactional
public class BrandServiceImpl extends BaseServiceImpl<Brand> implements BrandService {


    private final BrandMapper brandMapper;
    private final BrandTransfer brandTransfer;

    @Override
    public PageResult<BrandVo> searchPage(BrandCriteria brandCriteria) {
        //开启分页
        PageHelper.startPage(brandCriteria.getCurrentPage(), brandCriteria.getPageSize());
        LambdaQueryWrapper<Brand> lambda = new QueryWrapper<Brand>().lambda();
        if (!StringUtils.isEmpty(brandCriteria.getBrandName())) {
            lambda.like(Brand::getBrandName, brandCriteria.getBrandName());
        }
        LocalDateTime startTime = brandCriteria.getStartTime();
        LocalDateTime endTime = brandCriteria.getEndTime();
        if (startTime != null && endTime != null) {
            lambda.between(Brand::getCreateTime, startTime, endTime);
        }
        List<Brand> brands = brandMapper.selectList(lambda);
        PageInfo<Brand>  pageInfo  = new PageInfo<>(brands);
        return new PageResult<BrandVo>(pageInfo.getTotal(),brandTransfer.toVO(brands));

    }

    @Override
    public BrandVo findById(Long id) {
        Brand byId = getById(id);
        return brandTransfer.toVO(byId);
    }
}
