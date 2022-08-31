package com.laboratory.modules.base.certificateManage.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laboratory.modules.base.certificateManage.service.mapper.BasePersonCertificMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import com.laboratory.base.PageInfo;
import com.laboratory.base.QueryHelpMybatisPlus;
import com.laboratory.base.impl.CommonServiceImpl;
import com.laboratory.modules.base.certificateManage.domain.BasePersonCertific;
import com.laboratory.modules.base.certificateManage.service.BasePersonCertificService;
import com.laboratory.modules.base.certificateManage.service.dto.BasePersonCertificDto;
import com.laboratory.modules.base.certificateManage.service.dto.BasePersonCertificQueryParam;
import com.laboratory.storage.cloud.config.MinioConfig;
import com.laboratory.utils.ConvertUtil;
import com.laboratory.utils.FileUtil;
import com.laboratory.utils.PageUtil;

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
* @author randolph
* @date 2022-08-24
*/
@Service
@AllArgsConstructor
// @CacheConfig(cacheNames = BasePersonCertificService.CACHE_KEY)
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BasePersonCertificServiceImpl extends CommonServiceImpl<BasePersonCertificMapper, BasePersonCertific> implements BasePersonCertificService {

    // private final RedisUtils redisUtils;
    private final BasePersonCertificMapper basePersonCertificMapper;
    @Autowired
    private MinioConfig minioConfig;

    @Override
    public PageInfo<BasePersonCertificDto> queryAll(BasePersonCertificQueryParam query, Pageable pageable) {
        Page<BasePersonCertificDto> page = PageUtil.toMybatisPageSort(pageable);
        IPage<BasePersonCertificDto> pageDtos = basePersonCertificMapper.selectPageJoin(page,QueryHelpMybatisPlus.getPredicate(query));
        return ConvertUtil.convertPage(pageDtos, BasePersonCertificDto.class);
    }

    @Override
    public List<BasePersonCertificDto> queryAll(BasePersonCertificQueryParam query){
        return basePersonCertificMapper.listAll(QueryHelpMybatisPlus.getPredicate(query));
    }

    @Override
    public BasePersonCertific getById(Long id) {
        return basePersonCertificMapper.selectById(id);
    }

    @Override
    // @Cacheable(key = "'id:' + #p0")
    public BasePersonCertificDto findById(Long id) {
        return ConvertUtil.convert(getById(id), BasePersonCertificDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(BasePersonCertificDto resources) {
        BasePersonCertific entity = ConvertUtil.convert(resources, BasePersonCertific.class);
        return basePersonCertificMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateById(BasePersonCertificDto resources){
        BasePersonCertific entity = ConvertUtil.convert(resources, BasePersonCertific.class);
        int ret = basePersonCertificMapper.updateById(entity);
        // delCaches(resources.id);
        return ret;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeByIds(Set<Long> ids){
        // delCaches(ids);
        return basePersonCertificMapper.deleteBatchIds(ids);
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
    public void download(List<BasePersonCertificDto> all, HttpServletResponse response) throws IOException {
      List<Map<String, Object>> list = new ArrayList<>();
      for (BasePersonCertificDto basePersonCertific : all) {
        Map<String,Object> map = new LinkedHashMap<>();
              map.put("姓名", basePersonCertific.getUsername());
              map.put("部门", basePersonCertific.getDeptName());
              map.put("证书名称", basePersonCertific.getCertificName());
              map.put("证书编号", basePersonCertific.getCertificNo());
              map.put("证书级别", basePersonCertific.getCertificLevel());
              map.put("取证项目", basePersonCertific.getCertificProject());
              map.put("取证时间", DateUtil.format(basePersonCertific.getCertificTime(), com.laboratory.utils.DateUtil.DFY_MD));
              map.put("发证单位", basePersonCertific.getCertificDept());
              map.put("有效期至", DateUtil.format(basePersonCertific.getValidityTime(), com.laboratory.utils.DateUtil.DFY_MD));
              map.put("备注", basePersonCertific.getRemark());
              map.put("创建人", basePersonCertific.getCreateBy());
              map.put("创建时间", DateUtil.format(basePersonCertific.getCreateTime(), com.laboratory.utils.DateUtil.DFY_MD_HMS));;
              map.put("修改人", basePersonCertific.getUpdateBy());
              map.put("修改时间", DateUtil.format(basePersonCertific.getUpdateTime(), com.laboratory.utils.DateUtil.DFY_MD_HMS));;
              // 如果导出excel包含图片，需要把图片字段放置在最后一列
              map.put("电子附件", basePersonCertific.getAttachPath());
        list.add(map);
      }
      // 没有图片要导出可以直接调用  downloadExcel方法
      // FileUtil.downloadExcel(list, response);
      FileUtil.downloadExcelWithPicture(list,"电子附件",minioConfig.getEndpoint(),  response);
    }
}
