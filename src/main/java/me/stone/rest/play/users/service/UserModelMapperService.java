package me.stone.rest.play.users.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.stone.rest.play.exception.UserNotFoundException;
import me.stone.rest.play.exception.UsernameNotFoundException;
import me.stone.rest.play.users.domain.User;
import me.stone.rest.play.users.payload.UserRes.FindDTO;
import me.stone.rest.play.users.payload.UserRes.FindMmDTO;
import me.stone.rest.play.users.repository.UserRepository;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserModelMapperService {

	private final UserRepository userRepository;
	
	private final ModelMapper modelMapper;
	

	// getAllUsers
	public List<FindMmDTO> getAllUsers() {
		return userRepository.findAll().stream()
				.map(user -> modelMapper.map(user, FindMmDTO.class))
				.collect(Collectors.toList());
	}

	// getUserByID
	public FindMmDTO getUser(Long id) throws UserNotFoundException {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(String.format("Not Found User: Id=%s", id)));
		return modelMapper.map(user, FindMmDTO.class);
	}


	// findUserByUsername
	public FindDTO getUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("Not Found User: Username=%s", username)));
		return new FindDTO(user);
	}

}
