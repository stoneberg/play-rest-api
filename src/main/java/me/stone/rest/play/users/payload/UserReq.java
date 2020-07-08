package me.stone.rest.play.users.payload;

import lombok.Data;
import me.stone.rest.play.users.domain.User;

public class UserReq {
	
	@Data
	public static class CreateDTO {
		
		private String username;
		
		private String firstname;
		
		private String lastname;
		
		private String role;
		
		private String email;
		
		private Integer age;
		
		private String ssn;

		public User toEntity() {
			return User.builder()
					.username(this.username)
					.firstname(this.firstname)
					.lastname(this.lastname)
					.role(this.role)
					.email(this.email)
					.age(this.age)
					.ssn(this.ssn)
					.build();
		}
		
	}
	
	
	@Data
	public static class UpdateDTO {
		
		private String username;
		
		private String firstname;
		
		private String lastname;
		
		private String role;
		
		private String email;
		
		private Integer age;
		
		private String ssn;

		public User toEntity() {
			return User.builder()
					.username(this.username)
					.firstname(this.firstname)
					.lastname(this.lastname)
					.role(this.role)
					.email(this.email)
					.age(this.age)
					.ssn(this.ssn)
					.build();
		}
		
	}

}
