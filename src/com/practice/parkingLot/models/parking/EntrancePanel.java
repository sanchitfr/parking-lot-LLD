package com.practice.parkingLot.models.parking;

import java.time.LocalDateTime;
import java.util.UUID;

import com.practice.parkingLot.models.vehicle.Vehicle;
import com.practice.parkingLot.models.vehicle.VehicleType;

public class EntrancePanel {
	
	private String entrancePanelId;
	
	public EntrancePanel(String id){
		this.entrancePanelId = id;
	}

	public String getEntrancePanelId() {
		return entrancePanelId;
	}
	
	public ParkingTicket getParkingTicket(Vehicle vehicle) {
		ParkingSpot parkingSpot;
		VehicleType vehicleType= vehicle.getVehicleType();
		if(!ParkingLot.INSTANCE.canPark(vehicleType)) return null;
		else {
			parkingSpot = ParkingLot.INSTANCE.getParkingSpot(vehicleType);
			if(parkingSpot == null) return null;
		}
		return buildTicket(vehicle.getLicenseNumber(), parkingSpot.getParkingSpotId());
	}
	
	public ParkingTicket buildTicket(String license, String parkingSpotId) {
		ParkingTicket parkingTicket = new ParkingTicket();
		parkingTicket.setLicensePlateNumber(license);
		parkingTicket.setIssuedAt(LocalDateTime.now());
		parkingTicket.setAllocatedSpotId(parkingSpotId);
		parkingTicket.setId(UUID.randomUUID().toString());
		parkingTicket.setTicketStatus(TicketStatus.ACTIVE);
		return parkingTicket;
	}
}
