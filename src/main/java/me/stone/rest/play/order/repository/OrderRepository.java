package me.stone.rest.play.order.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import me.stone.rest.play.order.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	// if User.id -> User.userId then  findAllByUserId -> findAllByUserUserId
	// List<Order> findAllByUserUserId(Long userId);
	List<Order> findAllByUserId(Long userId);
	// if Order.id, User.id -> Order.orderId, User.userId then  findByIdAndUserId -> findByOrderIdAndUserUserId
	// Optional<Order> findByOrderIdAndUserUserId(Long orderId, Long userId);
	Optional<Order> findByIdAndUserId(Long orderId, Long userId);
}
