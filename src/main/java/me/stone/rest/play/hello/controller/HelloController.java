package me.stone.rest.play.hello.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import me.stone.rest.play.hello.payload.UserDetails;

@RestController
public class HelloController {
	
	@Autowired
	private ReloadableResourceBundleMessageSource messageSource;

	@GetMapping(path = "/helloworld")
	public String helloWorld() {
		return "Hello World1";
	}

	@GetMapping(path = "/helloworld-payload")
	public UserDetails helloWorldPayload() {
		return new UserDetails("Lee", "YuPyeong", "SeongNam");
	}

	@GetMapping("/i18n")
	public String getMessage(@RequestHeader(name = "Accept-Language", required = false) String locale) {
		return messageSource.getMessage("label.welcome", null, new Locale(locale));
	}
	
	@GetMapping("/i18n2")
	public String getMessage2() {
		return messageSource.getMessage("label.welcome", null, LocaleContextHolder.getLocale());
	}

}
