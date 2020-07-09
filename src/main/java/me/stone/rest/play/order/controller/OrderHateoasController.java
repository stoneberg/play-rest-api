package me.stone.rest.play.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.stone.rest.play.order.service.OrderService;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/hateoas/users")
public class OrderHateoasController {
	
	private final OrderService orderService;
	
	@GetMapping(path = "/{userId}/orders")
	public ResponseEntity<?> getAllOrdersByUserId(@PathVariable Long userId) {
		return new ResponseEntity<>(orderService.getAllOrdersByUserId(userId), HttpStatus.OK);
	}

}
