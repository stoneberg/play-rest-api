package me.stone.rest.play.users.payload;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.stone.rest.play.users.domain.User;

public class UserRes {

	@JsonFilter(value = "userFilter")
	@Data
	@EqualsAndHashCode(callSuper=false)
	@NoArgsConstructor
	@AllArgsConstructor
	public static class FindDTO extends RepresentationModel<FindDTO> {

		private Long userId;

		private String username;

		private String firstname;

		private String lastname;

		private String role;

		private String email;

		private Integer age;

		private String ssn;

		private List<OrderDTO> orders = new ArrayList<>();

		public FindDTO(User entity) {
			this.userId = entity.getId();
			this.username = entity.getUsername();
			this.firstname = entity.getFirstname();
			this.lastname = entity.getLastname();
			this.role = entity.getRole();
			this.email = entity.getEmail();
			this.age = entity.getAge();
			this.ssn = entity.getSsn();
			this.orders = entity.getOrders().stream()
					.map(order -> new OrderDTO(order.getId(), order.getName(), order.getDescription()))
					.collect(Collectors.toList());
		}

	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class OrderDTO {
		private Long id;
		private String name;
		private String description;
	}

}
