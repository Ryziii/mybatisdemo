package com.rysiw.demo.entity;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 * @author WuRuiZhi
 * @date 2022/3/23 16:54
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collation = "test")
public class MongoTestEntity {

    @MongoId
    private Long id;

    @ApiModelProperty("associated_order表唯一标识符")
    private String associatedRecordId;

    @ApiModelProperty("预留,暂时无用")
    private Integer status;

    @ApiModelProperty("身份证号")
    private String idNum;

    @ApiModelProperty("人员股东信息")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private JSONArray shareholder;

    @ApiModelProperty("人员高管信息")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private JSONArray ryposper;

    @ApiModelProperty("人员法人信息")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private JSONArray ryposfr;

    @ApiModelProperty("人员被执行人信息")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private JSONArray punished;

    @ApiModelProperty("人员失信被执行人信息")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private JSONArray punishBreak;

    @ApiModelProperty("行政处罚信息")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private JSONArray entCaseInfo;

    @ApiModelProperty("终本案件")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private JSONArray finalCase;

    @ApiModelProperty("限制高消费")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private JSONArray limitConsum;

}
