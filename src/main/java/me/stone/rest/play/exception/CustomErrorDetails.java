package me.stone.rest.play.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomErrorDetails {
	
	private LocalDateTime timestamp;
	private String messae;
	private String errorDetails;

}
