package com.example.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FlightController {

	private FlightRepository repository;

	public FlightController(FlightRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping()
    public Collection<Flight> getAll() {
        return this.repository.findAll();
    }

	@GetMapping(value = "/{reference}")
	public Flight getOne(@PathVariable("reference") String reference) {
		System.out.println("GET ONE !!!");
		return this.repository.findByReference(reference);
	}
	
	@PostMapping()
	public Collection<Flight> addFlight(@RequestBody Flight flight) {
		
		System.out.println("POST !!!");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		
		boolean flightAlreadyExists = false;

		for(Flight f: this.repository.findAll()) {
			if(f.getReference().equals(flight.getReference())) {
				flightAlreadyExists = true;
			}
		}
		
		if(!flightAlreadyExists) {
			Flight newFlight = new Flight();
			newFlight.setReference(flight.getReference());
			newFlight.setDepartureCity(flight.getDepartureCity());
			newFlight.setDestinationCity(flight.getDestinationCity());
			//newFlight.setDepartureDate(LocalDateTime.of(2018, 01, 01, 10, 50));
			newFlight.setDestinationDate(LocalDateTime.of(2018, 01, 01, 10, 50));
			newFlight.setDepartureDate(flight.getDepartureDate());
			//newFlight.setDestinationDate(flight.getDestinationDate());
			newFlight.setPilotName(flight.getPilotName());
			newFlight.setAirplaneName(flight.getAirplaneName());
			newFlight.setCompanyName(flight.getCompanyName());
			this.repository.save(newFlight);
		}
		
		return this.repository.findAll();
	}
	
	@PutMapping()
	public void putFlight(@RequestBody Flight flight) {
		
		System.out.println("PUT !!!");

		for(Flight f: this.repository.findAll()) {
			if(f.getReference().equals(flight.getReference())) {
				System.out.println("On a trouver le bon : " + flight.getReference() + ", " + flight.getDepartureCity());
				f.setReference(flight.getReference());
				f.setDepartureCity(flight.getDepartureCity());
				f.setDestinationCity(flight.getDestinationCity());
				f.setDepartureDate(flight.getDepartureDate());
				f.setDestinationDate(flight.getDestinationDate());
				f.setPilotName(flight.getPilotName());
				f.setAirplaneName(flight.getAirplaneName());
				f.setCompanyName(flight.getCompanyName());
				System.out.println("finalement => " + f);
				this.repository.save(f);
			}
		}
	}
	
	@DeleteMapping(value = "/{reference}")
	public void deleteFlight(@PathVariable("reference") String reference) {
		System.out.println("DELETE !!!");
		this.repository.delete(this.repository.findByReference(reference));
	}
}
