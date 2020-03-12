package com.hafidza.trashme.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hafidza.trashme.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginPetugasActivity extends AppCompatActivity {

    private EditText username, password;
    private Button btnlogin;
    private TextView link_regis;
    private ProgressBar loading;
    private static String URL_LOGIN = "http://192.168.43.239/API/login.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_petugas);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnlogin = findViewById(R.id.login);
        loading = findViewById(R.id.loading);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mUsername = username.getText().toString().trim();
                String mPass = password.getText().toString().trim();

                if (!mUsername.isEmpty() || !mPass.isEmpty()){
                    login(mUsername, mPass);
                } else {
                    username.setError("Tolong masukkan username/email");
                    password.setError("Tolong masukkan password");
                }
            }
        });

    }

    private void login(final String username, final String password)
    {
        loading.setVisibility(View.VISIBLE);
        btnlogin.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if (success.equals("1")){

                                for (int i = 0; i < jsonArray.length(); i++){
                                    
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    
                                    String name = object.getString("name").trim();
                                    String email = object.getString("email").trim();

                                    Toast.makeText(LoginPetugasActivity.this,
                                            "Succes Login \nYour Name: "
                                                    +name+"\nYour email: "
                                                    +email, Toast.LENGTH_SHORT)
                                            .show();
                                    loading.setVisibility(View.GONE);
                                }
                            }

                        } catch (JSONException e){
                            e.printStackTrace();
                            loading.setVisibility(View.GONE);
                            btnlogin.setVisibility(View.VISIBLE);
                            Toast.makeText(LoginPetugasActivity.this, "Error" +e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.setVisibility(View.GONE);
                        btnlogin.setVisibility(View.VISIBLE);
                        Toast.makeText(LoginPetugasActivity.this, "Error" + toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
