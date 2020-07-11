package me.stone.rest.play.users.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.stone.rest.play.common.exception.UserNotFoundException;
import me.stone.rest.play.users.domain.User;
import me.stone.rest.play.users.payload.UserRes.FindDTOV1;
import me.stone.rest.play.users.payload.UserRes.FindDTOV2;
import me.stone.rest.play.users.repository.UserRepository;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserVersionService {

	private final UserRepository userRepository;

	// getUserByID V1
	public FindDTOV1 getUserV1(Long id) throws UserNotFoundException {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(String.format("Not Found User: Id=%s", id)));
		return FindDTOV1.builder().entity(user).build();
	}
	
	// getUserByID V2
	public FindDTOV2 getUserV2(Long id) throws UserNotFoundException {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(String.format("Not Found User: Id=%s", id)));
		return FindDTOV2.builder().entity(user).build();
	}


}
