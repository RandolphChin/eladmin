package com.laboratory.domain;

import lombok.experimental.Accessors;
import com.laboratory.base.CommonModel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;

import java.io.Serializable;

/**
* @author fanglei
* @date 2021-08-11
*/
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("code_screw_config")
public class CodeScrewConfig extends CommonModel<CodeScrewConfig> implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type= IdType.ASSIGN_ID)
    private Long configId;

    @ApiModelProperty(value = "版本号")
    private String version;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "忽略表名")
    private String ignoreTableName;

    @ApiModelProperty(value = "忽略表前缀")
    private String ignoreTablePrefix;

    @ApiModelProperty(value = "忽略表后缀")
    private String ignoreTableSuffix;

    @ApiModelProperty(value = "指定表名生成")
    private String designatedTableName;

    @ApiModelProperty(value = "指定表前缀生成")
    private String designatedTablePrefix;

    @ApiModelProperty(value = "指定表后缀生成")
    private String designatedTableSuffix;

    public void copyFrom(CodeScrewConfig source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
