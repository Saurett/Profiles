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
import app.texium.com.profiles.models.StructureProfile;


public class StructureProfileFragment extends Fragment implements View.OnClickListener {

    static FragmentProfileListener activityListener;

    private static Button backBtn, nextBtn, exitBtn;
    private EditText txtCommittee, txtReference, txtLink, txtCoordinator;
    private TextView title;

    private static ProfileManager _PROFILE_MANAGER;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_structure_profile, container, false);


        backBtn = (Button) view.findViewById(R.id.backBtnStructureProfile);
        nextBtn = (Button) view.findViewById(R.id.nextBtnStructureProfile);
        exitBtn = (Button) view.findViewById(R.id.structureExit);

        txtCommittee = (EditText) view.findViewById(R.id.committee);
        txtReference = (EditText) view.findViewById(R.id.reference);
        txtLink = (EditText) view.findViewById(R.id.link);
        txtCoordinator = (EditText) view.findViewById(R.id.coordinator);
        title = (TextView) view.findViewById(R.id.structureProfileTitle);

        backBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
        exitBtn.setOnClickListener(this);

        if (null != _PROFILE_MANAGER) {
            if (null != _PROFILE_MANAGER.getStructureProfile().getCommittee())
                txtCommittee.setText(_PROFILE_MANAGER.getStructureProfile().getCommittee());
            if (null != _PROFILE_MANAGER.getStructureProfile().getReference())
                txtReference.setText(_PROFILE_MANAGER.getStructureProfile().getReference());
            if (null != _PROFILE_MANAGER.getStructureProfile().getLink())
                txtLink.setText(_PROFILE_MANAGER.getStructureProfile().getLink());
            if (null != _PROFILE_MANAGER.getStructureProfile().getCoordinator())
                txtCoordinator.setText(_PROFILE_MANAGER.getStructureProfile().getCoordinator());
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
        StructureProfile structureProfile = new StructureProfile();

        switch (v.getId()) {
            case R.id.backBtnStructureProfile:

                structureProfile.setCommittee(txtCommittee.getText().toString());
                structureProfile.setReference(txtReference.getText().toString());
                structureProfile.setLink(txtLink.getText().toString());
                structureProfile.setCoordinator(txtCoordinator.getText().toString());

                _PROFILE_MANAGER.setStructureProfile(structureProfile);

                activityListener.updateProfile(_PROFILE_MANAGER);
                activityListener.moveFragments(v);
                break;
            case R.id.nextBtnStructureProfile:

                structureProfile.setCommittee(txtCommittee.getText().toString());
                structureProfile.setReference(txtReference.getText().toString());
                structureProfile.setLink(txtLink.getText().toString());
                structureProfile.setCoordinator(txtCoordinator.getText().toString());

                _PROFILE_MANAGER.setStructureProfile(structureProfile);

                activityListener.updateProfile(_PROFILE_MANAGER);
                activityListener.moveFragments(v);
                break;
            case R.id.structureExit:
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
