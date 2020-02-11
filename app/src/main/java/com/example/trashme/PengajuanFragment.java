package com.example.trashme;

import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.Calendar;
import java.util.Locale;


public class PengajuanFragment extends Fragment {

    Spinner spinner;
    private EditText date;
    DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormat;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_pengajuan, container, false);

        String [] values =
                {"-","Rute 1","Rute 2","Rute 3","Rute 4",};
        Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        date=rootView.findViewById(R.id.Tanggal);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar=Calendar.getInstance();
                final int day=calendar.get(Calendar.DAY_OF_MONTH);
                final int month=calendar.get(Calendar.MONTH);
                final int year=calendar.get(Calendar.YEAR);
                final SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                datePickerDialog= new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //date.setText(dayOfMonth+"-"+(month+1)+"-"+year);
                        date.setText(dateFormat.format(calendar.getTime()));
                    }
                },day,month,year);
                datePickerDialog.show();
            }
        });

        return rootView;
    }


}
