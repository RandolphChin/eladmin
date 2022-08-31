package com.laboratory.modules.base.equipment.service.dto;

import lombok.Getter;
import lombok.Setter;
import com.laboratory.annotation.Query;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
* @author chen
* @date 2022-08-30
*/
@Getter
@Setter
public class BaseEquipmentAccountQueryParam{

    /** 精确 */
    @Query
    private String equipNo;

    /** 精确 */
    @Query
    private String equipType;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String outFactoryNo;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String equipName;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String equipModel;

    /** 精确 */
    @Query
    private String workStatus;

    /** 精确 */
    @Query
    private String meterageSort;

    /** 精确 */
    @Query
    private String meterageType;

    /** 精确 */
    @Query
    private String calibreMonth;

    /** 精确 */
    @Query
    private String validityTimeMonth;

    /** 精确 */
    @Query
    private String sureInterval;

    /** 精确 */
    @Query
    private String meterageForm;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String certificCompany;

    /** 精确 */
    @Query
    private String sourceWay;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String sourceCompany;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String entrustCompany;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String byCalibrator;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String byVerifier;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String bySigner;

    /** 精确 */
    @Query
    private String calibreStatus;

    /** 精确 */
    @Query
    private String customerType;

    /** 精确 */
    @Query
    private int delStatus;

    /** BETWEEN */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Query(type = Query.Type.BETWEEN)
    private List<Date> createTime;

}
