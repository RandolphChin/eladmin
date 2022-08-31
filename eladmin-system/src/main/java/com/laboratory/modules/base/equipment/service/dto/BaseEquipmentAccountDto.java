package com.laboratory.modules.base.equipment.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

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
public class BaseEquipmentAccountDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    /** 防止精度丢失 */
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "设备编号")
    private String equipNo;

    @ApiModelProperty(value = "设备类型")
    private String equipType;

    @ApiModelProperty(value = "出厂编号")
    private String outFactoryNo;

    @ApiModelProperty(value = "设备名称")
    private String equipName;

    @ApiModelProperty(value = "设备型号")
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
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改人")
    private String updateBy;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "删除状态")
    private Integer delStatus;
}
