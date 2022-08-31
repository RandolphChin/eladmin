package com.laboratory.modules.system.service;

import com.laboratory.base.CommonService;
import com.laboratory.modules.system.domain.RolesMenus;

import java.util.List;

/**
*
* @date 2020-09-25
*/
public interface RolesMenusService extends CommonService<RolesMenus> {
    List<Long> queryMenuIdByRoleId(Long id);
    List<Long> queryRoleIdByMenuId(Long id);
    boolean removeByRoleId(Long id);
    boolean removeByMenuId(Long id);
}
