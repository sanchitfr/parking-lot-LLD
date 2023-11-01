package com.practice.parkingLot.models.parking;

import java.util.List;

import com.practice.parkingLot.models.vehicle.VehicleType;

import java.util.ArrayList;
import java.util.BitSet;

public class ParkingLot {
	List<ParkingFloor> parkingFloors;
	List<EntrancePanel> entrancePanels;
	List<ExitPanel> exitPanels;
	
	private ParkingLot() {
		parkingFloors = new ArrayList<ParkingFloor>();
		entrancePanels = new ArrayList<EntrancePanel>();
		exitPanels = new ArrayList<ExitPanel>();
	}
	
	public static ParkingLot INSTANCE = new ParkingLot();

	public List<ParkingFloor> getParkingFloors() {
		return parkingFloors;
	}

	public List<EntrancePanel> getEntrancePanels() {
		return entrancePanels;
	}

	public List<ExitPanel> getExitPanels() {
		return exitPanels;
	}
	
	public boolean isFull() {
		BitSet bitset = new BitSet();
		int index = 0;
		for(ParkingFloor parkingFloor : parkingFloors) {
			bitset.set(index++, parkingFloor.isFloorFull());
		}
		
		return bitset.cardinality() == parkingFloors.size();
	}
	
	public boolean canPark(VehicleType vehicleType) {
		for(ParkingFloor parkingFloor : parkingFloors) {
			if(parkingFloor.canPark(parkingFloor.getSpotTypeForVehicleType(vehicleType))) {
				return true;
			}
		}
		return false;
	}
	
	public ParkingSpot getParkingSpot(VehicleType vehicleType) {
		
		for(ParkingFloor parkingFloor : parkingFloors) {
			ParkingSpot parkingSpot = parkingFloor.getSpot(vehicleType);
			if(parkingSpot != null) return parkingSpot;
		}
		return null;
	}
	
	public ParkingSpot vacateParkingSpot(String parkingSpotId) {
		
		for(ParkingFloor parkingFloor : parkingFloors) {
			ParkingSpot parkingSpot = parkingFloor.vacateSpot(parkingSpotId);
			if(parkingSpot != null) return parkingSpot;
		}
		return null;
	}

}
