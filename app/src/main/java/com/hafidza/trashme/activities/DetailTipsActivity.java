package com.hafidza.trashme.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.hafidza.trashme.R;

public class DetailTipsActivity extends AppCompatActivity {

    ImageView imageTips;
    TextView textTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tips);

        imageTips = findViewById(R.id.imagetipsDetail);
        textTitle = findViewById(R.id.detailtipsTitle);

        Intent i = getIntent();

        int image = i.getIntExtra("imageTips", 0);
        imageTips.setImageResource(image);


        String title = i.getStringExtra("titleTips");
        textTitle.setText(title);
    }
}
