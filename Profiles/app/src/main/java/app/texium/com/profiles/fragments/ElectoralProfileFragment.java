package app.texium.com.profiles.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.ksoap2.serialization.SoapObject;

import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;

import app.texium.com.profiles.R;
import app.texium.com.profiles.databases.BDProfileManagerQuery;
import app.texium.com.profiles.models.ElectoralActor;
import app.texium.com.profiles.models.ElectoralProfile;
import app.texium.com.profiles.models.PoliticalParties;
import app.texium.com.profiles.models.ProfileManager;
import app.texium.com.profiles.models.Sections;
import app.texium.com.profiles.models.SubItemElectoralActor;
import app.texium.com.profiles.services.SoapServices;
import app.texium.com.profiles.utils.Constants;


public class ElectoralProfileFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener, DialogInterface.OnClickListener, View.OnFocusChangeListener {

    static FragmentProfileListener activityListener;

    private static Button backBtn, nextBtn, pictureBtnBack, pictureBtnFront, deleteBtnBack, deleteBtnFront;
    private static EditText txtOCR, txtElectoralKey, txtValidityINE, txtElectoralSection, txtLocalDistrict, txtFederalDistrict, txtElectoralAdviser;
    private ProgressDialog pDialog;

    private static int positionPP, positionElectoralActor, positionSubItemEA;
    private static int idPoliticalParty, idElectoralActor, idSubItemEA;

    private Spinner politicalSpinner, electoralActorSpinner, subItemEASpinner;

    private ArrayList<String> list, electoralActorList, subItemEAList;

    private ArrayList<PoliticalParties> politicalParties;
    private ArrayList<ElectoralActor> electoralActors;
    private ArrayList<SubItemElectoralActor> subItemEAs;

    private static ProfileManager _PROFILE_MANAGER;

    boolean cancelMove = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_electoral_profile, container, false);

        backBtn = (Button) view.findViewById(R.id.backBtnElectoralProfile);
        nextBtn = (Button) view.findViewById(R.id.nextBtnElectoralProfile);
        pictureBtnBack = (Button) view.findViewById(R.id.pictureBtnBack);
        pictureBtnFront = (Button) view.findViewById(R.id.pictureBtnFront);
        deleteBtnBack = (Button) view.findViewById(R.id.deleteBtnBack);
        deleteBtnFront = (Button) view.findViewById(R.id.deleteBtnFront);

        txtOCR = (EditText) view.findViewById(R.id.ocrINE);
        txtElectoralKey = (EditText) view.findViewById(R.id.electoralKey);
        txtValidityINE = (EditText) view.findViewById(R.id.validityINE);
        txtElectoralSection = (EditText) view.findViewById(R.id.electoralSection);
        txtLocalDistrict = (EditText) view.findViewById(R.id.localDistrict);
        txtFederalDistrict = (EditText) view.findViewById(R.id.federalDistrict);
        txtElectoralAdviser = (EditText) view.findViewById(R.id.electoralAdviser);

        politicalSpinner = (Spinner) view.findViewById(R.id.politicalParty);
        electoralActorSpinner = (Spinner) view.findViewById(R.id.electoralActor);
        subItemEASpinner = (Spinner) view.findViewById(R.id.subItemElectoralActor);

        backBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
        pictureBtnBack.setOnClickListener(this);
        pictureBtnFront.setOnClickListener(this);
        deleteBtnBack.setOnClickListener(this);
        deleteBtnFront.setOnClickListener(this);

        politicalSpinner.setOnItemSelectedListener(this);
        electoralActorSpinner.setOnItemSelectedListener(this);
        subItemEASpinner.setOnItemSelectedListener(this);

        txtElectoralSection.setOnFocusChangeListener(this);

        if (null != _PROFILE_MANAGER) {
            if (null != _PROFILE_MANAGER.getElectoralProfile().getOcrINE())
                txtOCR.setText(_PROFILE_MANAGER.getElectoralProfile().getOcrINE());
            if (null != _PROFILE_MANAGER.getElectoralProfile().getElectoralKEY())
                txtElectoralKey.setText(_PROFILE_MANAGER.getElectoralProfile().getElectoralKEY());
            if (null != _PROFILE_MANAGER.getElectoralProfile().getElectoralSection()) {
                String tempES = String.valueOf(_PROFILE_MANAGER.getElectoralProfile().getElectoralSection());
                txtElectoralSection.setText(tempES.equals("0") ? null : tempES);
            }
            if (null != _PROFILE_MANAGER.getElectoralProfile().getValidityINE())
                txtValidityINE.setText(_PROFILE_MANAGER.getElectoralProfile().getValidityINE());
            if (null != _PROFILE_MANAGER.getElectoralProfile().getLocalDistrict())
                txtLocalDistrict.setText(_PROFILE_MANAGER.getElectoralProfile().getLocalDistrict());
            if (null != _PROFILE_MANAGER.getElectoralProfile().getFederalDistrict())
                txtFederalDistrict.setText(_PROFILE_MANAGER.getElectoralProfile().getFederalDistrict());
            if (null != _PROFILE_MANAGER.getElectoralProfile().getElectoralAdviser())
                txtElectoralAdviser.setText(_PROFILE_MANAGER.getElectoralProfile().getElectoralAdviser());
        }

        return view;
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        _PROFILE_MANAGER = activityListener.getProfileManager();

        AsyncElectoral wsSpinnerPP = new AsyncElectoral(Constants.WS_KEY_SPINNER_ALL_ELECTORAL_SERVICE);
        wsSpinnerPP.execute();
    }

    @Override
    public void onResume() {
        super.onResume();
        _PROFILE_MANAGER = activityListener.getProfileManager();

        if (!_PROFILE_MANAGER.getElectoralProfile().getPhotoINEBack().isEmpty()) {
            deleteBtnBack.setVisibility(View.VISIBLE);
        }
        if (!_PROFILE_MANAGER.getElectoralProfile().getPhotoINEFront().isEmpty()) {
            deleteBtnFront.setVisibility(View.VISIBLE);
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
        ElectoralProfile electoralProfile = new ElectoralProfile();
        switch (v.getId()) {
            case R.id.backBtnElectoralProfile:

                attemptMove();

                if (!cancelMove) {
                    electoralProfile.setOcrINE(txtOCR.getText().toString());
                    electoralProfile.setElectoralKEY(txtElectoralKey.getText().toString());
                    electoralProfile.setValidityINE(txtValidityINE.getText().toString());

                    if (txtElectoralSection.getText().toString().length() > 0) {
                        electoralProfile.setElectoralSection(Integer.valueOf(txtElectoralSection.getText().toString()));
                    } else {
                        electoralProfile.setElectoralSection(0);
                    }

                    electoralProfile.setLocalDistrict(txtLocalDistrict.getText().toString());
                    electoralProfile.setFederalDistrict(txtFederalDistrict.getText().toString());
                    electoralProfile.setElectoralAdviser(txtElectoralAdviser.getText().toString());
                    electoralProfile.setPoliticalParty(idPoliticalParty);
                    electoralProfile.setElectoralActor(idElectoralActor);
                    electoralProfile.setSubItemElectoralActor(idSubItemEA);
                    electoralProfile.setIdItemPP(positionPP);
                    electoralProfile.setIdItemEA(positionElectoralActor);
                    electoralProfile.setIdSubItemEA(positionSubItemEA);
                    electoralProfile.setPhotoINEBack(_PROFILE_MANAGER.getElectoralProfile().getPhotoINEBack());
                    electoralProfile.setPhotoINEFront(_PROFILE_MANAGER.getElectoralProfile().getPhotoINEFront());

                    _PROFILE_MANAGER.setElectoralProfile(electoralProfile);

                    activityListener.updateProfile(_PROFILE_MANAGER);
                    activityListener.moveFragments(v);
                }
                break;
            case R.id.nextBtnElectoralProfile:

                attemptMove();

                if (!cancelMove) {
                    electoralProfile.setOcrINE(txtOCR.getText().toString());
                    electoralProfile.setElectoralKEY(txtElectoralKey.getText().toString());
                    electoralProfile.setValidityINE(txtValidityINE.getText().toString());

                    if (txtElectoralSection.getText().toString().length() > 0) {
                        electoralProfile.setElectoralSection(Integer.valueOf(txtElectoralSection.getText().toString()));
                    } else {
                        electoralProfile.setElectoralSection(0);
                    }

                    electoralProfile.setLocalDistrict(txtLocalDistrict.getText().toString());
                    electoralProfile.setFederalDistrict(txtFederalDistrict.getText().toString());
                    electoralProfile.setElectoralAdviser(txtElectoralAdviser.getText().toString());
                    electoralProfile.setPoliticalParty(idPoliticalParty);
                    electoralProfile.setElectoralActor(idElectoralActor);
                    electoralProfile.setSubItemElectoralActor(idSubItemEA);
                    electoralProfile.setIdItemPP(positionPP);
                    electoralProfile.setIdItemEA(positionElectoralActor);
                    electoralProfile.setIdSubItemEA(positionSubItemEA);
                    electoralProfile.setPhotoINEBack(_PROFILE_MANAGER.getElectoralProfile().getPhotoINEBack());
                    electoralProfile.setPhotoINEFront(_PROFILE_MANAGER.getElectoralProfile().getPhotoINEFront());

                    _PROFILE_MANAGER.setElectoralProfile(electoralProfile);

                    activityListener.updateProfile(_PROFILE_MANAGER);
                    activityListener.moveFragments(v);
                }
                break;
            case R.id.pictureBtnBack:
            case R.id.pictureBtnFront:
                try {
                    activityListener.showCamera(v);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.deleteBtnBack:
            case R.id.deleteBtnFront:
                showQuestion(v.getId());
                break;
            default:
                break;
        }
    }


    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                _PROFILE_MANAGER.getElectoralProfile().setPhotoINEBack("");
                Toast.makeText(getActivity(), R.string.default_delete_img_msg, Toast.LENGTH_SHORT).show();
                deleteBtnBack.setVisibility(View.INVISIBLE);
                break;
        }

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {

            String section = txtElectoralSection.getText().toString();
            txtLocalDistrict.setText(null);

            if (!TextUtils.isEmpty(section)) {
                AsyncElectoral ws = new AsyncElectoral(
                        Constants.WS_KEY_ELECTORAL_SECTION,
                        Integer.valueOf(section));
                ws.execute();

            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case R.id.electoralActor:
                subItemEASpinner.setVisibility(View.INVISIBLE);

                positionElectoralActor = position;
                ElectoralActor ea = electoralActors.get(position);
                idElectoralActor = ea.getIdElectoralActor();

                if (idElectoralActor == Constants.SUB_ITEM_ACTION) {
                    subItemEASpinner.setVisibility(View.VISIBLE);
                    AsyncElectoral wsSpinnerPP = new AsyncElectoral(
                            Constants.WS_KEY_SPINNER_SUB_ITEM_EA_SERVICE,
                            idElectoralActor);
                    wsSpinnerPP.execute();
                }
                break;
            case R.id.politicalParty:
                positionPP = position;
                PoliticalParties pp = politicalParties.get(position);
                idPoliticalParty = pp.getIdPP();
                break;
            case R.id.subItemElectoralActor:
                positionSubItemEA = position;
                SubItemElectoralActor subItemEA = subItemEAs.get(position);
                idSubItemEA = subItemEA.getIdSubItemEA();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void showQuestion(int id) {

        String temp = (R.id.deleteBtnBack == id) ? getActivity().getString(R.string.default_question_delete_back)
                : getActivity().getString(R.string.default_question_delete_front);

        AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
        ad.setTitle(getActivity().getString(R.string.default_title_alert_dialog));
        ad.setMessage(temp);
        ad.setCancelable(false);
        ad.setPositiveButton(getActivity().getString(R.string.default_positive_button), this);
        ad.setNegativeButton(getActivity().getString(R.string.default_negative_button), this);
        ad.show();
    }


    private class AsyncElectoral extends AsyncTask<Void, Void, Boolean> {

        private SoapObject soPoliticalParty, soElectoralActor, soSubItemElectoralActor, soDistric;
        private Integer webServiceOperation;
        private Integer webServiceID;
        private String textError;
        private Boolean localAccess;

        private AsyncElectoral(Integer wsOperation) {
            webServiceOperation = wsOperation;
            textError = "";
            localAccess = false;
        }

        private AsyncElectoral(Integer wsOperation, Integer wsID) {
            webServiceOperation = wsOperation;
            webServiceID = wsID;
            textError = "";
            localAccess = false;
        }

        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(getContext());
            pDialog.setMessage("Espere un momento por favor");
            pDialog.setTitle("Cargando formulario");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            Boolean validOperation = false;

            try {
                switch (webServiceOperation) {
                    case Constants.WS_KEY_SPINNER_ALL_ELECTORAL_SERVICE:
                        soPoliticalParty = SoapServices.getSpinnerPP(getContext());
                        soElectoralActor = SoapServices.getSpinnerAllEA(getContext());
                        validOperation = (soElectoralActor.getPropertyCount() > 0);
                        break;
                    case Constants.WS_KEY_SPINNER_SUB_ITEM_EA_SERVICE:
                        soSubItemElectoralActor = SoapServices.getSpinnerSubItemEA(getContext(), webServiceID);
                        validOperation = (soSubItemElectoralActor.getPropertyCount() > 0);
                        break;
                    case Constants.WS_KEY_ELECTORAL_SECTION:
                        soDistric = SoapServices.getSection(getContext(), webServiceID);
                        validOperation = (soDistric.getPropertyCount() > 0);
                        break;
                }
            } catch (ConnectException e) {

                textError = e.getMessage();
                validOperation = false;

                e.printStackTrace();
                Log.e("WebServiceException", "Unknown error : " + e.getMessage());

                switch (webServiceOperation) {
                    case Constants.WS_KEY_SPINNER_ALL_ELECTORAL_SERVICE:
                        validOperation = true;

                        list = new ArrayList<>();
                        politicalParties = new ArrayList<>();

                        electoralActorList = new ArrayList<>();
                        electoralActors = new ArrayList<>();

                        try {
                            politicalParties = BDProfileManagerQuery.getAllPP(getContext());

                            for (PoliticalParties data :
                                    politicalParties) {
                                list.add(data.getAcronymName());
                            }

                            electoralActors = BDProfileManagerQuery.getAllEA(getContext(), 0);

                            for (ElectoralActor data :
                                    electoralActors) {
                                if (data.getIdFather() == 0) electoralActorList.add(data.getName());
                            }

                            localAccess = true;

                        } catch (Exception e1) {
                            e1.printStackTrace();
                            localAccess = false;
                        }

                        break;
                    case Constants.WS_KEY_SPINNER_SUB_ITEM_EA_SERVICE:

                        validOperation = true;

                        subItemEAList = new ArrayList<>();
                        subItemEAs = new ArrayList<>();

                        try {
                            ArrayList<ElectoralActor> temp = BDProfileManagerQuery.getAllEA(getContext(), webServiceID);

                            for (ElectoralActor data :
                                    temp) {
                                SubItemElectoralActor item = new SubItemElectoralActor();

                                item.setName(data.getName());
                                item.setIdSubItemEA(data.getIdElectoralActor());

                                subItemEAs.add(item);
                                subItemEAList.add(data.getName());
                            }

                            localAccess = true;

                        } catch (Exception e1) {
                            e1.printStackTrace();
                            localAccess = false;
                        }

                        break;
                }


            } catch (Exception e) {
                textError = e.getMessage();
                validOperation = false;
            }

            return validOperation;
        }

        @Override
        protected void onPostExecute(final Boolean success) {

            pDialog.dismiss();
            if (success) {

                switch (webServiceOperation) {

                    case Constants.WS_KEY_SPINNER_ALL_ELECTORAL_SERVICE:

                        if (!localAccess) {

                            list = new ArrayList<>();
                            politicalParties = new ArrayList<>();

                            electoralActorList = new ArrayList<>();
                            electoralActors = new ArrayList<>();


                            if (soPoliticalParty.hasProperty(Constants.SOAP_PROPERTY_DIFFGRAM)) {
                                SoapObject soDiffGram = (SoapObject) soPoliticalParty.getProperty(Constants.SOAP_PROPERTY_DIFFGRAM);
                                if (soDiffGram.hasProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET)) {
                                    SoapObject soNewDataSet = (SoapObject) soDiffGram.getProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET);

                                    for (int i = 0; i < soNewDataSet.getPropertyCount(); i++) {
                                        SoapObject soItem = (SoapObject) soNewDataSet.getProperty(i);

                                        PoliticalParties pp = new PoliticalParties();
                                        pp.setIdItem(i);
                                        pp.setIdPP(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_ID).toString()));
                                        pp.setAcronymName(soItem.getProperty(Constants.SOAP_OBJECT_KEY_ACRONYM_NAME).toString());
                                        pp.setIdStatus(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_STATUS).toString()));

                                        politicalParties.add(pp);
                                        list.add(pp.getAcronymName());
                                    }
                                }
                            }

                            if (soElectoralActor.hasProperty(Constants.SOAP_PROPERTY_DIFFGRAM)) {
                                SoapObject soDiffGram = (SoapObject) soElectoralActor.getProperty(Constants.SOAP_PROPERTY_DIFFGRAM);
                                if (soDiffGram.hasProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET)) {
                                    SoapObject soNewDataSet = (SoapObject) soDiffGram.getProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET);

                                    for (int i = 0; i < soNewDataSet.getPropertyCount(); i++) {
                                        SoapObject soItem = (SoapObject) soNewDataSet.getProperty(i);

                                        ElectoralActor ea = new ElectoralActor();

                                        ea.setIdItem(i);
                                        ea.setIdElectoralActor(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_ID).toString()));
                                        ea.setName(soItem.getProperty(Constants.SOAP_OBJECT_KEY_NAME).toString());

                                        if (!soItem.hasProperty(Constants.SOAP_OBJECT_KEY_FATHER)) {
                                            electoralActors.add(ea);
                                            electoralActorList.add(ea.getName());
                                        }
                                    }
                                }
                            }
                        }

                        try {
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                                    android.R.layout.simple_spinner_item,
                                    android.R.id.text1, list);

                            int actualSelection = (null != _PROFILE_MANAGER.getElectoralProfile().getIdItemPP())
                                    ? _PROFILE_MANAGER.getElectoralProfile().getIdItemPP() : 0;
                            politicalSpinner.setAdapter(adapter);
                            politicalSpinner.setSelection(actualSelection);

                            ArrayAdapter<String> electoralActorAdapter = new ArrayAdapter<>(getActivity(),
                                    android.R.layout.simple_spinner_item,
                                    android.R.id.text1, electoralActorList);

                            int electoralActorSelection = (null != _PROFILE_MANAGER.getElectoralProfile().getIdItemEA())
                                    ? _PROFILE_MANAGER.getElectoralProfile().getIdItemEA() : 0;
                            electoralActorSpinner.setAdapter(electoralActorAdapter);
                            electoralActorSpinner.setSelection(electoralActorSelection);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;

                    case Constants.WS_KEY_SPINNER_SUB_ITEM_EA_SERVICE:

                        if (!localAccess) {

                            subItemEAList = new ArrayList<>();
                            subItemEAs = new ArrayList<>();

                            if (soSubItemElectoralActor.hasProperty(Constants.SOAP_PROPERTY_DIFFGRAM)) {
                                SoapObject soDiffGram = (SoapObject) soSubItemElectoralActor.getProperty(Constants.SOAP_PROPERTY_DIFFGRAM);
                                if (soDiffGram.hasProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET)) {
                                    SoapObject soNewDataSet = (SoapObject) soDiffGram.getProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET);

                                    for (int i = 0; i < soNewDataSet.getPropertyCount(); i++) {
                                        SoapObject soItem = (SoapObject) soNewDataSet.getProperty(i);

                                        SubItemElectoralActor subItemEA = new SubItemElectoralActor();
                                        subItemEA.setIdItem(i);
                                        subItemEA.setIdSubItemEA(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_ID).toString()));
                                        subItemEA.setName(soItem.getProperty(Constants.SOAP_OBJECT_KEY_NAME).toString());

                                        subItemEAs.add(subItemEA);
                                        subItemEAList.add(subItemEA.getName());
                                    }
                                }
                            }
                        }

                        try {
                            ArrayAdapter<String> subItemAdapter = new ArrayAdapter<>(getActivity(),
                                    android.R.layout.simple_spinner_item,
                                    android.R.id.text1, subItemEAList);

                            int electoralActorSelection = (null != _PROFILE_MANAGER.getElectoralProfile().getIdSubItemEA())
                                    ? _PROFILE_MANAGER.getElectoralProfile().getIdSubItemEA() : 0;
                            subItemEASpinner.setAdapter(subItemAdapter);
                            subItemEASpinner.setSelection(electoralActorSelection);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case Constants.WS_KEY_ELECTORAL_SECTION:

                        Sections section = new Sections();
                        section.setId(Integer.valueOf(soDistric.getProperty(Constants.SOAP_OBJECT_KEY_ID).toString()));

                        section.setLocalDistrict((soDistric.hasProperty(Constants.SOAP_OBJECT_KEY_LOCAL_DISTRICT))
                                ? soDistric.getProperty(Constants.SOAP_OBJECT_KEY_LOCAL_DISTRICT).toString()
                                : null);

                        txtLocalDistrict.setText(section.getLocalDistrict());
                        break;

                }

            } else {
                String tempText = (textError.isEmpty() ? "Error desconocido" : textError);
                Toast.makeText(getContext(), tempText, Toast.LENGTH_LONG).show();

            }
        }
    }

    public void attemptMove() {

        cancelMove = false;

        txtElectoralKey.setError(null);

        String electoralKey = txtElectoralKey.getText().toString();

        checkValid(R.id.electoralKey, electoralKey);

    }

    private void checkValid(int id, String value) {

        if (TextUtils.isEmpty(value)) {
            switch (id) {
                case R.id.electoralKey:
                    txtElectoralKey.setError(getActivity().getString(R.string.default_field_required));
                    txtElectoralKey.requestFocus();
                    cancelMove = true;
                    break;
            }
        }

    }
}
