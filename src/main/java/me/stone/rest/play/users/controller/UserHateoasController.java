package me.stone.rest.play.users.controller;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
import me.stone.rest.play.exception.UserNotFoundException;
import me.stone.rest.play.users.payload.UserRes.FindDTO;
import me.stone.rest.play.users.service.UserService;

@Slf4j
@RequiredArgsConstructor
@Validated // @Min(1)
@RestController
@RequestMapping("/hateoas/users")
public class UserHateoasController {

	private final UserService userService;

	@GetMapping
	public ResponseEntity<?> getAllUsers() {
		List<FindDTO> users = userService.getAllUsers();
		
		for (FindDTO user : users) {
			Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(user.getUserId()).withSelfRel();
			user.add(selfLink);
		}
		
		CollectionModel<FindDTO> resources = CollectionModel.of(users);
	    return ResponseEntity.ok(resources);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			FindDTO user = userService.getUserById(id);
			Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(id).withSelfRel();
			user.add(selfLink);
			EntityModel<FindDTO> resource = EntityModel.of(user);
			return ResponseEntity.ok(resource);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}
