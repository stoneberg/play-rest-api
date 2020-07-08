package me.stone.rest.play.users.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.stone.rest.play.users.domain.User;
import me.stone.rest.play.users.payload.UserRes.FindDTO;
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
	
	// getUserByI
	public FindDTO getUserById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException(String.format("Not Found Such Id=%s", id)));
		return new FindDTO(user);
	}

}
