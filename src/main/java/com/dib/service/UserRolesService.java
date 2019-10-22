package com.dib.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import com.dib.controller.dto.RoleDTO;
import com.dib.controller.dto.UserRolesDTO;
import com.dib.exception.CustomNotFoundException;
import com.dib.mapper.RoleMapper;
import com.dib.mapper.UserRolesMapper;
import com.dib.model.UserRoles;
import com.dib.model.Role;
import com.dib.model.User;
import com.dib.repository.RoleRepository;
import com.dib.repository.UserRepository;
import com.dib.repository.UserRolesRepository;

public class UserRolesService {
	
	@Autowired
	UserRolesRepository userRolesRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserRolesMapper userRolesMapper;
	
	@Transactional(readOnly = true)
	public List<UserRolesDTO> loadAll() {
		List<UserRoles> urr = userRolesRepository.findAll();
		List<UserRolesDTO> res = userRolesMapper.enitiesToDtos(urr);
		return res;
	}

	@Transactional
	public Integer create(UserRolesDTO userRolesDTO) {
		UserRoles userRoles = userRolesMapper.dtoToEntity(userRolesDTO);
		UserRoles urr = userRolesRepository.save(userRoles);
		return urr.getUrrId();
	}

	@Transactional
	public void update(UserRolesDTO userRolesDTO) throws Exception {
		UserRoles userRolesDB = userRolesRepository.findById(userRolesDTO.getUrrId()).orElse(null);
	
		
		userRolesDB.setRole(new Role());

		userRolesMapper.updateEntityFromDto(userRolesDTO, userRolesDB);
		

		userRolesRepository.save(userRolesDB);
	}

	@Transactional
	public void delete(Long id) {
		userRolesRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public UserRolesDTO findById(Long id) throws Exception {
		if (id == null) {
			throw new CustomNotFoundException("NOT FOUND");
		}
		Optional<UserRoles> opt = userRolesRepository.findById(id);
		UserRoles urr = opt.orElseThrow(() -> new CustomNotFoundException("NOT FOUND"));
		UserRolesDTO dto = userRolesMapper.entityToDTO(urr);
		return dto;
	}

	@Transactional(readOnly = true)
	public boolean exists(Long id) {
		boolean acr = userRolesRepository.existsById(id);
		return acr;
	}

	// custom-start

	@Transactional(readOnly = true)
	public List<UserRolesDTO> findAllByAccId(Long urrId) {
		List<UserRoles> res = userRolesRepository.findAllByAccId(urrId);
		List<UserRolesDTO> resDTO = userRolesMapper.enitiesToDtos(res);
		return resDTO;
	}

	

}
