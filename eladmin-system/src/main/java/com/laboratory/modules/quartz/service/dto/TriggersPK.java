package com.laboratory.modules.quartz.service.dto;

import lombok.Data;

@Data
public class TriggersPK {
    private String schedName;

    private String triggerName;

    private String triggerGroup;
}
