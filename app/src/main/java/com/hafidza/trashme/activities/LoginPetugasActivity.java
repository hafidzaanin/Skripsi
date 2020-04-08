package com.hafidza.trashme.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class LoginPetugasActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnLoginPetugas;
    private EditText editTextUsername, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_petugas);

        if (SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, HomePetugasActivity.class));
        }

        editTextUsername = findViewById(R.id.username);
        editTextPassword = findViewById(R.id.password2);
        btnLoginPetugas = findViewById(R.id.btnLoginPetugas);

        btnLoginPetugas.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLoginPetugas){
            String username = editTextUsername.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            if (TextUtils.isEmpty(username)){
                editTextUsername.setError("Username harus diisi");
            }else if (TextUtils.isEmpty(password)){
                editTextPassword.setError("Password harus diisi");
            }else {
                petugasLogin(username, password);
            }
        }
    }


    private void petugasLogin (String username, String password) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

       username = editTextUsername.getText().toString().trim();
       password = editTextPassword.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);


        Call<Result> call = service.userLogin(username, password);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                if (!response.body().getError()) {
                    finish();
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Username atau Password anda salah", Toast.LENGTH_LONG).show();
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
