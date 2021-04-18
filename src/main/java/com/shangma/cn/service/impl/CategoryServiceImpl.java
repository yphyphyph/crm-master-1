package com.shangma.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.cn.common.page.PageResult;
import com.shangma.cn.common.utils.TreeUtils;
import com.shangma.cn.domin.criteria.CategoryCriteria;
import com.shangma.cn.domin.entity.Brand;
import com.shangma.cn.domin.entity.Category;
import com.shangma.cn.domin.entity.base.BaseEntity;
import com.shangma.cn.domin.vo.CategoryVo;
import com.shangma.cn.mapper.CategoryMapper;
import com.shangma.cn.service.BrandService;
import com.shangma.cn.service.CategoryService;
import com.shangma.cn.service.base.impl.BaseServiceImpl;
import com.shangma.cn.transfer.CategoryTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/14 16:54
 * 文件说明：
 */
@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {


    private final CategoryTransfer categoryTransfer;


    private final CategoryMapper categoryMapper;

    /**
     * 显示所有的一级分类 方便列表展示
     *
     * @return
     */
    @Override
    public PageResult<CategoryVo> findAllFirstLevelCategory(CategoryCriteria categoryCriteria) {
        PageHelper.startPage(categoryCriteria.getCurrentPage(), categoryCriteria.getPageSize());
        List<Category> categories = null;
        //判断是否携带查询条件
        if (!StringUtils.isEmpty(categoryCriteria.getCategoryName()) || (categoryCriteria.getStartTime() != null && categoryCriteria.getEndTime() != null)) {
            //有条件
            LambdaQueryWrapper<Category> lambda = new QueryWrapper<Category>().lambda();

            if (!StringUtils.isEmpty(categoryCriteria.getCategoryName())) {
                lambda.like(Category::getCatetoryName, categoryCriteria.getCategoryName());
            }
            if (categoryCriteria.getStartTime() != null && categoryCriteria.getEndTime() != null) {
                lambda.between(BaseEntity::getCreateTime, categoryCriteria.getStartTime(), categoryCriteria.getEndTime());
            }
            categories = categoryMapper.selectList(lambda);
        } else {
            //如果没有条件
            categories = categoryMapper.selectList(new QueryWrapper<Category>().lambda().eq(Category::getParentId, 0L));
        }

        PageInfo<Category> pageInfo = new PageInfo<>(categories);


        List<CategoryVo> categoryVos = categoryTransfer.toVO(categories);
        return new PageResult<>(pageInfo.getTotal(), categoryVos);
    }


    /**
     * 判断有没有孩子
     *
     * @param id
     * @return
     */
    @Override
    public boolean hasChildren(Long id) {
        Integer integer = categoryMapper.selectCount(new QueryWrapper<Category>().lambda().eq(Category::getParentId, id));
        return integer > 0;
    }


    /**
     * 找对应的孩子
     *
     * @param id
     * @return
     */
    @Override
    public List<CategoryVo> getChildrenById(Long id) {
        List<Category> categories = categoryMapper.selectList(new QueryWrapper<Category>().lambda().eq(Category::getParentId, id));
        List<CategoryVo> categoryVos = categoryTransfer.toVO(categories);
        return categoryVos;
    }

    /**
     * 根据前端勾选的等级来查询 这个等级的上级数据
     *
     * @return
     */
    @Override
    public List<CategoryVo> getByChooseLevel(int chooseLevel) {
        List<CategoryVo> categoryVos = this.getChildrenById(0L);
        if (chooseLevel == 3) {
            categoryVos.forEach(categoryVo -> {
                categoryVo.setChildren(this.getChildrenById(categoryVo.getId()));

            });
        }
        return categoryVos;
    }

    /**
     * 级联删除
     *
     * @param id
     * @param level
     * @return
     */
    @Override
    public int cascadeDelete(long id, int level) {
        if (level == 1) {
            //拿到二级分类（孩子）
            List<CategoryVo> secondLevelCategory = this.getChildrenById(id);
            secondLevelCategory.forEach(categoryVo -> {
                //删除三级分类
                categoryMapper.delete(new UpdateWrapper<Category>().lambda().eq(Category::getParentId, categoryVo.getId()));
            });
            //拿到所有的Id 批量删除二级分类
            List<Long> secondCategoryIds = secondLevelCategory.stream().map(categoryVo -> categoryVo.getId()).distinct().collect(Collectors.toList());
            if (secondCategoryIds.size() > 0) {
                categoryMapper.deleteBatchIds(secondCategoryIds);
            }

        }
        //删除的是二级分类
        if (level == 2) {
            //直接删除三级分类
            categoryMapper.delete(new UpdateWrapper<Category>().lambda().eq(Category::getParentId, id));
        }

        return this.deleteById(id);
    }
}
