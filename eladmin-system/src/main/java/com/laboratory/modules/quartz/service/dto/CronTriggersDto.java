package com.laboratory.modules.quartz.service.dto;

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
* @author wwe
* @date 2021-11-04
*/
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CronTriggersDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String schedName;

    private String triggerName;

    private String triggerGroup;

    private String cronExpression;

    private String timeZoneId;
}
