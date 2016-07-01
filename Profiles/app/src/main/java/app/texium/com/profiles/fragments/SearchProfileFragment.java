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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.serialization.SoapObject;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import app.texium.com.profiles.R;
import app.texium.com.profiles.adapters.ProfileListAdapter;
import app.texium.com.profiles.databases.BDProfileManagerQuery;
import app.texium.com.profiles.models.DecodeProfile;
import app.texium.com.profiles.models.Profile;
import app.texium.com.profiles.models.ProfileManager;
import app.texium.com.profiles.services.SoapServices;
import app.texium.com.profiles.utils.Constants;


public class SearchProfileFragment extends Fragment implements View.OnClickListener, SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    private ProgressDialog pDialog;

    static FragmentProfileListener activityListener;

    private static ProfileManager _PROFILE_MANAGER;
    private static List<Profile> profiles = new ArrayList<>();
    private static ProfileListAdapter profiles_adapter;

    private SearchView searchView;
    private static RecyclerView profile_list;
    private static LinearLayout emptySearch;

    private static TextView emptyTitle;
    private static TextView emptyMsg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search_profile, container, false);

        searchView = (SearchView) view.findViewById(R.id.searchView);
        profile_list = (RecyclerView) view.findViewById(R.id.profile_list);
        emptySearch = (LinearLayout) view.findViewById(R.id.emptySearch);

        emptyTitle = (TextView) view.findViewById(R.id.emptyTitle);
        emptyMsg = (TextView) view.findViewById(R.id.emptyMsg);

        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);
        searchView.setFocusable(true);
        searchView.requestFocusFromTouch();

        profiles_adapter = new ProfileListAdapter();

        profiles_adapter.setOnClickListener(this);

        setEmptyView(Constants.LOADING);

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
            AsyncSearch wsSearch = new AsyncSearch(Constants.WS_KEY_PROFILE_SEARCH, query);
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

    public static void showQuestion(DecodeProfile decodeProfile) {
        activityListener.updateDecodeProfile(decodeProfile);
        activityListener.showQuestion();
    }

    public static void removeAt(int position) {
        profiles_adapter.removeItem(position);
       setEmptyView(Constants.SEARCH);
    }

    private static void setEmptyView(Integer request) {
        profile_list.setVisibility((profiles_adapter.getItemCount() > 0) ? View.VISIBLE : View.INVISIBLE);
        emptySearch.setVisibility((profiles_adapter.getItemCount() > 0) ? View.INVISIBLE : View.VISIBLE);

        String title = "¿Desea buscar un perfil?";
        String msg = "Capture un nombre en la barra de busqueda";

        if ((profile_list.getVisibility() == View.INVISIBLE) && (Constants.SEARCH == request)){
            title = "¡No es posible localizar el perfil!";
            msg = "Lo sentimos su busqueda no arrojó resultados";
        }

        emptyTitle.setText(title);
        emptyMsg.setText(msg);
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

            try {
                switch (webServiceOperation) {
                    case Constants.WS_KEY_PROFILE_SEARCH:

                        soapObject = SoapServices.searchProfile(getContext(), webServiceSearch, _PROFILE_MANAGER.getUserProfile().getIdGroup());
                        validOperation = (soapObject.getPropertyCount() > 0);
                        break;
                }
            } catch (ConnectException e) {
                textError = e.getMessage();
                localAccess = true;
                validOperation = true;
            }catch (Exception e) {
                textError = e.getMessage();
                validOperation = false;
            }

            return validOperation;
        }

        @Override
        protected void onPostExecute(final Boolean success) {

            if (success) {

                switch (webServiceOperation) {
                    case Constants.WS_KEY_PROFILE_SEARCH:

                        try {
                            profiles_adapter = new ProfileListAdapter();

                            //LOCAL PROFILES
                            List<Profile> localProfile = BDProfileManagerQuery.getProfiles(getContext(),
                                    webServiceSearch, _PROFILE_MANAGER.getUserProfile().getIdGroup());

                            profiles = new ArrayList<>();
                            profiles.addAll(localProfile);

                           if (!localAccess) {
                               //WEB SERVICE PROFILES
                               if (soapObject.hasProperty(Constants.SOAP_PROPERTY_DIFFGRAM)) {
                                   SoapObject soDiffGramL = (SoapObject) soapObject.getProperty(Constants.SOAP_PROPERTY_DIFFGRAM);
                                   if (soDiffGramL.hasProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET)) {
                                       SoapObject soNewDataSetL = (SoapObject) soDiffGramL.getProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET);

                                       for (int iL = 0; iL < soNewDataSetL.getPropertyCount(); iL++) {
                                           SoapObject soItemL = (SoapObject) soNewDataSetL.getProperty(iL);

                                           Profile profile = new Profile();

                                           String city = soItemL.getProperty(Constants.SOAP_OBJECT_KEY_STATE_NAME).toString() + ", " +
                                                   soItemL.getProperty(Constants.SOAP_OBJECT_KEY_MUNICIPAL_NAME).toString() + "; " +
                                                   soItemL.getProperty(Constants.SOAP_OBJECT_KEY_LOCATION_NAME).toString();

                                           String name =  (soItemL.hasProperty(Constants.SOAP_OBJECT_KEY_NAME)
                                                   ? soItemL.getProperty(Constants.SOAP_OBJECT_KEY_NAME).toString().trim() : "") + " " +
                                                   (soItemL.hasProperty(Constants.SOAP_OBJECT_FIRST_SURNAME)
                                                           ? soItemL.getProperty(Constants.SOAP_OBJECT_FIRST_SURNAME).toString().trim() : "") + " " +
                                                   (soItemL.hasProperty(Constants.SOAP_OBJECT_SECOND_SURNAME)
                                                           ? soItemL.getProperty(Constants.SOAP_OBJECT_SECOND_SURNAME).toString().trim() : "") ;

                                           profile.setIdProfile(Integer.valueOf(soItemL.getProperty(Constants.SOAP_OBJECT_KEY_ID).toString()));
                                           profile.setProfileCity(city);
                                           profile.setProfileName(name);
                                           profile.setCveProfile(Constants.SERVER_SYNC_FALSE);
                                           profile.setProfileCloud(Constants.SERVER_SYNC_TRUE);

                                           profiles.add(profile);
                                       }
                                   }
                               }
                           }

                            Collections.sort(profiles, new Comparator() {
                                @Override
                                public int compare(Object softDrinkOne, Object softDrinkTwo) {
                                    //use instanceof to verify the references are indeed of the type in question
                                    return ((Profile) softDrinkOne).getProfileName()
                                            .compareTo(((Profile) softDrinkTwo).getProfileName());
                                }
                            });

                            /*
                            Collections.sort(profiles, new Comparator() {
                                @Override
                                public int compare(Object softDrinkOne, Object softDrinkTwo) {
                                    //use instanceof to verify the references are indeed of the type in question
                                    return ((Profile) softDrinkOne).getProfileCity()
                                            .compareTo(((Profile) softDrinkTwo).getProfileCity());
                                }
                            });*/

                            //WebService Profiles
                            profiles_adapter.addAll(profiles);

                            profile_list.setAdapter(profiles_adapter);

                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                            profile_list.setLayoutManager(linearLayoutManager);

                            setEmptyView(Constants.SEARCH);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        break;
                }
            } else {
                String tempText = (textError.isEmpty() ? "Error desconocido" : textError);
                Toast.makeText(getContext(), tempText, Toast.LENGTH_LONG).show();

            }

            pDialog.dismiss();
        }
    }
}
