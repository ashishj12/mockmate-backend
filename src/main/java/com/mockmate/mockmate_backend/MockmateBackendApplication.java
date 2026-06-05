package com.mockmate.mockmate_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;

@SpringBootApplication
public class MockmateBackendApplication {

	public static void main(String[] args) {

		// load environment file
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

		// Set environment variables as system properties
		dotenv.entries().forEach((DotenvEntry entry) -> {
			System.setProperty(entry.getKey(), entry.getValue());
		});
		SpringApplication.run(MockmateBackendApplication.class, args);
	}

}
