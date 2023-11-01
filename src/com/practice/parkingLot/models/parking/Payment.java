package com.practice.parkingLot.models.parking;

import java.time.LocalDateTime;

public class Payment {
	
	private String id;
	private String ticketId;
	private double amount;
	
	private LocalDateTime initiatedDate;
	private LocalDateTime completedData;
	private String paymentStatus;
	
	public Payment(String id, String ticketId, double amount) {
		this.id = id;
		this.ticketId = ticketId;
		this.amount = amount;
	}

}
