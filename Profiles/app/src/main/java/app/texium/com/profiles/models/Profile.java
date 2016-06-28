package app.texium.com.profiles.models;

/**
 * Created by texiumuser on 28/06/2016.
 */
public class Profile {

    private Integer cveProfile;
    private Integer idProfile;
    private String profileName;
    private String profileCity;
    private Integer profileCloud;

    public Profile() {

    }


    public Integer getCveProfile() {
        return cveProfile;
    }

    public void setCveProfile(Integer cveProfile) {
        this.cveProfile = cveProfile;
    }

    public Integer getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(Integer idProfile) {
        this.idProfile = idProfile;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileCity() {
        return profileCity;
    }

    public void setProfileCity(String profileCity) {
        this.profileCity = profileCity;
    }

    public Integer getProfileCloud() {
        return profileCloud;
    }

    public void setProfileCloud(Integer profileCloud) {
        this.profileCloud = profileCloud;
    }
}
