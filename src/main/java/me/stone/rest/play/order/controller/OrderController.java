package me.stone.rest.play.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.stone.rest.play.exception.ResourceNotFoundException;
import me.stone.rest.play.exception.UserNotFoundException;
import me.stone.rest.play.order.payload.OrderReq.CreateDTO;
import me.stone.rest.play.order.service.OrderService;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/users")
public class OrderController {
	
	private final OrderService orderService;
	
	@GetMapping(path = "/{userId}/orders")
	public ResponseEntity<?> getAllOrdersByUserId(@PathVariable Long userId) {
		return new ResponseEntity<>(orderService.getAllOrdersByUserId(userId), HttpStatus.OK);
	}
	
	@PostMapping(path = "/{userId}/orders")
	public ResponseEntity<?> createOrderByUserId(@PathVariable Long userId, @RequestBody CreateDTO dto) throws UserNotFoundException {
		return new ResponseEntity<>(orderService.createOrderByUserId(userId, dto), HttpStatus.OK);
	}
	
	@GetMapping(path = "/{userId}/orders/{orderId}")
	public ResponseEntity<?> getOrderByIds(@PathVariable Long userId, @PathVariable Long orderId) throws ResourceNotFoundException {
		return new ResponseEntity<>(orderService.getOrderByIds(userId, orderId), HttpStatus.OK);
	}

}
