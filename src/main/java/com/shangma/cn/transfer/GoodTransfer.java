package com.shangma.cn.transfer;

import com.shangma.cn.domin.entity.Brand;
import com.shangma.cn.domin.entity.Good;
import com.shangma.cn.domin.vo.GoodVO;
import com.shangma.cn.mapper.BrandMapper;
import com.shangma.cn.transfer.base.BaseTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/16 15:28
 * 文件说明：
 */
@Component
@RequiredArgsConstructor
public class GoodTransfer extends BaseTransfer<Good, GoodVO> {


    private final BrandMapper brandMapper;

    @Override
    public List<GoodVO> toVO(List<Good> list) {
        List<GoodVO> list1 = super.toVO(list);
        //填充品牌名称
        for (int i = 0; i <list.size() ; i++) {
            Brand brand = brandMapper.selectById(list.get(i).getBrandId());
            if(brand!=null){
                list1.get(i).setBrandName(brand.getBrandName());
            }
        }
        return list1;
    }
}
