package com.laboratory.modules.system.service.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.laboratory.base.CommonMapper;
import com.laboratory.modules.system.domain.DictDetail;
import com.laboratory.modules.system.service.dto.DictDetailDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*
* @date 2020-09-24
*/
@Repository
public interface DictDetailMapper extends CommonMapper<DictDetail> {

    List<DictDetailDto> getDictDetailsByDictName(@Param("dictName") String dictName);
    IPage<DictDetailDto> getDictDetailsByDictName(@Param("dictName") String dictName, IPage<DictDetailDto> page);
}
