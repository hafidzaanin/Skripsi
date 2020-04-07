package com.hafidza.trashme.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hafidza.trashme.R;
import com.hafidza.trashme.api.APIService;
import com.hafidza.trashme.api.APIUrl;
import com.hafidza.trashme.helper.SharedPrefManager;
import com.hafidza.trashme.models.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText nik, password;
    Button buttonLogin;
    TextView registrasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, HomeActivity.class));
        }

        nik = (EditText) findViewById(R.id.nik);
        password = (EditText) findViewById(R.id.password);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(this);
        registrasi = (TextView) findViewById(R.id.registrasi);


        registrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iRegister = new Intent(getApplicationContext(), RegistrasiActivity.class);
                startActivity(iRegister);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonLogin){
            String nik_text = nik.getText().toString().trim();
            String password_text = password.getText().toString().trim();

            if (TextUtils.isEmpty(nik_text)){
                nik.setError("NIK harus diisi");
            }else if (TextUtils.isEmpty(password_text)){
                password.setError("Password harus diisi");
            }else {
                userLogin(nik_text, password_text);
            }
        }
    }


    private void userLogin(String nik, String password) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<Result> call = service.userLogin(nik, password);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                if (!response.body().getError()) {
                    finish();
//                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(response.body().getUser());
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "NIK atau Password anda salah", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }

}
