package com.laboratory.modules.system.service;

import com.laboratory.base.CommonService;
import com.laboratory.modules.system.domain.UsersRoles;

import java.util.List;

/**
*
* @date 2020-09-25
*/
public interface UsersRolesService extends CommonService<UsersRoles> {
    List<Long> queryUserIdByRoleId(Long id);
    List<Long> queryRoleIdByUserId(Long id);
    boolean removeByRoleId(Long id);
    boolean removeByUserId(Long id);


}
