package com.greatnorthcap.compass;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dan on 7/22/2017.
 */

public class ApplyActivity extends AppCompatActivity {
    private Button updateButton;
    private EditText editTextFullName;
    private EditText editTextPhoneNumber;
    private EditText editTextAddress;
    private EditText editTextEmployment;
    private EditText editTextJobTitle;
    private UserSharedPref UserPref = new UserSharedPref();
    String Fullname;
    String PhoneNumber;
    String Address;
    String Employment;
    String JobTitle;
    public RequestQueue requestQueue = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalinformation);
        SharedPreferences sharedPreferences = getSharedPreferences(UserPref.getSharedPrefName(), Context.MODE_PRIVATE);
        updateButton = (Button) findViewById(R.id.updatepersonalinformation);
        editTextFullName = (EditText) findViewById(R.id.fullname);
        editTextPhoneNumber = (EditText) findViewById(R.id.phonenumber);
        editTextAddress = (EditText) findViewById(R.id.streetaddress);
        editTextEmployment = (EditText) findViewById(R.id.placeofemployment);
        editTextJobTitle = (EditText) findViewById(R.id.jobtitle);
       Fullname = sharedPreferences.getString(UserPref.getFullnameSharedPref(), "Null");
        PhoneNumber = sharedPreferences.getString(UserPref.getPhonenumberSharedPref(), "Null");
        Address = sharedPreferences.getString(UserPref.getAddress(), "Null");
        Employment = sharedPreferences.getString(UserPref.getEmploymentSharedPref(), "Null");
        JobTitle = sharedPreferences.getString(UserPref.getJobtitleSharedPref(), "Null");
        String check = "null";
         if(!Fullname.equalsIgnoreCase( check))
        {
            editTextFullName.setText(Fullname);
        }
        if(!PhoneNumber.equalsIgnoreCase( check))
        {
            editTextPhoneNumber.setText(PhoneNumber);
        }
        if(!Address.equalsIgnoreCase( check))
        {
            editTextAddress.setText(Address);
        }
        if(!Employment.equalsIgnoreCase( check))
        {
            editTextEmployment.setText(Employment);
        }
        if(!Fullname.equalsIgnoreCase(check))
        {
            editTextJobTitle.setText(JobTitle);
        }

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fullname = editTextFullName.getText().toString().trim();
                Address = editTextAddress.getText().toString().trim();
                PhoneNumber = editTextPhoneNumber.getText().toString().trim();
                Employment = editTextEmployment.getText().toString().trim();
                JobTitle = editTextJobTitle.getText().toString().trim();
                updateInformation();
            }
        });

    }
    private void updateInformation()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(UserPref.getSharedPrefName(), Context.MODE_PRIVATE);
        final String ID = sharedPreferences.getString(UserPref.getUseridSharedPref(), "Null");
        StringRequest stringGetRequest = new StringRequest(Request.Method.POST, UserPref.getUpdateinformationUrl(),
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response)
                    {
                        Toast.makeText(ApplyActivity.this, response, Toast.LENGTH_SHORT).show();
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(ApplyActivity.this,error.toString(),Toast.LENGTH_SHORT).show();

            }
        }){            @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<>();
            params.put(UserPref.getKeyUserId(),ID);
            params.put(UserPref.getFullName(),Fullname);
            params.put(UserPref.getPhoneNumber(),PhoneNumber);
            params.put(UserPref.getAddress(),Address);
            params.put(UserPref.getEmployment(),Employment);
            params.put(UserPref.getJOB_Title(),JobTitle);
            return params;
        }}
                ;
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringGetRequest);
    }

}
