package com.shangma.cn.domin.entity.base;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shangma.cn.common.LocalDateStringConverter;
import com.shangma.cn.common.valid.AddGroup;
import com.shangma.cn.common.valid.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/14 16:46
 * 文件说明：
 */
@Data
public class BaseEntity implements Serializable {

    /**
     * 管理员id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @Null(groups = AddGroup.class,message = "添加时Id不能有值") //添加时 id必须没有
    @NotNull(groups = UpdateGroup.class,message = "修改时id必须有值") //修改时 id必须有
    private Long id;


    /**
     * 创建者
     */

    @JsonIgnore
    private Long createBy;

    /**
     * 创建时间
     */
    @JsonIgnore
    private LocalDateTime createTime;

    /**
     * 修改者
     */
    @JsonIgnore
    private Long updateBy;

    /**
     * 修改时间
     */
    @JsonIgnore
    private LocalDateTime updateTime;


    public void setData() {
        if (id == null) {
            //添加功能
            this.createBy = 1L;
            this.createTime = LocalDateTime.now();
        } else {
            this.updateBy = 2L;
            this.updateTime = LocalDateTime.now();
        }
    }


}
