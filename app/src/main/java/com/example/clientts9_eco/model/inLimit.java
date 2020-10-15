package com.example.clientts9_eco.model;

import java.util.UUID;

public class inLimit {
	
	String type,id;
	boolean full;
	
	
	public inLimit() {
		
	}
	
	public inLimit(boolean full) {
		this.full = full;
		id = UUID.randomUUID().toString();
		type = "inLimit";
	}
	
	
}
