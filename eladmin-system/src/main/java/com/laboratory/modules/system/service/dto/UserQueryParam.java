package com.laboratory.modules.system.service.dto;

import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.laboratory.annotation.Query;
import org.springframework.format.annotation.DateTimeFormat;

/**
*
* @date 2020-09-25
*/
@Data
// @DataPermission(fieldName = "dept_id")
public class UserQueryParam{

    /** 精确 */
    @Query
    private Long userId;

    private Long deptId;
    @Query
    private Boolean isAdmin;

    @Query(propName = "dept_id", type = Query.Type.IN)
    private Set<Long> deptIds = new HashSet<>();

    @Query(blurry = "email,username")
    private String blurry;

    /** 精确 */
    @Query
    private Boolean enabled;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Query(type = Query.Type.BETWEEN)
    private List<Date> createTime;
}
