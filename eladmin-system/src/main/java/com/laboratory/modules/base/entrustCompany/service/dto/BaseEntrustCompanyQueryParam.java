package com.laboratory.modules.base.entrustCompany.service.dto;

import com.laboratory.utils.enums.DelStatusEnum;
import lombok.Getter;
import lombok.Setter;
import com.laboratory.annotation.Query;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
* @author Randolph
* @date 2022-08-31
*/
@Getter
@Setter
public class BaseEntrustCompanyQueryParam{

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String companyName;

    /** 模糊 */
    @Query
    private Long pid;

    @Query(type = Query.Type.IS_NULL, propName = "pid")
    private Boolean pidIsNull;

    /** BETWEEN */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Query(type = Query.Type.BETWEEN)
    private List<Date> createTime;

    // 逻辑删除
    @Query
    private Integer delStatus= DelStatusEnum.DEL_FALSE.ordinal();
}
