package app.texium.com.profiles.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import app.texium.com.profiles.R;
import app.texium.com.profiles.models.CommentProfile;
import app.texium.com.profiles.models.ProfileManager;


public class CommentProfileFragment extends Fragment implements View.OnClickListener {

    static FragmentProfileListener activityListener;

    private static Button backBtn, nextBtn;
    private EditText txtComment;

    private static ProfileManager _PROFILE_MANAGER;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_comment_profile, container, false);


        backBtn = (Button) view.findViewById(R.id.backBtnCommentProfile);
        nextBtn = (Button) view.findViewById(R.id.nextBtnCommentProfile);

        txtComment = (EditText) view.findViewById(R.id.comment);

        backBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);

        if (null != _PROFILE_MANAGER) {
            if (null != _PROFILE_MANAGER.getCommentProfile().getComment())
                txtComment.setText(_PROFILE_MANAGER.getCommentProfile().getComment());
        }

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
        CommentProfile commentProfile = new CommentProfile();

        switch (v.getId()) {
            case R.id.backBtnCommentProfile:

                commentProfile.setComment(txtComment.getText().toString());

                _PROFILE_MANAGER.setCommentProfile(commentProfile);

                activityListener.updateProfile(_PROFILE_MANAGER);
                activityListener.moveFragments(v);
                break;
            case R.id.nextBtnCommentProfile:

                commentProfile.setComment(txtComment.getText().toString());

                _PROFILE_MANAGER.setCommentProfile(commentProfile);

                activityListener.updateProfile(_PROFILE_MANAGER);
                activityListener.moveFragments(v);
                break;
            default:
                break;
        }
    }
}
