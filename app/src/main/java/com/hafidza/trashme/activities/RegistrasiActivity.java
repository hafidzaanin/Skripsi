package com.hafidza.trashme.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.hafidza.trashme.R;

public class RegistrasiActivity extends AppCompatActivity {

    Spinner jpspinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        Spinner jpspinner = (Spinner) findViewById(R.id.list_jenis_pelanggan);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegistrasiActivity.this,
            android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.jenis_pelanggan_array));
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        jpspinner.setAdapter(adapter);
    }
}
