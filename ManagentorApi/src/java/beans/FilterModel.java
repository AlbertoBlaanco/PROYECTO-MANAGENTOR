package beans;

import java.util.ArrayList;

/**
 *
 * @author alber
 */
public class FilterModel {

    private boolean ascensor;
    private Integer bedroomNumber;
    private boolean mascaros;
    private Integer maxPrice;
    private Integer maxSquareMeters;
    private boolean menoscaros;
    private Integer minPrice;
    private Integer minSquareMeters;
    private Integer bathroomNumber;
    private String tipo;
    
    
    

    
    

    public FilterModel() {
    }

    public FilterModel(boolean ascensor, Integer bedroomNumber, boolean mascaros, Integer maxPrice, Integer maxSquareMeters, boolean menoscaros, Integer minPrice, Integer minSquareMeters, Integer bathroomNumber, String tipo) {
        this.ascensor = ascensor;
        this.bedroomNumber = bedroomNumber;
        this.mascaros = mascaros;
        this.maxPrice = maxPrice;
        this.maxSquareMeters = maxSquareMeters;
        this.menoscaros = menoscaros;
        this.minPrice = minPrice;
        this.minSquareMeters = minSquareMeters;
        this.bathroomNumber = bathroomNumber;
        this.tipo = tipo;
    }

    public boolean isAscensor() {
        return ascensor;
    }

    public void setAscensor(boolean ascensor) {
        this.ascensor = ascensor;
    }

    public Integer getBedroomNumber() {
        return bedroomNumber;
    }

    public void setBedroomNumber(Integer bedroomNumber) {
        this.bedroomNumber = bedroomNumber;
    }

    public boolean isMascaros() {
        return mascaros;
    }

    public void setMascaros(boolean mascaros) {
        this.mascaros = mascaros;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getMaxSquareMeters() {
        return maxSquareMeters;
    }

    public void setMaxSquareMeters(Integer maxSquareMeters) {
        this.maxSquareMeters = maxSquareMeters;
    }

    public boolean isMenoscaros() {
        return menoscaros;
    }

    public void setMenoscaros(boolean menoscaros) {
        this.menoscaros = menoscaros;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMinSquareMeters() {
        return minSquareMeters;
    }

    public void setMinSquareMeters(Integer minSquareMeters) {
        this.minSquareMeters = minSquareMeters;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getBathroomNumber() {
        return bathroomNumber;
    }

    public void setBathroomNumber(Integer bathroomNumber) {
        this.bathroomNumber = bathroomNumber;
    }
 
}
