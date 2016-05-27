package app.texium.com.profiles.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import app.texium.com.profiles.R;


public class ContactProfileFragment extends Fragment implements View.OnClickListener {

    static FragmentProfileListener activityListener;

    private static Button backBtn, nextBtn;
    private ProgressDialog pDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contact_profile, container, false);

        backBtn = (Button) view.findViewById(R.id.backBtnContactProfile);
        nextBtn = (Button) view.findViewById(R.id.nextBtnContactProfile);

        backBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);

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
            case R.id.backBtnContactProfile:
                activityListener.moveFragments(v);
                break;
            case R.id.nextBtnContactProfile:
                activityListener.moveFragments(v);
                break;
            default:
                break;
        }
    }
}
