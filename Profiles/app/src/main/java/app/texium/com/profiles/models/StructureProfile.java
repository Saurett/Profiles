package app.texium.com.profiles.models;

import java.io.Serializable;

/**
 * Created by texiumuser on 02/06/2016.
 */
public class StructureProfile implements Serializable {

    private String committee;
    private String reference;
    private String link;
    private String coordinator;

    public StructureProfile() {

    }

    public String getCommittee() {
        return committee;
    }

    public void setCommittee(String committee) {
        this.committee = committee;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(String coordinator) {
        this.coordinator = coordinator;
    }
}
