package com.laboratory.modules.quartz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.laboratory.modules.quartz.service.mapper.CronTriggersMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import com.laboratory.base.PageInfo;
import com.laboratory.exception.BadRequestException;
import com.laboratory.modules.quartz.domain.Triggers;
import com.laboratory.modules.quartz.service.QuartzJobServices;
import com.laboratory.modules.quartz.service.TriggersService;
import com.laboratory.modules.quartz.service.dto.CronInfoDto;
import com.laboratory.utils.ConvertUtil;
import com.laboratory.utils.PageUtil;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class QuartzJobServicesImpl implements QuartzJobServices {
    @Autowired
    private Scheduler scheduler;
    @Autowired
    private CronTriggersMapper triggersMapper;

    @Override
    public void addJob(String clazzName, String jobName, String groupName, String cronExp, Map<String, Object> param) {
        try{
            // 启动调度器，默认初始化的时候已经启动
//            scheduler.start();
            //构建job信息
            Class<? extends Job> jobClass = (Class<? extends Job>) Class.forName(clazzName);
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, groupName).build();
            //表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExp);
            //按新的cronExpression表达式构建一个新的trigger
            // scheduleBuilder.withMisfireHandlingInstructionDoNothing(); // 错误触发时间后不再触发
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, groupName).withDescription(JSONUtil.toJsonStr(param)).withSchedule(scheduleBuilder.withMisfireHandlingInstructionDoNothing()).build();
            //获得JobDataMap，写入数据到 trigger 的 job_data 中
            // 或 可以写入到 jobDetail 的job_data中
            // jobDetail.getJobDataMap().putAll(param);
            if (param != null) {
                trigger.getJobDataMap().putAll(param);
            }
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e){
            log.error("创建定时任务失败", e.getMessage());
            throw new BadRequestException("创建定时任务失败 "+e.getMessage());
        }
    }

    @Override
    public void pauseJob(String jobName, String groupName) {
        try {
            scheduler.pauseJob(JobKey.jobKey(jobName, groupName));
        } catch (SchedulerException e) {
            log.error("暂停任务失败", e);
        }
    }

    @Override
    public void resumeJob(String jobName, String groupName) {
        try {
            scheduler.resumeJob(JobKey.jobKey(jobName, groupName));
        } catch (SchedulerException e) {
            log.error("恢复任务失败", e);
        }
    }
    @Autowired
    private TriggersService triggersService;
    @Override
    public void runOnce(String jobName, String groupName) {
        Triggers triggers =triggersService.findByJobNameAndGroupName(jobName,groupName);
        Map paramsMap =BeanUtil.beanToMap(JSONUtil.parseObj(triggers.getDescription()));
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.putAll(paramsMap);
        try {
            scheduler.triggerJob(JobKey.jobKey(jobName, groupName),jobDataMap);
        } catch (SchedulerException e) {
            log.error("立即运行一次定时任务失败", e);
        }
    }

    @Override
    public void updateJob(String jobName, String groupName, String cronExp, Map<String, Object> param) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, groupName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (cronExp != null) {
                // 表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExp);
                // 按新的cronExpression表达式重新构建trigger
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withDescription(JSONUtil.toJsonStr(param)).withSchedule(scheduleBuilder.withMisfireHandlingInstructionDoNothing()).build();
            }
            //修改map
            if (param != null) {
                trigger.getJobDataMap().putAll(param);
            }
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (Exception e) {
            log.error("更新定时任务失败", e);
            throw new BadRequestException("更新定时任务失败");
        }
    }

    @Override
    @Transactional
    public void deleteJob(String jobName, String groupName) {
        try {
            //暂停、移除、删除
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobName, groupName));
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobName, groupName));
            scheduler.deleteJob(JobKey.jobKey(jobName, groupName));
        } catch (Exception e) {
            log.error("删除任务失败", e);
        }
    }

    @Override
    public void startAllJobs() {
        try {
            scheduler.start();
        } catch (Exception e) {
            log.error("开启所有的任务失败", e);
        }
    }

    @Override
    public void pauseAllJobs() {
        try {
            scheduler.pauseAll();
        } catch (Exception e) {
            log.error("暂停所有任务失败", e);
        }
    }

    @Override
    public void resumeAllJobs() {
        try {
            scheduler.resumeAll();
        } catch (Exception e) {
            log.error("恢复所有任务失败", e);
        }
    }

    @Override
    public void shutdownAllJobs() {
        try {

            if (!scheduler.isShutdown()) {
                // 需谨慎操作关闭scheduler容器
                // scheduler生命周期结束，无法再 start() 启动scheduler
                scheduler.shutdown(true);
            }
        } catch (Exception e) {
            log.error("关闭所有的任务失败", e);
        }
    }



    @Override
    public PageInfo<CronInfoDto> getAllJob(Pageable pageable, String jobName) {
        IPage<CronInfoDto> page = PageUtil.toMybatisPage(pageable,true);
        IPage<CronInfoDto> pageList =triggersMapper.getAllJob(page);
        PageInfo<CronInfoDto> pageInfo =ConvertUtil.convertPage(pageList,CronInfoDto.class);
       return pageInfo;
    }
}
