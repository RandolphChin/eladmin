package com.laboratory.modules.base.equipment.rest;

import com.laboratory.annotation.Log;
import com.laboratory.modules.base.equipment.service.BaseEquipmentAccountService;
import com.laboratory.modules.base.equipment.service.dto.BaseEquipmentAccountDto;
import com.laboratory.modules.base.equipment.service.dto.BaseEquipmentAccountQueryParam;
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
* @author chen
* @date 2022-08-30
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "设备台账管理")
@RequestMapping("/api/baseEquipmentAccount")
public class BaseEquipmentAccountController {

    private final BaseEquipmentAccountService baseEquipmentAccountService;

    // @Log("查询设备台账")
    @GetMapping
    @ApiOperation("查询设备台账")
    @PreAuthorize("@el.check('baseEquipmentAccount:list')")
    public ResponseEntity query(BaseEquipmentAccountQueryParam query, Pageable pageable){
        query.setDelStatus(0);
        return new ResponseEntity<>(baseEquipmentAccountService.queryAll(query,pageable),HttpStatus.OK);
    }

    @ApiOperation("获取单个设备台账")
    @GetMapping(value = "/{id}")
    @PreAuthorize("@el.check('baseEquipmentAccount:list')")
    public ResponseEntity<Object> query(@PathVariable Long id){
        return new ResponseEntity<>(baseEquipmentAccountService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @Log("新增设备台账")
    @ApiOperation("新增设备台账")
    @PreAuthorize("@el.check('baseEquipmentAccount:add')")
    public ResponseEntity create(@Validated @RequestBody BaseEquipmentAccountDto resources){
        return new ResponseEntity<>(baseEquipmentAccountService.insert(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改设备台账")
    @ApiOperation("修改设备台账")
    @PreAuthorize("@el.check('baseEquipmentAccount:edit')")
    public ResponseEntity update(@Validated @RequestBody BaseEquipmentAccountDto resources){
        baseEquipmentAccountService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除设备台账")
    @ApiOperation("删除设备台账")
    @PreAuthorize("@el.check('baseEquipmentAccount:del')")
    public ResponseEntity delete(@RequestBody Long[] ids) {

        //baseEquipmentAccountService.removeByIds(ids);
        baseEquipmentAccountService.updateByIds(ids);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // @Log("导出设备台账")
    @ApiOperation("导出设备台账")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('baseEquipmentAccount:list')")
    public void download(HttpServletResponse response, BaseEquipmentAccountQueryParam query) throws IOException {
        baseEquipmentAccountService.download(baseEquipmentAccountService.queryAll(query), response);
    }

}
