package com.laboratory.modules.quartz.domain;

import com.laboratory.base.CommonModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import java.io.Serializable;

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
@TableName("qrtz_cron_triggers")
public class CronTriggers extends CommonModel<CronTriggers> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String schedName;

    private String triggerName;

    private String triggerGroup;

    private String cronExpression;

    private String timeZoneId;

    public void copyFrom(CronTriggers source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
