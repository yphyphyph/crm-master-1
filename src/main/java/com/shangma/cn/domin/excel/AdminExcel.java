package com.shangma.cn.domin.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.*;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.shangma.cn.common.LocalDateStringConverter;
import lombok.Data;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.net.URL;
import java.time.LocalDateTime;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/22 9:38
 * 文件说明：
 */
@Data
@ContentRowHeight
@HeadRowHeight(40)
@ColumnWidth(25)
// 头背景设置成红色 IndexedColors.RED.getIndex()
@HeadStyle(horizontalAlignment = HorizontalAlignment.CENTER, verticalAlignment = VerticalAlignment.CENTER, fillForegroundColor = 6)
// 头字体设置成20
@HeadFontStyle(fontHeightInPoints = 20, color = 1)
// 内容的背景设置成绿色 IndexedColors.GREEN.getIndex()
@ContentStyle(verticalAlignment = VerticalAlignment.CENTER, horizontalAlignment = HorizontalAlignment.CENTER)
// 内容字体设置成20
@ContentFontStyle(fontHeightInPoints = 18)
public class AdminExcel {

    @ExcelProperty(value = "员工id", index = 0)
    private Long id;
    /**
     * 管理员名称
     */
    @ExcelProperty("员工名称")
    private String adminName;

    /**
     * 管理员昵称
     */
    @ExcelProperty("员工昵称")
    private String nickName;

    /**
     * 管理员性别
     */
    @ExcelProperty("员工性别")
    private String sex;

    /**
     * 管理员手机
     */
    @ExcelProperty("员工手机")
    private String adminPhone;

    /**
     * 管理员邮箱
     */
    @ExcelProperty("员工邮箱")
    private String adminEmail;

    /**
     * 管理员家住地址
     */
    @ColumnWidth(40)
    @ExcelProperty("员工地址")
    private String adminAddress;


    @ExcelProperty("是否可用")
    private String isActive;


    @ExcelProperty("所属部门")
    private String deptName;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "入职时间", converter = LocalDateStringConverter.class)
    private LocalDateTime createTime;

    @ExcelProperty("员工头像")
    private URL url;

}
