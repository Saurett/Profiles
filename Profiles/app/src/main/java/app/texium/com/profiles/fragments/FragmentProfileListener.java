package app.texium.com.profiles.fragments;

import android.view.View;
import android.widget.EditText;

import app.texium.com.profiles.models.ProfileManager;

/**
 * Created by saurett on 05/02/2016.
 */
public interface FragmentProfileListener {

    void moveFragments(View view);
    void showCalendar(View view, EditText txtDate, EditText txtAge);
    ProfileManager updateProfile(ProfileManager oldProfile);
    ProfileManager getProfileManager();
    void showCamera(View view);
}
