package com.laboratory.modules.system.service.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;
import com.laboratory.annotation.Query;
import org.springframework.format.annotation.DateTimeFormat;

/**
*
* @date 2020-09-25
*/
@Data
public class JobQueryParam{

    /** 精确 */
    @Query
    private Long jobId;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    /** 精确 */
    @Query
    private Boolean enabled;

    /** BETWEEN */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Query(type = Query.Type.BETWEEN)
    private List<Date> createTime;
}
