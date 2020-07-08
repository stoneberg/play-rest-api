package me.stone.rest.play.users.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.stone.rest.play.users.payload.UserReq.CreateDTO;
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
	public ResponseEntity<?> createUser(@RequestBody CreateDTO dto) {
		log.info("dto==============>{}", dto);
		return ResponseEntity.ok(userService.save(dto.toEntity()));
	}
	
	@GetMapping(path = "/users/{id}")
	public ResponseEntity<?> getUserId(@PathVariable("id") Long id) {
		return ResponseEntity.ok(userService.getUserById(id));
	}
	

}
