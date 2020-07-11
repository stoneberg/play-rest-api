package me.stone.rest.play.users.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import me.stone.rest.play.users.domain.User;

public class UserReq {
    
    @Data
    public static class CreateDTO {
        
        @NotBlank(message = "username is mandatory field. please provide username")
        private String username;
        
        @Size(min = 2, message = "firstname should have at least 2 characters")
        private String firstname;
        
        @Size(min = 2, message = "lastname should have at least 2 characters")
        private String lastname;
        
        private String role;
        
        private String email;
        
        private Integer age;
        
        private String ssn;
        
        private String address;

        public User toEntity() {
            return User.builder()
                    .username(this.username)
                    .firstname(this.firstname)
                    .lastname(this.lastname)
                    .role(this.role)
                    .email(this.email)
                    .age(this.age)
                    .ssn(this.ssn)
                    .address(this.address)
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
        
        private String address;

        public User toEntity() {
            return User.builder()
                    .username(this.username)
                    .firstname(this.firstname)
                    .lastname(this.lastname)
                    .role(this.role)
                    .email(this.email)
                    .age(this.age)
                    .ssn(this.ssn)
                    .address(this.address)
                    .build();
        }
        
    }
    
}
