package me.stone.rest.play.users.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import me.stone.rest.play.order.repository.OrderRepository;
import me.stone.rest.play.users.domain.User;

@SpringBootTest
class UserRepoTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@BeforeEach
	void init() {
		orderRepository.deleteAll();
		userRepository.deleteAll();
	}
	
	@Test
	@DisplayName("BaseTimeEntity 등록 확인")
	void baseTimeEntityTest() {
		// given
		LocalDateTime now = LocalDateTime.now();
		userRepository.save(User.builder()
				.username("oldfriend")
				.firstname("Park")
				.lastname("SuYoung")
				.email("park@gmail.com")
				.age(47)
				.role("admin")
				.ssn("ssn-7777-1111")
				.address("343-6527 Purus. Avenue Logan NV 12657 (581) 379-7573")
				.build());
		// when
		List<User> users = userRepository.findAll();
		
		// then
		long count = users.stream().count();
		User user = users.stream().skip(count - 1).findFirst().get(); // get last element
		System.out.println("========> createdAt=" + user.getCreatedAt() + ", modifiedAt=" + user.getModifiedAt());
		assertThat(user.getCreatedAt()).isAfter(now);
		assertThat(user.getModifiedAt()).isAfter(now);
	}

}
