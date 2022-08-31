package com.laboratory.modules.quartz.service;

import com.laboratory.base.CommonService;
import com.laboratory.modules.quartz.domain.Triggers;

public interface TriggersService extends CommonService<Triggers> {
    Triggers findByJobNameAndGroupName(String jobName, String groupName);
}
