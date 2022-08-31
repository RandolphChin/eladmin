package com.laboratory.modules.base.certificateManage.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import com.laboratory.annotation.Query;

import java.util.Date;
import java.util.List;

/**
* @author randolph
* @date 2022-08-24
*/
@Getter
@Setter
public class BasePersonCertificQueryParam{

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String certificName;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String certificNo;

    /** 精确 */
    @Query
    private String certificLevel;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String certificProject;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String certificDept;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String createBy;

    /** 精确 */
    @Query(type = Query.Type.INNER_LIKE, propName="u.username")
    private String username;

    /** BETWEEN */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Query(type = Query.Type.BETWEEN)
    private List<Date> validityTime;
}
