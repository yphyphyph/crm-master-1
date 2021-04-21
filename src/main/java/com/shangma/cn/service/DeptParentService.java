package com.shangma.cn.service;

import com.shangma.cn.common.page.PageResult;
import com.shangma.cn.domin.criteria.DeptCriteria;
import com.shangma.cn.domin.entity.Dept;
import com.shangma.cn.domin.vo.DeptVo;
import com.shangma.cn.service.base.BaseService;

import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/14 16:54
 * 文件说明：
 */
public interface DeptParentService extends BaseService<Dept> {


    List<DeptVo> getDeptVoTree(Long id,List<Dept> lsit);
}
