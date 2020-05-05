package com.hafidza.trashme.fragments;

import android.app.DatePickerDialog;
import java.text.SimpleDateFormat;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hafidza.trashme.R;
import com.hafidza.trashme.api.APIService;
import com.hafidza.trashme.api.APIUrl;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.Calendar;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;


public class PengajuanFragment extends Fragment {

    Spinner jalurspinner;
    private DatePickerDialog.OnDateSetListener datePickerDialog;
    private Calendar calendar;
    RelativeLayout rlAlamat;
    private EditText date;
    private SimpleDateFormat dateFormat;
    Double lat,lon;
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssz")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService service = retrofit.create(APIService.class);
    private TextView tvDate, tvAlamat;
    private String alamat,latitude,longitude;
    private int PLACE_PICKER_REQUEST = 1;

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_pengajuan, container, false);

        Spinner jalurspinner = (Spinner) view.findViewById(R.id.jalurSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.jalur_array));
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        jalurspinner.setAdapter(adapter);

        rlAlamat = view.findViewById(R.id.rlAlamat);
        tvAlamat = view.findViewById(R.id.tvAlamat);
        lat = 0.0;

        rlAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder  = new PlacePicker.IntentBuilder();
                try {
                    //menjalankan place picker
                    startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);

                    // check apabila <a title="Solusi Tidak Bisa Download Google Play Services di Android" href="http://www.twoh.co/2014/11/solusi-tidak-bisa-download-google-play-services-di-android/" target="_blank">Google Play Services tidak terinstall</a> di HP
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });


//        date.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Calendar calendar=Calendar.getInstance();
//                final int day=calendar.get(Calendar.DAY_OF_MONTH);
//                final int month=calendar.get(Calendar.MONTH);
//                final int year=calendar.get(Calendar.YEAR);
//                final SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
//                datePickerDialog= new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        //date.setText(dayOfMonth+"-"+(month+1)+"-"+year);
//                        date.setText(dateFormat.format(calendar.getTime()));
//                    }
//                },day,month,year);
//                datePickerDialog.show();
//            }
//        });

        return view;
    };

        //date.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View v) {
                //new DatePickerDialog(getActivity(), datePickerDialog, calendar.get(Calendar.YEAR),
                       // calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            //}
        //});

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //date=view.findViewById(R.id.Tanggal);
        //tvDate = view.findViewById(R.id.tv_date);
        jalurspinner = view.findViewById(R.id.jalurSpinner);

        String id_jalur = "0";
        switch (jalurspinner.getSelectedItem().toString()){
            case "Jalur 1":
                id_jalur = "1";
                break;
            case "Jalur 2":
                id_jalur = "2";
                break;
            case "Jalur 3":
                id_jalur = "3";
                break;
            case "Jalur 4":
                id_jalur = "4";
                break;
            default:
                //id_jalur = "000";
                break;
        }
    }

    private void userKirimAjuan(){

    }

@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //calendar = Calendar.getInstance();
        //datePickerDialog = new DatePickerDialog.OnDateSetListener(){

            //@Override
            //public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
              //  calendar.set(Calendar.YEAR, year);
                //calendar.set(Calendar.MONTH, month);
                //calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                //String tanggal =  sdf.format(calendar.getTime());
                //Toast.makeText(getActivity(), tanggal, Toast.LENGTH_SHORT).show();
                //date.setText(tanggal);
                //tvDate.setText(tanggal);
            //}
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PLACE_PICKER_REQUEST && resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, getActivity());
                String toastMsg = String.format(
                        "Place: %s \n" +
                                "Alamat: %s \n" +
                                "Latlng %s \n", place.getName(), place.getAddress(), place.getLatLng().latitude + " " + place.getLatLng().longitude);
                //tvPlaceAPI.setText(toastMsg);

                tvAlamat.setText(place.getAddress());
                tvAlamat.setVisibility(View.VISIBLE);

                alamat = (String) place.getAddress();
                lat = place.getLatLng().latitude;
                lon = place.getLatLng().longitude;
                latitude = "" + lat;
                longitude = "" + lon;
                //Toast.makeText(getActivity()," "+toastMsg,Toast.LENGTH_SHORT).show();

        }
    }
}
