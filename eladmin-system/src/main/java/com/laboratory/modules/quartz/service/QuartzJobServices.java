package com.laboratory.modules.quartz.service;

import com.laboratory.modules.quartz.service.dto.CronInfoDto;
import com.laboratory.base.PageInfo;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface QuartzJobServices {
    /**
     * 添加任务可以传参数
     * @param clazzName
     * @param jobName
     * @param groupName
     * @param cronExp
     * @param param JobDataMap 参数传递
     */
    void addJob(String clazzName, String jobName, String groupName, String cronExp, Map<String, Object> param);

    /**
     * 暂停任务
     * @param jobName
     * @param groupName
     */
    void pauseJob(String jobName, String groupName);

    /**
     * 恢复任务
     * @param jobName
     * @param groupName
     */
    void resumeJob(String jobName, String groupName);

    /**
     * 立即运行一次定时任务
     * @param jobName
     * @param groupName
     */
    void runOnce(String jobName, String groupName);

    /**
     * 更新任务
     * @param jobName
     * @param groupName
     * @param cronExp
     * @param param
     */
    void updateJob(String jobName, String groupName, String cronExp, Map<String, Object> param);

    /**
     * 删除任务
     * @param jobName
     * @param groupName
     */
    void deleteJob(String jobName, String groupName);

    /**
     * 启动所有任务
     */
    void startAllJobs();

    /**
     * 暂停所有任务
     */
    void pauseAllJobs();

    /**
     * 恢复所有任务
     */
    void resumeAllJobs();

    /**
     * 关闭所有任务
     */
    void shutdownAllJobs();

    PageInfo<CronInfoDto> getAllJob(Pageable pageable, String jobName);
}
