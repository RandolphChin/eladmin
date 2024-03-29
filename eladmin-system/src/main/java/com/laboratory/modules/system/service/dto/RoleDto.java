package com.laboratory.modules.system.service.dto;

import lombok.Data;
import com.laboratory.base.CommonDto;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
*
* @date 2020-09-25
*/
@Data
public class RoleDto extends CommonDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Set<MenuDto> menus;

    private Set<DeptDto> depts;

    private String name;

    private Integer level;

    private String description;

    private String dataScope;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoleDto roleDto = (RoleDto) o;
        return Objects.equals(id, roleDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
