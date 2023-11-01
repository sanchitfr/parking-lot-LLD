package com.practice.parkingLot.models.vehicle;

public abstract class Vehicle {
	private String licenseNumber;
	private final VehicleType vehicleType;
//	private ParkingTicket parkingTicket;
	
	public Vehicle(String licenseNum, VehicleType vehicleType) {
		this.licenseNumber = licenseNum;
		this.vehicleType = vehicleType;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}


	public VehicleType getVehicleType() {
		return vehicleType;
	}
}
