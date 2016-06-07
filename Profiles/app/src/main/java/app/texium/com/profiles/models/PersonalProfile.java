package app.texium.com.profiles.models;

import java.io.Serializable;

/**
 * Created by texiumuser on 02/06/2016.
 */
public class PersonalProfile implements Serializable {

    private String name;
    private String firstSurname;
    private String secondSurname;
    private String birthDate;
    private String ageProfile;
    private String civilState;
    private String sex;

    public PersonalProfile() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstSurname() {
        return firstSurname;
    }

    public void setFirstSurname(String firstSurname) {
        this.firstSurname = firstSurname;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAgeProfile() {
        return ageProfile;
    }

    public void setAgeProfile(String ageProfile) {
        this.ageProfile = ageProfile;
    }

    public String getCivilState() {
        return civilState;
    }

    public void setCivilState(String civilState) {
        this.civilState = civilState;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
