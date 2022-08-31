package com.laboratory.modules.base.equipment.service.mapper;

import com.laboratory.base.CommonMapper;
import com.laboratory.modules.base.equipment.domain.BaseEquipmentAccount;
import org.springframework.stereotype.Repository;

/**
* @author chen
* @date 2022-08-30
*/
@Repository
public interface BaseEquipmentAccountMapper extends CommonMapper<BaseEquipmentAccount> {

    /**
     * 批量假删除
     * @param ids 需要删除的ID
     * @return 结果
     */
    int deleteEquipmentByIds(Long[] ids);

}
