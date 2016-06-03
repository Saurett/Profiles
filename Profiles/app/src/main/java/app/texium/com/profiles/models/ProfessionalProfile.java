package app.texium.com.profiles.models;

import java.io.Serializable;

/**
 * Created by texiumuser on 02/06/2016.
 */
public class ProfessionalProfile implements Serializable {

    private String nss;
    private Integer level;
    private Integer idItemLevel;
    private Integer career;
    private Integer idItemCareer;
    private Integer professionalTitle;
    private Integer idItemPT;
    private String actualJob;
    private Integer company;
    private Integer idItemCompany;

    public ProfessionalProfile(){}

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getCareer() {
        return career;
    }

    public void setCareer(Integer career) {
        this.career = career;
    }

    public Integer getProfessionalTitle() {
        return professionalTitle;
    }

    public void setProfessionalTitle(Integer professionalTitle) {
        this.professionalTitle = professionalTitle;
    }

    public String getActualJob() {
        return actualJob;
    }

    public void setActualJob(String actualJob) {
        this.actualJob = actualJob;
    }

    public Integer getCompany() {
        return company;
    }

    public void setCompany(Integer company) {
        this.company = company;
    }

    public Integer getIdItemLevel() {
        return idItemLevel;
    }

    public void setIdItemLevel(Integer idItemLevel) {
        this.idItemLevel = idItemLevel;
    }

    public Integer getIdItemCareer() {
        return idItemCareer;
    }

    public void setIdItemCareer(Integer idItemCareer) {
        this.idItemCareer = idItemCareer;
    }

    public Integer getIdItemPT() {
        return idItemPT;
    }

    public void setIdItemPT(Integer idItemPT) {
        this.idItemPT = idItemPT;
    }

    public Integer getIdItemCompany() {
        return idItemCompany;
    }

    public void setIdItemCompany(Integer idItemCompany) {
        this.idItemCompany = idItemCompany;
    }
}
