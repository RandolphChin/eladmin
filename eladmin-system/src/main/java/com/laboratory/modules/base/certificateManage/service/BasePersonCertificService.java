package com.laboratory.modules.base.certificateManage.service;

import com.laboratory.modules.base.certificateManage.service.dto.BasePersonCertificDto;
import com.laboratory.modules.base.certificateManage.service.dto.BasePersonCertificQueryParam;
import com.laboratory.base.PageInfo;
import com.laboratory.base.CommonService;
import com.laboratory.modules.base.certificateManage.domain.BasePersonCertific;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Set;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author randolph
* @date 2022-08-24
*/
public interface BasePersonCertificService extends CommonService<BasePersonCertific>  {

    static final String CACHE_KEY = "basePersonCertific";

    /**
    * 查询数据分页
    * @param query 条件
    * @param pageable 分页参数
    * @return PageInfo<BasePersonCertificDto>
    */
    PageInfo<BasePersonCertificDto> queryAll(BasePersonCertificQueryParam query, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param query 条件参数
    * @return List<BasePersonCertificDto>
    */
    List<BasePersonCertificDto> queryAll(BasePersonCertificQueryParam query);

    BasePersonCertific getById(Long id);
    BasePersonCertificDto findById(Long id);

    /**
     * 插入一条新数据。
     */
    int insert(BasePersonCertificDto resources);
    int updateById(BasePersonCertificDto resources);
    int removeById(Long id);
    int removeByIds(Set<Long> ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<BasePersonCertificDto> all, HttpServletResponse response) throws IOException;
}
