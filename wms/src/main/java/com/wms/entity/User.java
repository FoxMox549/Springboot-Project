package com.wms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author wms
 * @since 2024-04-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "User", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "primary key")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "account")
    private String no;

    @ApiModelProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "password")
    private String password;

    private Integer age;

    @ApiModelProperty(value = "sex")
    private Integer sex;

    @ApiModelProperty(value = "phone")
    private String phone;

    @ApiModelProperty(value = "role 0=super admin, 1=admin, 2=normal user")
    private Integer roleId;

    @ApiModelProperty(value = "is valid, Y yes, other invalid")
    @TableField("isValid")
    private String isvalid;


}
