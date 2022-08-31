package com.laboratory.modules.base.entrustCompany.domain;

import com.laboratory.base.CommonModel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;

import java.io.Serializable;
import java.util.Date;

/**
* @author Randolph
* @date 2022-08-31
*/
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("base_entrust_company")
public class BaseEntrustCompany extends CommonModel<BaseEntrustCompany> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(type= IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "单位名称")
    private String companyName;

    @ApiModelProperty(value = "单位地址")
    private String companyAddress;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill= FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill= FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "父单位ID")
    private Long pid;

    @ApiModelProperty(value = "子单位个数")
    private Integer subCount;

    private Integer delStatus;
    public void copyFrom(BaseEntrustCompany source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
