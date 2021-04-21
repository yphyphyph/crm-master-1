package com.shangma.cn.service;

import com.shangma.cn.common.page.PageResult;
import com.shangma.cn.domin.criteria.DeptCriteria;
import com.shangma.cn.domin.entity.Dept;
import com.shangma.cn.domin.entity.Good;
import com.shangma.cn.domin.vo.DeptVo;
import com.shangma.cn.service.base.BaseService;

import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/14 16:54
 * 文件说明：
 */
public interface DeptService extends BaseService<Dept> {


    /**
     * 通过id找孩子
     *
     * @param id
     * @return
     */
    public List<DeptVo> getChildrenById(Long id);

    /**
     * 分页条件查询
     *
     * @param deptCriteria
     * @return
     */
    PageResult<DeptVo> searchPage(DeptCriteria deptCriteria);


    /**
     * 通过一个部门父id 找到同级以及上一级 上上一级...
     *
     * @param parentId
     * @return
     */
    public List<DeptVo> getSuperByParent(Long parentId, List<DeptVo> list);


    /**
     * 级联递归删除
     *
     * @param id
     * @return
     */
    int deleteSelfAndChildren(Long id);

    List<DeptVo> getDeptTree();


    public List<DeptVo> getDeptVoTree(Long id, List<Dept> list);
}
