package com.laboratory.modules.base.certificateManage.domain;

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
* @author randolph
* @date 2022-08-24
*/
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("base_person_certific")
public class BasePersonCertific extends CommonModel<BasePersonCertific> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(type= IdType.ASSIGN_ID) // ASSIGN_ID 默认为雪花ID，不使用数据库的自增ID
    private Long id;

    @ApiModelProperty(value = "证书名称")
    private String certificName;

    @ApiModelProperty(value = "证书编号")
    private String certificNo;

    @ApiModelProperty(value = "证书级别")
    private String certificLevel;

    @ApiModelProperty(value = "取证项目")
    private String certificProject;

    @ApiModelProperty(value = "取证时间")
    private Date certificTime;

    @ApiModelProperty(value = "发证单位")
    private String certificDept;

    @ApiModelProperty(value = "有效期至")
    private Date validityTime;

    @ApiModelProperty(value = "电子附件")
    private String attach;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建人")
    @TableField(fill= FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill= FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改人")
    @TableField(fill= FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill= FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "删除状态")
    private Integer delStatus;

    @ApiModelProperty(value = "姓名")
    private Long userId;

    @ApiModelProperty(value = "部门")
    private Long deptId;

    public void copyFrom(BasePersonCertific source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
