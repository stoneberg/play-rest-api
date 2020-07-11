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

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import me.stone.rest.play.common.exception.UserNotFoundException;
import me.stone.rest.play.users.service.UserService;
import me.stone.rest.play.users.view.Views;


@Api(tags = "User Management RESTful Services [Json View]", value = "UserJsonViewController")
@RequiredArgsConstructor
@Validated // @Min(1)
@RestController
@RequestMapping("/api/jsonview/users")
public class UserJsonViewController {
	
	private final UserService userService;
	
	@JsonView(Views.External.class)
	@GetMapping(path = "/external/{id}")
	public ResponseEntity<?> getUserExternal(@PathVariable("id") @Min(1) Long id) {
		try {
			return ResponseEntity.ok(userService.getUser(id));
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@JsonView(Views.Internal.class)
	@GetMapping(path = "/internal/{id}")
	public ResponseEntity<?> getUserInternal(@PathVariable("id") @Min(1) Long id) {
		try {
			return ResponseEntity.ok(userService.getUser(id));
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}
