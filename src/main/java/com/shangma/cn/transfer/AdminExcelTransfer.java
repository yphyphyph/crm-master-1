package com.shangma.cn.transfer;

import com.shangma.cn.domin.entity.Admin;
import com.shangma.cn.domin.excel.AdminExcel;
import com.shangma.cn.mapper.DeptMapper;
import com.shangma.cn.transfer.base.BaseTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/22 9:44
 * 文件说明：
 */
@Component
@RequiredArgsConstructor
public class AdminExcelTransfer extends BaseTransfer<Admin, AdminExcel> {


    private final DeptMapper deptMapper;

    @Override
    public List<AdminExcel> toVO(List<Admin> list) {
        List<AdminExcel> adminExcels = super.toVO(list);
        try {
            for (int i = 0; i < list.size(); i++) {
                Admin admin = list.get(i);
                AdminExcel adminExcel = adminExcels.get(i);
                Integer gender = admin.getGender();
                if (gender == 0) {
                    adminExcel.setSex("男");
                }
                if (gender == 1) {
                    adminExcel.setSex("女");
                }
                if (gender == 2) {
                    adminExcel.setSex("未知");
                }

                adminExcel.setDeptName(deptMapper.selectById(admin.getDeptId()).getDeptName());
                adminExcel.setUrl(new URL(admin.getAdminAvatar()));
                adminExcel.setIsActive(admin.getIsEnable() ? "可用" : "不可用");
            }
        } catch (Exception e) {

        }


        return adminExcels;

    }
}
