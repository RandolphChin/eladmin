package com.laboratory.modules.base.entrustCompany.rest;

import com.laboratory.annotation.Log;
import com.laboratory.modules.base.entrustCompany.service.BaseEntrustCompanyService;
import com.laboratory.modules.base.entrustCompany.service.dto.BaseEntrustCompanyDto;
import com.laboratory.modules.base.entrustCompany.service.dto.BaseEntrustCompanyQueryParam;
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
* @author Randolph
* @date 2022-08-31
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "委托单位管理")
@RequestMapping("/api/baseEntrustCompany")
public class BaseEntrustCompanyController {

    private final BaseEntrustCompanyService baseEntrustCompanyService;

    // @Log("查询委托单位")
    @GetMapping
    @ApiOperation("查询委托单位")
    @PreAuthorize("@el.check('baseEntrustCompany:list')")
    public ResponseEntity query(BaseEntrustCompanyQueryParam query, Pageable pageable){
        return new ResponseEntity<>(baseEntrustCompanyService.queryAll(query,pageable,true),HttpStatus.OK);
    }

    @GetMapping("/all")
    @ApiOperation("查询全部委托单位")
    @PreAuthorize("@el.check('baseEntrustCompany:list')")
    public ResponseEntity queryAll(BaseEntrustCompanyQueryParam query){
        return new ResponseEntity<>(baseEntrustCompanyService.queryList(query,true),HttpStatus.OK);
    }

    @ApiOperation("获取单个委托单位")
    @GetMapping(value = "/{id}")
    @PreAuthorize("@el.check('baseEntrustCompany:list')")
    public ResponseEntity<Object> query(@PathVariable Long id){
        return new ResponseEntity<>(baseEntrustCompanyService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @Log("新增委托单位")
    @ApiOperation("新增委托单位")
    @PreAuthorize("@el.check('baseEntrustCompany:add')")
    public ResponseEntity create(@Validated @RequestBody BaseEntrustCompanyDto resources){
        return new ResponseEntity<>(baseEntrustCompanyService.insert(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改委托单位")
    @ApiOperation("修改委托单位")
    @PreAuthorize("@el.check('baseEntrustCompany:edit')")
    public ResponseEntity update(@Validated @RequestBody BaseEntrustCompanyDto resources){
        baseEntrustCompanyService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除委托单位")
    @ApiOperation("删除委托单位")
    @PreAuthorize("@el.check('baseEntrustCompany:del')")
    public ResponseEntity delete(@RequestBody Set<Long> ids) {
        baseEntrustCompanyService.removeByIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // @Log("导出委托单位")
    @ApiOperation("导出委托单位")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('baseEntrustCompany:list')")
    public void download(HttpServletResponse response, BaseEntrustCompanyQueryParam query) throws IOException {
        baseEntrustCompanyService.download(baseEntrustCompanyService.queryAll(query), response);
    }

}
