//package com.dib.service;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.client.RestTemplate;
//
//import com.dib.controller.dto.UserDTO;
//import com.dib.controller.dto.system.ErrorMessage;
//import com.dib.exception.CustomNotFoundException;
//import com.dib.mapper.UserMapper;
//import com.dib.model.User;
//import com.dib.repository.UserRepository;
//
//@Service
//public class UserService {
//	
//	@Autowired
//	private UserRepository userRepository;
//	
//	@Autowired
//	private UserMapper userMapper;
//	
//	@Autowired
//	RestTemplate restTemplate;
//
//	@Transactional(readOnly=true)
//	public List<UserDTO> loadAll()
//	{
//		List<User> res = userRepository.findAll();
//		List<UserDTO> res1 = userMapper.enitiesToDtos(res);
//		return res1;
//	}
//	
//	@Transactional
//	public Integer create(UserDTO input)
//	{
//		User entity = userMapper.dtoToEntity(input);
//		User usr = userRepository.save(entity);
//		return usr.getUserId();
//	}
//	
//	@Transactional
//	public void update(UserDTO input) throws Exception
//	{
//		User userDB = userRepository.findById(input.getUserId()).orElse(null);
//		if (input.getUserId() == null || userDB == null) {
//			throw new CustomNotFoundException("NOT FOUND");
//		}
//		
//		userMapper.updateEntityFromDto(input, userDB);
//		User usr = userRepository.save(userDB);
//	}
//	
//	@Transactional
//	public void delete(Long id)
//	{
//		userRepository.deleteById(id);
//	}
//	
//	@Transactional(readOnly=true)
//	public UserDTO findById(Long id) throws Exception
//	{
//		if (id == null) {
//			throw new CustomNotFoundException("NOT FOUND");
//		}
//		Optional<User> opt = userRepository.findById(id);
//		User usr = opt.orElseThrow(() -> new CustomNotFoundException("NOT FOUND"));
//		UserDTO userDTO = userMapper.entityToDTO(usr);
//		return userDTO;
//	}
//	
//	@Transactional(readOnly=true)
//	public boolean exists(Long id)
//	{
//		boolean usr = userRepository.existsById(id);
//		return usr;
//	}
//	
//	//custom methods
//	
//	public UserDTO findUserByName(String name) throws CustomNotFoundException {
//		User usr = userRepository.findByUserName(name);
//		UserDTO dto = userMapper.entityToDTO(usr);
//		return dto;
//	}
//	
//	public UserDTO findUserByEmail(String email) throws CustomNotFoundException {
//		if (email == null) {
//			throw new CustomNotFoundException("NOT FOUND");
//		}
//		User usr = userRepository.findByUserEmail(email);
//		UserDTO dto = userMapper.entityToDTO(usr);
//		return dto;
//	}
//	
//	public UserDTO findUserByNameAndNotId(String name, Long id) throws CustomNotFoundException {
//		User usr = userRepository.findByUserNameAndUserIdNot(name, id);
//		UserDTO dto = userMapper.entityToDTO(usr);
//		return dto;
//	}
//	
//	public UserDTO findUserByEmailAndNotId(String email, Long id) throws CustomNotFoundException {
//		if (email == null) {
//			throw new CustomNotFoundException("NOT FOUND");
//		}
//		User usr = userRepository.findByUserEmailAndUserIdNot(email, id);
//		UserDTO dto = userMapper.entityToDTO(usr);
//		return dto;
//	}
//	
//	public UserDTO findOneByUserName(String username) {
//		User usr = userRepository.findOneByUserName(username);
//		UserDTO dto = userMapper.entityToDTO(usr);
//		return dto;
//	}
//	
//	//only admin can see users
////	@Transactional(readOnly=true)
////	public List<UserDTO> loadAllByPrivilege()
////	{	
////		User user = userRepository.findOneByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
////		if(!userRepository.getUserRole(user.getUserId()).equals("ROLE_ADMIN")) {
////			return null;
////		}else {
////			List<User> res = userRepository.findAll();
////			List<UserDTO> res1 = userMapper.enitiesToDtos(res);
////			return res1;
////		}
////	}
//
//}
