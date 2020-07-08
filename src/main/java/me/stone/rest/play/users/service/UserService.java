package me.stone.rest.play.users.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.stone.rest.play.users.domain.User;
import me.stone.rest.play.users.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	// getAllUsers
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	// createUser
	public Long save(User entity) {
		return userRepository.save(entity).getId();
	}

}
