package com.learn.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="User对象", description="")
public class User implements Serializable {
    private static final long serialVersionUID = -4443113102506974071L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "状态")
    private Integer status;

    //自动填充，必须要自己写一个handler实现MetaObjectHandler接口
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_Time", fill = FieldFill.INSERT)
    private Date createTime;

    //如果是FieldFill.INSERT_UPDATE，handler里在insert方法里没有设置updateTime的值，那也不会有值
    //如果是FieldFill.UPDATE，handler里在insert方法里有设置updateTime的值，那也不会更新数据库
    @ApiModelProperty(value = "修改时间")
    @TableField(value = "update_Time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    //乐观锁
    @ApiModelProperty(value = "版本号")
    @Version
    private Integer version;
}
