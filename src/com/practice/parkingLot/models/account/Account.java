package com.practice.parkingLot.models.account;

import java.time.LocalDateTime;

public abstract class Account {
	private String name;
	private String email;
	private String password;
	private LocalDateTime lastAccessedAt;
	private Contact contact;
}
