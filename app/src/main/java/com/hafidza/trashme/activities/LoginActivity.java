package com.hafidza.trashme.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hafidza.trashme.R;

public class LoginActivity extends AppCompatActivity {

    EditText nik, password;
    Button login;
    TextView registrasi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nik=(EditText)findViewById(R.id.nik);
        password=(EditText)findViewById(R.id.password);

        login=(Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        cek_form(nik);
        cek_form(password);

        registrasi=(TextView) findViewById(R.id.registrasi);

        registrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iRegister = new Intent(getApplicationContext(), RegistrasiActivity.class);
                startActivity(iRegister);
            }
        });
    }

    void login()
    {
        if (nik.getText().length()<1)
        {
            nik.setBackgroundResource(R.drawable.form_error);
        }
        if (password.getText().length()<1)
        {
            password.setBackgroundResource(R.drawable.form_error);
        }
        else
        {

        }
    }


    void cek_form(EditText editText)
    {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count<1)
                {
                    nik.setBackgroundResource(R.drawable.form_error);
                }
                else
                {
                    nik.setBackgroundResource(R.drawable.form);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
