package me.stone.rest.play.users.service;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.stone.rest.play.users.domain.User;
import me.stone.rest.play.users.payload.UserReq.UpdateDTO;
import me.stone.rest.play.users.payload.UserRes.FindDTO;
import me.stone.rest.play.users.repository.UserRepository;

@RequiredArgsConstructor
@Transactional(readOnly=true)
@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	// getAllUsers
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	// createUser
	@Transactional
	public Long createUser(User entity) {
		return userRepository.save(entity).getId();
	}
	
	// getUserByID
	public FindDTO getUserById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException(String.format("Not Found Such Id=%s", id)));
		return new FindDTO(user);
	}

	// updateUserById
	@Transactional
	public Long updateUserById(Long id, UpdateDTO dto) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException(String.format("Not Found Such Id=%s", id)));
		user.updateUser(dto);
		return id;
	}
	
	// deleteUserById
	@Transactional
	public Long deleteUserById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException(String.format("Not Found Such Id=%s", id)));
		userRepository.delete(user);
		return id;
	}

}
