package me.stone.rest.play.order.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import me.stone.rest.play.order.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findAllByUserId(Long userId);
	Optional<Order> findByIdAndUserId(Long orderId, Long userId);
}
