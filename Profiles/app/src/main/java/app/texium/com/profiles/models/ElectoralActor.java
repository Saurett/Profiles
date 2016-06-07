package app.texium.com.profiles.models;

/**
 * Created by texiumuser on 02/06/2016.
 */
public class ElectoralActor {

    private Integer idItem;
    private Integer idElectoralActor;
    private String name;

    public ElectoralActor() {

    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getIdElectoralActor() {
        return idElectoralActor;
    }

    public void setIdElectoralActor(Integer idElectoralActor) {
        this.idElectoralActor = idElectoralActor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
