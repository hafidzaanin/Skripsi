package com.hafidza.trashme.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    Button login;
    TextView registrasi;
    private Object buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nik = (EditText) findViewById(R.id.nik);
        password = (EditText) findViewById(R.id.password);

        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        cek_form(nik);
        cek_form(password);

        registrasi = (TextView) findViewById(R.id.registrasi);

        registrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iRegister = new Intent(getApplicationContext(), RegistrasiActivity.class);
                startActivity(iRegister);
            }
        });
    }

    void login() {
        if (nik.getText().length() < 1) {
            nik.setBackgroundResource(R.drawable.form_error);
        }
        if (password.getText().length() < 1) {
            password.setBackgroundResource(R.drawable.form_error);
        } else {

        }
    }

    void cek_form(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count < 1) {
                    nik.setBackgroundResource(R.drawable.form_error);
                } else {
                    nik.setBackgroundResource(R.drawable.form);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void userSignIn() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing Up...");
        progressDialog.show();

        String nik_text = nik.getText().toString().trim();
        String password_text = password.getText().toString().trim();

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
                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(response.body().getUser());
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid email or password", Toast.LENGTH_LONG).show();
                }
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
        if (v == buttonLogin) {
            userSignIn();
        }
    }
}
