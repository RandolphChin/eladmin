package com.laboratory.modules.base.certificateManage.service.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.laboratory.base.CommonMapper;
import com.laboratory.modules.base.certificateManage.domain.BasePersonCertific;
import com.laboratory.modules.base.certificateManage.service.dto.BasePersonCertificDto;

import java.util.List;


/**
* @author randolph
* @date 2022-08-24
*/
@Repository
public interface BasePersonCertificMapper extends CommonMapper<BasePersonCertific> {
    // 联表查询
    @Select("select pc.*,u.username,d.name as deptName from base_person_certific pc left join sys_user u on pc.user_id = u.user_id left join sys_dept d on pc.dept_id = d.dept_id ${ew.customSqlSegment}")
    IPage<BasePersonCertificDto> selectPageJoin(Page page, @Param(Constants.WRAPPER) QueryWrapper wrapper);

    // 联表查询 全部
    // https://blog.csdn.net/chh0912/article/details/102662500
    // mysql数据库查询,查询字段用逗号隔开,关联另一个表并显示
    @Select("select pc.*,u.username,d.name as deptName,GROUP_CONCAT(s.path) as attachPath from base_person_certific pc left join sys_user u on pc.user_id = u.user_id left join sys_dept d on pc.dept_id = d.dept_id left join tool_local_storage s on FIND_IN_SET(s.storage_id,pc.attach) ${ew.customSqlSegment} group by pc.id ")
    List<BasePersonCertificDto> listAll(@Param(Constants.WRAPPER) QueryWrapper wrapper);
}
