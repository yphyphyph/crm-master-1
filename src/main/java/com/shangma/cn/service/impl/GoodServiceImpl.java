package com.shangma.cn.service.impl;

import com.shangma.cn.domin.entity.Dept;
import com.shangma.cn.domin.entity.Good;
import com.shangma.cn.service.DeptService;
import com.shangma.cn.service.GoodService;
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
public class GoodServiceImpl extends BaseServiceImpl<Good> implements GoodService {
}
