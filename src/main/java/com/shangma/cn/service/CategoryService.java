package com.shangma.cn.service;

import com.shangma.cn.common.page.PageResult;
import com.shangma.cn.domin.criteria.CategoryCriteria;
import com.shangma.cn.domin.entity.Brand;
import com.shangma.cn.domin.entity.Category;
import com.shangma.cn.domin.vo.CategoryVo;
import com.shangma.cn.service.base.BaseService;

import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/14 16:54
 * 文件说明：
 */
public interface CategoryService extends BaseService<Category> {

    /**
     * 显示所有的一级分类 方便列表展示
     * @return
     */
    PageResult<CategoryVo> findAllFirstLevelCategory(CategoryCriteria categoryCriteria);

    /**
     * 判断有没有孩子
     * @param id
     * @return
     */
    boolean hasChildren(Long id);

    /**
     * 找对应的孩子
     * @param id
     * @return
     */
    List<CategoryVo> getChildrenById(Long id);
    /**
     * 根据前端勾选的等级来查询 这个等级的上级数据
     *
     * @return
     */
    List<CategoryVo> getByChooseLevel(int chooseLevel);

    /*
     级联删除
     */
    int cascadeDelete(long id, int level);
}
