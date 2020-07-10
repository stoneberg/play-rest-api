package me.stone.rest.play.order.payload;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.stone.rest.play.order.domain.Order;

public class OrderRes {
	
	@Data
	@EqualsAndHashCode(callSuper=false)
	@AllArgsConstructor
	@NoArgsConstructor
	public static class FindDTO extends RepresentationModel<FindDTO> {
		private Long orderId;
		private String name;
		private String description;
		private String username;
		
		public FindDTO(Order entity) {
			super();
			this.orderId = entity.getId();
			this.name = entity.getName();
			this.description = entity.getDescription();
			this.username = entity.getUser().getUsername();
		}
		
		public FindDTO(Long id, String name, String description) {
			super();
			this.orderId = id;
			this.name = name;
			this.description = description;
		}
		
	}

}
