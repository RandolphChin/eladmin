package com.laboratory.modules.system.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.laboratory.base.CommonDto;

import java.io.Serializable;

/**
*
* @date 2020-09-24
*/
@Data
@NoArgsConstructor
public class DictDto extends CommonDto implements Serializable {

    private Long id;

    //     private List<DictDetailDto> dictDetails;

    private String name;

    private String description;
}
