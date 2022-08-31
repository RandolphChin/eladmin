package com.laboratory.modules.quartz.service.impl;

import com.laboratory.modules.quartz.service.mapper.TriggersMapper;
import com.laboratory.base.impl.CommonServiceImpl;
import com.laboratory.modules.quartz.domain.Triggers;
import com.laboratory.modules.quartz.service.TriggersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TriggersServiceImpl extends CommonServiceImpl<TriggersMapper, Triggers> implements TriggersService {
    @Autowired
    private TriggersMapper triggersMapper;

    public Triggers findByJobNameAndGroupName(String jobName, String groupName){
        Triggers triggers =triggersMapper.findByJobNameAndGroupName(jobName,groupName);
        return triggers;
    }
}
