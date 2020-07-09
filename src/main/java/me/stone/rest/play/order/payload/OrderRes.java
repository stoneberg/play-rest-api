package me.stone.rest.play.order.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class OrderRes {
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class OrderDTO {
		private Long id;
		private String description;
	}

}
