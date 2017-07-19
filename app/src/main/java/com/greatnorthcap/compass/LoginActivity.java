package com.greatnorthcap.compass;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by aspiree15 on 16/07/17.
 */

public class LoginActivity extends Activity {

    private UserSharedPref UserPref = new UserSharedPref();

    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin;

    private boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail= findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);

        buttonLogin = findViewById(R.id.login);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(UserPref.getSharedPrefName(), Context.MODE_PRIVATE);

        loggedIn = sharedPreferences.getBoolean(UserPref.getLoggedinSharedPref(), false);

        if (loggedIn) {
            Intent intent = new Intent(LoginActivity.this, UserProfileActivity.class);
            startActivity(intent);
        }
    }

    private void loginUser(){
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String dataurl = UserPref.getDatauseridUrl() + email;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, dataurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //if(response.equalsIgnoreCase(UserPref.getLoginSuccess())) {
                            final String userid = getUIDJSON(response).trim();
                            //SharedPreferences sharedPreferences = getSharedPreferences(UserPref.getSharedPrefName(), Context.MODE_PRIVATE);
                            //SharedPreferences.Editor prefEditor = sharedPreferences.edit();
                            //prefEditor.putString(UserPref.getKeyUserId(), userid);
                            //prefEditor.commit();
                        //}
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<>();
            //params.put(UserPref.getKeyUserId(), userid);
            return params;
        }
    };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, UserPref.getLoginURL(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equalsIgnoreCase(UserPref.getLoginSuccess())) {
                            SharedPreferences sharedPreferences = getSharedPreferences(UserPref.getSharedPrefName(), Context.MODE_PRIVATE);
                            SharedPreferences.Editor prefEditor = sharedPreferences.edit();
                            prefEditor.putBoolean(UserPref.getLoggedinSharedPref(), true);
                            prefEditor.putString(UserPref.getEmailSharedPref(), email);
                            //prefEditor.putString(UserPref.getUserId(), userid);
                            prefEditor.commit();
                            Intent intent = new Intent(LoginActivity.this, UserProfileActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(UserPref.getKeyEmail(), email);
                params.put(UserPref.getKeyPassword(), password);
                return params;
            }
        };
        requestQueue.add(stringRequest2);
    }

    public String getUIDJSON(String response) {
        String UID = "";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(UserPref.getJsonArray());
            JSONObject UserData = result.getJSONObject(0);
            UID = UserData.getString(UserPref.getKeyUserId());

            //SharedPreferences sharedPreferences = getSharedPreferences(UserPref.getSharedPrefName(), Context.MODE_PRIVATE);
            //SharedPreferences.Editor prefEditor = sharedPreferences.edit();
            //prefEditor.putString(UserPref.getKeyUserId(), UID);
            //prefEditor.commit();
        } catch (JSONException e){
            e.printStackTrace();
        }
        return UID;
    }
}
