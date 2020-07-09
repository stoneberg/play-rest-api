package me.stone.rest.play.order.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.stone.rest.play.exception.ResourceNotFoundException;
import me.stone.rest.play.exception.UserNotFoundException;
import me.stone.rest.play.order.domain.Order;
import me.stone.rest.play.order.payload.OrderReq.CreateDTO;
import me.stone.rest.play.order.payload.OrderRes.FindDTO;
import me.stone.rest.play.order.repository.OrderRepository;
import me.stone.rest.play.users.domain.User;
import me.stone.rest.play.users.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class OrderService {
	
	private final UserRepository userRepository;
	
	private final OrderRepository orderRepository;
	
	// find All Orders By UserId
	public List<FindDTO> getAllOrdersByUserId(Long userId) {
		List<Order> orders = orderRepository.findAllByUserUserId(userId);
		return orders.stream().map(order -> new FindDTO(order.getOrderId(), order.getName(), order.getDescription()))
		.collect(Collectors.toList());
	}

	// create User Order
	public Long createOrderByUserId(Long userId, CreateDTO dto) throws UserNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(String.format("Not Found User: Id=%s", userId)));
		
		Order order = dto.toEntity();
		order.addUser(user);
		
		return orderRepository.save(order).getOrderId();
	}

	// get Order by UserId and OrderId
	public FindDTO getOrderByIds(Long userId, Long orderId) throws ResourceNotFoundException {
		Order order = orderRepository.findByOrderIdAndUserUserId(orderId, userId).orElseThrow(() -> new ResourceNotFoundException(String.format("Resource Not Found: orderId=%s", orderId)));
		return new FindDTO(order);
	}

}