package com.laboratory.modules.base.entrustCompany.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.laboratory.exception.EntityExistException;
import com.laboratory.modules.base.entrustCompany.service.mapper.BaseEntrustCompanyMapper;
import com.laboratory.utils.StringUtils;
import com.laboratory.utils.enums.DelStatusEnum;
import lombok.AllArgsConstructor;
import com.laboratory.base.PageInfo;
import com.laboratory.base.QueryHelpMybatisPlus;
import com.laboratory.base.impl.CommonServiceImpl;
import com.laboratory.utils.ConvertUtil;
import com.laboratory.utils.FileUtil;
import com.laboratory.utils.PageUtil;
import com.laboratory.modules.base.entrustCompany.domain.BaseEntrustCompany;
import com.laboratory.modules.base.entrustCompany.service.BaseEntrustCompanyService;
import com.laboratory.modules.base.entrustCompany.service.dto.BaseEntrustCompanyDto;
import com.laboratory.modules.base.entrustCompany.service.dto.BaseEntrustCompanyQueryParam;
import org.apache.commons.collections4.CollectionUtils;
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
* @author Randolph
* @date 2022-08-31
*/
@Service
@AllArgsConstructor
// @CacheConfig(cacheNames = BaseEntrustCompanyService.CACHE_KEY)
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BaseEntrustCompanyServiceImpl extends CommonServiceImpl<BaseEntrustCompanyMapper, BaseEntrustCompany> implements BaseEntrustCompanyService {

    // private final RedisUtils redisUtils;
    private final BaseEntrustCompanyMapper baseEntrustCompanyMapper;

    @Override
    public PageInfo<BaseEntrustCompanyDto> queryAll(BaseEntrustCompanyQueryParam query, Pageable pageable, boolean isQuery) {
        QueryWrapper wrapper = QueryHelpMybatisPlus.getPredicate(query);
        if (ObjectUtil.isNull(query.getPid())) {
            wrapper.isNull("pid");
        }
        IPage<BaseEntrustCompany> queryPage = PageUtil.toMybatisPage(pageable);
        IPage<BaseEntrustCompany> page = baseEntrustCompanyMapper.selectPage(queryPage, wrapper);
        return ConvertUtil.convertPage(page, BaseEntrustCompanyDto.class);
    }

    @Override
    public List<BaseEntrustCompanyDto> queryAll(BaseEntrustCompanyQueryParam query){
        return ConvertUtil.convertList(baseEntrustCompanyMapper.selectList(QueryHelpMybatisPlus.getPredicate(query)), BaseEntrustCompanyDto.class);
    }

    @Override
    public BaseEntrustCompany getById(Long id) {
        return baseEntrustCompanyMapper.selectById(id);
    }

    @Override
    // @Cacheable(key = "'id:' + #p0")
    public BaseEntrustCompanyDto findById(Long id) {
        return ConvertUtil.convert(getById(id), BaseEntrustCompanyDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(BaseEntrustCompanyDto resources) {
        if(ObjectUtil.isNotNull(lambdaQuery().eq(BaseEntrustCompany::getCompanyName, resources.getCompanyName()).one())){
            throw new EntityExistException(BaseEntrustCompany.class, "companyName", resources.getCompanyName());
        }
        if (ObjectUtil.isNull(resources.getPid())) {
            resources.setPid(null);
        }
        resources.setSubCount(resources.getSubCount() == null ? 0 : resources.getSubCount());
        BaseEntrustCompany entity = ConvertUtil.convert(resources, BaseEntrustCompany.class);
        int ret = baseEntrustCompanyMapper.insert(entity);
        // 计算子节点数目
        if (ObjectUtil.isNotNull(resources.getPid())) {
            updateSubCount(resources.getPid());
        }
        return ret;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateById(BaseEntrustCompanyDto resources){
        BaseEntrustCompany entity = ConvertUtil.convert(resources, BaseEntrustCompany.class);
        int ret = baseEntrustCompanyMapper.updateById(entity);
        // delCaches(resources.id);
        return ret;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeByIds(Set<Long> ids){
        // delCaches(ids);
        List<BaseEntrustCompany> list = lambdaQuery().in(BaseEntrustCompany::getId,ids).list();
        list.stream().forEach(k->{
            k.setDelStatus(DelStatusEnum.DEL_TRUE.ordinal());
        });
        //return baseEntrustCompanyMapper.deleteBatchIds(ids);
        return this.saveOrUpdateBatch(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeById(Long id){
        Set<Long> set = new HashSet<>(1);
        set.add(id);
        return this.removeByIds(set);
    }

    /*
    private void delCaches(Long id) {
        redisUtils.delByKey(CACHE_KEY + "::id:", id);
    }

    private void delCaches(Set<Long> ids) {
        for (Long id: ids) {
            delCaches(id);
        }
    }*/


    @Override
    public void download(List<BaseEntrustCompanyDto> all, HttpServletResponse response) throws IOException {
      List<Map<String, Object>> list = new ArrayList<>();
      for (BaseEntrustCompanyDto baseEntrustCompany : all) {
        Map<String,Object> map = new LinkedHashMap<>();
              map.put("单位名称", baseEntrustCompany.getCompanyName());
              map.put("单位地址", baseEntrustCompany.getCompanyAddress());
              map.put("创建时间", baseEntrustCompany.getCreateTime());
              map.put("修改时间", baseEntrustCompany.getUpdateTime());
        list.add(map);
      }
      FileUtil.downloadExcel(list, response);
    }

    @Override
    public List<BaseEntrustCompanyDto> queryList(BaseEntrustCompanyQueryParam query, boolean isQuery) {
        QueryWrapper wrapper = QueryHelpMybatisPlus.getPredicate(query);
        if (ObjectUtil.isNull(query.getPid())) {
            wrapper.isNull("pid");
        }
        wrapper.orderByDesc("create_time");
        return ConvertUtil.convertList(baseEntrustCompanyMapper.selectList(wrapper),BaseEntrustCompanyDto.class);
    }
    private void updateSubCount(Long pid){
        if(ObjectUtil.isNull(pid)){
            return;
        }
        BaseEntrustCompany parent = getById(pid);
        int count = lambdaQuery().eq(BaseEntrustCompany::getPid,pid).count();
        BaseEntrustCompany entrustCompany = new BaseEntrustCompany();
        entrustCompany.setSubCount(count);
        if(ObjectUtil.isNotNull(parent)){
            entrustCompany.setPid(parent.getPid());
        }
        lambdaUpdate().eq(BaseEntrustCompany::getId,pid).update(entrustCompany);
    }
}
