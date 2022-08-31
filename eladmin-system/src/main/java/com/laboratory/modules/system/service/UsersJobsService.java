package com.laboratory.modules.system.service;

import com.laboratory.base.CommonService;
import com.laboratory.modules.system.domain.UsersJobs;

import java.util.List;

/**
*
* @date 2020-09-25
*/
public interface UsersJobsService extends CommonService<UsersJobs> {
    List<Long> queryUserIdByJobId(Long id);
    List<Long> queryJobIdByUserId(Long id);
    boolean removeByUserId(Long id);
    boolean removeByJobId(Long id);
}
