package com.laboratory.modules.quartz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.laboratory.modules.quartz.service.mapper.CronTriggersMapper;
import lombok.AllArgsConstructor;
import com.laboratory.base.PageInfo;
import com.laboratory.base.QueryHelpMybatisPlus;
import com.laboratory.base.impl.CommonServiceImpl;
import com.laboratory.utils.ConvertUtil;
import com.laboratory.utils.PageUtil;
import com.laboratory.modules.quartz.domain.CronTriggers;
import com.laboratory.modules.quartz.service.CronTriggersService;
import com.laboratory.modules.quartz.service.dto.CronTriggersDto;
import com.laboratory.modules.quartz.service.dto.CronTriggersQueryParam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import java.util.*;

/**
* @author wwe
* @date 2021-11-04
*/
@Service
@AllArgsConstructor
// @CacheConfig(cacheNames = CronTriggersService.CACHE_KEY)
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CronTriggersServiceImpl extends CommonServiceImpl<CronTriggersMapper, CronTriggers> implements CronTriggersService {

    // private final RedisUtils redisUtils;
    private final CronTriggersMapper cronTriggersMapper;

    @Override
    public PageInfo<CronTriggersDto> queryAll(CronTriggersQueryParam query, Pageable pageable) {
        IPage<CronTriggers> queryPage = PageUtil.toMybatisPage(pageable);
        IPage<CronTriggers> page = cronTriggersMapper.selectPage(queryPage, QueryHelpMybatisPlus.getPredicate(query));
        return ConvertUtil.convertPage(page, CronTriggersDto.class);
    }

    @Override
    public List<CronTriggersDto> queryAll(CronTriggersQueryParam query){
        return ConvertUtil.convertList(cronTriggersMapper.selectList(QueryHelpMybatisPlus.getPredicate(query)), CronTriggersDto.class);
    }

    @Override
    public CronTriggers getById(String id) {
        return cronTriggersMapper.selectById(id);
    }

    @Override
    // @Cacheable(key = "'id:' + #p0")
    public CronTriggersDto findById(String id) {
        return ConvertUtil.convert(getById(id), CronTriggersDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(CronTriggersDto resources) {
        CronTriggers entity = ConvertUtil.convert(resources, CronTriggers.class);
        return cronTriggersMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateById(CronTriggersDto resources){
        CronTriggers entity = ConvertUtil.convert(resources, CronTriggers.class);
        int ret = cronTriggersMapper.updateById(entity);
        // delCaches(resources.id);
        return ret;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeByIds(Set<String> ids){
        // delCaches(ids);
        return cronTriggersMapper.deleteBatchIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeById(String id){
        Set<String> set = new HashSet<>(1);
        set.add(id);
        return this.removeByIds(set);
    }

    /*
    private void delCaches(String id) {
        redisUtils.delByKey(CACHE_KEY + "::id:", id);
    }

    private void delCaches(Set<String> ids) {
        for (String id: ids) {
            delCaches(id);
        }
    }*/

    /*
    @Override
    public void download(List<CronTriggersDto> all, HttpServletResponse response) throws IOException {
      List<Map<String, Object>> list = new ArrayList<>();
      for (CronTriggersDto cronTriggers : all) {
        Map<String,Object> map = new LinkedHashMap<>();
                map.put(" cronExpression",  cronTriggers.getCronExpression());
                map.put(" timeZoneId",  cronTriggers.getTimeZoneId());
        list.add(map);
      }
      FileUtil.downloadExcel(list, response);
    }*/
}
