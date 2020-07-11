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
import me.stone.rest.play.users.service.UserVersionService;

@Api(tags = "User Management RESTful Services [Parameter Versioning]", value = "UserRequestParamVersionController")
@RequiredArgsConstructor
@Validated // @Min(1) for method parameter validation not bean validation
@RestController
@RequestMapping("/api/version/param/users")
public class UserRequestParamVersionController {
	
	private final UserVersionService userVersionService;
	
	/**
	 * Request Param Version V1 Test
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/{id}", params = "version=1")
	public ResponseEntity<?> getUserV1(@PathVariable("id") @Min(1) Long id) {
		try {
			return ResponseEntity.ok(userVersionService.getUserV1(id));
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	/**
	 * Request Param Version V2 Test
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/{id}", params = "version=2")
	public ResponseEntity<?> getUserV2(@PathVariable("id") @Min(1) Long id) {
		try {
			return ResponseEntity.ok(userVersionService.getUserV2(id));
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}
