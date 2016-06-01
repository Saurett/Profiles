package app.texium.com.profiles.models;

/**
 * Created by texiumuser on 01/06/2016.
 */
public class Locations {

    private Integer idItem;
    private Integer idLocation;
    private String locationName;
    private Integer idMunicipal;
    private String municipalName;
    private Integer idState;
    private String stateName;
    private String stateAcronym;
    private Integer idStatus;

    public Locations() {

    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(Integer idLocation) {
        this.idLocation = idLocation;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Integer getIdMunicipal() {
        return idMunicipal;
    }

    public void setIdMunicipal(Integer idMunicipal) {
        this.idMunicipal = idMunicipal;
    }

    public String getMunicipalName() {
        return municipalName;
    }

    public void setMunicipalName(String municipalName) {
        this.municipalName = municipalName;
    }

    public Integer getIdState() {
        return idState;
    }

    public void setIdState(Integer idState) {
        this.idState = idState;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateAcronym() {
        return stateAcronym;
    }

    public void setStateAcronym(String stateAcronym) {
        this.stateAcronym = stateAcronym;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }
}
