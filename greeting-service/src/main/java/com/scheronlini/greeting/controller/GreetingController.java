package com.scheronlini.greeting.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.scheronlini.greeting.configuration.GreetingConfig;
import com.scheronlini.greeting.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "%s, %s!";
	private final AtomicLong counter = new AtomicLong();

	@Autowired
	private GreetingConfig greetingConfig;
	
	@RequestMapping("/greeting")
	public Greeting greeting(
			@RequestParam(value="name",
			defaultValue = "World") String name) {
		if (name.isEmpty()) name = greetingConfig.getDefaultValue();

		return new Greeting(
					counter.incrementAndGet(),
					String.format(template,greetingConfig.getGreeting(), name)
				);
	}
}
