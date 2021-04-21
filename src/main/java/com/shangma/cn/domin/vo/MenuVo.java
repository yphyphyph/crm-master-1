package com.shangma.cn.domin.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shangma.cn.domin.vo.base.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/20 11:34
 * 文件说明：
 */
@Data
public class MenuVo extends BaseVo {
    private String menuTitle;
    private Long parentId;
    private int menuType;
    private int menuSort;
    private String menuPath;
    private String menuIcon;
    private String component;
    private boolean isHidden;
    private String permission;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<MenuVo> children;
}
