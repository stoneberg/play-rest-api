package me.stone.rest.play.users.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.stone.rest.play.users.domain.User;

public class UserReq {
    
    @ApiModel(description = "This model is to create a user")
    @Data
    public static class CreateDTO {
        
        @ApiModelProperty(notes = "username is login id", example = "stoneberg", required = true, position = 1)
        @Size(min = 2, max = 50)
        @NotBlank(message = "username is mandatory field. please provide username")
        private String username;
        
        @ApiModelProperty(notes = "user firstname", example = "Bill", required = true, position = 2)
        @Size(min = 2, max = 50, message = "firstname should have at least 2 characters")
        private String firstname;
        
        @ApiModelProperty(notes = "user lastname", example = "Gates", required = true, position = 3)
        @Size(min = 2, max = 50, message = "lastname should have at least 2 characters")
        private String lastname;
        
        @ApiModelProperty(notes = "user authority type", example = "user or admin", required = true, position = 4)
        private String role;
        
        @ApiModelProperty(notes = "user email address", example = "stoneberg73@gmail.com", required = true, position = 5)
        private String email;
        
        @ApiModelProperty(notes = "user age", example = "33" ,required = false, position = 6)
        private Integer age;
        
        @ApiModelProperty(notes = "user social serial number", example = "ssn-1234-1234" ,required = true, position = 7)
        private String ssn;
        
        @ApiModelProperty(notes = "user address", example = "P.O. Box 567 1561 Duis Rd. Pomona TN 08609 (750) 558-3965" ,required = false, position = 8)
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
