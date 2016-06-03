package app.texium.com.profiles.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import app.texium.com.profiles.R;
import app.texium.com.profiles.models.ContactProfile;
import app.texium.com.profiles.models.ProfileManager;


public class ContactProfileFragment extends Fragment implements View.OnClickListener {

    static FragmentProfileListener activityListener;

    private static Button backBtn, nextBtn;
    private EditText txtPersonalEmail, txtProfessionalEmail,txtCellPhone, txtOfficePhone, txtHomePhone, txtOtherPhone,txtCURP, txtRFC;

    private static ProfileManager _PROFILE_MANAGER;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contact_profile, container, false);

        backBtn = (Button) view.findViewById(R.id.backBtnContactProfile);
        nextBtn = (Button) view.findViewById(R.id.nextBtnContactProfile);

        txtPersonalEmail = (EditText) view.findViewById(R.id.personalEmail);
        txtProfessionalEmail = (EditText) view.findViewById(R.id.professionalEmail);
        txtCellPhone = (EditText) view.findViewById(R.id.cellPhoneNumber);
        txtOfficePhone = (EditText) view.findViewById(R.id.officePhoneNumber);
        txtHomePhone = (EditText) view.findViewById(R.id.homePhoneNumber);
        txtOtherPhone = (EditText) view.findViewById(R.id.otherPhoneNumber);
        txtCURP = (EditText) view.findViewById(R.id.curp);
        txtRFC = (EditText) view.findViewById(R.id.rfc);

        backBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);

        if (null != _PROFILE_MANAGER) {
            if (null != _PROFILE_MANAGER.getContactProfile().getPersonalEmail())
                txtPersonalEmail.setText(_PROFILE_MANAGER.getContactProfile().getPersonalEmail());
            if (null != _PROFILE_MANAGER.getContactProfile().getProfessionalEmail())
                txtProfessionalEmail.setText(_PROFILE_MANAGER.getContactProfile().getProfessionalEmail());
            if (null != _PROFILE_MANAGER.getContactProfile().getCellPhoneNumber())
                txtCellPhone.setText(_PROFILE_MANAGER.getContactProfile().getCellPhoneNumber());
            if (null != _PROFILE_MANAGER.getContactProfile().getOfficePhoneNumber())
                txtOfficePhone.setText(_PROFILE_MANAGER.getContactProfile().getOfficePhoneNumber());
            if (null != _PROFILE_MANAGER.getContactProfile().getHomePhoneNumber())
                txtHomePhone.setText(_PROFILE_MANAGER.getContactProfile().getHomePhoneNumber());
            if (null != _PROFILE_MANAGER.getContactProfile().getOtherPhoneNumber())
                txtOtherPhone.setText(_PROFILE_MANAGER.getContactProfile().getOtherPhoneNumber());
            if (null != _PROFILE_MANAGER.getContactProfile().getCurp())
                txtCURP.setText(_PROFILE_MANAGER.getContactProfile().getCurp());
            if (null != _PROFILE_MANAGER.getContactProfile().getRfc())
                txtRFC.setText(_PROFILE_MANAGER.getContactProfile().getRfc());
        }

        return view;
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
        ContactProfile contactProfile = new ContactProfile();

        switch (v.getId()) {
            case R.id.backBtnContactProfile:

                contactProfile.setPersonalEmail(txtPersonalEmail.getText().toString());
                contactProfile.setProfessionalEmail(txtProfessionalEmail.getText().toString());
                contactProfile.setCellPhoneNumber(txtCellPhone.getText().toString());
                contactProfile.setOfficePhoneNumber(txtOfficePhone.getText().toString());
                contactProfile.setHomePhoneNumber(txtHomePhone.getText().toString());
                contactProfile.setOtherPhoneNumber(txtOtherPhone.getText().toString());
                contactProfile.setCurp(txtCURP.getText().toString());
                contactProfile.setRfc(txtRFC.getText().toString());

                _PROFILE_MANAGER.setContactProfile(contactProfile);

                activityListener.updateProfile(_PROFILE_MANAGER);
                activityListener.moveFragments(v);
                break;
            case R.id.nextBtnContactProfile:

                contactProfile.setPersonalEmail(txtPersonalEmail.getText().toString());
                contactProfile.setProfessionalEmail(txtProfessionalEmail.getText().toString());
                contactProfile.setCellPhoneNumber(txtCellPhone.getText().toString());
                contactProfile.setOfficePhoneNumber(txtOfficePhone.getText().toString());
                contactProfile.setHomePhoneNumber(txtHomePhone.getText().toString());
                contactProfile.setOtherPhoneNumber(txtOtherPhone.getText().toString());
                contactProfile.setCurp(txtCURP.getText().toString());
                contactProfile.setRfc(txtRFC.getText().toString());

                _PROFILE_MANAGER.setContactProfile(contactProfile);

                activityListener.updateProfile(_PROFILE_MANAGER);
                activityListener.moveFragments(v);
                break;
            default:
                break;
        }
    }
}
