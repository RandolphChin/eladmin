package com.laboratory.modules.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import com.laboratory.base.CommonEntity;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
*
* @date 2020-09-24
*/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("sys_dict")
public class Dict extends CommonEntity<Dict> implements Serializable {

    @ApiModelProperty(value = "ID")
    @TableId(value = "dict_id", type= IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "字典名称")
    @NotBlank
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    public void copyFrom(Dict source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
