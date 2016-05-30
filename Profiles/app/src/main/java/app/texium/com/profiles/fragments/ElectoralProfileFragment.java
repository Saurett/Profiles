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
import android.widget.Toast;

import java.util.ArrayList;

import app.texium.com.profiles.R;
import app.texium.com.profiles.databases.BDProfileManagerQuery;


public class ElectoralProfileFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    static FragmentProfileListener activityListener;

    private static Button backBtn, nextBtn;
    private ProgressDialog pDialog;

    private int position;
    private String selection;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_electoral_profile, container, false);

        backBtn = (Button) view.findViewById(R.id.backBtnElectoralProfile);
        nextBtn = (Button) view.findViewById(R.id.nextBtnElectoralProfile);
        Spinner politicalSpinner = (Spinner) view.findViewById(R.id.politicalParty);


        backBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);

        try {
            ArrayList<String> list =  BDProfileManagerQuery.getAllPP(getContext());

            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item,
                    android.R.id.text1,list);

            politicalSpinner.setAdapter(adapter);
            politicalSpinner.setSelection(list.size() - 1);
            politicalSpinner.setOnItemSelectedListener(this);

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


    /*
    private TasksDecode attachFiles(TasksDecode tasksDecode) throws Exception {
        try {
            FilesManager sendFile;

            if(TASK_FILES.containsKey(_ACTUAL_POSITION)) {
                sendFile = TASK_FILES.get(_ACTUAL_POSITION);

                List<Uri> uriFilesPicture = sendFile.getFilesPicture();
                List<Uri> uriFileVideo = sendFile.getFilesVideo();

                tasksDecode.setSendVideoFiles(FileServices.attachVideo(getActivity(), uriFileVideo));
                tasksDecode.setSendImgFiles(FileServices.attachImg(getActivity(), uriFilesPicture));

            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("File Exception:",e.getMessage());
            throw  new Exception(e.getMessage());
        }

        return tasksDecode;
    }

    */

    /*
    private class AsyncSendTask extends AsyncTask<Void, Void, Boolean> {

        private SoapPrimitive soapPrimitive;

        private Integer webServiceOperation;
        private View webServiceView;
        private TaskListAdapter webServiceAdapter;
        private Tasks webServiceTask;
        private TasksDecode webServiceTaskDecode;

        private String textError;

        private AsyncSendTask(Integer wsOperation,View wsView,TaskListAdapter wsAdapter,Tasks wsTask
                ,TasksDecode wsServiceTaskDecode) {
            webServiceOperation = wsOperation;
            webServiceView = wsView;
            webServiceAdapter = wsAdapter;
            webServiceTask = wsTask;
            webServiceTaskDecode = wsServiceTaskDecode;
            textError = "";
        }

        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(getContext());
            pDialog.setMessage(getString(R.string.default_attaching_img));
            pDialog.setTitle("Adjuntanto archivos");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            Boolean validOperation = false;

            try{
                switch (webServiceOperation) {
                    case Constants.WS_KEY_UPDATE_TASK_WITH_PICTURE:
                        webServiceTaskDecode = attachFiles(webServiceTaskDecode);
                        validOperation = (webServiceTaskDecode != null);
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

                taskToken = new HashMap<>();
                activityListener.taskActions(webServiceView, webServiceAdapter
                        , webServiceTask, webServiceTaskDecode);

            } else {
                String tempText = (textError.isEmpty() ? "Se excedio el limite de imagenes" : textError);
                Toast.makeText(getContext(), tempText, Toast.LENGTH_LONG).show();

               clearActualFiles();
            }
        }
    }

    */

}
