package me.stone.rest.play.order.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.stone.rest.play.order.domain.Order;
import me.stone.rest.play.order.payload.OrderRes.OrderDTO;
import me.stone.rest.play.order.repository.OrderRepository;
import me.stone.rest.play.users.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class OrderService {
	
	private final UserRepository userRepository;
	
	private final OrderRepository orderRepository;
	
	// findAllOrdersByUserId
	public List<OrderDTO> getAllOrdersByUserId(Long userId) {
		List<Order> orders = orderRepository.findAllByUserId(userId);
		return orders.stream().map(order -> new OrderDTO(order.getId(), order.getDescription()))
		.collect(Collectors.toList());
	}

}
