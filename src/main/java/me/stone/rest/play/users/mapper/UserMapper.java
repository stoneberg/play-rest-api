package me.stone.rest.play.users.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import me.stone.rest.play.users.domain.User;
import me.stone.rest.play.users.payload.UserRes.FindMsDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {
    
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	// UserEntity To UserDTO
	@Mapping(source = "id", target = "userId")
	@Mapping(source = "email", target = "emailAddress")
//	@Mapping(source = "orders.id", target = "orders.orderId")
//	@Mapping(source = "orders.name", target = "orders.ordername")
	FindMsDTO userToUserDTO(User user);
	
	// List<UserEntity> To List<UserDTO>
	@Mapping(source = "id", target = "userId")
	@Mapping(source = "email", target = "emailAddress")
//	@Mapping(source = "orders.id", target = "orders.orderId")
//	@Mapping(source = "orders.name", target = "orders.ordername")
	List<FindMsDTO> usersToUserDTOs(List<User> users);
    
}
