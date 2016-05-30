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


public class ProfessionalProfileFragment extends Fragment implements View.OnClickListener,AdapterView.OnItemSelectedListener {

    static FragmentProfileListener activityListener;

    private static Button backBtn, nextBtn;
    private ProgressDialog pDialog;
    private static Spinner professionalTitleSpinner, companySpinner,careerSpinner,levelSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_professional_profile, container, false);

        backBtn = (Button) view.findViewById(R.id.backBtnProfessionalProfile);
        nextBtn = (Button) view.findViewById(R.id.nextBtnProfessionalProfile);

        backBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);

        professionalTitleSpinner = (Spinner) view.findViewById(R.id.professionalTitle);
        companySpinner = (Spinner) view.findViewById(R.id.company);
        careerSpinner = (Spinner) view.findViewById(R.id.career);
        levelSpinner = (Spinner) view.findViewById(R.id.level);


        try {
            ArrayList<String> levelsList =  BDProfileManagerQuery.getAllLevels(getContext());

            ArrayAdapter<String> levelsAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item,
                    android.R.id.text1,levelsList);

            levelSpinner.setAdapter(levelsAdapter);
            levelSpinner.setSelection(levelsList.size() - 1);
            levelSpinner.setOnItemSelectedListener(this);

            ArrayList<String> titlesList =  BDProfileManagerQuery.getAllTitles(getContext());

            ArrayAdapter<String> titlesAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item,
                    android.R.id.text1,titlesList);

            professionalTitleSpinner.setAdapter(titlesAdapter);
            professionalTitleSpinner.setSelection(titlesList.size() - 1);
            professionalTitleSpinner.setOnItemSelectedListener(this);

            ArrayList<String> companiesList =  BDProfileManagerQuery.getAllCompanies(getContext());

            ArrayAdapter<String> companiesAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item,
                    android.R.id.text1,companiesList);

            companySpinner.setAdapter(companiesAdapter);
            companySpinner.setSelection(companiesList.size() - 1);
            companySpinner.setOnItemSelectedListener(this);

            ArrayList<String> careersList =  BDProfileManagerQuery.getAllCareears(getContext());

            ArrayAdapter<String> careersAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item,
                    android.R.id.text1,careersList);

            careerSpinner.setAdapter(careersAdapter);
            careerSpinner.setSelection(careersList.size() - 1);
            careerSpinner.setOnItemSelectedListener(this);

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
            case R.id.backBtnProfessionalProfile:
                activityListener.moveFragments(v);
                break;
            case R.id.nextBtnProfessionalProfile:
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
