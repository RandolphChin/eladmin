package com.laboratory.modules.system.service.mapper;

import com.laboratory.base.CommonMapper;
import com.laboratory.modules.system.domain.Menu;
import com.laboratory.modules.system.service.dto.MenuDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
*
* @date 2020-09-25
*/
@Repository
public interface MenuMapper extends CommonMapper<Menu> {

    @Select("SELECT m.menu_id as id, m.* FROM sys_roles_menus rm INNER JOIN sys_menu m ON rm.menu_id=m.menu_id WHERE rm.role_id=#{roleId}")
    Set<Menu> selectLink(Long roleId);

    @Select({"<script>SELECT m.menu_id as id, m.* FROM sys_roles_menus rm INNER JOIN sys_menu m ON rm.menu_id=m.menu_id WHERE rm.role_id IN"
            + "<foreach item='item' index='index' collection='roleIds' open='(' separator=',' close=')'> #{item} </foreach>"
            + "</script>"})
    Set<Menu> selectByRoleIds(@Param("roleIds") Set<Long> roleIds);

    @Select({"<script>SELECT m.menu_id as id, m.* FROM sys_menu m LEFT OUTER JOIN sys_roles_menus rm ON m.menu_id=rm.menu_id LEFT OUTER JOIN sys_role r ON rm.role_id=r.role_id WHERE r.role_id IN "
            + "<foreach item='item' index='index' collection='roleIds' open='(' separator=',' close=')'> #{item} </foreach>"
            + " AND m.type &lt;&gt; #{type} ORDER BY m.menu_sort ASC</script>"})
    LinkedHashSet<Menu> selectLinkRole(@Param("roleIds") Set<Long> roleIds, @Param("type") Long type);

    List<MenuDto> selectRecursivelyById(Long menuId);
}
