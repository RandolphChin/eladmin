package com.laboratory.modules.base.equipment.domain;

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
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
* @author chen
* @date 2022-08-30
*/
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("base_equipment_account")
public class BaseEquipmentAccount extends CommonModel<BaseEquipmentAccount> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(type= IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "设备编号")
    private String equipNo;

    @ApiModelProperty(value = "设备类型")
    @NotBlank
    private String equipType;

    @ApiModelProperty(value = "出厂编号")
    private String outFactoryNo;

    @ApiModelProperty(value = "设备名称")
    @NotBlank
    private String equipName;

    @ApiModelProperty(value = "设备型号")
    @NotBlank
    private String equipModel;

    @ApiModelProperty(value = "准确度等级")
    private String rightLevel;

    @ApiModelProperty(value = "规格/测量范围")
    private String meterageRange;

    @ApiModelProperty(value = "原值（元）")
    private BigDecimal originalValue;

    @ApiModelProperty(value = "制造厂")
    private String manufacturer;

    @ApiModelProperty(value = "国别")
    private String contry;

    @ApiModelProperty(value = "入厂日期")
    private Date inFactoryDate;

    @ApiModelProperty(value = "供应商")
    private String supplier;

    @ApiModelProperty(value = "工作状态")
    private String workStatus;

    @ApiModelProperty(value = "计量分类")
    private String meterageSort;

    @ApiModelProperty(value = "计量类型")
    private String meterageType;

    @ApiModelProperty(value = "校准月份")
    private String calibreMonth;

    @ApiModelProperty(value = "校准年份")
    private String calibreYear;

    @ApiModelProperty(value = "有效月份")
    private String validityTimeMonth;

    @ApiModelProperty(value = "有效期至")
    private Date validityTime;

    @ApiModelProperty(value = "确认间隔")
    private String sureInterval;

    @ApiModelProperty(value = "计量形式")
    private String meterageForm;

    @ApiModelProperty(value = "证书单位")
    private String certificCompany;

    @ApiModelProperty(value = "证书编号")
    private String certificNo;

    @ApiModelProperty(value = "贴证日期")
    private Date onCertificDate;

    @ApiModelProperty(value = "校准费（元）")
    private BigDecimal calibreFee;

    @ApiModelProperty(value = "校准地点")
    private String calibreAddress;

    @ApiModelProperty(value = "校准日期")
    private Date calibreDate;

    @ApiModelProperty(value = "溯源方式")
    private String sourceWay;

    @ApiModelProperty(value = "溯源单位")
    private String sourceCompany;

    @ApiModelProperty(value = "委托单位")
    private String entrustCompany;

    @ApiModelProperty(value = "委托子单位")
    private String entrustSonCompany;

    @ApiModelProperty(value = "使用人")
    private String byUser;

    @ApiModelProperty(value = "存放地点")
    private String storageLocation;

    @ApiModelProperty(value = "主要附件")
    private String equipAttach;

    @ApiModelProperty(value = "计量要求")
    private String meterageAsk;

    @ApiModelProperty(value = "确认结果")
    private String sureResult;

    @ApiModelProperty(value = "校准员")
    private String byCalibrator;

    @ApiModelProperty(value = "核验员")
    private String byVerifier;

    @ApiModelProperty(value = "签发人")
    private String bySigner;

    @ApiModelProperty(value = "校准状态")
    private String calibreStatus;

    @ApiModelProperty(value = "证书状态")
    private String certificStatus;

    @ApiModelProperty(value = "客户类别")
    private String customerType;

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

    public void copyFrom(BaseEquipmentAccount source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
