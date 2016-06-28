package app.texium.com.profiles.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.texium.com.profiles.R;
import app.texium.com.profiles.adapters.ProfileListAdapter;
import app.texium.com.profiles.models.Profile;
import app.texium.com.profiles.models.ProfileManager;


public class SearchProfileFragment extends Fragment implements View.OnClickListener, SearchView.OnQueryTextListener {

    static FragmentProfileListener activityListener;

    private static ProfileManager _PROFILE_MANAGER;
    private static List<Profile> profiles;
    private static ProfileListAdapter profiles_adapter;

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

        SearchView sv = (SearchView) view.findViewById(R.id.searchView);
        sv.setOnQueryTextListener(this);

        profile_list = (RecyclerView) view.findViewById(R.id.profile_list);
        profiles_adapter = new ProfileListAdapter();

        profiles_adapter.setOnClickListener(this);
        profiles_adapter.addAll(profiles);
        profile_list.setAdapter(profiles_adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        profile_list.setLayoutManager(linearLayoutManager);

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
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
