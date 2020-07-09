package me.stone.rest.play.users.controller;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.stone.rest.play.exception.UserExistsException;
import me.stone.rest.play.exception.UserNotFoundException;
import me.stone.rest.play.users.payload.UserReq.CreateDTO;
import me.stone.rest.play.users.payload.UserReq.UpdateDTO;
import me.stone.rest.play.users.service.UserService;

@Slf4j
@RequiredArgsConstructor
@RestController
//@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;
	
	
	@GetMapping(path = "/users")
	public ResponseEntity<?> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	@PostMapping(path = "/users")
	public ResponseEntity<?> createUser(@Valid @RequestBody CreateDTO dto, UriComponentsBuilder builder) {
		try {
			Long id = userService.createUser(dto.toEntity());
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/users/{id}").buildAndExpand(id).toUri());
			return new ResponseEntity<>(headers, HttpStatus.CREATED);
		} catch (UserExistsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@GetMapping(path = "/users/{id}")
	public ResponseEntity<?> getUserId(@PathVariable("id") Long id) {
		try {
			return ResponseEntity.ok(userService.getUserById(id));
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@PutMapping(path = "/users/{id}")
	public ResponseEntity<?> updateUserById(@PathVariable("id") Long id, @RequestBody UpdateDTO dto) {
		try {
			return ResponseEntity.ok(userService.updateUserById(id, dto));
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@DeleteMapping(path = "/users/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable("id") Long id) {
		try {
			return ResponseEntity.ok(userService.deleteUserById(id));
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@GetMapping(path = "/users/username/{username}")
	public ResponseEntity<?> getUserByUsername(@PathVariable("username") String username) {
		try {
			return ResponseEntity.ok(userService.getUserByUsername(username));
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	

}
