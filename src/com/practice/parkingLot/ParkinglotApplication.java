package com.practice.parkingLot;

import java.util.UUID;

import com.practice.parkingLot.models.account.Account;
import com.practice.parkingLot.models.account.Address;
import com.practice.parkingLot.models.account.Admin;
import com.practice.parkingLot.models.parking.BikeParkingSpot;
import com.practice.parkingLot.models.parking.CarParkingSpot;
import com.practice.parkingLot.models.parking.EntrancePanel;
import com.practice.parkingLot.models.parking.ExitPanel;
import com.practice.parkingLot.models.parking.ParkingFloor;
import com.practice.parkingLot.models.parking.ParkingLot;
import com.practice.parkingLot.models.parking.ParkingSpot;
import com.practice.parkingLot.models.parking.ParkingSpotType;
import com.practice.parkingLot.models.parking.ParkingTicket;
import com.practice.parkingLot.models.vehicle.Bike;
import com.practice.parkingLot.models.vehicle.Car;
import com.practice.parkingLot.models.vehicle.Van;
import com.practice.parkingLot.models.vehicle.Vehicle;
import com.practice.parkingLot.models.vehicle.VehicleType;

public class ParkinglotApplication {
    public static void main(String[] args) {
        ParkingLot parkingLot = ParkingLot.INSTANCE;

        Address address = new Address();
        address.setAddress1("Ram parking Complex");
        address.setStreet("BG Road");
        address.setCity("Bangalore");
        address.setState("Karnataka");
        address.setCountry("India");
        address.setPinCode("560075");

//        parkingLot.setAddress(address);
        //Admin tests
        Account adminAccount = new Admin();
        //Admin Case 1 - should be able to add parking floor case
        ((Admin) adminAccount).addParkingFloor(new ParkingFloor("1"));
        //Admin Case 2 - should be able to add parking floor case
        ((Admin) adminAccount).addParkingFloor(new ParkingFloor("2"));

        //Admin Case 3 - should be able to add entrance panel
        EntrancePanel entrancePanel = new EntrancePanel("1");
        ((Admin) adminAccount).addEntrancePanel(entrancePanel);

        //Admin Case 4 - should be able to add exit panel
        ExitPanel exitPanel = new ExitPanel("1");
        ((Admin) adminAccount).addExitPanel(exitPanel);

        String floorId = parkingLot.getParkingFloors().get(0).getFloorId();

        ///Admin case 5 - should be able to add car parking spot
        ParkingSpot carSpot1 = new CarParkingSpot("c1");
        ((Admin) adminAccount).addParkingSpot(floorId, carSpot1);
        ///Admin case 6 - should be able to add bike parking spot
        ParkingSpot bikeSport = new BikeParkingSpot("b1");
        ((Admin) adminAccount).addParkingSpot(floorId, bikeSport);
        ///Admin case 7 - should be able to add car parking spot
        ParkingSpot carSpot2 = new CarParkingSpot("c2");
        ((Admin) adminAccount).addParkingSpot(floorId, carSpot2);

        // Test case 1 - check for availability of parking lot - TRUE
        System.out.println(ParkingLot.INSTANCE.canPark(VehicleType.CAR));

        // Test case 2 - check for availability of parking lot - FALSE
        System.out.println(ParkingLot.INSTANCE.canPark(VehicleType.EBIKE));

        // Test case 3 - check for availability of parking lot - FALSE
        System.out.println(ParkingLot.INSTANCE.canPark(VehicleType.ECAR));

        // TEST case 4 - Check if full
        System.out.println(ParkingLot.INSTANCE.isFull());

        // Test case 5 - get parking spot
        Vehicle vehicle = new Car("KA05MR2311");
        ParkingSpot availableSpot = ParkingLot.INSTANCE.getParkingSpot(vehicle.getVehicleType());
        System.out.println(availableSpot.getParkingSpotType());
        System.out.println(availableSpot.getParkingSpotId());

        // Test case 6 - should not be able to get spot
        Vehicle van = new Van("KA01MR7804");
        ParkingSpot vanSpot = ParkingLot.INSTANCE.getParkingSpot(van.getVehicleType());
        System.out.println(null == vanSpot);

        //Test case 7 - Entrance Panel - 1
        System.out.println(ParkingLot.INSTANCE.getEntrancePanels().size());

        // Test case - 8 - Should be able to get parking ticket
        ParkingTicket parkingTicket = entrancePanel.getParkingTicket(vehicle);
        System.out.println(parkingTicket.getAllocatedSpotId());

        ((Admin) adminAccount).addParkingSpot(floorId, carSpot1);
        // Test case - 9 - Should be able to get parking ticket
        Vehicle car = new Car("KA02MR6355");
        ParkingTicket parkingTicket1 = entrancePanel.getParkingTicket(car);

        // Test case 10 - Should not be able to get ticket
        ParkingTicket tkt = entrancePanel.getParkingTicket(new Car("ka04rb8458"));
        System.out.println(null == tkt);

        // Test case 11 - Should be able to get ticket
        ParkingTicket mtrTkt = entrancePanel.getParkingTicket(new Bike("ka01ee4901"));
        System.out.println(mtrTkt.getAllocatedSpotId());

        //Test case 12 - vacate parking spot
        mtrTkt = exitPanel.scanAndVacate(mtrTkt);
        System.out.println(mtrTkt.getCharges());
        System.out.println(mtrTkt.getCharges() > 0);

        // Test case 13 - park on vacated spot
        ParkingTicket mtrTkt1 = entrancePanel.getParkingTicket(new Bike("ka01ee7791"));
        System.out.println(mtrTkt.getAllocatedSpotId());

        // Test case 14 - park when spot is not available
        ParkingTicket unavaialbemTkt =
                entrancePanel.getParkingTicket(new Bike("ka01ee4455"));
        System.out.println(null == unavaialbemTkt);

        // Test cast 15 - vacate car
        parkingTicket = exitPanel.scanAndVacate(parkingTicket);
        System.out.println(parkingTicket.getCharges());
        System.out.println(parkingTicket.getCharges() > 0);

        //Test case 16 - Now should be able to park car
        System.out.println(ParkingLot.INSTANCE.canPark(VehicleType.CAR));

        //Test case 17 - Should be able to vacate parked vehicle
        parkingTicket1 = exitPanel.scanAndVacate(parkingTicket1);
        System.out.println(parkingTicket1.getCharges());
        System.out.println(parkingTicket1.getCharges() > 0);

        //Test case 18 - check for slots count
        System.out.println(ParkingLot.INSTANCE.getParkingFloors()
                .get(0).getParkingSpots().get(ParkingSpotType.CAR).size());

        //Test case 19 - Payment
//        Payment payment = new Payment(UUID.randomUUID().toString(),
//                parkingTicket1.getId(), parkingTicket1.getCharges());
//        payment.makePayment();
//        System.out.println(payment.getPaymentStatus());

        //Test case 20 - vacate motorbike spot
        mtrTkt = exitPanel.scanAndVacate(mtrTkt);
        System.out.println(ParkingLot.INSTANCE.getParkingFloors()
                .get(0).getParkingSpots().get(ParkingSpotType.BIKE).size());
        System.out.println(mtrTkt.getCharges());
    }
}
