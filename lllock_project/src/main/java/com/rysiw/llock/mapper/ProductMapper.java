package com.rysiw.llock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rysiw.llock.pojo.ProductPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Rysiw
 * @date 2022/9/28 11:22
 */
@Mapper
public interface ProductMapper extends BaseMapper<ProductPO> {
}
