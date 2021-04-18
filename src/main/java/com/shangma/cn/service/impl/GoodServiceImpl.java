package com.shangma.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.cn.common.page.PageResult;
import com.shangma.cn.domin.criteria.GoodCriteria;
import com.shangma.cn.domin.entity.Dept;
import com.shangma.cn.domin.entity.Good;
import com.shangma.cn.domin.vo.CategoryVo;
import com.shangma.cn.domin.vo.GoodVO;
import com.shangma.cn.mapper.BrandMapper;
import com.shangma.cn.mapper.GoodMapper;
import com.shangma.cn.service.CategoryService;
import com.shangma.cn.service.DeptService;
import com.shangma.cn.service.GoodService;
import com.shangma.cn.service.base.impl.BaseServiceImpl;
import com.shangma.cn.transfer.GoodTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/14 16:54
 * 文件说明：
 */
@Service
@Transactional
@RequiredArgsConstructor
public class GoodServiceImpl extends BaseServiceImpl<Good> implements GoodService {


    private final GoodMapper goodMapper;

    private final GoodTransfer goodTransfer;


    private final BrandMapper brandMapper;

    private final CategoryService categoryService;


    @Override
    public PageResult<GoodVO> searchPage(GoodCriteria goodCriteria) {
        PageHelper.startPage(goodCriteria.getCurrentPage(), goodCriteria.getPageSize());
        LambdaQueryWrapper<Good> lambda = new QueryWrapper<Good>().lambda();
        if (!StringUtils.isEmpty(goodCriteria.getGoodName())) {
            lambda.like(Good::getGoodName, goodCriteria.getGoodName());
        }
        if (!StringUtils.isEmpty(goodCriteria.getGoodDesc())) {
            lambda.like(Good::getGoodDesc, goodCriteria.getGoodDesc());
        }
        if (!StringUtils.isEmpty(goodCriteria.getBrandId())) {
            lambda.eq(Good::getBrandId, goodCriteria.getBrandId());
        }
        if (!StringUtils.isEmpty(goodCriteria.getCategoryId()) && !StringUtils.isEmpty(goodCriteria.getCategoryLevel())) {
            switch (goodCriteria.getCategoryLevel()) {
                case 1:
                    lambda.eq(Good::getFirstCategoryId, goodCriteria.getCategoryId());
                    break;
                case 2:
                    lambda.eq(Good::getSecondCategoryId, goodCriteria.getCategoryId());
                    break;
                case 3:
                    lambda.eq(Good::getThreeCategoryId, goodCriteria.getCategoryId());
                    break;
            }
        }
        //条件拼接
        List<Good> goods = goodMapper.selectList(lambda);
        PageInfo<Good> pageInfo = new PageInfo<>(goods);
        List<GoodVO> list = goodTransfer.toVO(goods);
        return new PageResult<GoodVO>(pageInfo.getTotal(),list);
    }
}
