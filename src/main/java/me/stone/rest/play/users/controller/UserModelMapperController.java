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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.stone.rest.play.common.exception.UserNotFoundException;
import me.stone.rest.play.users.service.UserModelMapperService;

@Slf4j
@RequiredArgsConstructor
@Validated // @Min(1)
@RestController
@RequestMapping("/modelmapper/users")
public class UserModelMapperController {
	
	private final UserModelMapperService userModelMapperService;
	
	@GetMapping
	public ResponseEntity<?> getAllUsers() {
		return ResponseEntity.ok(userModelMapperService.getAllUsers());
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getUser(@PathVariable("id") @Min(1) Long id) {
		try {
			return ResponseEntity.ok(userModelMapperService.getUser(id));
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}
