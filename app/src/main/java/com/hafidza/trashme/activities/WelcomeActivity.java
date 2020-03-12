package com.hafidza.trashme.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hafidza.trashme.R;

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void pengguna(View view) {
        Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    public void petugas (View view) {
        Intent intent = new Intent(WelcomeActivity.this, LoginPetugasActivity.class);
        startActivity(intent);
    }
}

