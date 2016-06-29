package app.texium.com.profiles.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.serialization.SoapObject;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import app.texium.com.profiles.R;
import app.texium.com.profiles.adapters.ProfileListAdapter;
import app.texium.com.profiles.databases.BDProfileManagerQuery;
import app.texium.com.profiles.models.AcademyLevels;
import app.texium.com.profiles.models.Careers;
import app.texium.com.profiles.models.Companies;
import app.texium.com.profiles.models.ProfessionalTitles;
import app.texium.com.profiles.models.Profile;
import app.texium.com.profiles.models.ProfileManager;
import app.texium.com.profiles.services.SoapServices;
import app.texium.com.profiles.utils.Constants;


public class SearchProfileFragment extends Fragment implements View.OnClickListener, SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    private ProgressDialog pDialog;

    static FragmentProfileListener activityListener;

    private static ProfileManager _PROFILE_MANAGER;
    private static List<Profile> profiles;
    private static ProfileListAdapter profiles_adapter;

    private SearchView searchView;

    RecyclerView profile_list;

    static {

        Profile profile = new Profile();
        profiles = new ArrayList<>();

        profile.setProfileName("Francisco Javier Díaz Saurett");
        profile.setProfileCity("Villahermosa, Tabasco");
        profile.setProfileCloud(0);

        profiles.add(profile);


        profile = new Profile();
        profile.setProfileName("Fred Gomez Leyva");
        profile.setProfileCity("Villahermosa, Tabasco");
        profile.setProfileCloud(1);

        profiles.add(profile);

        profile = new Profile();
        profile.setProfileName("Sasha");
        profile.setProfileCity("Villahermosa, Tabasco");
        profile.setProfileCloud(0);

        profiles.add(profile);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search_profile, container, false);

        searchView = (SearchView) view.findViewById(R.id.searchView);
        TextView emptyProfile = (TextView) view.findViewById(R.id.emptyProfile);
        profile_list = (RecyclerView) view.findViewById(R.id.profile_list);

        profile_list.setVisibility(View.INVISIBLE);
        emptyProfile.setVisibility(View.VISIBLE);


        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);
        searchView.setFocusable(true);
        searchView.requestFocusFromTouch();



        profiles_adapter = new ProfileListAdapter();
        profiles_adapter.setOnClickListener(this);




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
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (!TextUtils.isEmpty(query)) {
            AsyncSearch wsSearch = new AsyncSearch(Constants.WS_KEY_PROFILE_SEARCH,query);
            wsSearch.execute();
            searchView.setQuery("", false);
            searchView.clearFocus();
        }

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public boolean onClose() {
        searchView.setQuery("", false);
        return false;
    }

    private class AsyncSearch extends AsyncTask<Void, Void, Boolean> {

        private SoapObject soapObject;
        private Integer webServiceOperation;
        private String webServiceSearch;
        private String textError;
        private Boolean localAccess;

        private AsyncSearch(Integer wsOperation) {
            webServiceOperation = wsOperation;
            textError = "";
            localAccess = false;
        }

        private AsyncSearch(Integer wsOperation, String wsSearch) {
            webServiceOperation = wsOperation;
            webServiceSearch = wsSearch;
            textError = "";
            localAccess = false;
        }

        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(getContext());
            pDialog.setMessage(getContext().getString(R.string.default_loading_title));
            pDialog.setTitle("Cargando perfiles");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            Boolean validOperation = false;

            try{
                switch (webServiceOperation) {
                    case Constants.WS_KEY_PROFILE_SEARCH:
                        /*
                        soapObjectAL = SoapServices.getSpinnerAcademyLevels(getContext());
                        soapObjectCareer = SoapServices.getSpinnerCareer(getContext());
                        soapObjectPT = SoapServices.getSpinnerProfessionalTitles(getContext());
                        soapObjectCompany = SoapServices.getSpinnerCompanies(getContext(),SESSION_DATA.getIdGroup());
                        validOperation = (soapObjectCompany.getPropertyCount() > 0);
                        */
                        validOperation = true;
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
                    case Constants.WS_KEY_PROFILE_SEARCH:

                        if (!localAccess) {


                        }

                        profiles_adapter = new ProfileListAdapter();

                        profiles_adapter.addAll(profiles);
                        profile_list.setAdapter(profiles_adapter);

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        profile_list.setLayoutManager(linearLayoutManager);

                        break;
                }
            } else {
                String tempText = (textError.isEmpty() ? "Error desconocido" : textError);
                Toast.makeText(getContext(), tempText, Toast.LENGTH_LONG).show();

            }
        }
    }
}
