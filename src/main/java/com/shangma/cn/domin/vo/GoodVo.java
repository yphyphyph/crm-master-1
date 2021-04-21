package com.shangma.cn.domin.vo;

import com.shangma.cn.domin.vo.base.BaseVo;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/16 15:15
 * 文件说明：
 */
@Data
public class GoodVo extends BaseVo {

    /**
     * 商品名称
     */
    private String goodName;

    /**
     * 商品价格
     */
    private BigDecimal goodPrice;

    /**
     * 商品描述
     */
    private String goodDesc;

    /**
     * 商品页面静态化使用的
     */
    private String goodContent;

    /**
     * 商品图片
     */
    private String goodImg;

    private String brandName;

}
