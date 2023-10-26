package tech.hidetora.commandapi;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CommandApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommandApiApplication.class, args);
	}

	@Bean
	CommandBus commandBus() {
		return SimpleCommandBus.builder().build();
	}
}
