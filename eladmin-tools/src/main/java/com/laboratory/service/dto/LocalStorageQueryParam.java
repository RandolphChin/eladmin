package com.laboratory.service.dto;

import com.laboratory.annotation.Query;
import lombok.Data;

import java.util.List;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
*
* @date 2020-09-27
*/
@Data
public class LocalStorageQueryParam{

    @Query(blurry = "name,suffix,type,createBy,size")
    private String blurry;

    /** BETWEEN */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Query(type = Query.Type.BETWEEN)
    private List<Date> createTime;

    @Query(type = Query.Type.IN)
    private List<Long> storageId;
}
