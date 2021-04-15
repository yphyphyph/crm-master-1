package com.shangma.cn.domin.vo;

import com.shangma.cn.domin.entity.Brand;
import com.shangma.cn.domin.vo.base.BaseVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/15 15:58
 * 文件说明：
 */
@Data
public class BrandVo extends BaseVo {
    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 品牌描述
     */
    private String brandDesc;

    /**
     * 品牌LOGO
     */
    private String brandLogo;

    /**
     * 品牌官网
     */
    private String brandSite;


}
