package app.texium.com.profiles.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import app.texium.com.profiles.R;
import app.texium.com.profiles.models.DecodeProfile;
import app.texium.com.profiles.models.ProfileManager;
import app.texium.com.profiles.models.SocialNetworkProfile;


public class SocialNetworkProfileFragment extends Fragment implements View.OnClickListener {

    static FragmentProfileListener activityListener;

    private static Button backBtn, nextBtn, finishBtn, exitBtn;
    private static EditText txtFacebook, txtTwitter, txtInstagram;
    private TextView title;

    private static ProfileManager _PROFILE_MANAGER;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_social_network_profile, container, false);

        backBtn = (Button) view.findViewById(R.id.backBtnSNProfile);
        nextBtn = (Button) view.findViewById(R.id.nextBtnSNProfile);
        finishBtn = (Button) view.findViewById(R.id.finishBtnSNProfile);
        exitBtn = (Button) view.findViewById(R.id.socialNetworkExit);

        txtFacebook = (EditText) view.findViewById(R.id.facebook);
        txtTwitter = (EditText) view.findViewById(R.id.twitter);
        txtInstagram = (EditText) view.findViewById(R.id.instagram);
        title = (TextView) view.findViewById(R.id.socialNetworkProfileTitle);

        backBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
        finishBtn.setOnClickListener(this);
        exitBtn.setOnClickListener(this);

        if (null != _PROFILE_MANAGER) {
            if (null != _PROFILE_MANAGER.getSocialNetworkProfile().getFacebook())
                txtFacebook.setText(_PROFILE_MANAGER.getSocialNetworkProfile().getFacebook());
            if (null !=_PROFILE_MANAGER.getSocialNetworkProfile().getTwitter());
                txtTwitter.setText(_PROFILE_MANAGER.getSocialNetworkProfile().getTwitter());
            if (null != _PROFILE_MANAGER.getSocialNetworkProfile().getInstagram())
                txtInstagram.setText(_PROFILE_MANAGER.getSocialNetworkProfile().getInstagram());
        }
        setTitle();

        return view;
    }

    public void setTitle() {
        if (null !=_PROFILE_MANAGER.getDecodeProfile()) {
            title.setText(R.string.default_edit_profile_title);
            exitBtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        _PROFILE_MANAGER = activityListener.getProfileManager();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            activityListener = (FragmentProfileListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " debe implementar TaskListener");
        }
    }

    @Override
    public void onClick(View v) {
        SocialNetworkProfile socialNetworkProfile = new SocialNetworkProfile();
        switch (v.getId()) {
            case R.id.backBtnSNProfile:

                socialNetworkProfile.setFacebook(txtFacebook.getText().toString());
                socialNetworkProfile.setTwitter(txtTwitter.getText().toString());
                socialNetworkProfile.setInstagram(txtInstagram.getText().toString());

                _PROFILE_MANAGER.setSocialNetworkProfile(socialNetworkProfile);

                activityListener.updateProfile(_PROFILE_MANAGER);
                activityListener.moveFragments(v);
                break;
            case R.id.nextBtnSNProfile:
                socialNetworkProfile.setFacebook(txtFacebook.getText().toString());
                socialNetworkProfile.setTwitter(txtTwitter.getText().toString());
                socialNetworkProfile.setInstagram(txtInstagram.getText().toString());

                _PROFILE_MANAGER.setSocialNetworkProfile(socialNetworkProfile);

                activityListener.updateProfile(_PROFILE_MANAGER);
                activityListener.moveFragments(v);
                break;
            case R.id.finishBtnSNProfile:
                socialNetworkProfile.setFacebook(txtFacebook.getText().toString());
                socialNetworkProfile.setTwitter(txtTwitter.getText().toString());
                socialNetworkProfile.setInstagram(txtInstagram.getText().toString());

                _PROFILE_MANAGER.setSocialNetworkProfile(socialNetworkProfile);

                activityListener.updateProfile(_PROFILE_MANAGER);
                activityListener.moveFragments(v);
                break;
            case R.id.socialNetworkExit:
                DecodeProfile decodeProfile = activityListener.getDecodeProfile();
                decodeProfile.setIdView(v.getId());

                activityListener.updateDecodeProfile(decodeProfile);
                activityListener.showQuestion();
                break;
            default:
                break;
        }
    }
}
