package com.laboratory.service.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.laboratory.base.CommonMapper;
import com.laboratory.domain.ColumnInfo;
import com.laboratory.domain.vo.TableInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*
* @date 2020-09-25
*/
@Repository
public interface ColumnInfoMapper extends CommonMapper<ColumnInfo> {

    List<TableInfo> getTables();

    int getTablesTotal();

    IPage<TableInfo> selectPageOfTables(IPage<?> page, @Param("name") String tableName);

    List<ColumnInfo> queryColumnInfo(String tableName);
}
