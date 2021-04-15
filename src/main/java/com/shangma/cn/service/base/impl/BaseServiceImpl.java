package com.shangma.cn.service.base.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.shangma.cn.mapper.base.MyMapper;
import com.shangma.cn.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/14 16:34
 * 文件说明：
 */
public class BaseServiceImpl<T> implements BaseService<T> {


    @Autowired
    private MyMapper<T> myMapper;

    protected MyMapper<T> getMyMapper() {
        return myMapper;
    }

    @Override
    public List<T> list() {
        return myMapper.selectList(null);
    }

    @Override
    public List<T> search(Wrapper<T> wrapper) {
        return myMapper.selectList(wrapper);
    }

    @Override
    public T getById(Long id) {
        return myMapper.selectById(id);
    }

    @Override
    public int save(T t) {
        return myMapper.insert(t);
    }

    @Override
    public int update(T t) {
        return myMapper.updateById(t);
    }

    @Override
    public int deleteById(Long id) {
        return myMapper.deleteById(id);
    }

    @Transactional
    @Override
    public int batchDeleteByIds(List<Long> ids) {
        return myMapper.deleteBatchIds(ids);
    }
}
