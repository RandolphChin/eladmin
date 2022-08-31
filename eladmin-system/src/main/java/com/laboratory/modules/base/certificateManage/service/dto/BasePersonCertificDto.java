package com.laboratory.modules.base.certificateManage.service.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

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
public class BasePersonCertificDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    /** 防止精度丢失 */
    @JSONField(serializeUsing = ToStringSerializer.class)
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
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改人")
    private String updateBy;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "删除状态")
    private Integer delStatus;

    @ApiModelProperty(value = "姓名")
    private Long userId;

    @ApiModelProperty(value = "部门")
    private Long deptId;

    private String username;

    private String deptName;
    // 附件名称逗号分隔 certificate/第5页-20220825095344191.jpg,certificate/01_1 首页-20220825095448468.png
    private String attachPath;
}
