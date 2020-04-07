package com.hafidza.trashme.activities;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.hafidza.trashme.api.APIService;
import com.hafidza.trashme.R;
import com.hafidza.trashme.api.APIUrl;
import com.hafidza.trashme.models.Result;
import com.hafidza.trashme.models.User;


public class RegistrasiActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonDaftar;
    private EditText nik, nama_pelanggan, no_telp, jalan, password;
    Spinner jpspinner, kecspinner, kelspinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        buttonDaftar = (Button) findViewById(R.id.buttonDaftar);

        nik = (EditText) findViewById(R.id.nik);
        nama_pelanggan = (EditText) findViewById(R.id.nama_pelanggan);
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
        String no_telp_text = no_telp.getText().toString().trim();
        String jalan_text = jalan.getText().toString().trim();
        String password_text = password.getText().toString().trim();
        String jpspinner_data = jpspinner.getSelectedItem().toString();
        String kecpspinner_data = kecspinner.getSelectedItem().toString();
        String kelspinner_data = kelspinner.getSelectedItem().toString();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        //Defining the user object as we need to pass it with the call
        User user = new User(nik, nama_pelanggan, no_telp,  jalan, password);

        //defining the call
        Call<Result> call = service.createUser(
                user.getNik(),
                user.getNama_pelanggan(),
                user.getNo_telp(),
                user.getJenis_pelanggan(),
                user.getKelurahan(),
                user.getKecamatan(),
                user.getNama_jalan_rt_rw(),
                user.getPassword()
        );

        //calling the api
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                //hiding progress dialog
                progressDialog.dismiss();

                //displaying the message from the response as toast
                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v == buttonDaftar) {
            userSignUp();
        }
    }
}
