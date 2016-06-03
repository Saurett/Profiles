package app.texium.com.profiles.models;

/**
 * Created by texiumuser on 02/06/2016.
 */
public class AcademyLevels {

    private Integer idItem;
    private Integer idAcademyLevel;
    private String description;
    private Integer idStatus;

    public AcademyLevels() {

    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public Integer getIdAcademyLevel() {
        return idAcademyLevel;
    }

    public void setIdAcademyLevel(Integer idAcademyLevel) {
        this.idAcademyLevel = idAcademyLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
