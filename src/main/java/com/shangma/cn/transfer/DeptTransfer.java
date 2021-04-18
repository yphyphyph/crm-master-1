package com.shangma.cn.transfer;

import com.shangma.cn.domin.entity.Dept;
import com.shangma.cn.domin.vo.DeptVo;
import com.shangma.cn.mapper.DeptMapper;
import com.shangma.cn.transfer.base.BaseTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/18 10:42
 * 文件说明：
 */
@Component
@RequiredArgsConstructor
public class DeptTransfer extends BaseTransfer<Dept, DeptVo> {


    private final DeptMapper deptMapper;

    @Override
    public List<DeptVo> toVO(List<Dept> list) {
        List<DeptVo> deptVos = super.toVO(list);
        deptVos.forEach(deptVo -> {
            int childrenCount = deptMapper.getChildrenCount(deptVo.getId());
            deptVo.setHasChildren(childrenCount > 0);
            deptVo.setIsLeaf(childrenCount <= 0);
        });
        return deptVos;
    }
}
