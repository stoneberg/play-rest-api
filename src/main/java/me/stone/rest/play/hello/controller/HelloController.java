package me.stone.rest.play.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import me.stone.rest.play.hello.payload.UserDetails;

@RestController
public class HelloController {
	
	@GetMapping(path = "/helloworld")
	public String helloWorld() {
		return "Hello World1";
	}
	
	@GetMapping(path="/helloworld-payload")
	public UserDetails helloWorldPayload() {
		return new UserDetails("Lee", "YuPyeong", "SeongNam");
	}

}
