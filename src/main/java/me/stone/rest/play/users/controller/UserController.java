package me.stone.rest.play.users.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import me.stone.rest.play.common.exception.UserExistsException;
import me.stone.rest.play.common.exception.UserNotFoundException;
import me.stone.rest.play.common.exception.UsernameNotFoundException;
import me.stone.rest.play.users.payload.UserReq.CreateDTO;
import me.stone.rest.play.users.payload.UserReq.UpdateDTO;
import me.stone.rest.play.users.service.UserService;

@Api(tags = "User Management RESTful Services", value = "UserController")
@RequiredArgsConstructor
@Validated // @Min(1) for method parameter validation not bean validation
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final UserService userService;
    
    
    @ApiOperation(value = "Retrieve list of users")
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    
    @ApiOperation(value = "Create new user")
    @PostMapping
    public ResponseEntity<?> createUser(@ApiParam("User information for a new user to be created.") 
        @Valid @RequestBody CreateDTO dto, UriComponentsBuilder builder) {
        
        try {
            Long id = userService.createUser(dto.toEntity());
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(builder.path("/users/{id}").buildAndExpand(id).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (UserExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") @Min(1) Long id) {
        try {
            return ResponseEntity.ok(userService.getUser(id));
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable("id") Long id, @RequestBody UpdateDTO dto) {
        try {
            return ResponseEntity.ok(userService.updateUser(id, dto));
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(userService.deleteUser(id));
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    
    @GetMapping(path = "/username/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable("username") String username) throws UsernameNotFoundException {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }
    

}
