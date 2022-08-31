package com.laboratory.modules.base.equipment.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.laboratory.modules.base.equipment.service.mapper.BaseEquipmentAccountMapper;
import lombok.AllArgsConstructor;
import com.laboratory.base.PageInfo;
import com.laboratory.base.QueryHelpMybatisPlus;
import com.laboratory.base.impl.CommonServiceImpl;
import com.laboratory.utils.ConvertUtil;
import com.laboratory.utils.FileUtil;
import com.laboratory.utils.PageUtil;
import com.laboratory.modules.base.equipment.domain.BaseEquipmentAccount;
import com.laboratory.modules.base.equipment.service.BaseEquipmentAccountService;
import com.laboratory.modules.base.equipment.service.dto.BaseEquipmentAccountDto;
import com.laboratory.modules.base.equipment.service.dto.BaseEquipmentAccountQueryParam;
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
// @CacheConfig(cacheNames = BaseEquipmentAccountService.CACHE_KEY)
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BaseEquipmentAccountServiceImpl extends CommonServiceImpl<BaseEquipmentAccountMapper, BaseEquipmentAccount> implements BaseEquipmentAccountService {

    // private final RedisUtils redisUtils;
    private final BaseEquipmentAccountMapper baseEquipmentAccountMapper;

    @Override
    public PageInfo<BaseEquipmentAccountDto> queryAll(BaseEquipmentAccountQueryParam query, Pageable pageable) {
        IPage<BaseEquipmentAccount> queryPage = PageUtil.toMybatisPage(pageable);
        IPage<BaseEquipmentAccount> page = baseEquipmentAccountMapper.selectPage(queryPage, QueryHelpMybatisPlus.getPredicate(query));
        return ConvertUtil.convertPage(page, BaseEquipmentAccountDto.class);
    }

    @Override
    public List<BaseEquipmentAccountDto> queryAll(BaseEquipmentAccountQueryParam query){
        return ConvertUtil.convertList(baseEquipmentAccountMapper.selectList(QueryHelpMybatisPlus.getPredicate(query)), BaseEquipmentAccountDto.class);
    }

    @Override
    public BaseEquipmentAccount getById(Long id) {
        return baseEquipmentAccountMapper.selectById(id);
    }

    @Override
    // @Cacheable(key = "'id:' + #p0")
    public BaseEquipmentAccountDto findById(Long id) {
        return ConvertUtil.convert(getById(id), BaseEquipmentAccountDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(BaseEquipmentAccountDto resources) {
        BaseEquipmentAccount entity = ConvertUtil.convert(resources, BaseEquipmentAccount.class);
        return baseEquipmentAccountMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateById(BaseEquipmentAccountDto resources){
        BaseEquipmentAccount entity = ConvertUtil.convert(resources, BaseEquipmentAccount.class);
        int ret = baseEquipmentAccountMapper.updateById(entity);
        // delCaches(resources.id);
        return ret;
    }

    @Override
    public int updateByIds(Long[] ids) {
        return baseEquipmentAccountMapper.deleteEquipmentByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeByIds(Set<Long> ids){
        // delCaches(ids);
        return baseEquipmentAccountMapper.deleteBatchIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeById(Long id){
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
    public void download(List<BaseEquipmentAccountDto> all, HttpServletResponse response) throws IOException {
      List<Map<String, Object>> list = new ArrayList<>();
      for (BaseEquipmentAccountDto baseEquipmentAccount : all) {
        Map<String,Object> map = new LinkedHashMap<>();
              map.put("设备编号", baseEquipmentAccount.getEquipNo());
              map.put("设备类型", baseEquipmentAccount.getEquipType());
              map.put("出厂编号", baseEquipmentAccount.getOutFactoryNo());
              map.put("设备名称", baseEquipmentAccount.getEquipName());
              map.put("设备型号", baseEquipmentAccount.getEquipModel());
              map.put("准确度等级", baseEquipmentAccount.getRightLevel());
              map.put("规格/测量范围", baseEquipmentAccount.getMeterageRange());
              map.put("原值（元）", baseEquipmentAccount.getOriginalValue());
              map.put("制造厂", baseEquipmentAccount.getManufacturer());
              map.put("国别", baseEquipmentAccount.getContry());
              map.put("入厂日期", baseEquipmentAccount.getInFactoryDate());
              map.put("供应商", baseEquipmentAccount.getSupplier());
              map.put("工作状态", baseEquipmentAccount.getWorkStatus());
              map.put("计量分类", baseEquipmentAccount.getMeterageSort());
              map.put("计量类型", baseEquipmentAccount.getMeterageType());
              map.put("校准月份", baseEquipmentAccount.getCalibreMonth());
              map.put("校准年份", baseEquipmentAccount.getCalibreYear());
              map.put("有效月份", baseEquipmentAccount.getValidityTimeMonth());
              map.put("有效期至", baseEquipmentAccount.getValidityTime());
              map.put("确认间隔", baseEquipmentAccount.getSureInterval());
              map.put("计量形式", baseEquipmentAccount.getMeterageForm());
              map.put("证书单位", baseEquipmentAccount.getCertificCompany());
              map.put("证书编号", baseEquipmentAccount.getCertificNo());
              map.put("贴证日期", baseEquipmentAccount.getOnCertificDate());
              map.put("校准费（元）", baseEquipmentAccount.getCalibreFee());
              map.put("校准地点", baseEquipmentAccount.getCalibreAddress());
              map.put("校准日期", baseEquipmentAccount.getCalibreDate());
              map.put("溯源方式", baseEquipmentAccount.getSourceWay());
              map.put("溯源单位", baseEquipmentAccount.getSourceCompany());
              map.put("委托单位", baseEquipmentAccount.getEntrustCompany());
              map.put("委托子单位", baseEquipmentAccount.getEntrustSonCompany());
              map.put("使用人", baseEquipmentAccount.getByUser());
              map.put("存放地点", baseEquipmentAccount.getStorageLocation());
              map.put("主要附件", baseEquipmentAccount.getEquipAttach());
              map.put("计量要求", baseEquipmentAccount.getMeterageAsk());
              map.put("确认结果", baseEquipmentAccount.getSureResult());
              map.put("校准员", baseEquipmentAccount.getByCalibrator());
              map.put("核验员", baseEquipmentAccount.getByVerifier());
              map.put("签发人", baseEquipmentAccount.getBySigner());
              map.put("校准状态", baseEquipmentAccount.getCalibreStatus());
              map.put("证书状态", baseEquipmentAccount.getCertificStatus());
              map.put("客户类别", baseEquipmentAccount.getCustomerType());
              map.put("备注", baseEquipmentAccount.getRemark());
              map.put("创建人", baseEquipmentAccount.getCreateBy());
              map.put("创建时间", baseEquipmentAccount.getCreateTime());
              map.put("修改人", baseEquipmentAccount.getUpdateBy());
              map.put("修改时间", baseEquipmentAccount.getUpdateTime());
              map.put("删除状态", baseEquipmentAccount.getDelStatus());
        list.add(map);
      }
      FileUtil.downloadExcel(list, response);
    }
}
