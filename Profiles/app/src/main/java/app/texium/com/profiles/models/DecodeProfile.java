package app.texium.com.profiles.models;

/**
 * Created by texiumuser on 29/06/2016.
 */
public class DecodeProfile {


    private Profile profile;
    private Integer idView;
    private Integer position;

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Integer getIdView() {
        return idView;
    }

    public void setIdView(Integer idView) {
        this.idView = idView;
    }
}
