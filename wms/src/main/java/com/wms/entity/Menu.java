package com.wms.entity;

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
 * @since 2024-04-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="MenuObject", description="")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "MenuCode")
    @TableField("menuCode")
    private String menucode;

    @ApiModelProperty(value = "MenuName")
    @TableField("menuName")
    private String menuname;

    @ApiModelProperty(value = "MenuLevel")
    @TableField("menuLevel")
    private String menulevel;

    @ApiModelProperty(value = "MenuParentCode")
    @TableField("menuParentCode")
    private String menuparentcode;

    @ApiModelProperty(value = "MenuClickFunction")
    @TableField("menuClick")
    private String menuclick;

    @ApiModelProperty(value = "Right 0IsSuperAdmin，1IsAdmin，2IsUser，Can be used in combination with commas")
    @TableField("menuRight")
    private String menuright;

    @TableField("menuComponent")
    private String menucomponent;

    @TableField("menuIcon")
    private String menuicon;


}
