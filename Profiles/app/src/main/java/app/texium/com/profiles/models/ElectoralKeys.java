package app.texium.com.profiles.models;

/**
 * Created by texiumuser on 02/06/2016.
 */
public class ElectoralKeys {

    private Integer idItem;
    private Integer idElectoralKey;
    private String electoralKey;
    private Integer idStatus;

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getIdElectoralKey() {
        return idElectoralKey;
    }

    public void setIdElectoralKey(Integer idElectoralKey) {
        this.idElectoralKey = idElectoralKey;
    }

    public String getElectoralKey() {
        return electoralKey;
    }

    public void setElectoralKey(String electoralKey) {
        this.electoralKey = electoralKey;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }
}
