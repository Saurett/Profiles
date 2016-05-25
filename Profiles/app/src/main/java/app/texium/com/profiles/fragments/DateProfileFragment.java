package app.texium.com.profiles.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by texiumuser on 25/05/2016.
 */
public class DateProfileFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    EditText txtBirthDate;
    EditText txtAge;

    static int actualYear;

    public DateProfileFragment(EditText birthDate, EditText age) {
        txtBirthDate = birthDate;
        txtAge = age;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        actualYear = year;

        return  new DatePickerDialog(getActivity(), this, year,month,day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth + " / " + (monthOfYear + 1) + " / " + year;
        String ageProfile = String.valueOf(actualYear - year);


        txtBirthDate.setText(date);
        txtAge.setText(ageProfile + " " + "Años");
    }
}
