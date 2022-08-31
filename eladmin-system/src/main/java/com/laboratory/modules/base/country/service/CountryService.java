package com.laboratory.modules.base.country.service;

import com.laboratory.modules.base.country.service.dto.CountryDto;
import com.laboratory.modules.base.country.service.dto.CountryQueryParam;
import com.laboratory.base.PageInfo;
import com.laboratory.base.CommonService;
import com.laboratory.modules.base.country.domain.Country;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Set;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author chen
* @date 2022-08-30
*/
public interface CountryService extends CommonService<Country>  {

    static final String CACHE_KEY = "country";

    /**
    * 查询数据分页
    * @param query 条件
    * @param pageable 分页参数
    * @return PageInfo<CountryDto>
    */
    PageInfo<CountryDto> queryAll(CountryQueryParam query, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param query 条件参数
    * @return List<CountryDto>
    */
    List<CountryDto> queryAll(CountryQueryParam query);

    Country getById(Integer id);
    CountryDto findById(Integer id);

    /**
     * 插入一条新数据。
     */
    int insert(CountryDto resources);
    int updateById(CountryDto resources);
    int removeById(Integer id);
    int removeByIds(Set<Integer> ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<CountryDto> all, HttpServletResponse response) throws IOException;
}
