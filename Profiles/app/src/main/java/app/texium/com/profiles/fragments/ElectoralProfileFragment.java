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
import android.widget.Spinner;
import android.widget.Toast;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import java.util.ArrayList;

import app.texium.com.profiles.R;
import app.texium.com.profiles.services.SoapServices;
import app.texium.com.profiles.utils.Constants;


public class ElectoralProfileFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    static FragmentProfileListener activityListener;

    private static Button backBtn, nextBtn;
    private ProgressDialog pDialog;

    private int position;
    private String selection;
    private Spinner politicalSpinner;

    private ArrayList<String> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_electoral_profile, container, false);

        backBtn = (Button) view.findViewById(R.id.backBtnElectoralProfile);
        nextBtn = (Button) view.findViewById(R.id.nextBtnElectoralProfile);
        politicalSpinner = (Spinner) view.findViewById(R.id.politicalParty);

        backBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);

        politicalSpinner.setOnItemSelectedListener(this);

        return view;
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

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
        switch (v.getId()) {
            case R.id.backBtnElectoralProfile:
                activityListener.moveFragments(v);
                break;
            case R.id.nextBtnElectoralProfile:
                activityListener.moveFragments(v);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.position = position;
        selection = parent.getItemAtPosition(position).toString();

        if (position > 0) {
            Toast.makeText(getActivity(),"Selección actual: " + selection, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getActivity(),"Fred se la come", Toast.LENGTH_SHORT).show();

    }


    private class AsyncElectoral extends AsyncTask<Void, Void, Boolean> {

        private SoapPrimitive soapPrimitive;
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
            pDialog.setMessage("Espere un momento porfavor");
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

                if (soapObject.hasProperty(Constants.SOAP_PROPERTY_DIFFGRAM)) {
                    SoapObject soDiffGram = (SoapObject) soapObject.getProperty(Constants.SOAP_PROPERTY_DIFFGRAM);
                    if (soDiffGram.hasProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET)) {
                        SoapObject soNewDataSet = (SoapObject) soDiffGram.getProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET);

                        for (int i = 0; i < soNewDataSet.getPropertyCount(); i ++) {
                            SoapObject soItem = (SoapObject) soNewDataSet.getProperty(i);
                            list.add(soItem.getProperty(2).toString());
                        }
                    }
                }

                try {
                    //ArrayList<String> list =  BDProfileManagerQuery.getAllPP(getContext());

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                            android.R.layout.simple_spinner_item,
                            android.R.id.text1,list);

                    politicalSpinner.setAdapter(adapter);
                    politicalSpinner.setSelection(0);

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
