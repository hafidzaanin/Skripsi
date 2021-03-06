package com.hafidza.trashme.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hafidza.trashme.fragments.BantuanFragment;
import com.hafidza.trashme.fragments.HomeFragment;
import com.hafidza.trashme.fragments.PengajuanFragment;
import com.hafidza.trashme.fragments.ProfilFragment;
import com.hafidza.trashme.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.MediaStore;
import android.view.MenuItem;


public class HomeActivity extends AppCompatActivity {


    Uri imageUri;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    Fragment fragment = new HomeFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                            .commit();
                    return true;
                case R.id.pengajuan:
                    fragment = new PengajuanFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                            .commit();
                    return true;
                case R.id.pengaduan:
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 0);
                    //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    //File photo = new File(Environment.getExternalStorageDirectory(),  "Pic.jpg");
                    //intent.putExtra(MediaStore.EXTRA_OUTPUT,
                    //        Uri.fromFile(photo));
                    //imageUri = Uri.fromFile(photo);
                    //startActivityForResult(intent, 101);
                    return true;
                case R.id.navigation_bantuan:
                    fragment = new BantuanFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                            .commit();
                    return true;
                case R.id.person:
                    fragment = new ProfilFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                            .commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState == null){
            navigation.setSelectedItemId(R.id.home);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            //Bitmap mphoto = (Bitmap) data.getExtras().get("data");
            //panggil method uploadImage
            //uploadImage(mphoto);

        //switch (requestCode) {
            //case 101:
                //if (resultCode == Activity.RESULT_OK) {
                    //Uri selectedImage = imageUri;
                    //Buka halaman form
                //}
        //}
    }
}

