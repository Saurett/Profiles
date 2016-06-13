package app.texium.com.profiles.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
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
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

import app.texium.com.profiles.R;
import app.texium.com.profiles.models.AddressProfile;
import app.texium.com.profiles.models.Locations;
import app.texium.com.profiles.models.Municipalities;
import app.texium.com.profiles.models.ProfileManager;
import app.texium.com.profiles.models.States;
import app.texium.com.profiles.services.SoapServices;
import app.texium.com.profiles.utils.Constants;


public class AddressProfileFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    static FragmentProfileListener activityListener;

    private static Button backBtn, nextBtn;
    private static EditText txtStreet, txtNumExt, txtNumInt, txtCity, txtDivision, txtPostalCode;
    private static Spinner stateSpinner, municipalitySpinner, locationSpinner;
    private ProgressDialog pDialog;

    private ArrayList<String> stateList, municipalList, locationList;
    private List<States> states;
    private List<Municipalities> municipalities;
    private List<Locations> locations;

    private static int positionState, positionMunicipal, positionLocation;
    private static int idState, idMunicipal, idLocation;

    private static ProfileManager _PROFILE_MANAGER;
    private boolean cancelMove;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_address_profile, container, false);

        backBtn = (Button) view.findViewById(R.id.backBtnAddressProfile);
        nextBtn = (Button) view.findViewById(R.id.nextBtnAddressProfile);

        txtStreet = (EditText) view.findViewById(R.id.street);
        txtNumInt = (EditText) view.findViewById(R.id.numInt);
        txtNumExt = (EditText) view.findViewById(R.id.numExt);
        txtCity = (EditText) view.findViewById(R.id.city);
        txtDivision = (EditText) view.findViewById(R.id.division);
        txtPostalCode = (EditText) view.findViewById(R.id.postalCode);

        stateSpinner = (Spinner) view.findViewById(R.id.state);
        municipalitySpinner = (Spinner) view.findViewById(R.id.municipality);
        locationSpinner = (Spinner) view.findViewById(R.id.location);


        backBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);

        stateSpinner.setOnItemSelectedListener(this);
        municipalitySpinner.setOnItemSelectedListener(this);
        locationSpinner.setOnItemSelectedListener(this);

        if (null != _PROFILE_MANAGER) {
            if (null != _PROFILE_MANAGER.getAddressProfile().getStreet())
                txtStreet.setText(_PROFILE_MANAGER.getAddressProfile().getStreet());
            if (null != _PROFILE_MANAGER.getAddressProfile().getNumExt())
                txtNumExt.setText(_PROFILE_MANAGER.getAddressProfile().getNumExt());
            if (null != _PROFILE_MANAGER.getAddressProfile().getNumInt())
                txtNumInt.setText(_PROFILE_MANAGER.getAddressProfile().getNumInt());
            if (null != _PROFILE_MANAGER.getAddressProfile().getCity())
                txtCity.setText(_PROFILE_MANAGER.getAddressProfile().getCity());
            if (null != _PROFILE_MANAGER.getAddressProfile().getDivision())
                txtDivision.setText(_PROFILE_MANAGER.getAddressProfile().getDivision());
            if (null != _PROFILE_MANAGER.getAddressProfile().getPostalCode()) {
                String tempPC = String.valueOf(_PROFILE_MANAGER.getAddressProfile().getPostalCode());
                txtPostalCode.setText(tempPC.equals("0") ? null : tempPC);
            }
        }

        return view;
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);

        _PROFILE_MANAGER = activityListener.getProfileManager();

        AsyncAddress wsSpinnerState = new AsyncAddress(Constants.WS_KEY_SPINNER_ADDRESS_STATE_SERVICE);
        wsSpinnerState.execute();
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
        AddressProfile addressProfile = new AddressProfile();
        switch (v.getId()) {
            case R.id.backBtnAddressProfile:

                attemptMove();

                if (!cancelMove) {

                    addressProfile.setStreet(txtStreet.getText().toString());
                    addressProfile.setNumExt(txtNumExt.getText().toString());
                    addressProfile.setNumInt(txtNumInt.getText().toString());
                    addressProfile.setCity(txtCity.getText().toString());
                    addressProfile.setDivision(txtDivision.getText().toString());

                    if (txtPostalCode.getText().toString().length() > 0) {
                        addressProfile.setPostalCode(Integer.valueOf(txtPostalCode.getText().toString()));
                    } else {
                        addressProfile.setPostalCode(0);
                    }

                    addressProfile.setIdState(idState);
                    addressProfile.setIdMunicipal(idMunicipal);
                    addressProfile.setIdLocation(idLocation);
                    addressProfile.setIdItemState(positionState);
                    addressProfile.setIdItemMunicipal(positionMunicipal);
                    addressProfile.setIdItemLocation(positionLocation);

                    _PROFILE_MANAGER.setAddressProfile(addressProfile);

                    activityListener.updateProfile(_PROFILE_MANAGER);
                    activityListener.moveFragments(v);

                }


                break;
            case R.id.nextBtnAddressProfile:

                attemptMove();

                if (!cancelMove) {

                    addressProfile.setStreet(txtStreet.getText().toString());
                    addressProfile.setNumExt(txtNumExt.getText().toString());
                    addressProfile.setNumInt(txtNumInt.getText().toString());
                    addressProfile.setCity(txtCity.getText().toString());
                    addressProfile.setDivision(txtDivision.getText().toString());

                    if (txtPostalCode.getText().toString().length() > 0) {
                        addressProfile.setPostalCode(Integer.valueOf(txtPostalCode.getText().toString()));
                    } else {
                        addressProfile.setPostalCode(0);
                    }

                    addressProfile.setIdState(idState);
                    addressProfile.setIdMunicipal(idMunicipal);
                    addressProfile.setIdLocation(idLocation);
                    addressProfile.setIdItemState(positionState);
                    addressProfile.setIdItemMunicipal(positionMunicipal);
                    addressProfile.setIdItemLocation(positionLocation);

                    _PROFILE_MANAGER.setAddressProfile(addressProfile);

                    activityListener.updateProfile(_PROFILE_MANAGER);
                    activityListener.moveFragments(v);

                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (position > 0) {

            switch (parent.getId()) {
                case R.id.state:
                    positionState = position;
                    States state = states.get(position - 1);
                    idState = state.getIdState();

                    municipalitySpinner.setVisibility(View.VISIBLE);
                    AsyncAddress wsSpinnerMunicipal = new AsyncAddress(
                            Constants.WS_KEY_SPINNER_ADDRESS_MUNICIPAL_SERVICE,
                            state.getIdState());
                    wsSpinnerMunicipal.execute();

                    break;
                case R.id.municipality:
                    positionMunicipal = position;
                    Municipalities municipal = municipalities.get(position - 1);
                    idMunicipal = municipal.getIdMunicipal();

                    locationSpinner.setVisibility(View.VISIBLE);
                    AsyncAddress wsSpinnerLocation = new AsyncAddress(
                            Constants.WS_KEY_SPINNER_ADDRESS_LOCATION_SERVICE,
                            municipal.getIdState(), municipal.getIdMunicipal());
                    wsSpinnerLocation.execute();
                    break;
                case R.id.location:
                    positionLocation = position;
                    Locations location = locations.get(position - 1);
                    idLocation = location.getIdLocation();
                    break;
                default:
                    break;
            }

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private class AsyncAddress extends AsyncTask<Void, Void, Boolean> {

        private SoapObject soapObject;
        private Integer webServiceOperation;
        private Integer webServiceIdState;
        private Integer webServiceIdMunicipal;
        private String textError;

        private AsyncAddress(Integer wsOperation) {
            webServiceOperation = wsOperation;
            textError = "";
        }

        private AsyncAddress(Integer wsOperation, Integer wsIdItem) {
            webServiceOperation = wsOperation;
            webServiceIdState = wsIdItem;
            textError = "";
        }

        private AsyncAddress(Integer wsOperation, Integer wsState, Integer wsMunicipal) {
            webServiceOperation = wsOperation;
            webServiceIdState = wsState;
            webServiceIdMunicipal = wsMunicipal;
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

            try {
                switch (webServiceOperation) {
                    case Constants.WS_KEY_SPINNER_ADDRESS_STATE_SERVICE:
                        soapObject = SoapServices.getSpinnerStates(getContext());
                        validOperation = (soapObject.getPropertyCount() > 0);
                        break;
                    case Constants.WS_KEY_SPINNER_ADDRESS_MUNICIPAL_SERVICE:
                        soapObject = SoapServices.getSpinnerMunicipal(getContext(), webServiceIdState);
                        validOperation = (soapObject.getPropertyCount() > 0);
                        break;
                    case Constants.WS_KEY_SPINNER_ADDRESS_LOCATION_SERVICE:
                        soapObject = SoapServices.getSpinnerLocation(getContext(), webServiceIdState,
                                webServiceIdMunicipal);
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
            if (success) {

                switch (webServiceOperation) {
                    case Constants.WS_KEY_SPINNER_ADDRESS_STATE_SERVICE:
                        stateList = new ArrayList<>();
                        states = new ArrayList<>();
                        stateList.add("Seleccione un estado ...");

                        if (soapObject.hasProperty(Constants.SOAP_PROPERTY_DIFFGRAM)) {
                            SoapObject soDiffGram = (SoapObject) soapObject.getProperty(Constants.SOAP_PROPERTY_DIFFGRAM);
                            if (soDiffGram.hasProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET)) {
                                SoapObject soNewDataSet = (SoapObject) soDiffGram.getProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET);

                                for (int i = 0; i < soNewDataSet.getPropertyCount(); i++) {
                                    SoapObject soItem = (SoapObject) soNewDataSet.getProperty(i);

                                    States state = new States();
                                    state.setIdItem(i);
                                    state.setIdState(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_STATE_ID).toString()));
                                    state.setAcronymName(soItem.getProperty(Constants.SOAP_OBJECT_KEY_STATE_ACRONYM_NAME).toString());
                                    state.setStateName(soItem.getProperty(Constants.SOAP_OBJECT_KEY_STATE_NAME).toString());
                                    state.setIdStatus(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_STATUS).toString()));

                                    states.add(state);
                                    stateList.add(state.getStateName());
                                }
                            }
                        }

                        try {
                            //ArrayList<String> list =  BDProfileManagerQuery.getAllPP(getContext());

                            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                                    android.R.layout.simple_spinner_item,
                                    android.R.id.text1, stateList);

                            int selectionState = (null != _PROFILE_MANAGER.getAddressProfile().getIdItemState())
                                    ? _PROFILE_MANAGER.getAddressProfile().getIdItemState() : 0;
                            stateSpinner.setAdapter(adapter);
                            stateSpinner.setSelection(selectionState);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case Constants.WS_KEY_SPINNER_ADDRESS_MUNICIPAL_SERVICE:
                        municipalList = new ArrayList<>();
                        municipalities = new ArrayList<>();
                        municipalList.add("Seleccione un municipio ...");

                        if (soapObject.hasProperty(Constants.SOAP_PROPERTY_DIFFGRAM)) {
                            SoapObject soDiffGram = (SoapObject) soapObject.getProperty(Constants.SOAP_PROPERTY_DIFFGRAM);
                            if (soDiffGram.hasProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET)) {
                                SoapObject soNewDataSet = (SoapObject) soDiffGram.getProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET);

                                for (int i = 0; i < soNewDataSet.getPropertyCount(); i++) {
                                    SoapObject soItem = (SoapObject) soNewDataSet.getProperty(i);

                                    Municipalities municipal = new Municipalities();
                                    municipal.setIdItem(i);
                                    municipal.setIdState(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_STATE_ID).toString()));
                                    municipal.setStateName(soItem.getProperty(Constants.SOAP_OBJECT_KEY_STATE_NAME).toString());
                                    municipal.setStateAcronym(soItem.getProperty(Constants.SOAP_OBJECT_KEY_STATE_ACRONYM_NAME).toString());
                                    municipal.setIdMunicipal(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_MUNICIPAL_ID).toString()));
                                    municipal.setMunicipalName(soItem.getProperty(Constants.SOAP_OBJECT_KEY_MUNICIPAL_NAME).toString());
                                    municipal.setIdstatus(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_STATUS).toString()));

                                    municipalities.add(municipal);
                                    municipalList.add(municipal.getMunicipalName());
                                }
                            }
                        }

                        try {

                            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                                    android.R.layout.simple_spinner_item,
                                    android.R.id.text1, municipalList);

                            int selectionMunicipal = (null != _PROFILE_MANAGER.getAddressProfile().getIdItemMunicipal())
                                    ? _PROFILE_MANAGER.getAddressProfile().getIdItemMunicipal() : 0;
                            municipalitySpinner.setAdapter(adapter);
                            municipalitySpinner.setSelection(selectionMunicipal);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        break;
                    case Constants.WS_KEY_SPINNER_ADDRESS_LOCATION_SERVICE:
                        locations = new ArrayList<>();
                        locationList = new ArrayList<>();
                        locationList.add("Seleccione una localidad ...");

                        if (soapObject.hasProperty(Constants.SOAP_PROPERTY_DIFFGRAM)) {
                            SoapObject soDiffGram = (SoapObject) soapObject.getProperty(Constants.SOAP_PROPERTY_DIFFGRAM);
                            if (soDiffGram.hasProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET)) {
                                SoapObject soNewDataSet = (SoapObject) soDiffGram.getProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET);

                                for (int i = 0; i < soNewDataSet.getPropertyCount(); i++) {
                                    SoapObject soItem = (SoapObject) soNewDataSet.getProperty(i);

                                    Locations location = new Locations();
                                    location.setIdItem(i);
                                    location.setIdState(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_STATE_ID).toString()));
                                    location.setStateName(soItem.getProperty(Constants.SOAP_OBJECT_KEY_STATE_NAME).toString());
                                    location.setStateAcronym(soItem.getProperty(Constants.SOAP_OBJECT_KEY_STATE_ACRONYM_NAME).toString());
                                    location.setIdMunicipal(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_MUNICIPAL_ID).toString()));
                                    location.setMunicipalName(soItem.getProperty(Constants.SOAP_OBJECT_KEY_MUNICIPAL_NAME).toString());
                                    location.setIdLocation(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_LOCATION_ID).toString()));
                                    location.setLocationName(soItem.getProperty(Constants.SOAP_OBJECT_KEY_LOCATION_NAME).toString());
                                    location.setIdStatus(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_STATUS).toString()));


                                    locations.add(location);
                                    locationList.add(location.getLocationName());
                                }
                            }
                        }

                        try {

                            int selectionLocation = (null != _PROFILE_MANAGER.getAddressProfile().getIdItemLocation())
                                    ? _PROFILE_MANAGER.getAddressProfile().getIdItemLocation() : 0;

                            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                                    android.R.layout.simple_spinner_item,
                                    android.R.id.text1, locationList);

                            locationSpinner.setAdapter(adapter);
                            locationSpinner.setSelection(selectionLocation);

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

    public void attemptMove() {

        cancelMove = false;

        checkValid(R.id.state, idState);

        if (locationSpinner.getVisibility()== View.VISIBLE) {
            checkValid(R.id.location,idLocation);
        }

        if (municipalitySpinner.getVisibility() == View.VISIBLE) {
            checkValid(R.id.municipality,idMunicipal);
        }
    }

    private void checkValid(int id, Integer value) {

        TextView errorText = null;

        if (value <= 0) {
            switch (id) {
                case R.id.state:
                    errorText = (TextView) stateSpinner.getSelectedView();
                    cancelMove = true;
                    break;
                case R.id.municipality:
                    errorText = (TextView) municipalitySpinner.getSelectedView();
                    cancelMove = true;
                    break;
                case R.id.location:
                    errorText = (TextView) locationSpinner.getSelectedView();
                    cancelMove = true;
                    break;
            }

            errorText.setError(getActivity().getString(R.string.default_field_required));
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText(getActivity().getString(R.string.default_field_required));//changes t
            errorText.requestFocus();
        }



    }
}
