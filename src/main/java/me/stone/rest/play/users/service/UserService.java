package me.stone.rest.play.users.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.stone.rest.play.exception.UserExistsException;
import me.stone.rest.play.exception.UserNotFoundException;
import me.stone.rest.play.exception.UsernameNotFoundException;
import me.stone.rest.play.users.domain.User;
import me.stone.rest.play.users.payload.UserReq.UpdateDTO;
import me.stone.rest.play.users.payload.UserRes.FindDTO;
import me.stone.rest.play.users.repository.UserRepository;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

	private final UserRepository userRepository;

	// @todo return entity to return dto
	// getAllUsers
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	// createUser
	@Transactional
	public Long createUser(User entity) throws UserExistsException {
		// boolean existingUser = userRepository.findByUsername(entity.getUsername()).isPresent();
		boolean existingUsername = userRepository.existsByEmail(entity.getUsername());
		if (existingUsername) {
			throw new UserExistsException(String.format("Username Already Exists: Username=%s", entity.getUsername()));
		}

		boolean existingEmail = userRepository.existsByEmail(entity.getEmail());
		if (existingEmail) {
			throw new UserExistsException(String.format("User Email Already Exists: Email=%s", entity.getEmail()));
		}

		boolean existingSsn = userRepository.existsBySsn(entity.getSsn());
		if (existingSsn) {
			throw new UserExistsException(String.format("User Ssn Already Exists: Ssn=%s", entity.getSsn()));
		}

		return userRepository.save(entity).getId();
	}

	// getUserByID
	public FindDTO getUserById(Long id) throws UserNotFoundException {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(String.format("Not Found User: Id=%s", id)));
		return new FindDTO(user);
	}

	// updateUserById
	@Transactional
	public Long updateUserById(Long id, UpdateDTO dto) throws UserNotFoundException {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(String.format("Not Found User: Id=%s", id)));
		user.updateUser(dto);
		return id;
	}

	// deleteUserById
	@Transactional
	public Long deleteUserById(Long id) throws UserNotFoundException {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(String.format("Not Found User: Id=%s", id)));
		userRepository.delete(user);
		return id;
	}

	// findUserByUsername
	public FindDTO getUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("Not Found User: Username=%s", username)));
		return new FindDTO(user);
	}

}
