package me.stone.rest.play.order.payload;

import lombok.Data;
import me.stone.rest.play.order.domain.Order;

public class OrderReq {
	
	@Data
	public static class CreateDTO {
		private String name;
		private String description;
		
		public Order toEntity() {
			return Order.builder()
			.name(this.name)
			.description(this.description)
			.build();
		}
	}

}
