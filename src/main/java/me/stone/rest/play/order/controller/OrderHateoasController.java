package me.stone.rest.play.order.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.stone.rest.play.order.payload.OrderRes.FindDTO;
import me.stone.rest.play.order.service.OrderService;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/hateoas/users")
public class OrderHateoasController {
	
	private final OrderService orderService;
	
	@GetMapping(path = "/{userId}/orders")
	public ResponseEntity<?> getAllOrders(@PathVariable Long userId) {
		
		List<FindDTO> orders = orderService.getAllOrders(userId);
		
		for (FindDTO order : orders) {
			Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(order.getOrderId()).withSelfRel();
			order.add(selfLink);
		}
		
		CollectionModel<FindDTO> resources = CollectionModel.of(orders);
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}

}
