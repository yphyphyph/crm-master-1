package com.shangma.cn.transfer;

import com.shangma.cn.domin.entity.Admin;
import com.shangma.cn.domin.entity.Dept;
import com.shangma.cn.domin.vo.AdminVo;
import com.shangma.cn.mapper.DeptMapper;
import com.shangma.cn.transfer.base.BaseTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/19 10:52
 * 文件说明：
 */
@Component
@RequiredArgsConstructor
public class AdminTransfer extends BaseTransfer<Admin, AdminVo> {

    private final  DeptMapper deptMapper;

    public List<AdminVo> setSex(List<Admin> list) {

        List<AdminVo> adminVos = super.toVO(list);
        for (int i = 0; i < adminVos.size(); i++) {
            Admin admin = list.get(i);
            Dept dept = deptMapper.selectById(admin.getDeptId());
            AdminVo adminVo = adminVos.get(i);
            adminVo.setDeptName(dept==null?"":dept.getDeptName());
            if (admin.getGender() == 0) {
                adminVo.setSex("男");
            } else if (admin.getGender() == 1) {
                adminVo.setSex("女");
            } else {
                adminVo.setSex("未知");
            }
        }
        return adminVos;
    }


}
