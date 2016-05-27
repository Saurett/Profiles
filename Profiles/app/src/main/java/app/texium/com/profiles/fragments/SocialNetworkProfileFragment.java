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


public class SocialNetworkProfileFragment extends Fragment implements View.OnClickListener {

    static FragmentProfileListener activityListener;

    private static Button backBtn, nextBtn, finishBtn;
    private ProgressDialog pDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_social_network_profile, container, false);

        backBtn = (Button) view.findViewById(R.id.backBtnSNProfile);
        nextBtn = (Button) view.findViewById(R.id.nextBtnSNProfile);
        finishBtn = (Button) view.findViewById(R.id.finishBtnSNProfile);

        backBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
        finishBtn.setOnClickListener(this);

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
            case R.id.backBtnSNProfile:
                activityListener.moveFragments(v);
                break;
            case R.id.nextBtnSNProfile:
                activityListener.moveFragments(v);
                break;
            case R.id.finishBtnSNProfile:
                activityListener.moveFragments(v);
            default:
                break;
        }
    }
}
