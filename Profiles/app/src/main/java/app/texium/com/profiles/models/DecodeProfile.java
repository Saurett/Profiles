package app.texium.com.profiles.models;

/**
 * Created by texiumuser on 29/06/2016.
 */
public class DecodeProfile {

    private Profile profile;
    private ProfileManager profileManager;
    private Integer idView;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public ProfileManager getProfileManager() {
        return profileManager;
    }

    public void setProfileManager(ProfileManager profileManager) {
        this.profileManager = profileManager;
    }

    public Integer getIdView() {
        return idView;
    }

    public void setIdView(Integer idView) {
        this.idView = idView;
    }
}
