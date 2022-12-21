package com.laboratory.modules.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.laboratory.exception.BadRequestException;
import com.laboratory.exception.EntityExistException;
import com.laboratory.modules.system.service.mapper.DictDetailMapper;
import com.laboratory.modules.system.service.mapper.DictMapper;
import lombok.AllArgsConstructor;
import com.laboratory.base.PageInfo;
import com.laboratory.base.QueryHelpMybatisPlus;
import com.laboratory.base.impl.CommonServiceImpl;
import com.laboratory.modules.system.domain.Dict;
import com.laboratory.utils.CacheKey;
import com.laboratory.utils.ConvertUtil;
import com.laboratory.modules.system.domain.DictDetail;
import com.laboratory.modules.system.service.DictDetailService;
import com.laboratory.modules.system.service.dto.DictDetailDto;
import com.laboratory.modules.system.service.dto.DictDetailQueryParam;
import com.laboratory.utils.PageUtil;
import com.laboratory.utils.RedisUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
*
* @date 2020-09-24
*/
@Service
@AllArgsConstructor
@CacheConfig(cacheNames = "dictDetail")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DictDetailServiceImpl extends CommonServiceImpl<DictDetailMapper, DictDetail> implements DictDetailService {

    private final DictDetailMapper dictDetailMapper;
    private final DictMapper dictMapper;
    private final RedisUtils redisUtils;

    @Override
    //@Cacheable
    public PageInfo<DictDetailDto> queryAll(DictDetailQueryParam query, Pageable pageable) {
        IPage<DictDetail> page = PageUtil.toMybatisPage(pageable);
        IPage<DictDetail> pageList = dictDetailMapper.selectPage(page, QueryHelpMybatisPlus.getPredicate(query));
        return ConvertUtil.convertPage(pageList, DictDetailDto.class);
    }

    @Override
    //@Cacheable
    public List<DictDetailDto> queryAll(DictDetailQueryParam query){
        return ConvertUtil.convertList(dictDetailMapper.selectList(QueryHelpMybatisPlus.getPredicate(query)), DictDetailDto.class);
    }

    @Override
    public List<DictDetailDto> getDictByName(String dictName) {
        Dict dict = dictMapper.lambdaQuery().eq(Dict::getName, dictName).one();
        List<DictDetailDto> ret = dictDetailMapper.getDictDetailsByDictName(dictName);
        redisUtils.set(CacheKey.DICTDEAIL_DICTID + dict.getId(), ret);
        return ret;
    }

    @Override
    public PageInfo<DictDetailDto> getDictByName(String dictName, Pageable pageable) {
        IPage<DictDetailDto> page = PageUtil.toMybatisPage(pageable, true);
        return ConvertUtil.convertPage(dictDetailMapper.getDictDetailsByDictName(dictName, page), DictDetailDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(DictDetailDto resources) {
        DictDetail existDd = getById(resources.getId());
        DictDetail detail = ConvertUtil.convert(resources, DictDetail.class);
        if(ObjectUtil.isNotNull(lambdaQuery().eq(DictDetail::getDictId, existDd.getDictId()).eq(DictDetail::getValue,detail.getValue()).one())){
            throw new BadRequestException("字典值 " +detail.getValue() +" 已经存在");
        }
        boolean ret = dictDetailMapper.updateById(detail) > 0;
        // 清理缓存
        delCaches(detail.getDictId());
        return ret;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(DictDetailDto resources){
        DictDetail detail = ConvertUtil.convert(resources, DictDetail.class);
        detail.setDictId(resources.getDict().getId());
        if(ObjectUtil.isNotNull(lambdaQuery().eq(DictDetail::getDictId, detail.getDictId()).eq(DictDetail::getValue,detail.getValue()).one())){
            throw new EntityExistException(DictDetail.class,"字典值",detail.getValue());
        }
        boolean ret = dictDetailMapper.insert(detail) > 0;
        // 清理缓存
        delCaches(detail.getDictId());
        return ret;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(Long id) {
        DictDetail dictDetail = dictDetailMapper.selectById(id);
        boolean ret = dictDetailMapper.deleteById(id) > 0;
        // 清理缓存
        delCaches(dictDetail.getDictId());
        return ret;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByDictId(Long id) {
        boolean ret = lambdaUpdate().eq(DictDetail::getDictId, id).remove();
        delCaches(id);
        return ret;
    }

    private void delCaches(Long dictId){
        redisUtils.del(CacheKey.DICTDEAIL_DICTID + dictId);
    }
}
