package com.hafidza.trashme.activities;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hafidza.trashme.fragments.HomePetugasFragment;
import com.hafidza.trashme.fragments.BantuanPetugasFragment;
import com.hafidza.trashme.R;
import com.hafidza.trashme.fragments.ProfilPetugasFragment;

import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;

public class HomePetugasActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Fragment fragment = new HomePetugasFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_petugas_layout, fragment, fragment.getClass().getSimpleName())
                            .commit();
                    return true;
                case R.id.navigation_profil:
                    fragment = new ProfilPetugasFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_petugas_layout, fragment, fragment.getClass().getSimpleName())
                            .commit();
                    return true;
                case R.id.navigation_bantuan2:
                    fragment = new BantuanPetugasFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_petugas_layout, fragment, fragment.getClass().getSimpleName())
                            .commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_petugas);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState == null){
            navigation.setSelectedItemId(R.id.navigation_home);
        }
    }

}
