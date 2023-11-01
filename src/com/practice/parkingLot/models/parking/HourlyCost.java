package com.practice.parkingLot.models.parking;

import java.util.HashMap;
import java.util.Map;


public class HourlyCost {
	
	private Map<ParkingSpotType, Double> hourlyCost = new HashMap<>();
	
	public HourlyCost() {
		hourlyCost.put(ParkingSpotType.CAR, 25.0);
		hourlyCost.put(ParkingSpotType.LARGE, 30.0);
		hourlyCost.put(ParkingSpotType.BIKE, 20.0);
		hourlyCost.put(ParkingSpotType.ELECTRIC, 25.0);
		hourlyCost.put(ParkingSpotType.EBIKE, 20.0);
		hourlyCost.put(ParkingSpotType.HANDICAPPED, 25.0);
	}
	
	public double getCost(ParkingSpotType spotType) {
		return hourlyCost.get(spotType);
	}

	public Map<ParkingSpotType, Double> getHourlyCost() {
		return hourlyCost;
	}

	public void setHourlyCost(Map<ParkingSpotType, Double> hourlyCost) {
		this.hourlyCost = hourlyCost;
	}

}
