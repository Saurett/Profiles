package app.texium.com.profiles.models;

import java.io.Serializable;

/**
 * Created by texiumuser on 02/06/2016.
 */
public class AddressProfile implements Serializable {

    private Integer idState;
    private Integer idItemState;
    private Integer idMunicipal;
    private Integer idItemMunicipal;
    private Integer idLocation;
    private Integer idItemLocation;
    private String street;
    private String numExt;
    private String numInt;
    private String city;
    private String division;
    private Integer postalCode;

    public AddressProfile() {

    }

    public Integer getIdState() {
        return idState;
    }

    public void setIdState(Integer idState) {
        this.idState = idState;
    }

    public Integer getIdMunicipal() {
        return idMunicipal;
    }

    public void setIdMunicipal(Integer idMunicipal) {
        this.idMunicipal = idMunicipal;
    }

    public Integer getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(Integer idLocation) {
        this.idLocation = idLocation;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumExt() {
        return numExt;
    }

    public void setNumExt(String numExt) {
        this.numExt = numExt;
    }

    public String getNumInt() {
        return numInt;
    }

    public void setNumInt(String numInt) {
        this.numInt = numInt;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public Integer getIdItemState() {
        return idItemState;
    }

    public void setIdItemState(Integer idItemState) {
        this.idItemState = idItemState;
    }

    public Integer getIdItemLocation() {
        return idItemLocation;
    }

    public void setIdItemLocation(Integer idItemLocation) {
        this.idItemLocation = idItemLocation;
    }

    public Integer getIdItemMunicipal() {
        return idItemMunicipal;
    }

    public void setIdItemMunicipal(Integer idItemMunicipal) {
        this.idItemMunicipal = idItemMunicipal;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }
}
