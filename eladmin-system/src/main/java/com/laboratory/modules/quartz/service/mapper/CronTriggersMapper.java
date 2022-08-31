package com.laboratory.modules.quartz.service.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.laboratory.base.CommonMapper;
import com.laboratory.modules.quartz.domain.CronTriggers;
import com.laboratory.modules.quartz.service.dto.CronInfoDto;
import org.springframework.stereotype.Repository;

/**
* @author wwe
* @date 2021-11-04
*/
@Repository
public interface CronTriggersMapper extends CommonMapper<CronTriggers> {
    IPage<CronInfoDto> getAllJob(IPage<CronInfoDto> page);
}
