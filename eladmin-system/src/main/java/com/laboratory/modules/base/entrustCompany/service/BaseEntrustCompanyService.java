package com.laboratory.modules.base.entrustCompany.service;

import com.laboratory.modules.base.entrustCompany.service.dto.BaseEntrustCompanyDto;
import com.laboratory.modules.base.entrustCompany.service.dto.BaseEntrustCompanyQueryParam;
import com.laboratory.base.PageInfo;
import com.laboratory.base.CommonService;
import com.laboratory.modules.base.entrustCompany.domain.BaseEntrustCompany;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Set;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author Randolph
* @date 2022-08-31
*/
public interface BaseEntrustCompanyService extends CommonService<BaseEntrustCompany>  {

    static final String CACHE_KEY = "baseEntrustCompany";

    /**
    * 查询数据分页
    * @param query 条件
    * @param pageable 分页参数
    * @return PageInfo<BaseEntrustCompanyDto>
    */
    PageInfo<BaseEntrustCompanyDto> queryAll(BaseEntrustCompanyQueryParam query, Pageable pageable, boolean isQuery);

    /**
    * 查询所有数据不分页
    * @param query 条件参数
    * @return List<BaseEntrustCompanyDto>
    */
    List<BaseEntrustCompanyDto> queryAll(BaseEntrustCompanyQueryParam query);

    BaseEntrustCompany getById(Long id);
    BaseEntrustCompanyDto findById(Long id);

    /**
     * 插入一条新数据。
     */
    int insert(BaseEntrustCompanyDto resources);
    int updateById(BaseEntrustCompanyDto resources);
    Boolean removeById(Long id);
    Boolean removeByIds(Set<Long> ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<BaseEntrustCompanyDto> all, HttpServletResponse response) throws IOException;
    // 查询全部数据
    List<BaseEntrustCompanyDto> queryList(BaseEntrustCompanyQueryParam query, boolean isQuery);
}
