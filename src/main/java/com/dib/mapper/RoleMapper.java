package com.dib.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import com.dib.controller.dto.RoleDTO;
import com.dib.model.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
	
	RoleDTO entityToDTO(Role account);
	Role dtoToEntity(RoleDTO accountDTO);
    
    List<RoleDTO> enitiesToDtos(List<Role> list);
    List<Role> dtoToEntities (List<RoleDTO> list);
    Role updateEntityFromDto(RoleDTO dto, @MappingTarget Role entity);
}
