package com.laboratory.modules.base.country.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;
import io.swagger.annotations.ApiModelProperty;

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
public class CountryDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String iso2;

    private String iso3;

    private String country;

    @ApiModelProperty(value = "中国惯用名")
    private String countryCn;
}
