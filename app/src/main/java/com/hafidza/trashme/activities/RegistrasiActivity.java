package com.hafidza.trashme.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.hafidza.trashme.api.APIService;
import com.hafidza.trashme.R;
import com.hafidza.trashme.api.APIUrl;
import com.hafidza.trashme.api.FileService;
import com.hafidza.trashme.models.FileInfo;
import com.hafidza.trashme.models.Result;
import com.hafidza.trashme.models.User;

import java.io.File;


public class RegistrasiActivity extends AppCompatActivity implements View.OnClickListener {

    FileService fileService;
    Button btnUploadKTP;
    Spinner jpspinner, kecspinner, kelspinner;
    String imagePath;
    private EditText nik, nama_pelanggan, no_telp, email, jalan, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        btnUploadKTP =(Button) findViewById(R.id.btnUploadKTP);
        Button buttonDaftar = (Button) findViewById(R.id.buttonDaftar);
        fileService = APIUrl.getFileService();

        btnUploadKTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, RESULT_OK);
            }
        });

        buttonDaftar.setOnClickListener(this);

        nik = (EditText) findViewById(R.id.nik);
        nama_pelanggan = (EditText) findViewById(R.id.nama_pelanggan);
        email = (EditText) findViewById(R.id.email);
        no_telp = (EditText) findViewById(R.id.no_telp);
        jalan = (EditText) findViewById(R.id.jalan);
        password = (EditText) findViewById(R.id.password);

        Spinner jpspinner = (Spinner) findViewById(R.id.list_jenis_pelanggan);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegistrasiActivity.this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.jenis_pelanggan_array));
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        jpspinner.setAdapter(adapter);

        Spinner kecspinner = (Spinner) findViewById(R.id.list_kecamatan);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(RegistrasiActivity.this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.kecamatan_array));
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        kecspinner.setAdapter(adapter2);

        Spinner kelspinner = (Spinner) findViewById(R.id.list_kelurahan);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(RegistrasiActivity.this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.kelurahan_array));
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        kelspinner.setAdapter(adapter3);



    }

    private void userSignUp() {
        //defining a progress dialog to show while signing up
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        final Spinner jpspinner = (Spinner) findViewById(R.id.list_jenis_pelanggan);
        final Spinner kecspinner = (Spinner) findViewById(R.id.list_kecamatan);
        final Spinner kelspinner = (Spinner) findViewById(R.id.list_kelurahan);

        //getting the user values
        String nik_text = nik.getText().toString().trim();
        String nama_pelanggan_text = nama_pelanggan.getText().toString().trim();
        String email_text = email.getText().toString().trim();
        String no_telp_text = no_telp.getText().toString().trim();
        String jalan_text = jalan.getText().toString().trim();
        String password_text = password.getText().toString().trim();
        String kecpspinner_data = kecspinner.getSelectedItem().toString();
        String kelspinner_data = kelspinner.getSelectedItem().toString();

        String id_jenisPelanggan = "0";
        switch (jpspinner.getSelectedItem().toString()){
            case "Rumah Tangga":
                id_jenisPelanggan = "1";
                break;
            case "Ruko":
                id_jenisPelanggan = "2";
                break;
            default:
                //id_jenisPelanggann = "000";
                break;
        }


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(),requestBody);

        //Call<FileInfo> call2 = fileService.upload(body);

        //Defining the user object as we need to pass it with the call
        User user = new User(nik_text, nama_pelanggan_text, email_text, no_telp_text, id_jenisPelanggan,
                kecpspinner_data, kelspinner_data, jalan_text, password_text);

        //Toast.makeText(RegistrasiActivity.this, nik_text + nama_pelanggan_text + no_telp_text + id_jenisPelanggan + kecpspinner_data + kelspinner_data + jalan_text, Toast.LENGTH_SHORT).show();

        //defining the call
        Call<Result> call = service.createUser(
                nik_text,
                nama_pelanggan_text,
                email_text,
                no_telp_text,
                id_jenisPelanggan,
                kecpspinner_data,
                kelspinner_data,
                jalan_text,
                password_text
        );

        Call<Result> call2 = service.upload(body);


        //calling the api
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                //hiding progress dialog
                progressDialog.dismiss();

                //displaying the message from the response as toast
                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        call2.enqueue(new Callback<Result>() {
          @Override
          public void onResponse(Call<Result> call2, Response<Result> response) {
          if (response.isSuccessful()){
            Toast.makeText(RegistrasiActivity.this, "Image uploaded successfully", Toast.LENGTH_SHORT).show();
          }
        }

        @Override
        public void onFailure(Call<Result> call, Throwable t) {
          Toast.makeText(RegistrasiActivity.this, "ERROR: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
        }
        });


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonDaftar) {
            userSignUp();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_OK){
            if (data == null){
                Toast.makeText(this, "Unable to choose image", Toast.LENGTH_SHORT).show();
                return;
            }

            Uri imageUri = data.getData();
            imagePath = getRealPathFromUri(imageUri);
        }
    }

    private String getRealPathFromUri (Uri uri){
        String projection = (MediaStore.Images.Media.DATA);
        CursorLoader loader = new CursorLoader(getApplicationContext(), uri, new String[]{projection}, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_idx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_idx);
        cursor.close();
        return result;
    }
}
