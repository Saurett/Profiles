package app.texium.com.profiles.fragments;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import app.texium.com.profiles.R;
import app.texium.com.profiles.models.DecodeProfile;
import app.texium.com.profiles.models.PersonalProfile;
import app.texium.com.profiles.models.ProfileManager;
import app.texium.com.profiles.utils.Constants;


public class PersonalProfileFragment extends Fragment implements View.OnClickListener {

    static FragmentProfileListener activityListener;

    private static Button nextBtn, exitBtn;
    private ImageView picture;
    private EditText txtDate, txtAge, txtName, txtFirstSurname, txtSecondSurname, txtNationality, txtPlace;
    private TextView title;
    private CheckBox checkBox, checkBox2, checkBox3, checkBox4, checkBoxWoman, checkBoxMan;

    private static ProfileManager _PROFILE_MANAGER;
    boolean cancelMove = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_personal_profile, container, false);

        picture = (ImageView) view.findViewById(R.id.profilePicture);

        txtName = (EditText) view.findViewById(R.id.name);
        txtFirstSurname = (EditText) view.findViewById(R.id.firstSurname);
        txtSecondSurname = (EditText) view.findViewById(R.id.secondSurname);
        txtDate = (EditText) view.findViewById(R.id.birthDate);
        txtAge = (EditText) view.findViewById(R.id.ageProfile);
        txtNationality = (EditText) view.findViewById(R.id.nationality);
        txtPlace = (EditText) view.findViewById(R.id.birthPlace);
        title = (TextView) view.findViewById(R.id.personalProfileTitle);

        checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        checkBox2 = (CheckBox) view.findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) view.findViewById(R.id.checkBox3);
        checkBox4 = (CheckBox) view.findViewById(R.id.checkBox4);
        checkBoxWoman = (CheckBox) view.findViewById(R.id.checkBoxWoman);
        checkBoxMan = (CheckBox) view.findViewById(R.id.checkBoxMan);


        Button calendarButton = (Button) view.findViewById(R.id.calendarButton);
        calendarButton.setOnClickListener(this);

        nextBtn = (Button) view.findViewById(R.id.nextBtnPersonalProfile);
        exitBtn = (Button) view.findViewById(R.id.personalExit);

        picture.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
        exitBtn.setOnClickListener(this);

        checkBox.setOnClickListener(this);
        checkBox2.setOnClickListener(this);
        checkBox3.setOnClickListener(this);
        checkBox4.setOnClickListener(this);
        checkBoxWoman.setOnClickListener(this);
        checkBoxMan.setOnClickListener(this);

        if (null != _PROFILE_MANAGER) {
            if (null != _PROFILE_MANAGER.getPersonalProfile().getName())
                txtName.setText(_PROFILE_MANAGER.getPersonalProfile().getName());
            if (null != _PROFILE_MANAGER.getPersonalProfile().getFirstSurname())
                txtFirstSurname.setText(_PROFILE_MANAGER.getPersonalProfile().getFirstSurname());
            if (null != _PROFILE_MANAGER.getPersonalProfile().getSecondSurname())
                txtSecondSurname.setText(_PROFILE_MANAGER.getPersonalProfile().getSecondSurname());
            if (null != _PROFILE_MANAGER.getPersonalProfile().getBirthDate())
                txtDate.setText(_PROFILE_MANAGER.getPersonalProfile().getBirthDate());
            if (null != _PROFILE_MANAGER.getPersonalProfile().getAgeProfile())
                txtAge.setText(_PROFILE_MANAGER.getPersonalProfile().getAgeProfile());
            if (null != _PROFILE_MANAGER.getPersonalProfile().getBirthPlace())
                txtPlace.setText(_PROFILE_MANAGER.getPersonalProfile().getBirthPlace());
            if (null != _PROFILE_MANAGER.getPersonalProfile().getNationality())
                txtNationality.setText(_PROFILE_MANAGER.getPersonalProfile().getNationality());
            if (null != _PROFILE_MANAGER.getPersonalProfile().getCivilState()) {
                Integer check = Constants.MAP_CHECKED.get(_PROFILE_MANAGER.getPersonalProfile().getCivilState());

                checkBox.setChecked(false);

                switch (check) {
                    case R.id.checkBox:
                        checkBox.setChecked(true);
                        break;
                    case R.id.checkBox2:
                        checkBox2.setChecked(true);
                        break;
                    case R.id.checkBox3:
                        checkBox3.setChecked(true);
                        break;
                    case R.id.checkBox4:
                        checkBox4.setChecked(true);
                        break;
                }
            }

            if (null != _PROFILE_MANAGER.getPersonalProfile().getSex()) {
                Integer check = Constants.MAP_CHECKED_SEX.get(_PROFILE_MANAGER.getPersonalProfile()
                        .getSex());

                checkBoxWoman.setChecked(false);

                switch (check) {
                    case R.id.checkBoxWoman:
                        checkBoxWoman.setChecked(true);
                        break;
                    case R.id.checkBoxMan:
                        checkBoxMan.setChecked(true);
                        break;
                }

            }
        }

        setTitle();
        return view;
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        _PROFILE_MANAGER = activityListener.getProfileManager();

       try {
           if (null != _PROFILE_MANAGER.getPersonalProfile()) {
               if (null !=_PROFILE_MANAGER.getPersonalProfile().getBirthDate()) {
                   String currentDate = _PROFILE_MANAGER.getPersonalProfile().getBirthDate();

                   if (currentDate.contains("-")) {
                       String[] actualDate = currentDate.split("-");

                       int year = Integer.valueOf(actualDate[0]);
                       int month = Integer.valueOf(actualDate[1]);
                       int day = Integer.valueOf(actualDate[2]);

                       String date = day + " / " + month + " / " + year;

                       _PROFILE_MANAGER.getPersonalProfile().setBirthDate(date);

                       final Calendar c = Calendar.getInstance();
                       int actualYear = c.get(Calendar.YEAR);

                       _PROFILE_MANAGER.getPersonalProfile().setAgeProfile(String.valueOf(actualYear - year) + " Años");

                   }
               }
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    @Override
    public void onResume() {
        super.onResume();
        _PROFILE_MANAGER = activityListener.getProfileManager();

        if (null != _PROFILE_MANAGER.getPersonalProfile().getProfilePicture()) {
            String encodedImage = _PROFILE_MANAGER.getPersonalProfile().getProfilePicture();
            byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            picture.setImageBitmap(decodedByte);
        }
    }

    public void setTitle() {
       try {
           if (null !=_PROFILE_MANAGER.getDecodeProfile()) {
               title.setText(R.string.default_edit_profile_title);
               exitBtn.setVisibility(View.VISIBLE);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
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
        switch (v.getId()) {
            case R.id.profilePicture:
                try {
                    activityListener.showCamera(v);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.calendarButton:
                activityListener.showCalendar(v, txtDate, txtAge);
                break;
            case R.id.nextBtnPersonalProfile:

                attemptMove();

                if (!cancelMove) {
                    PersonalProfile personalProfile = new PersonalProfile();

                    personalProfile.setName(txtName.getText().toString());
                    personalProfile.setFirstSurname(txtFirstSurname.getText().toString());
                    personalProfile.setSecondSurname(txtSecondSurname.getText().toString());
                    personalProfile.setBirthDate(txtDate.getText().toString());
                    personalProfile.setAgeProfile(txtAge.getText().toString());
                    personalProfile.setCivilState(getCheckedCivilState().getText().toString());
                    personalProfile.setSex(getCheckedSex().getText().toString());
                    personalProfile.setNationality(txtNationality.getText().toString());
                    personalProfile.setBirthPlace(txtPlace.getText().toString());
                    personalProfile.setProfilePicture(_PROFILE_MANAGER.getPersonalProfile().getProfilePicture());

                    _PROFILE_MANAGER.setPersonalProfile(personalProfile);

                    activityListener.updateProfile(_PROFILE_MANAGER);
                    activityListener.moveFragments(v);
                }
                break;
            case R.id.personalExit:
                DecodeProfile decodeProfile = activityListener.getDecodeProfile();
                decodeProfile.setIdView(v.getId());

                activityListener.updateDecodeProfile(decodeProfile);
                activityListener.showQuestion();
                break;
            default:
                boolean checked = ((CheckBox) v).isChecked();

                switch (v.getId()) {
                    case R.id.checkBox:
                        if (checked) {
                            checkBox2.setChecked(false);
                            checkBox3.setChecked(false);
                            checkBox4.setChecked(false);
                        } else {
                            checkBox.setChecked(true);
                        }
                        break;
                    case R.id.checkBox2:
                        if (checked) {
                            checkBox.setChecked(false);
                            checkBox3.setChecked(false);
                            checkBox4.setChecked(false);
                        } else {
                            checkBox2.setChecked(true);
                        }
                        break;
                    case R.id.checkBox3:
                        if (checked) {
                            checkBox.setChecked(false);
                            checkBox2.setChecked(false);
                            checkBox4.setChecked(false);
                        } else {
                            checkBox3.setChecked(true);
                        }
                        break;
                    case R.id.checkBox4:
                        if (checked) {
                            checkBox.setChecked(false);
                            checkBox2.setChecked(false);
                            checkBox3.setChecked(false);
                        } else {
                            checkBox4.setChecked(true);
                        }
                        break;
                    case R.id.checkBoxWoman:
                        if (checked) {
                            checkBoxMan.setChecked(false);
                        } else {
                            checkBoxWoman.setChecked(true);
                        }
                        break;
                    case R.id.checkBoxMan:
                        if (checked) {
                            checkBoxWoman.setChecked(false);
                        } else {
                            checkBoxMan.setChecked(true);
                        }
                        break;
                }
                break;
        }
    }

    public CheckBox getCheckedCivilState() {
        CheckBox checked = null;

        List<CheckBox> checkBoxes = new ArrayList<>();

        checkBoxes.add(checkBox);
        checkBoxes.add(checkBox2);
        checkBoxes.add(checkBox3);
        checkBoxes.add(checkBox4);

        for (CheckBox checkBox :
                checkBoxes) {
            if (checkBox.isChecked()) {
                checked = checkBox;
                break;
            }
        }

        return checked;
    }

    public CheckBox getCheckedSex() {
        CheckBox checked = null;

        List<CheckBox> checkBoxes = new ArrayList<>();

        checkBoxes.add(checkBoxWoman);
        checkBoxes.add(checkBoxMan);

        for (CheckBox checkBox :
                checkBoxes) {
            if (checkBox.isChecked()) {
                checked = checkBox;
                break;
            }
        }

        return checked;
    }

    public void attemptMove() {

        cancelMove = false;

        txtName.setError(null);
        txtFirstSurname.setError(null);

        String name = txtName.getText().toString();
        String fSurname = txtFirstSurname .getText().toString();

        checkValid(R.id.name,name);
        checkValid(R.id.firstSurname,fSurname);

    }

    private void checkValid(int id, String value) {

       if (TextUtils.isEmpty(value)) {
           switch (id) {
               case R.id.name:
                   txtName.setError(getActivity().getString(R.string.default_field_required));
                   txtName.requestFocus();
                   cancelMove = true;
                   break;
               case R.id.firstSurname:
                   txtFirstSurname.setError(getActivity().getString(R.string.default_field_required));
                   txtFirstSurname.requestFocus();
                   cancelMove = true;
                   break;
           }
       }

    }
}
