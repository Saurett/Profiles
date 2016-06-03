package app.texium.com.profiles.models;

/**
 * Created by texiumuser on 02/06/2016.
 */
public class ProfessionalTitles {

    private Integer idItem;
    private Integer idProfessionalTitle;
    private String name;
    private Integer idStatus;

    public ProfessionalTitles() {

    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getIdProfessionalTitle() {
        return idProfessionalTitle;
    }

    public void setIdProfessionalTitle(Integer idProfessionalTitle) {
        this.idProfessionalTitle = idProfessionalTitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }
}
