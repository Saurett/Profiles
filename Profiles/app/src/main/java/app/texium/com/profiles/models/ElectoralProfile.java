package app.texium.com.profiles.models;

import android.net.Uri;

import java.io.Serializable;

/**
 * Created by texiumuser on 02/06/2016.
 */
public class ElectoralProfile implements Serializable {

    private String ocrINE;
    private String electoralKEY;
    private String validityINE;
    private Integer electoralSection;
    private String localDistrict;
    private String federalDistrict;
    private String electoralAdviser;
    private Integer politicalParty;
    private Integer idItemPP;
    private String photoINEBack;
    private Uri uriINEBack;
    private String photoINEFront;
    private Uri uriINEFront;
    private Integer electoralActor;
    private Integer idItemEA;
    private Integer subItemElectoralActor;
    private Integer idSubItemEA;


    public ElectoralProfile(){
    }

    public String getOcrINE() {
        return ocrINE;
    }

    public void setOcrINE(String ocrINE) {
        this.ocrINE = ocrINE;
    }

    public String getElectoralKEY() {
        return electoralKEY;
    }

    public void setElectoralKEY(String electoralKEY) {
        this.electoralKEY = electoralKEY;
    }

    public String getValidityINE() {
        return validityINE;
    }

    public void setValidityINE(String validityINE) {
        this.validityINE = validityINE;
    }

    public Integer getElectoralSection() {
        return electoralSection;
    }

    public void setElectoralSection(Integer electoralSection) {
        this.electoralSection = electoralSection;
    }

    public String getLocalDistrict() {
        return localDistrict;
    }

    public void setLocalDistrict(String localDistrict) {
        this.localDistrict = localDistrict;
    }

    public String getFederalDistrict() {
        return federalDistrict;
    }

    public void setFederalDistrict(String federalDistrict) {
        this.federalDistrict = federalDistrict;
    }

    public String getElectoralAdviser() {
        return electoralAdviser;
    }

    public void setElectoralAdviser(String electoralAdviser) {
        this.electoralAdviser = electoralAdviser;
    }

    public Integer getPoliticalParty() {
        return politicalParty;
    }

    public void setPoliticalParty(Integer politicalParty) {
        this.politicalParty = politicalParty;
    }

    public Integer getIdItemPP() {
        return idItemPP;
    }

    public void setIdItemPP(Integer idItemPP) {
        this.idItemPP = idItemPP;
    }

    public String getPhotoINEBack() {
        return photoINEBack;
    }

    public void setPhotoINEBack(String photoINEBack) {
        this.photoINEBack = photoINEBack;
    }

    public String getPhotoINEFront() {
        return photoINEFront;
    }

    public void setPhotoINEFront(String photoINEFront) {
        this.photoINEFront = photoINEFront;
    }

    public Integer getElectoralActor() {
        return electoralActor;
    }

    public void setElectoralActor(Integer electoralActor) {
        this.electoralActor = electoralActor;
    }

    public Integer getIdItemEA() {
        return idItemEA;
    }

    public void setIdItemEA(Integer idItemEA) {
        this.idItemEA = idItemEA;
    }

    public Integer getSubItemElectoralActor() {
        return subItemElectoralActor;
    }

    public void setSubItemElectoralActor(Integer subItemElectoralActor) {
        this.subItemElectoralActor = subItemElectoralActor;
    }

    public Integer getIdSubItemEA() {
        return idSubItemEA;
    }

    public void setIdSubItemEA(Integer idSubItemEA) {
        this.idSubItemEA = idSubItemEA;
    }

    public Uri getUriINEBack() {
        return uriINEBack;
    }

    public void setUriINEBack(Uri uriINEBack) {
        this.uriINEBack = uriINEBack;
    }

    public Uri getUriINEFront() {
        return uriINEFront;
    }

    public void setUriINEFront(Uri uriINEFront) {
        this.uriINEFront = uriINEFront;
    }
}
