package app.texium.com.profiles.models;

/**
 * Created by texiumuser on 02/06/2016.
 */
public class PoliticalParties {

    private Integer idItem;
    private Integer idPP;
    private String name;
    private String acronymName;
    private Integer idStatus;

    public  PoliticalParties() {

    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getIdPP() {
        return idPP;
    }

    public void setIdPP(Integer idPP) {
        this.idPP = idPP;
    }
}
