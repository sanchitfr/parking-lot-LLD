package com.practice.parkingLot.models.account;

import com.practice.parkingLot.models.parking.EntrancePanel;
import com.practice.parkingLot.models.parking.ExitPanel;
import com.practice.parkingLot.models.parking.ParkingFloor;
import com.practice.parkingLot.models.parking.ParkingLot;
import com.practice.parkingLot.models.parking.ParkingSpot;
import com.practice.parkingLot.models.parking.ParkingSpotType;

public class Admin extends Account {
	
	public void addParkingFloor(ParkingFloor parkingFloor) {
		String parkingFloorId = parkingFloor.getFloorId();
		
		boolean parkingFloorExists = false;
		
		for(ParkingFloor floor : ParkingLot.INSTANCE.getParkingFloors()) {
			if(floor.getFloorId() == parkingFloorId) {
				parkingFloorExists = true;
				System.out.println("Parking floor with given ID already exists.");
				return;
			}
		}
		ParkingLot.INSTANCE.getParkingFloors().add(parkingFloor);
		System.out.println("Parking floor with ID " + parkingFloorId + " has been added.");
	}
	
	public void addParkingSpot(String parkingFloorId, ParkingSpot parkingSpot) {
		boolean parkingFloorExists = false;
		ParkingFloor parkingFloor = null;
		for(ParkingFloor floor : ParkingLot.INSTANCE.getParkingFloors()) {
			if(floor.getFloorId() == parkingFloorId) {
				parkingFloorExists = true;
				parkingFloor = floor;
				break;
			}
		}
		if(!parkingFloorExists) {
			System.out.println("Parking floor with given ID does not exist");
		}
		else if(parkingFloor != null) {
			boolean parkingSpotExists = false;
			
			for(ParkingSpot spot : parkingFloor.getParkingSpots().get(parkingSpot.getParkingSpotType())) {
				if(spot.getParkingSpotId() == parkingSpot.getParkingSpotId()) {
					parkingSpotExists = true;
					break;
				}
			}
			if(parkingSpotExists) System.out.println("Parking spot with given ID already exists on this floor!");
			else parkingFloor.getParkingSpots().get(parkingSpot.getParkingSpotType()).addLast(parkingSpot);
		}
	}
	
	public void addEntrancePanel(EntrancePanel entrancePanel) {
		boolean entrancePanelExists = false;
		String entrancePanelId = entrancePanel.getEntrancePanelId();
		for(EntrancePanel entrance : ParkingLot.INSTANCE.getEntrancePanels()) {
			if(entrance.getEntrancePanelId() == entrancePanelId) {
				entrancePanelExists = true;
				System.out.println("Entrance panel with the given ID already exists");
				return;
			}
		}
		ParkingLot.INSTANCE.getEntrancePanels().add(entrancePanel);
		System.out.println("Entrance panel with ID " + entrancePanelId +" has been added.");
	}
	
	public void addExitPanel(ExitPanel exitPanel) {
		boolean exitPanelExists = false;
		String exitPanelId = exitPanel.getId();
		for(ExitPanel exit : ParkingLot.INSTANCE.getExitPanels()) {
			if(exit.getId() == exitPanelId) {
				exitPanelExists = true;
				System.out.println("Exit panel with the given ID already exists");
				return;
			}
		}
		ParkingLot.INSTANCE.getExitPanels().add(exitPanel);
		System.out.println("Exit panel with ID " + exitPanelId +" has been added.");
	}

}
