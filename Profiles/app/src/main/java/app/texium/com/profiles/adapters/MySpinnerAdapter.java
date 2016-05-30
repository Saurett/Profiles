package app.texium.com.profiles.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

import app.texium.com.profiles.models.SpinnerItem;

/**
 * Created by texiumuser on 30/05/2016.
 */
public class MySpinnerAdapter extends ArrayAdapter<SpinnerItem> {

    public MySpinnerAdapter(Context context, int resource, List<SpinnerItem> objects) {
        super(context, resource, objects);
    }

    @Override
    public int getCount() {
        return super.getCount() - 1;
    }

    @Override
    public SpinnerItem getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }


}
