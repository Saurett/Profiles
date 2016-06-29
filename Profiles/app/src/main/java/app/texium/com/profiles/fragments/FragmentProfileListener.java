package app.texium.com.profiles.fragments;

import android.view.View;
import android.widget.EditText;

import java.io.IOException;

import app.texium.com.profiles.models.DecodeProfile;
import app.texium.com.profiles.models.ProfileManager;

/**
 * Created by saurett on 05/02/2016.
 */
public interface FragmentProfileListener {

    void moveFragments(View view);
    void showCalendar(View view, EditText txtDate, EditText txtAge);
    ProfileManager updateProfile(ProfileManager oldProfile);
    ProfileManager getProfileManager();
    DecodeProfile updateDecodeProfile(DecodeProfile oldProfile);
    DecodeProfile getDecodeProfile();
    void showCamera(View view) throws IOException;
    void showQuestion();
}
