package me.stone.rest.play.users.payload;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.stone.rest.play.users.domain.User;
import me.stone.rest.play.users.view.Views;

public class UserRes {

	@Data
	@EqualsAndHashCode(callSuper=false)
	@NoArgsConstructor
	@AllArgsConstructor
	public static class FindDTO extends RepresentationModel<FindDTO> {

		@JsonView(Views.External.class)
		private Long userId;

		@JsonView(Views.External.class)
		private String username;

		@JsonView(Views.External.class)
		private String firstname;

		@JsonView(Views.External.class)
		private String lastname;

		@JsonView(Views.Internal.class)
		private String role;

		@JsonView(Views.Internal.class)
		private String email;

		@JsonView(Views.Internal.class)
		private Integer age;

		@JsonView(Views.Internal.class)
		private String ssn;

		@JsonView(Views.Internal.class)
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
		@JsonView(Views.Internal.class)
		private Long orderId;
		@JsonView(Views.Internal.class)
		private String name;
		@JsonView(Views.Internal.class)
		private String description;
	}

}
