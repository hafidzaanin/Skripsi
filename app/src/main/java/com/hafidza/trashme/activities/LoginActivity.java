package com.hafidza.trashme.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hafidza.trashme.R;

public class LoginActivity extends AppCompatActivity {

    EditText nik, password;
    Button login;
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

    void kirim_data()
    {

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
