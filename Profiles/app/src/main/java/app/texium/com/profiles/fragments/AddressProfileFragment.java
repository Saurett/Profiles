package app.texium.com.profiles.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

import app.texium.com.profiles.R;
import app.texium.com.profiles.databases.BDProfileManagerQuery;


public class AddressProfileFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    static FragmentProfileListener activityListener;

    private static Button backBtn, nextBtn;
    private static Spinner stateSpinner, municipalitySpinner,citySpinner;
    private ProgressDialog pDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_address_profile, container, false);

        backBtn = (Button) view.findViewById(R.id.backBtnAddressProfile);
        nextBtn = (Button) view.findViewById(R.id.nextBtnAddressProfile);

        stateSpinner = (Spinner) view.findViewById(R.id.state);
        municipalitySpinner = (Spinner) view.findViewById(R.id.municipality);
        citySpinner = (Spinner) view.findViewById(R.id.city);


        backBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);

        try {
            ArrayList<String> statesList =  BDProfileManagerQuery.getAllStates(getContext());

            ArrayAdapter<String> statesAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item,
                    android.R.id.text1,statesList);

            stateSpinner.setAdapter(statesAdapter);
            stateSpinner.setSelection(statesList.size() - 1);
            stateSpinner.setOnItemSelectedListener(this);

            ArrayList<String> municipalitiesList =  BDProfileManagerQuery.getAllMunicipalities(getContext());

            ArrayAdapter<String> municipalitiesAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item,
                    android.R.id.text1,municipalitiesList);

            municipalitySpinner.setAdapter(municipalitiesAdapter);
            municipalitySpinner.setSelection(municipalitiesList.size() - 1);
            municipalitySpinner.setOnItemSelectedListener(this);

            ArrayList<String> citiesList =  BDProfileManagerQuery.getAllCities(getContext());

            ArrayAdapter<String> citiesAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item,
                    android.R.id.text1,citiesList);

            citySpinner.setAdapter(citiesAdapter);
            citySpinner.setSelection(citiesList.size() - 1);
            citySpinner.setOnItemSelectedListener(this);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
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
            case R.id.backBtnAddressProfile:
                activityListener.moveFragments(v);
                break;
            case R.id.nextBtnAddressProfile:
                activityListener.moveFragments(v);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
