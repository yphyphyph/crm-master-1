package com.shangma.cn.domin.criteria;

import com.shangma.cn.domin.criteria.base.BaseQueryCriteria;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/15 15:34
 * 文件说明：
 */
@Data
public class DeptCriteria extends BaseQueryCriteria {


    private String deptName;


    /**
     * 判断是否是查询
     * @return
     */
    public boolean isQuery() {
        return !StringUtils.isEmpty(this.deptName) || (!StringUtils.isEmpty(this.getStartTime()) && !StringUtils.isEmpty(this.getEndTime()));
    }


}
