package com.rysiw.llock.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rysiw.llock.mapper.ProductMapper;
import com.rysiw.llock.pojo.ProductPO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Rysiw
 * @date 2022/9/28 11:20
 */
@Service
public class ProductService extends ServiceImpl<ProductMapper, ProductPO> {

    @Resource
    private ProductMapper productMapper;

    public synchronized String buyProduct(){
        ProductPO productPO = this.baseMapper.selectOne(new QueryWrapper<ProductPO>().lambda().eq(ProductPO::getProductCode,"1001"));
        productPO.setStock(productPO.getStock() - 1);
        this.baseMapper.updateById(productPO);
        return productPO.getStock().toString();
    }

    public String buyProductAtomicSql(){
        return productMapper.updateStock("1001").toString();
    }
}
