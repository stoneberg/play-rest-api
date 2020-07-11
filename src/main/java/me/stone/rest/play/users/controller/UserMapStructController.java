package me.stone.rest.play.users.controller;

import javax.validation.constraints.Min;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import me.stone.rest.play.common.exception.UserNotFoundException;
import me.stone.rest.play.users.service.UserMapStructService;

@Api(tags = "User Management RESTful Services [MapStruct]", value = "UserMapStructController")
@RequiredArgsConstructor
@Validated // @Min(1)
@RestController
@RequestMapping("/api/mapstruct/users")
public class UserMapStructController {
	
	private final UserMapStructService userMapStructService;
	
	@GetMapping
	public ResponseEntity<?> getAllUsers() {
		return ResponseEntity.ok(userMapStructService.getAllUsers());
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getUser(@PathVariable("id") @Min(1) Long id) {
		try {
			return ResponseEntity.ok(userMapStructService.getUser(id));
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}
