package me.stone.rest.play.order.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.stone.rest.play.order.domain.Order;

public class OrderRes {
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class FindDTO {
		private Long id;
		private String name;
		private String description;
		private String username;
		
		public FindDTO(Order entity) {
			super();
			this.id = entity.getId();
			this.name = entity.getName();
			this.description = entity.getDescription();
			this.username = entity.getUser().getUsername();
		}
		
		public FindDTO(Long id, String name, String description) {
			super();
			this.id = id;
			this.name = name;
			this.description = description;
		}
		
	}

}
