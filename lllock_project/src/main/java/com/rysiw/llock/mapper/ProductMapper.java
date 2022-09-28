package com.rysiw.llock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rysiw.llock.pojo.ProductPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author Rysiw
 * @date 2022/9/28 11:22
 */
@Mapper
public interface ProductMapper extends BaseMapper<ProductPO> {

    /**
     * 更新产品库存-1
     * @param productCode 产品编号
     * @return 受影响行数
     */
    @Update("UPDATE t_product SET stock = stock - 1 WHERE product_code = #{productCode} AND stock >= 1")
    Long updateStock(@Param("productCode") String productCode);
}
