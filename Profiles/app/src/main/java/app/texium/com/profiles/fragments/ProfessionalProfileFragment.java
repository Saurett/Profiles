package app.texium.com.profiles.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import app.texium.com.profiles.R;
import app.texium.com.profiles.databases.BDProfileManagerQuery;
import app.texium.com.profiles.models.AcademyLevels;
import app.texium.com.profiles.models.Careers;
import app.texium.com.profiles.models.Companies;
import app.texium.com.profiles.models.ProfessionalProfile;
import app.texium.com.profiles.models.ProfessionalTitles;
import app.texium.com.profiles.models.ProfileManager;
import app.texium.com.profiles.models.Users;
import app.texium.com.profiles.services.SoapServices;
import app.texium.com.profiles.utils.Constants;


public class ProfessionalProfileFragment extends Fragment implements View.OnClickListener,AdapterView.OnItemSelectedListener {

    static FragmentProfileListener activityListener;

    private static Button backBtn, nextBtn;
    private static EditText txtNSS, txtActualJob, txtProfessionalResume;
    private ProgressDialog pDialog;
    private static Spinner professionalTitleSpinner, companySpinner,careerSpinner,levelSpinner;

    private ArrayList<String> academyList, careerList, professionalTitleList, companyList;
    private List<AcademyLevels> academyLevels;
    private List<Careers> careers;
    private List<ProfessionalTitles> professionalTitles;
    private List<Companies> companies;

    private static int positionTitle, positionCompany, positionCareer, positionLevel;
    private static int idTitle, idCompany, idCareer, idLevel;

    private Users SESSION_DATA;
    private static ProfileManager _PROFILE_MANAGER;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_professional_profile, container, false);

        backBtn = (Button) view.findViewById(R.id.backBtnProfessionalProfile);
        nextBtn = (Button) view.findViewById(R.id.nextBtnProfessionalProfile);

        txtNSS = (EditText) view.findViewById(R.id.nss);
        txtActualJob = (EditText) view.findViewById(R.id.actualJob);
        txtProfessionalResume = (EditText) view.findViewById(R.id.professionalResume);

        backBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);

        professionalTitleSpinner = (Spinner) view.findViewById(R.id.professionalTitle);
        companySpinner = (Spinner) view.findViewById(R.id.company);
        careerSpinner = (Spinner) view.findViewById(R.id.career);
        levelSpinner = (Spinner) view.findViewById(R.id.level);

        professionalTitleSpinner.setOnItemSelectedListener(this);
        companySpinner.setOnItemSelectedListener(this);
        careerSpinner.setOnItemSelectedListener(this);
        levelSpinner.setOnItemSelectedListener(this);


        if (null != _PROFILE_MANAGER) {
            if (null != _PROFILE_MANAGER.getProfessionalProfile().getActualJob())
                txtActualJob.setText(_PROFILE_MANAGER.getProfessionalProfile().getActualJob());
            if (null != _PROFILE_MANAGER.getProfessionalProfile().getNss())
                txtNSS.setText(_PROFILE_MANAGER.getProfessionalProfile().getNss());
            if (null != _PROFILE_MANAGER.getProfessionalProfile().getProfessionalResume())
                txtProfessionalResume.setText(_PROFILE_MANAGER.getProfessionalProfile().getProfessionalResume());
        }

        return view;
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);

        SESSION_DATA = (Users) getActivity().getIntent().getExtras().getSerializable(Constants.ACTIVITY_EXTRA_PARAMS_LOGIN);
        _PROFILE_MANAGER = activityListener.getProfileManager();

        AsyncProfessional wsSpinnerProfessional = new AsyncProfessional(Constants.WS_KEY_SPINNER_ALL_PROFESSIONAL_SERVICE);
        wsSpinnerProfessional.execute();
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
        ProfessionalProfile professionalProfile = new ProfessionalProfile();
        switch (v.getId()) {
            case R.id.backBtnProfessionalProfile:

                professionalProfile.setNss(txtNSS.getText().toString());
                professionalProfile.setActualJob(txtActualJob.getText().toString());
                professionalProfile.setCompany(idCompany);
                professionalProfile.setCareer(idCareer);
                professionalProfile.setLevel(idLevel);
                professionalProfile.setProfessionalTitle(idTitle);
                professionalProfile.setIdItemCompany(positionCompany);
                professionalProfile.setIdItemCareer(positionCareer);
                professionalProfile.setIdItemLevel(positionLevel);
                professionalProfile.setIdItemPT(positionTitle);
                professionalProfile.setProfessionalResume(txtProfessionalResume.getText().toString());

                _PROFILE_MANAGER.setProfessionalProfile(professionalProfile);

                activityListener.updateProfile(_PROFILE_MANAGER);
                activityListener.moveFragments(v);
                break;
            case R.id.nextBtnProfessionalProfile:

                professionalProfile.setNss(txtNSS.getText().toString());
                professionalProfile.setActualJob(txtActualJob.getText().toString());
                professionalProfile.setCompany(idCompany);
                professionalProfile.setCareer(idCareer);
                professionalProfile.setLevel(idLevel);
                professionalProfile.setProfessionalTitle(idTitle);
                professionalProfile.setIdItemCompany(positionCompany);
                professionalProfile.setIdItemCareer(positionCareer);
                professionalProfile.setIdItemLevel(positionLevel);
                professionalProfile.setIdItemPT(positionTitle);
                professionalProfile.setProfessionalResume(txtProfessionalResume.getText().toString());

                _PROFILE_MANAGER.setProfessionalProfile(professionalProfile);

                activityListener.updateProfile(_PROFILE_MANAGER);
                activityListener.moveFragments(v);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (position > 0) {
            switch (parent.getId()) {
                case R.id.company:
                    this.positionCompany = position;
                    Companies company = companies.get(position - 1) ;
                    this.idCompany = company.getIdCompany();
                    break;
                case R.id.level:
                    this.positionLevel = position;
                    AcademyLevels level = academyLevels.get(position - 1);
                    this.idLevel = level.getIdAcademyLevel();
                    break;
                case R.id.career:
                    this.positionCareer = position;
                    Careers career = careers.get(position - 1);
                    this.idCareer = career.getIdCareer();
                    break;
                case R.id.professionalTitle:
                    this.positionTitle = position;
                    ProfessionalTitles pt = professionalTitles.get(position - 1);
                    this.idTitle = pt.getIdProfessionalTitle();
                    break;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private class AsyncProfessional extends AsyncTask<Void, Void, Boolean> {

        private SoapObject soapObjectAL;
        private SoapObject soapObjectCareer;
        private SoapObject soapObjectPT;
        private SoapObject soapObjectCompany;
        private Integer webServiceOperation;
        private String textError;
        private Boolean localAccess;

        private AsyncProfessional(Integer wsOperation) {
            webServiceOperation = wsOperation;
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

            try{
                switch (webServiceOperation) {
                    case Constants.WS_KEY_SPINNER_ALL_PROFESSIONAL_SERVICE:
                        soapObjectAL = SoapServices.getSpinnerAcademyLevels(getContext());
                        soapObjectCareer = SoapServices.getSpinnerCareer(getContext());
                        soapObjectPT = SoapServices.getSpinnerProfessionalTitles(getContext());
                        soapObjectCompany = SoapServices.getSpinnerCompanies(getContext(),SESSION_DATA.getIdGroup());
                        validOperation = (soapObjectCompany.getPropertyCount() > 0);
                        break;
                }
            } catch (ConnectException e) {

                textError = e.getMessage();
                validOperation = false;

                e.printStackTrace();
                Log.e("WebServiceException", "Unknown error : " + e.getMessage());

                switch (webServiceOperation) {
                    case Constants.WS_KEY_SPINNER_ALL_PROFESSIONAL_SERVICE:
                        validOperation = true;
                        localAccess = true;

                        academyList = new ArrayList<>();
                        academyLevels = new ArrayList<>();
                        academyList.add("Seleccione un nivel de estudio ...");

                        careerList = new ArrayList<>();
                        careers = new ArrayList<>();
                        careerList.add("Seleccione una carrera ...");

                        professionalTitleList = new ArrayList<>();
                        professionalTitles = new ArrayList<>();
                        professionalTitleList.add("Seleccione ultimo titulo obtenido ...");

                        companyList = new ArrayList<>();
                        companies = new ArrayList<>();
                        companyList.add("Seleccione una empresa ...");

                        try {
                            academyLevels = BDProfileManagerQuery.getAllAL(getContext());

                            for (AcademyLevels data :
                                    academyLevels) {
                                academyList.add(data.getDescription());
                            }

                            careers = BDProfileManagerQuery.getAllCareers(getContext());

                            for (Careers data :
                                    careers) {
                                careerList.add(data.getName());
                            }

                            professionalTitles = BDProfileManagerQuery.getAllPT(getContext());

                            for (ProfessionalTitles data :
                                    professionalTitles) {
                                professionalTitleList.add(data.getName());
                            }

                            companies.addAll(companies = BDProfileManagerQuery.getAllCompany(getContext(), 0));
                            companies.addAll(BDProfileManagerQuery.getAllCompany(getContext(), SESSION_DATA.getIdGroup()));


                            for (Companies data :
                                    companies) {
                                companyList.add(data.getName());
                            }

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
            if(success) {

                switch (webServiceOperation) {
                    case Constants.WS_KEY_SPINNER_ALL_PROFESSIONAL_SERVICE:

                        if (!localAccess) {
                            academyList = new ArrayList<>();
                            academyLevels = new ArrayList<>();
                            academyList.add("Seleccione un nivel de estudio ...");

                            careerList = new ArrayList<>();
                            careers = new ArrayList<>();
                            careerList.add("Seleccione una carrera ...");

                            professionalTitleList = new ArrayList<>();
                            professionalTitles = new ArrayList<>();
                            professionalTitleList.add("Seleccione ultimo titulo obtenido ...");

                            companyList = new ArrayList<>();
                            companies = new ArrayList<>();
                            companyList.add("Seleccione una empresa ...");

                            if (soapObjectAL.hasProperty(Constants.SOAP_PROPERTY_DIFFGRAM)) {
                                SoapObject soDiffGram = (SoapObject) soapObjectAL.getProperty(Constants.SOAP_PROPERTY_DIFFGRAM);
                                if (soDiffGram.hasProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET)) {
                                    SoapObject soNewDataSet = (SoapObject) soDiffGram.getProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET);

                                    for (int i = 0; i < soNewDataSet.getPropertyCount(); i ++) {
                                        SoapObject soItem = (SoapObject) soNewDataSet.getProperty(i);

                                        AcademyLevels al = new AcademyLevels();
                                        al.setIdItem(i);
                                        al.setIdAcademyLevel(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_ID).toString()));
                                        al.setDescription(soItem.getProperty(Constants.SOAP_OBJECT_KEY_DESCRIPTION).toString());
                                        al.setIdStatus(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_STATUS).toString()));

                                        academyLevels.add(al);
                                        academyList.add(al.getDescription());
                                    }
                                }
                            }

                            if (soapObjectCareer.hasProperty(Constants.SOAP_PROPERTY_DIFFGRAM)) {
                                SoapObject soDiffGram = (SoapObject) soapObjectCareer.getProperty(Constants.SOAP_PROPERTY_DIFFGRAM);
                                if (soDiffGram.hasProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET)) {
                                    SoapObject soNewDataSet = (SoapObject) soDiffGram.getProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET);

                                    for (int i = 0; i < soNewDataSet.getPropertyCount(); i ++) {
                                        SoapObject soItem = (SoapObject) soNewDataSet.getProperty(i);

                                        Careers career = new Careers();
                                        career.setIdItem(i);
                                        career.setIdCareer(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_ID).toString()));
                                        career.setName(soItem.getProperty(Constants.SOAP_OBJECT_KEY_NAME).toString());
                                        career.setIdStatus(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_STATUS).toString()));

                                        careers.add(career);
                                        careerList.add(career.getName());
                                    }
                                }
                            }

                            if (soapObjectPT.hasProperty(Constants.SOAP_PROPERTY_DIFFGRAM)) {
                                SoapObject soDiffGram = (SoapObject) soapObjectPT.getProperty(Constants.SOAP_PROPERTY_DIFFGRAM);
                                if (soDiffGram.hasProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET)) {
                                    SoapObject soNewDataSet = (SoapObject) soDiffGram.getProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET);

                                    for (int i = 0; i < soNewDataSet.getPropertyCount(); i ++) {
                                        SoapObject soItem = (SoapObject) soNewDataSet.getProperty(i);

                                        ProfessionalTitles pt = new ProfessionalTitles();
                                        pt.setIdItem(i);
                                        pt.setIdProfessionalTitle(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_ID).toString()));
                                        pt.setName(soItem.getProperty(Constants.SOAP_OBJECT_KEY_NAME).toString());
                                        pt.setIdStatus(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_STATUS).toString()));

                                        professionalTitles.add(pt);
                                        professionalTitleList.add(pt.getName());
                                    }
                                }
                            }

                            if (soapObjectCompany.hasProperty(Constants.SOAP_PROPERTY_DIFFGRAM)) {
                                SoapObject soDiffGram = (SoapObject) soapObjectCompany.getProperty(Constants.SOAP_PROPERTY_DIFFGRAM);
                                if (soDiffGram.hasProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET)) {
                                    SoapObject soNewDataSet = (SoapObject) soDiffGram.getProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET);

                                    for (int i = 0; i < soNewDataSet.getPropertyCount(); i ++) {
                                        SoapObject soItem = (SoapObject) soNewDataSet.getProperty(i);

                                        Companies company = new Companies();
                                        company.setIdItem(i);
                                        company.setIdCompany(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_ID).toString()));
                                        company.setName(soItem.getProperty(Constants.SOAP_OBJECT_KEY_NAME).toString());
                                        company.setIdStatus(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_STATUS).toString()));

                                        companies.add(company);
                                        companyList.add(company.getName());
                                    }
                                }
                            }
                        }

                        try {

                            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                                    android.R.layout.simple_spinner_item,
                                    android.R.id.text1,academyList);

                            ArrayAdapter<String> careerAdapter = new ArrayAdapter<>(getActivity(),
                                    android.R.layout.simple_spinner_item,
                                    android.R.id.text1,careerList);

                            ArrayAdapter<String> ptAdapter = new ArrayAdapter<>(getActivity(),
                                    android.R.layout.simple_spinner_item,
                                    android.R.id.text1,professionalTitleList);

                            ArrayAdapter<String> companyAdapter = new ArrayAdapter<>(getActivity(),
                                    android.R.layout.simple_spinner_item,
                                    android.R.id.text1,companyList);

                            int selectLevel = (null != _PROFILE_MANAGER.getProfessionalProfile().getIdItemLevel())
                                    ? _PROFILE_MANAGER.getProfessionalProfile().getIdItemLevel() : 0;

                            levelSpinner.setAdapter(adapter);
                            levelSpinner.setSelection(selectLevel);

                            int selectCareer = (null != _PROFILE_MANAGER.getProfessionalProfile().getIdItemCareer())
                                    ? _PROFILE_MANAGER.getProfessionalProfile().getIdItemCareer() : 0;

                            careerSpinner.setAdapter(careerAdapter);
                            careerSpinner.setSelection(selectCareer);

                            int selectPT = (null != _PROFILE_MANAGER.getProfessionalProfile().getIdItemPT())
                                    ? _PROFILE_MANAGER.getProfessionalProfile().getIdItemPT() : 0;

                            professionalTitleSpinner.setAdapter(ptAdapter);
                            professionalTitleSpinner.setSelection(selectPT);

                            int selectCompany = (null != _PROFILE_MANAGER.getProfessionalProfile().getIdItemCompany())
                                    ? _PROFILE_MANAGER.getProfessionalProfile().getIdItemCompany() : 0;

                            companySpinner.setAdapter(companyAdapter);
                            companySpinner.setSelection(selectCompany);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                }


            } else {
                String tempText = (textError.isEmpty() ? "Error desconocido" : textError);
                Toast.makeText(getContext(), tempText, Toast.LENGTH_LONG).show();

            }
        }
    }
}
