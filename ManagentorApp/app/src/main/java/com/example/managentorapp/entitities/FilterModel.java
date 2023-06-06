package com.example.managentorapp.entitities;



import java.io.Serializable;

public class FilterModel implements Serializable {

	private Integer minPrice;
	private Integer maxPrice;
	private Integer minSquareMeters;
	private Integer maxSquareMeters;
	private Integer bedroomNumber;
	private Integer bathroomNumber;

	private boolean mascaros;

	private boolean menoscaros;

	private boolean ascensor;

	private String tipo;


	public FilterModel() {

	}

	public FilterModel(Integer minPrice, Integer maxPrice, Integer minSquareMeters, Integer maxSquareMeters, Integer bedroomNumber, Integer bathroomNumber, boolean mascaros, boolean menoscaros, boolean ascensor, String tipo) {
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.minSquareMeters = minSquareMeters;
		this.maxSquareMeters = maxSquareMeters;
		this.bedroomNumber = bedroomNumber;
		this.bathroomNumber = bathroomNumber;

		this.mascaros = mascaros;
		this.menoscaros = menoscaros;
		this.ascensor = ascensor;
		this.tipo = tipo;
	}

	public Integer getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Integer minPrice) {
		this.minPrice = minPrice;
	}

	public Integer getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Integer maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Integer getMinSquareMeters() {
		return minSquareMeters;
	}

	public void setMinSquareMeters(Integer minSquareMeters) {
		this.minSquareMeters = minSquareMeters;
	}

	public Integer getMaxSquareMeters() {
		return maxSquareMeters;
	}

	public void setMaxSquareMeters(Integer maxSquareMeters) {
		this.maxSquareMeters = maxSquareMeters;
	}

	public Integer getBedroomNumber() {
		return bedroomNumber;
	}

	public void setBedroomNumber(Integer bedroomNumber) {
		this.bedroomNumber = bedroomNumber;
	}

	public Integer getBathroomNumber() {
		return bathroomNumber;
	}

	public void setBathroomNumber(Integer bathroomNumber) {
		this.bathroomNumber = bathroomNumber;
	}



	public boolean isMascaros() {
		return mascaros;
	}

	public void setMascaros(boolean mascaros) {
		this.mascaros = mascaros;
	}

	public boolean isMenoscaros() {
		return menoscaros;
	}

	public void setMenoscaros(boolean menoscaros) {
		this.menoscaros = menoscaros;
	}

	public boolean isAscensor() {
		return ascensor;
	}

	public void setAscensor(boolean ascensor) {
		this.ascensor = ascensor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
