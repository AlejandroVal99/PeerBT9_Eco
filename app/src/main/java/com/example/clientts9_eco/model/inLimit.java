package com.example.clientts9_eco.model;

import java.util.UUID;

public class inLimit {

	public inLimit() {

	}

	public inLimit(boolean full) {
		this.full = full;
		id = UUID.randomUUID().toString();
		type = "inLimit";
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isFull() {
		return full;
	}

	public void setFull(boolean full) {
		this.full = full;
	}

	String type,id;
	boolean full;
	
	

	
	
}
