package me.stone.rest.play.users.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Min;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import me.stone.rest.play.common.exception.UserNotFoundException;
import me.stone.rest.play.users.payload.UserRes.FindDTO;
import me.stone.rest.play.users.service.UserService;

@Api(tags = "User Management RESTful Services [Jackson Mapper Filter]", value = "UserMappingJacksonController")
@RequiredArgsConstructor
@Validated // @Min(1)
@RestController
@RequestMapping("/api/jacksonfilter/users")
public class UserMappingJacksonController {
	
	private final UserService userService;
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getUser(@PathVariable("id") @Min(1) Long id) {
		try {
			
			FindDTO user = userService.getUser(id);
			
			/**
			 * values to send
			 */
			Set<String> fileds = new HashSet<>();
			fileds.add("userId");
			fileds.add("username");
			fileds.add("email");
			
			FilterProvider filterProvider = new SimpleFilterProvider()
					.addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fileds));
			
		    MappingJacksonValue mapper = new MappingJacksonValue(user);
		    mapper.setFilters(filterProvider);
			
			return ResponseEntity.ok(mapper);
			
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	
	@GetMapping(path = "/params/{id}")
	public ResponseEntity<?> getUser2(@PathVariable("id") @Min(1) Long id, @RequestParam Set<String> fields) {
		try {
			
			FindDTO user = userService.getUser(id);
			
			FilterProvider filterProvider = new SimpleFilterProvider()
					.addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
			
		    MappingJacksonValue mapper = new MappingJacksonValue(user);
		    mapper.setFilters(filterProvider);
			
			return ResponseEntity.ok(mapper);
			
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	

}
