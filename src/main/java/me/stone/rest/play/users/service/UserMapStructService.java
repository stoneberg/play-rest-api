package me.stone.rest.play.users.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.stone.rest.play.common.exception.UserNotFoundException;
import me.stone.rest.play.users.domain.User;
import me.stone.rest.play.users.mapper.UserMapper;
import me.stone.rest.play.users.payload.UserRes.FindMsDTO;
import me.stone.rest.play.users.repository.UserRepository;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserMapStructService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	

	// getAllUsers
	public List<FindMsDTO> getAllUsers() {
		List<User> users = userRepository.findAll();
		return userMapper.usersToUserDTOs(users);
	}

	// getUserByID
	public FindMsDTO getUser(Long id) throws UserNotFoundException {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(String.format("Not Found User: Id=%s", id)));
		return userMapper.userToUserDTO(user);
	}

}
