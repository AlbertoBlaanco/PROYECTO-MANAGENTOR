package com.example.managentorapp.entitities;

public class ClientPropertyInfo {

	private int clientId;
	private int propertyId;
	private String clientName;
	private String propertyAddress;

	public ClientPropertyInfo(int clientId, int propertyId,
                              String clientName, String propertyAddress) {
		this.clientId = clientId;
		this.propertyId = propertyId;
		this.clientName = clientName;
		this.propertyAddress = propertyAddress;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getPropertyAddress() {
		return propertyAddress;
	}

	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}
}
