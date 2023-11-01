package com.practice.parkingLot.models.parking;

import java.time.Duration;
import java.time.LocalDateTime;


public class ExitPanel {
	private String id;
	
	public ExitPanel(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
	public ParkingTicket scanAndVacate(ParkingTicket parkingTicket) {
		ParkingSpot parkingSpot = ParkingLot.INSTANCE.vacateParkingSpot(parkingTicket.getAllocatedSpotId());
		
		parkingTicket.setCharges(calculateCost(parkingTicket, parkingSpot));
		
		return parkingTicket;
	}
	
	public double calculateCost(ParkingTicket parkingTicket, ParkingSpot parkingSpot) {
		Duration duration = Duration.between(parkingTicket.getIssuedAt(), LocalDateTime.now());
		long hours = duration.toHours();
		if(hours == 0) hours = 1;
		double amount = hours * new HourlyCost().getCost(parkingSpot.getParkingSpotType());
		
		return amount;
	}

}
