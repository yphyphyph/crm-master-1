package com.shangma.cn.transfer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shangma.cn.domin.entity.Category;
import com.shangma.cn.domin.vo.CategoryVo;
import com.shangma.cn.mapper.CategoryMapper;
import com.shangma.cn.transfer.base.BaseTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/16 10:33
 * 文件说明：
 */
@Component
@RequiredArgsConstructor
public class CategoryTransfer extends BaseTransfer<Category, CategoryVo> {


    private final CategoryMapper categoryMapper;

    /**
     * 封装是否有孩子的数据
     *
     * @param list
     * @return
     */
    @Override
    public List<CategoryVo> toVO(List<Category> list) {
        List<CategoryVo> categoryVos = super.toVO(list);
        categoryVos.forEach(categoryVo -> {
            Integer integer = categoryMapper.selectCount(new QueryWrapper<Category>().lambda().eq(Category::getParentId, categoryVo.getId()));
            categoryVo.setHasChildren(integer > 0);
            categoryVo.setIsLeaf(integer == 0);
        });
        return categoryVos;
    }
}
