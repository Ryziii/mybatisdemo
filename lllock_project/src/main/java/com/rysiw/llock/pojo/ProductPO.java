package com.rysiw.llock.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Rysiw
 * @date 2022/9/28 11:18
 */
@Data
@TableName("t_product")
public class ProductPO {

    private Long id;
    private String productCode;
    private Integer stock;
    private String area;
}
