package com.laboratory.modules.system.service;

import com.laboratory.base.CommonService;
import com.laboratory.modules.system.domain.RolesDepts;

import java.util.List;

/**
*
* @date 2020-09-25
*/
public interface RolesDeptsService extends CommonService<RolesDepts> {

    List<Long> queryDeptIdByRoleId(Long id);
    List<Long> queryRoleIdByDeptId(Long id);
    boolean removeByRoleId(Long id);
    boolean removeByDeptId(Long id);
}
