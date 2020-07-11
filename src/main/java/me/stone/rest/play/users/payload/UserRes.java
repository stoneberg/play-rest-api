package me.stone.rest.play.users.payload;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
        
        private String addrees;

        @JsonView(Views.Internal.class)
        private List<OrderDTO> orders = new ArrayList<>();

        @Builder
        public FindDTO(User entity) {
            this.userId = entity.getId();
            this.username = entity.getUsername();
            this.firstname = entity.getFirstname();
            this.lastname = entity.getLastname();
            this.role = entity.getRole();
            this.email = entity.getEmail();
            this.age = entity.getAge();
            this.ssn = entity.getSsn();
            this.addrees = entity.getAddress();
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
    
    /**
     * ModelMapper Test
     * @author stone
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FindMmDTO {
        private Long userId; // id -> userId auto convert
        private String username;
        private String firstname;
        private String lastname;
        private String role;
        private String email;
        private Integer age;
        private String ssn;
        private List<OrderMmDTO> orders = new ArrayList<>();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderMmDTO {
        private Long orderId;  // id -> orderId auto convert
        private String name;
        private String description;
    }
    
    /**
     * MapStruct Test
     * @author stone
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FindMsDTO {
        private Long userId;
        private String username;
        private String firstname;
        private String lastname;
        private String role;
        private String emailAddress;
        private Integer age;
        private String ssn;
        private List<OrderMsDTO> orders = new ArrayList<>();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderMsDTO {
        private Long orderId;
        private String name;
        private String description;
    }
    
    
    /**
     * Version Test #1
     * @author stone
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FindDTOV1 {
        private Long userId;
        private String username;
        private String firstname;
        private String lastname;
        private String email;
        private String role;
        private Integer age;
        private String ssn;
        private List<OrderDTOV1> orders = new ArrayList<>();
        
        @Builder
        public FindDTOV1(User entity) {
            this.userId = entity.getId();
            this.username = entity.getUsername();
            this.firstname = entity.getFirstname();
            this.lastname = entity.getLastname();
            this.role = entity.getRole();
            this.email = entity.getEmail();
            this.age = entity.getAge();
            this.ssn = entity.getSsn();
            this.orders = entity.getOrders().stream()
                    .map(order -> new OrderDTOV1(order.getId(), order.getName(), order.getDescription()))
                    .collect(Collectors.toList());
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderDTOV1 {
        private Long orderId;
        private String name;
        private String description;
    }
    
    /**
     * Version Test #2
     * @author stone
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FindDTOV2 {
        private Long userId;
        private String username;
        private String firstname;
        private String lastname;
        private String email;
        private String role;
        private Integer age;
        private String ssn;
        private String address;
        private List<OrderDTOV1> orders = new ArrayList<>();
        
        @Builder
        public FindDTOV2(User entity) {
            this.userId = entity.getId();
            this.username = entity.getUsername();
            this.firstname = entity.getFirstname();
            this.lastname = entity.getLastname();
            this.role = entity.getRole();
            this.email = entity.getEmail();
            this.age = entity.getAge();
            this.ssn = entity.getSsn();
            this.address = entity.getAddress();
            this.orders = entity.getOrders().stream()
                    .map(order -> new OrderDTOV1(order.getId(), order.getName(), order.getDescription()))
                    .collect(Collectors.toList());
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderDTOV2 {
        private Long orderId;
        private String name;
        private String description;
    }
    
    

}
