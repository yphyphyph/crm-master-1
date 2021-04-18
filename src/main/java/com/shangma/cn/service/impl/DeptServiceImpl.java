package com.shangma.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.cn.common.page.PageResult;
import com.shangma.cn.domin.criteria.DeptCriteria;
import com.shangma.cn.domin.entity.Brand;
import com.shangma.cn.domin.entity.Dept;
import com.shangma.cn.domin.vo.DeptVo;
import com.shangma.cn.mapper.DeptMapper;
import com.shangma.cn.service.BrandService;
import com.shangma.cn.service.DeptService;
import com.shangma.cn.service.base.impl.BaseServiceImpl;
import com.shangma.cn.transfer.DeptTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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
public class DeptServiceImpl extends BaseServiceImpl<Dept> implements DeptService {


    private final DeptMapper deptMapper;


    private final DeptTransfer deptTransfer;

    /**
     * 通过部门id找孩子
     *
     * @param id
     * @return
     */
    @Override
    public List<DeptVo> getChildrenById(Long id) {
        List<Dept> depts = deptMapper.selectList(new QueryWrapper<Dept>().lambda().eq(Dept::getParentId, id).orderByAsc(Dept::getDeptSort));
        return deptTransfer.toVO(depts);
    }

    /**
     * 分页条件查询
     *
     * @param deptCriteria
     * @return
     */
    @Override
    public PageResult<DeptVo> searchPage(DeptCriteria deptCriteria) {
        PageHelper.startPage(deptCriteria.getCurrentPage(), deptCriteria.getPageSize());
        LambdaQueryWrapper<Dept> lambda = new QueryWrapper<Dept>().lambda();
        if (deptCriteria.isQuery()) {
            //        如果是查询 查询的不一定是第一级数据 所以不能拼接一级的条件
            if (!StringUtils.isEmpty(deptCriteria.getDeptName())) {
                lambda.like(Dept::getDeptName, deptCriteria.getDeptName());
            }
            if (!StringUtils.isEmpty(deptCriteria.getStartTime()) && !StringUtils.isEmpty(deptCriteria.getEndTime())) {
                lambda.between(Dept::getCreateTime, deptCriteria.getStartTime(), deptCriteria.getEndTime());
            }
        } else {
            //如果不是条件查询 则查询的是第一级
            lambda.eq(Dept::getParentId, 0).orderByAsc(Dept::getDeptSort);
        }
        List<Dept> depts = deptMapper.selectList(lambda);
        PageInfo<Dept> pageInfo = new PageInfo<>(depts);
        return new PageResult<DeptVo>(pageInfo.getTotal(), deptTransfer.toVO(depts));
    }

    /**
     * 通过一个部门父id 找到同级以及上一级 上上一级
     *
     * @param parentId
     * @return
     */
    @Override
    public List<DeptVo> getSuperByParent(Long parentId, List<DeptVo> list) {
        //如果查询的就是一级数据 则直接返回一级即可
        if (parentId.longValue() == 0) {
            list.addAll(this.getChildrenById(0L));
            return list;
        } else {
            list.addAll(this.getChildrenById(parentId));
            return getSuperByParent(getById(parentId).getParentId(), list);
        }


    }


    /**
     * 级联递归删除
     *
     * @param id
     * @return
     */
    @Override
    public int deleteSelfAndChildren(Long id) {
        //递归找到所有要删除的id
        List<Long> deleteIds = new ArrayList<>();
        deleteIds.add(id);
        setChildrenId(deleteIds, id);
        return batchDeleteByIds(deleteIds);
    }

    /**
     * 递归封装要删除的孩子
     * @param ids
     * @param id
     */
    private void setChildrenId(List<Long> ids, Long id) {
        List<DeptVo> children = getChildrenById(id);
        children.forEach(item -> {
            ids.add(item.getId());
            setChildrenId(ids, item.getId());
        });
    }
}
