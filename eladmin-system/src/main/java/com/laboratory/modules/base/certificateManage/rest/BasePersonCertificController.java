package com.laboratory.modules.base.certificateManage.rest;

import com.laboratory.annotation.Log;
import com.laboratory.modules.base.certificateManage.service.BasePersonCertificService;
import com.laboratory.modules.base.certificateManage.service.dto.BasePersonCertificDto;
import com.laboratory.modules.base.certificateManage.service.dto.BasePersonCertificQueryParam;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.Set;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* @author randolph
* @date 2022-08-24
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "人员资质管理")
@RequestMapping("/api/basePersonCertific")
public class BasePersonCertificController {

    private final BasePersonCertificService basePersonCertificService;

    // @Log("查询人员资质")
    @GetMapping
    @ApiOperation("查询人员资质")
    @PreAuthorize("@el.check('basePersonCertific:list')")
    public ResponseEntity query(BasePersonCertificQueryParam query, Pageable pageable){
        return new ResponseEntity<>(basePersonCertificService.queryAll(query,pageable),HttpStatus.OK);
    }

    @ApiOperation("获取单个人员资质")
    @GetMapping(value = "/{id}")
    @PreAuthorize("@el.check('basePersonCertific:list')")
    public ResponseEntity<Object> query(@PathVariable Long id){
        return new ResponseEntity<>(basePersonCertificService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @Log("新增人员资质")
    @ApiOperation("新增人员资质")
    @PreAuthorize("@el.check('basePersonCertific:add')")
    public ResponseEntity create(@Validated @RequestBody BasePersonCertificDto resources){
        return new ResponseEntity<>(basePersonCertificService.insert(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改人员资质")
    @ApiOperation("修改人员资质")
    @PreAuthorize("@el.check('basePersonCertific:edit')")
    public ResponseEntity update(@Validated @RequestBody BasePersonCertificDto resources){
        basePersonCertificService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除人员资质")
    @ApiOperation("删除人员资质")
    @PreAuthorize("@el.check('basePersonCertific:del')")
    public ResponseEntity delete(@RequestBody Set<Long> ids) {
        basePersonCertificService.removeByIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // @Log("导出人员资质")
    @ApiOperation("导出人员资质")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('basePersonCertific:list')")
    public void download(HttpServletResponse response, BasePersonCertificQueryParam query) throws IOException {
        basePersonCertificService.download(basePersonCertificService.queryAll(query), response);
    }

}
