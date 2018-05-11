package com.example.demo;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Entity
public class Flight {
	
	@Id
	private String reference;
    private String departureCity;
    private String destinationCity;
    private LocalDateTime departureDate;
    private LocalDateTime destinationDate;
    private String pilotName;
    private String airplaneName;
    private String companyName;
    
    public Flight() {}
    
	public Flight(String reference, String departureCity, String destinationCity, LocalDateTime departureDate,
			LocalDateTime destinationDate, String pilotName, String airplaneName, String companyName) {
		super();
		this.reference = reference;
		this.departureCity = departureCity;
		this.destinationCity = destinationCity;
		this.departureDate = departureDate;
		this.destinationDate = destinationDate;
		this.pilotName = pilotName;
		this.airplaneName = airplaneName;
		this.companyName = companyName;
	}
	
	/*@PrePersist
	private void ensureId(){
	    this.setReference(UUID.randomUUID().toString());
	}*/

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getDepartureCity() {
		return departureCity;
	}

	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}

	public String getDestinationCity() {
		return destinationCity;
	}

	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

	public LocalDateTime getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDateTime departureDate) {
		this.departureDate = departureDate;
	}

	public LocalDateTime getDestinationDate() {
		return destinationDate;
	}

	public void setDestinationDate(LocalDateTime destinationDate) {
		this.destinationDate = destinationDate;
	}

	public String getPilotName() {
		return pilotName;
	}

	public void setPilotName(String pilotName) {
		this.pilotName = pilotName;
	}

	public String getAirplaneName() {
		return airplaneName;
	}

	public void setAirplaneName(String airplaneName) {
		this.airplaneName = airplaneName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "Flight [reference=" + reference + ", departureCity=" + departureCity
				+ ", destinationCity=" + destinationCity + ", departureDate=" + departureDate + ", destinationDate="
				+ destinationDate + ", pilotName=" + pilotName + ", airplaneName=" + airplaneName + ", companyName="
				+ companyName + "]";
	}

}
