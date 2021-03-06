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

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import me.stone.rest.play.common.exception.UserNotFoundException;
import me.stone.rest.play.order.controller.OrderHateoasController;
import me.stone.rest.play.users.payload.UserRes.FindDTO;
import me.stone.rest.play.users.service.UserService;

@Api(tags = "User Management RESTful Services [HATEOAS]", value = "UserHateoasController")
@RequiredArgsConstructor
@Validated // @Min(1)
@RestController
@RequestMapping("/api/hateoas/users")
public class UserHateoasController {

	private final UserService userService;

	@GetMapping
	public ResponseEntity<?> getAllUsers() {
		List<FindDTO> users = userService.getAllUsers();
		
		for (FindDTO user : users) {
			// selflink("href": "http://localhost:8080/hateoas/users/4")
			Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(user.getUserId()).withSelfRel();
			user.add(selfLink);
			// relationship link("http://localhost:8080/hateoas/users/4/orders")
			Object orders = WebMvcLinkBuilder.methodOn(OrderHateoasController.class).getAllOrders(user.getUserId());
			Link ordersLink = WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
			user.add(ordersLink);
		}
		
		// selflink for getAllUsers("href": "http://localhost:8080/hateoas/users")
		Link selfLinkForAllUsers = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
		CollectionModel<FindDTO> resources = CollectionModel.of(users, selfLinkForAllUsers);
	    return ResponseEntity.ok(resources);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getUser(@PathVariable("id") @Min(1) Long id) {
		try {
			FindDTO user = userService.getUser(id);
			Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(id).withSelfRel();
			user.add(selfLink);
			EntityModel<FindDTO> resource = EntityModel.of(user);
			return ResponseEntity.ok(resource);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}
