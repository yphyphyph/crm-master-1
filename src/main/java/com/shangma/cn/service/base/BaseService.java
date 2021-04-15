package com.shangma.cn.service.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import java.io.Serializable;
import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/14 16:28
 * 文件说明：
 */
public interface BaseService<T> {

    /**
     * 查询所有
     * @return
     */
    List<T> list();

    /**
     * 条件查询
     */
    List<T> search(Wrapper<T> wrapper);

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 添加操作
     *
     * @param t
     * @return
     */
    int save(T t);

    /**
     * 修改操作
     *
     * @param t
     * @return
     */
    int update(T t);

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    int deleteById(Long id);


    /**
     * 批量删除
     */
    int batchDeleteByIds(List<Long> ids);


}
