package com.laboratory.modules.base.equipment.service;

import com.laboratory.modules.base.equipment.service.dto.BaseEquipmentAccountDto;
import com.laboratory.modules.base.equipment.service.dto.BaseEquipmentAccountQueryParam;
import com.laboratory.base.PageInfo;
import com.laboratory.base.CommonService;
import com.laboratory.modules.base.equipment.domain.BaseEquipmentAccount;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Set;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author chen
* @date 2022-08-30
*/
public interface BaseEquipmentAccountService extends CommonService<BaseEquipmentAccount>  {

    static final String CACHE_KEY = "baseEquipmentAccount";

    /**
    * 查询数据分页
    * @param query 条件
    * @param pageable 分页参数
    * @return PageInfo<BaseEquipmentAccountDto>
    */
    PageInfo<BaseEquipmentAccountDto> queryAll(BaseEquipmentAccountQueryParam query, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param query 条件参数
    * @return List<BaseEquipmentAccountDto>
    */
    List<BaseEquipmentAccountDto> queryAll(BaseEquipmentAccountQueryParam query);

    BaseEquipmentAccount getById(Long id);
    BaseEquipmentAccountDto findById(Long id);

    /**
     * 插入一条新数据。
     */
    int insert(BaseEquipmentAccountDto resources);
    int updateById(BaseEquipmentAccountDto resources);

    int updateByIds(Long[] ids);//用于假删除
    int removeById(Long id);
    int removeByIds(Set<Long> ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<BaseEquipmentAccountDto> all, HttpServletResponse response) throws IOException;
}
