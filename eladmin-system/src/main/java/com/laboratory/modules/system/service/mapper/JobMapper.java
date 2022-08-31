package com.laboratory.modules.system.service.mapper;

import com.laboratory.base.CommonMapper;
import com.laboratory.modules.system.domain.Job;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
*
* @date 2020-09-25
*/
@Repository
public interface JobMapper extends CommonMapper<Job> {
    @Select("select j.job_id as id, j.* from sys_job where job_id = #{id}")
    Job selectLink(Long id);
}
