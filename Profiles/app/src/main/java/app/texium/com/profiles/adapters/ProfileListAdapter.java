package app.texium.com.profiles.adapters;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.texium.com.profiles.R;
import app.texium.com.profiles.fragments.SearchProfileFragment;
import app.texium.com.profiles.models.DecodeProfile;
import app.texium.com.profiles.models.Profile;
import app.texium.com.profiles.models.ProfileManager;

/**
 * Created by texiumuser on 28/06/2016.
 */
public class ProfileListAdapter extends RecyclerView.Adapter<ProfileListAdapter.ViewHolder>  {

    View.OnClickListener onClickListener;
    List<Profile> profiles = new ArrayList<>();

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView profile_name;
        TextView profile_city;
        Button profile_cloud_button;
        Button profile_edit_button;
        Button profile_delete_button;

        public ViewHolder(View itemView) {
            super(itemView);

            profile_name = (TextView) itemView.findViewById(R.id.profile_name);
            profile_city = (TextView) itemView.findViewById(R.id.profile_city);

            profile_cloud_button = (Button) itemView.findViewById(R.id.profile_cloud);
            profile_edit_button = (Button) itemView.findViewById(R.id.profile_edit);
            profile_delete_button = (Button) itemView.findViewById(R.id.profile_delete);

        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Profile profile = profiles.get(position);

        Integer resource = (profile.getProfileCloud() == 0) ?
                R.mipmap.ic_cloud_off_black : R.mipmap.ic_cloud_black;

        holder.profile_name.setText(profile.getProfileName());
        holder.profile_city.setText(profile.getProfileCity());
        holder.profile_cloud_button.setBackgroundResource(resource);

        final DecodeProfile decodeProfile = new DecodeProfile();
        decodeProfile.setProfile(profile);
        decodeProfile.setProfileManager(new ProfileManager());

        holder.profile_cloud_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, profile.getProfileName() + " Sincronizar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        holder.profile_edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decodeProfile.setIdView(v.getId());
                SearchProfileFragment.showQuestion(decodeProfile);
            }
        });

        holder.profile_delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, profile.getProfileName() + "Borrar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return profiles == null ? 0 : profiles.size();
    }

    public void addAll(List<Profile> profiles) {
        this.profiles.addAll(profiles);
    }


}

