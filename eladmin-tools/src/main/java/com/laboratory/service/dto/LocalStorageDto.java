package com.laboratory.service.dto;

import com.laboratory.base.CommonDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
*
* @date 2020-09-27
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LocalStorageDto extends CommonDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String realName;

    private String name;

    private String suffix;

    private String path;

    private String type;

    private String size;
}
