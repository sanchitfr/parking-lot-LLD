package com.practice.parkingLot.models.parking;

import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;

import com.practice.parkingLot.models.vehicle.VehicleType;

import java.util.HashMap;
import java.util.BitSet;
import java.util.Deque;

public class ParkingFloor {
	
	private String floorId;
	
	private Map<ParkingSpotType, Deque<ParkingSpot>> parkingSpots = new HashMap<>();
	private Map<String, ParkingSpot> usedParkingSpots = new HashMap<>();
	
	public ParkingFloor(String id) {
		this.floorId = id;
		parkingSpots.put(ParkingSpotType.HANDICAPPED, new ConcurrentLinkedDeque<>());
		parkingSpots.put(ParkingSpotType.CAR, new ConcurrentLinkedDeque<>());
		parkingSpots.put(ParkingSpotType.BIKE, new ConcurrentLinkedDeque<>());
		parkingSpots.put(ParkingSpotType.ELECTRIC, new ConcurrentLinkedDeque<>());
		parkingSpots.put(ParkingSpotType.LARGE, new ConcurrentLinkedDeque<>());
		parkingSpots.put(ParkingSpotType.EBIKE, new ConcurrentLinkedDeque<>());
	}

	public String getFloorId() {
		return floorId;
	}

	public Map<ParkingSpotType, Deque<ParkingSpot>> getParkingSpots() {
		return parkingSpots;
	}

	public void setParkingSpots(Map<ParkingSpotType, Deque<ParkingSpot>> parkingSpots) {
		this.parkingSpots = parkingSpots;
	}

	public Map<String, ParkingSpot> getUsedParkingSpots() {
		return usedParkingSpots;
	}

	public void setUsedParkingSpots(Map<String, ParkingSpot> usedParkingSpots) {
		this.usedParkingSpots = usedParkingSpots;
	}
	
	public boolean isFloorFull() {
		BitSet fullBitSet = new BitSet();
		int index = 0;
		for(Map.Entry<ParkingSpotType, Deque<ParkingSpot>> entry : parkingSpots.entrySet()) {
			if(entry.getValue().size() == 0) {
				fullBitSet.set(index++);
			}else break;
		}
		return fullBitSet.cardinality() == fullBitSet.size();
	}
	
	public ParkingSpotType getSpotTypeForVehicleType(VehicleType vehicleType) {
		switch(vehicleType) {
		case CAR:
			return ParkingSpotType.CAR;
		case BIKE:
			return ParkingSpotType.BIKE;
		case EBIKE:
			return ParkingSpotType.EBIKE;
		case ECAR:
			return ParkingSpotType.ELECTRIC;
		case TRUCK:
			return ParkingSpotType.LARGE;
		case VAN:
			return ParkingSpotType.LARGE;
		default:
			return ParkingSpotType.LARGE;
		}	
	}
	
	public synchronized ParkingSpot getSpot(VehicleType vehicleType) {
		if(!canPark(getSpotTypeForVehicleType(vehicleType))) {
			return null;
		}
		ParkingSpotType parkingSpotType = getSpotTypeForVehicleType(vehicleType);
		
		ParkingSpot parkingSpot = parkingSpots.get(parkingSpotType).poll();
		
		usedParkingSpots.put(parkingSpot.getParkingSpotId(), parkingSpot);
		
		return parkingSpot;
	}
	
	public ParkingSpot vacateSpot(String parkingSpotId) {
		ParkingSpot parkingSpot = usedParkingSpots.get(parkingSpotId);
		if(parkingSpot != null) {
			parkingSpot.freeSpot();
			parkingSpots.get(parkingSpot.getParkingSpotType()).addFirst(parkingSpot);
		}
		return parkingSpot;
	}
	
	public boolean canPark(ParkingSpotType parkingSpotType) {
		return parkingSpots.get(parkingSpotType).size() > 0;
	}

}
