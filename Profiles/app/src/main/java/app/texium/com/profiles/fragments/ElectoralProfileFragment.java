package app.texium.com.profiles.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import java.util.ArrayList;

import app.texium.com.profiles.R;
import app.texium.com.profiles.models.ElectoralProfile;
import app.texium.com.profiles.models.PoliticalParties;
import app.texium.com.profiles.models.ProfileManager;
import app.texium.com.profiles.services.SoapServices;
import app.texium.com.profiles.utils.Constants;


public class ElectoralProfileFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    static FragmentProfileListener activityListener;

    private static Button backBtn, nextBtn;
    private static EditText txtOCR, txtElectoralKey,txtValidityINE, txtElectoralSection,txtLocalDistrict,txtFederalDistrict,txtElectoralAdviser,txtSympathizer;
    private ProgressDialog pDialog;

    private static int position;
    private static int idPoliticalParty;

    private Spinner politicalSpinner;

    private ArrayList<String> list;
    private ArrayList<PoliticalParties> politicalParties;

    private static ProfileManager _PROFILE_MANAGER;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_electoral_profile, container, false);

        backBtn = (Button) view.findViewById(R.id.backBtnElectoralProfile);
        nextBtn = (Button) view.findViewById(R.id.nextBtnElectoralProfile);

        txtOCR = (EditText) view.findViewById(R.id.ocrINE);
        txtElectoralKey = (EditText) view.findViewById(R.id.electoralKey);
        txtValidityINE = (EditText) view.findViewById(R.id.validityINE);
        txtElectoralSection = (EditText) view.findViewById(R.id.electoralSection);
        txtLocalDistrict = (EditText) view.findViewById(R.id.localDistrict);
        txtFederalDistrict = (EditText) view.findViewById(R.id.federalDistrict);
        txtElectoralAdviser = (EditText) view.findViewById(R.id.electoralAdviser);
        txtSympathizer = (EditText) view.findViewById(R.id.sympathizer);

        politicalSpinner = (Spinner) view.findViewById(R.id.politicalParty);

        backBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);

        politicalSpinner.setOnItemSelectedListener(this);

        if (null != _PROFILE_MANAGER) {
            if (null != _PROFILE_MANAGER.getElectoralProfile().getOcrINE())
                txtOCR.setText(_PROFILE_MANAGER.getElectoralProfile().getOcrINE());
            if (null != _PROFILE_MANAGER.getElectoralProfile().getElectoralKEY())
                txtElectoralKey.setText(_PROFILE_MANAGER.getElectoralProfile().getElectoralKEY());
            if (null != _PROFILE_MANAGER.getElectoralProfile().getElectoralSection())
                txtElectoralSection.setText(String.valueOf(_PROFILE_MANAGER.getElectoralProfile().getElectoralSection()));
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

        AsyncElectoral wsSpinnerPP = new AsyncElectoral(Constants.WS_KEY_SPINNER_ELECTORAL_SERVICE);
        wsSpinnerPP.execute();

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

                electoralProfile.setOcrINE(txtOCR.getText().toString());
                electoralProfile.setElectoralKEY(txtElectoralKey.getText().toString());
                electoralProfile.setValidityINE(txtValidityINE.getText().toString());

                if (txtElectoralSection.getText().toString().length() > 0) {
                    electoralProfile.setElectoralSection(Integer.valueOf(txtElectoralSection.getText().toString()));
                }

                electoralProfile.setLocalDistrict(txtLocalDistrict.getText().toString());
                electoralProfile.setFederalDistrict(txtFederalDistrict.getText().toString());
                electoralProfile.setElectoralAdviser(txtElectoralAdviser.getText().toString());
                electoralProfile.setPoliticalParty(idPoliticalParty);
                electoralProfile.setIdItemPP(position);

                _PROFILE_MANAGER.setElectoralProfile(electoralProfile);

                activityListener.updateProfile(_PROFILE_MANAGER);
                activityListener.moveFragments(v);
                break;
            case R.id.nextBtnElectoralProfile:

                electoralProfile.setOcrINE(txtOCR.getText().toString());
                electoralProfile.setElectoralKEY(txtElectoralKey.getText().toString());
                electoralProfile.setValidityINE(txtValidityINE.getText().toString());

                if (txtElectoralSection.getText().toString().length() > 0) {
                    electoralProfile.setElectoralSection(Integer.valueOf(txtElectoralSection.getText().toString()));
                }

                electoralProfile.setLocalDistrict(txtLocalDistrict.getText().toString());
                electoralProfile.setFederalDistrict(txtFederalDistrict.getText().toString());
                electoralProfile.setElectoralAdviser(txtElectoralAdviser.getText().toString());
                electoralProfile.setPoliticalParty(idPoliticalParty);
                electoralProfile.setIdItemPP(position);

                _PROFILE_MANAGER.setElectoralProfile(electoralProfile);

                activityListener.updateProfile(_PROFILE_MANAGER);
                activityListener.moveFragments(v);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.position = position;
        PoliticalParties pp = politicalParties.get(position);
        this.idPoliticalParty = pp.getIdPP();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private class AsyncElectoral extends AsyncTask<Void, Void, Boolean> {

        private SoapObject soapObject;
        private Integer webServiceOperation;
        private String textError;

        private AsyncElectoral(Integer wsOperation) {
            webServiceOperation = wsOperation;
            textError = "";
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

            try{
                switch (webServiceOperation) {
                    case Constants.WS_KEY_SPINNER_ELECTORAL_SERVICE:
                        soapObject = SoapServices.getSpinnerPP(getContext());
                        validOperation = (soapObject.getPropertyCount() > 0);
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
            if(success) {

                list = new ArrayList<>();
                politicalParties = new ArrayList<>();


                if (soapObject.hasProperty(Constants.SOAP_PROPERTY_DIFFGRAM)) {
                    SoapObject soDiffGram = (SoapObject) soapObject.getProperty(Constants.SOAP_PROPERTY_DIFFGRAM);
                    if (soDiffGram.hasProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET)) {
                        SoapObject soNewDataSet = (SoapObject) soDiffGram.getProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET);

                        for (int i = 0; i < soNewDataSet.getPropertyCount(); i ++) {
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

                try {
                    //ArrayList<String> list =  BDProfileManagerQuery.getAllPP(getContext());

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                            android.R.layout.simple_spinner_item,
                            android.R.id.text1,list);

                    int actualSelection = (null != _PROFILE_MANAGER.getElectoralProfile().getIdItemPP())
                            ? _PROFILE_MANAGER.getElectoralProfile().getIdItemPP() : 0;
                    politicalSpinner.setAdapter(adapter);
                    politicalSpinner.setSelection(actualSelection);

                } catch (Exception e) {
                    e.printStackTrace();
                }


            } else {
                String tempText = (textError.isEmpty() ? "Error desconocido" : textError);
                Toast.makeText(getContext(), tempText, Toast.LENGTH_LONG).show();

            }
        }
    }

}
