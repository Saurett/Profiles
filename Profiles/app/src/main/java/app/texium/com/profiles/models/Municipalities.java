package app.texium.com.profiles.models;

/**
 * Created by texiumuser on 01/06/2016.
 */
public class Municipalities {

    private Integer idItem;
    private Integer idMunicipal;
    private String municipalName;
    private Integer idState;
    private String stateName;
    private String stateAcronym;
    private Integer idStatus;

    public Municipalities() {

    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
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

    public void setIdstatus(Integer idStatus) {
        this.idStatus = idStatus;
    }
}
