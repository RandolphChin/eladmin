package com.laboratory.modules.base.country.rest;

import com.laboratory.annotation.Log;
import com.laboratory.modules.base.country.service.CountryService;
import com.laboratory.modules.base.country.service.dto.CountryDto;
import com.laboratory.modules.base.country.service.dto.CountryQueryParam;
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
@Api(tags = "各国编码管理")
@RequestMapping("/api/country")
public class CountryController {

    private final CountryService countryService;

    // @Log("查询各国编码")
    @GetMapping
    @ApiOperation("查询各国编码")
    @PreAuthorize("@el.check('country:list')")
    public ResponseEntity query(CountryQueryParam query, Pageable pageable){
        return new ResponseEntity<>(countryService.queryAll(query,pageable),HttpStatus.OK);
    }

    @ApiOperation("获取单个各国编码")
    @GetMapping(value = "/{id}")
    @PreAuthorize("@el.check('country:list')")
    public ResponseEntity<Object> query(@PathVariable Integer id){
        return new ResponseEntity<>(countryService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @Log("新增各国编码")
    @ApiOperation("新增各国编码")
    @PreAuthorize("@el.check('country:add')")
    public ResponseEntity create(@Validated @RequestBody CountryDto resources){
        return new ResponseEntity<>(countryService.insert(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改各国编码")
    @ApiOperation("修改各国编码")
    @PreAuthorize("@el.check('country:edit')")
    public ResponseEntity update(@Validated @RequestBody CountryDto resources){
        countryService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除各国编码")
    @ApiOperation("删除各国编码")
    @PreAuthorize("@el.check('country:del')")
    public ResponseEntity delete(@RequestBody Set<Integer> ids) {
        countryService.removeByIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // @Log("导出各国编码")
    @ApiOperation("导出各国编码")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('country:list')")
    public void download(HttpServletResponse response, CountryQueryParam query) throws IOException {
        countryService.download(countryService.queryAll(query), response);
    }

}
