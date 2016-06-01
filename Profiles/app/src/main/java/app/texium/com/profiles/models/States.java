package app.texium.com.profiles.models;

/**
 * Created by texiumuser on 01/06/2016.
 */
public class States {

    private Integer idItem;
    private Integer idState;
    private String stateName;
    private String acronymName;
    private Integer idStatus;

    public States() {

    }

    public States(Integer _idItem, Integer _idState, String _stateName, String _acronymName
            , Integer _idStatus){
        this.idItem = _idItem;
        this.idState = _idState;
        this.stateName = _stateName;
        this.acronymName = _acronymName;
        this.idStatus = _idStatus;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
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

    public String getAcronymName() {
        return acronymName;
    }

    public void setAcronymName(String acronymName) {
        this.acronymName = acronymName;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }
}
