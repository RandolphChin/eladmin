package com.laboratory.modules.base.country.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.laboratory.modules.base.country.service.dto.CountryDto;
import com.laboratory.modules.base.country.service.mapper.CountryMapper;
import lombok.AllArgsConstructor;
import com.laboratory.base.PageInfo;
import com.laboratory.base.QueryHelpMybatisPlus;
import com.laboratory.base.impl.CommonServiceImpl;
import com.laboratory.utils.ConvertUtil;
import com.laboratory.utils.FileUtil;
import com.laboratory.utils.PageUtil;
import com.laboratory.modules.base.country.domain.Country;
import com.laboratory.modules.base.country.service.CountryService;
import com.laboratory.modules.base.country.service.dto.CountryQueryParam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import java.util.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author chen
* @date 2022-08-30
*/
@Service
@AllArgsConstructor
// @CacheConfig(cacheNames = CountryService.CACHE_KEY)
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CountryServiceImpl extends CommonServiceImpl<CountryMapper, Country> implements CountryService {

    // private final RedisUtils redisUtils;
    private final CountryMapper countryMapper;

    @Override
    public PageInfo<CountryDto> queryAll(CountryQueryParam query, Pageable pageable) {
        IPage<Country> queryPage = PageUtil.toMybatisPage(pageable);
        IPage<Country> page = countryMapper.selectPage(queryPage, QueryHelpMybatisPlus.getPredicate(query));
        return ConvertUtil.convertPage(page, CountryDto.class);
    }

    @Override
    public List<CountryDto> queryAll(CountryQueryParam query){
        return ConvertUtil.convertList(countryMapper.selectList(QueryHelpMybatisPlus.getPredicate(query)), CountryDto.class);
    }

    @Override
    public Country getById(Integer id) {
        return countryMapper.selectById(id);
    }

    @Override
    // @Cacheable(key = "'id:' + #p0")
    public CountryDto findById(Integer id) {
        return ConvertUtil.convert(getById(id), CountryDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(CountryDto resources) {
        Country entity = ConvertUtil.convert(resources, Country.class);
        return countryMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateById(CountryDto resources){
        Country entity = ConvertUtil.convert(resources, Country.class);
        int ret = countryMapper.updateById(entity);
        // delCaches(resources.id);
        return ret;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeByIds(Set<Integer> ids){
        // delCaches(ids);
        return countryMapper.deleteBatchIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeById(Integer id){
        Set<Integer> set = new HashSet<>(1);
        set.add(id);
        return this.removeByIds(set);
    }

    /*
    private void delCaches(Integer id) {
        redisUtils.delByKey(CACHE_KEY + "::id:", id);
    }

    private void delCaches(Set<Integer> ids) {
        for (Integer id: ids) {
            delCaches(id);
        }
    }*/


    @Override
    public void download(List<CountryDto> all, HttpServletResponse response) throws IOException {
      List<Map<String, Object>> list = new ArrayList<>();
      for (CountryDto country : all) {
        Map<String,Object> map = new LinkedHashMap<>();
                map.put(" iso2",  country.getIso2());
                map.put(" iso3",  country.getIso3());
                map.put(" country",  country.getCountry());
              map.put("中国惯用名", country.getCountryCn());
        list.add(map);
      }
      FileUtil.downloadExcel(list, response);
    }
}
