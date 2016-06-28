package app.texium.com.profiles.models;

/**
 * Created by texiumuser on 02/06/2016.
 */
public class ElectoralSections {

    private Integer idItem;
    private Integer idElectoralSection;
    private String localDistrict;

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getIdElectoralSection() {
        return idElectoralSection;
    }

    public void setIdElectoralSection(Integer idElectoralSection) {
        this.idElectoralSection = idElectoralSection;
    }

    public String getLocalDistrict() {
        return localDistrict;
    }

    public void setLocalDistrict(String localDistrict) {
        this.localDistrict = localDistrict;
    }
}
