/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.laboratory.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.laboratory.base.QueryHelpMybatisPlus;
import com.laboratory.base.impl.CommonServiceImpl;
import com.laboratory.config.FileProperties;
import com.laboratory.exception.BadRequestException;
import com.laboratory.service.dto.LocalStorageDto;
import com.laboratory.service.dto.LocalStorageQueryParam;
import com.laboratory.service.mapper.LocalStorageMapper;
import com.laboratory.utils.ConvertUtil;
import com.laboratory.utils.FileUtil;
import com.laboratory.utils.PageUtil;
import com.laboratory.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import com.laboratory.domain.LocalStorage;
import com.laboratory.utils.*;
import com.laboratory.service.LocalStorageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
*
* @date 2019-09-05
*/
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LocalStorageServiceImpl extends CommonServiceImpl<LocalStorageMapper, LocalStorage> implements LocalStorageService {

    private final FileProperties properties;
    private final LocalStorageMapper localStorageMapper;

    @Override
    public Object queryAll(LocalStorageQueryParam query, Pageable pageable){
        IPage<LocalStorage> page = PageUtil.toMybatisPage(pageable);
        IPage<LocalStorage> pageList = localStorageMapper.selectPage(page, QueryHelpMybatisPlus.getPredicate(query));
        return ConvertUtil.convertPage(pageList, LocalStorageDto.class);
    }

    @Override
    public List<LocalStorageDto> queryAll(LocalStorageQueryParam query){
        return ConvertUtil.convertList(localStorageMapper.selectList(QueryHelpMybatisPlus.getPredicate(query)), LocalStorageDto.class);
    }

    @Override
    public LocalStorageDto findById(Long id){
        return ConvertUtil.convert(getById(id), LocalStorageDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LocalStorage create(String name, MultipartFile multipartFile) {
        FileUtil.checkSize(properties.getMaxSize(), multipartFile.getSize());
        String suffix = FileUtil.getExtensionName(multipartFile.getOriginalFilename());
        String type = FileUtil.getFileType(suffix);
        File file = FileUtil.upload(multipartFile, properties.getPath().getPath() + type +  File.separator);
        if(ObjectUtil.isNull(file)){
            throw new BadRequestException("上传失败");
        }
        try {
            name = StringUtils.isBlank(name) ? FileUtil.getFileNameNoEx(multipartFile.getOriginalFilename()) : name;
            LocalStorage localStorage = new LocalStorage(
                    file.getName(),
                    name,
                    suffix,
                    file.getPath(),
                    type,
                    FileUtil.getSize(multipartFile.getSize())
            );
            localStorageMapper.insert(localStorage);
            return localStorage;
        }catch (Exception e){
            FileUtil.del(file);
            throw e;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(LocalStorage resources) {
        updateById(resources);
        // delCaches(resources.id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            LocalStorage storage = getById(id);
            FileUtil.del(storage.getPath());
            removeById(id);
        }
    }

    @Override
    public void download(List<LocalStorageDto> queryAll, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (LocalStorageDto localStorageDTO : queryAll) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("文件名", localStorageDTO.getRealName());
            map.put("备注名", localStorageDTO.getName());
            map.put("文件类型", localStorageDTO.getType());
            map.put("文件大小", localStorageDTO.getSize());
            map.put("创建者", localStorageDTO.getCreateBy());
            map.put("创建日期", localStorageDTO.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public LocalStorage insert(String name, MultipartFile multipartFile,String bucketName) {
        FileUtil.checkSize(properties.getMaxSize(), multipartFile.getSize());
        String suffix = FileUtil.getExtensionName(multipartFile.getOriginalFilename());
        String type = FileUtil.getFileType(suffix);
        String nameNoEx = FileUtil.getFileNameNoEx(multipartFile.getOriginalFilename());
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmssS");
        String nowStr = "-" + format.format(date);
        String fileName = nameNoEx + nowStr + "." + suffix;
            name = StringUtils.isBlank(name) ? FileUtil.getFileNameNoEx(multipartFile.getOriginalFilename()) : name;
            LocalStorage localStorage = new LocalStorage(
                    fileName,
                    name,
                    suffix,
                    bucketName+"/"+fileName,
                    type,
                    FileUtil.getSize(multipartFile.getSize())
            );
            localStorageMapper.insert(localStorage);
            return localStorage;
    }
}
