package com.practice.parkingLot.models.parking;

public abstract class ParkingSpot {
	private String parkingSpotId;
	private ParkingSpotType parkingSpotType;
	private boolean isFree;
	private String assignedVehicleId;
	
	public ParkingSpot(String id, ParkingSpotType spotType) {
		this.parkingSpotId = id;
		this.parkingSpotType = spotType;
	}

	public ParkingSpotType getParkingSpotType() {
		return parkingSpotType;
	}

	public String getParkingSpotId() {
		return parkingSpotId;
	}
	
    public void assignVehicleToSpot(String vehicleId) {
        this.assignedVehicleId = vehicleId;
    }

	public void freeSpot() {
		this.isFree = true;
		this.assignedVehicleId = null;
		
		
	}
}
