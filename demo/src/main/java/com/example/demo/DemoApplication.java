package com.example.demo;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	ApplicationRunner init(FlightRepository repository) {

		List<Flight> flights = Arrays.asList(
			new Flight("A1", "Paris", "Marrakech", LocalDateTime.of(2018, 01, 01, 10, 50), LocalDateTime.of(2018, 01, 01, 13, 0), "Luke", "UH-60", "Royal Air Maroc"),
			new Flight("A2", "Paris", "Londre", LocalDateTime.of(2018, 01, 01, 10, 50), LocalDateTime.of(2018, 01, 01, 11, 20), "Ron", "P-59", "Air France"),
			new Flight("A3", "Paris", "Berlin", LocalDateTime.of(2018, 01, 01, 10, 50), LocalDateTime.of(2018, 01, 01, 11, 40), "Douglas", "Z-506", "Air France"),
			new Flight("A4", "Paris", "Barcelone", LocalDateTime.of(2018, 01, 01, 10, 50), LocalDateTime.of(2018, 01, 01, 12, 10), "Pablo", "HU-16", "Lufthansa"),
			new Flight("A5", "Paris", "Miami", LocalDateTime.of(2018, 01, 01, 10, 50), LocalDateTime.of(2018, 01, 01, 16, 30), "John", "L-159", "American Airlines")
		);

		return args -> {
			flights.stream().forEach(flight -> repository.save(flight));
			repository.findAll().forEach(System.out::println);
		};
	}
}
