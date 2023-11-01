package com.practice.parkingLot.models.parking;

import java.time.LocalDateTime;

public class ParkingTicket {
	private String id;
	private String licensePlateNumber;
	private LocalDateTime issuedAt;
	private LocalDateTime vacatedAt;
	private String allocatedSpotId;
	private double charges;
	private TicketStatus ticketStatus;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLicensePlateNumber() {
		return licensePlateNumber;
	}
	public void setLicensePlateNumber(String licensePlateNumber) {
		this.licensePlateNumber = licensePlateNumber;
	}
	public LocalDateTime getIssuedAt() {
		return issuedAt;
	}
	public void setIssuedAt(LocalDateTime issuedAt) {
		this.issuedAt = issuedAt;
	}
	public LocalDateTime getVacatedAt() {
		return vacatedAt;
	}
	public void setVacatedAt(LocalDateTime vacatedAt) {
		this.vacatedAt = vacatedAt;
	}
	public String getAllocatedSpotId() {
		return allocatedSpotId;
	}
	public void setAllocatedSpotId(String allocatedSpotId) {
		this.allocatedSpotId = allocatedSpotId;
	}
	public double getCharges() {
		return charges;
	}
	public void setCharges(double charges) {
		this.charges = charges;
	}
	public TicketStatus getTicketStatus() {
		return ticketStatus;
	}
	public void setTicketStatus(TicketStatus ticketStatus) {
		this.ticketStatus = ticketStatus;
	}
}
