package com.example.trashme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsKreasiActivity extends AppCompatActivity {

    ImageView imageKreasi;
    TextView textTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_kreasi);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageKreasi = findViewById(R.id.imagekreasiDetail);
        textTitle = findViewById(R.id.detailkreasiTitle);

        Intent i = getIntent();
        
        int image = i.getIntExtra("imageKreasi", 0);
        imageKreasi.setImageResource(image);
        

        String title = i.getStringExtra("titleTips");
        textTitle.setText(title);


    }

}
