package com.hafidza.trashme.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.hafidza.trashme.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

public class PengaduanFragment extends Fragment {

    private ImageView imageView;
    String imageUri;
    RelativeLayout rlAlamat;
    Double lat,lon;
    private String alamat,latitude,longitude;
    private TextView tvAlamat;
    private int PLACE_PICKER_REQUEST = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pengaduan, container, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageCamera);

        rlAlamat = view.findViewById(R.id.rlAlamat2);
        tvAlamat = view.findViewById(R.id.tvAlamat2);
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

        return view;
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        Object currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private File getExternalFilesDir(String directoryPictures) {
        return null;
    }

    // private ContentResolver getContentResolver() {
    //}

   // private Uri getIntent(String data_foto) {
    //}

    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);

        //Uri selectedImage = getIntent("DATA_FOTO");
        //getContentResolver().notifyChange(selectedImage, null);

        //ContentResolver cr = getContentResolver();
        //Bitmap bitmap;
        // try {
        // bitmap = android.provider.MediaStore.Images.Media
        //        .getBitmap(cr, selectedImage);
        //imageView.setImageBitmap(bitmap);
        //Toast.makeText(this, selectedImage.toString(), Toast.LENGTH_LONG).show();
        //} catch (Exception e) {
        //Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT).show();
        //Log.e("Camera", e.toString());
        //}

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
