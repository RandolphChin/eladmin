package com.laboratory.modules.system.service.dto;

import lombok.Data;
import com.laboratory.annotation.Query;

/**
*
* @date 2020-09-24
*/
@Data
public class DictDetailQueryParam{

    private String dictName;

    /** 精确 */
    @Query
    private Long detailId;

    /** 精确 */
    @Query
    private Long dictId;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String label;
}
