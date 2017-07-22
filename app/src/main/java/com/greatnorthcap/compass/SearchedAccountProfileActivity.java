package com.greatnorthcap.compass;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
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

public class SearchedAccountProfileActivity extends AppCompatActivity {

    private UserSharedPref UserPref = new UserSharedPref();
    private TextView TextViewID;
    private TextView TextViewFullName;
    private TextView TextViewPhoneNumber;
    private TextView TextViewAddress;
    private TextView TextViewEmployment;
    private TextView TextViewJobtitle;
    private String Fullname;
    private String PhoneNumber;
    private String Address;
    private String Employment;
    private String JobTitle;
    public RequestQueue requestQueue = null;
    String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchedaccount);
        SharedPreferences sharedPreferences = getSharedPreferences(UserPref.getSharedPrefName(), Context.MODE_PRIVATE);
        ID = sharedPreferences.getString(UserPref.getSearchedIDSharedPref(), "Not Available");
        TextViewID = (TextView) findViewById(R.id.textViewUserId);
        TextViewFullName = (TextView) findViewById(R.id.textViewFullName);
        TextViewAddress = (TextView) findViewById(R.id.textViewAddress);
        TextViewPhoneNumber= (TextView) findViewById(R.id.textViewPhoneNumber);
        TextViewEmployment = (TextView) findViewById(R.id.textViewEmployment);
        TextViewJobtitle = (TextView) findViewById(R.id.textJobTitle);

        SendRequest();

    }
    protected void SendRequest()
    {
        StringRequest stringGetRequest = new StringRequest(Request.Method.POST, UserPref.getAccountprofileUrl(),
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response)
                    {
                        ParseJSON(response);
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(SearchedAccountProfileActivity.this,error.toString(),Toast.LENGTH_SHORT).show();

            }
        }){            @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<>();
            params.put(UserPref.getKeyUserId(), ID);
            return params;
        }}
                ;
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringGetRequest);

    }
    protected void ParseJSON(String response)
    {
        JSONObject JSONobj = null;
        try {
            JSONobj = new JSONObject(response);
            JSONArray JSONAr=  JSONobj.getJSONArray(UserPref.getJsonArray());
            JSONObject Row = JSONAr.getJSONObject(0);

            TextViewID.setText("User ID: " + Row.getString("UserID"));
            TextViewFullName.setText("Full Name: " + Row.getString("FullName"));
            TextViewPhoneNumber.setText("Phone Number: " + Row.getString("PhoneNumber"));
            TextViewAddress.setText("Address: " + Row.getString("Address"));
            TextViewEmployment.setText("Employment: " + Row.getString("Employment"));
            TextViewJobtitle.setText("Job Title: " + Row.getString("JobTitle"));

            Fullname = Row.getString("FullName");
            PhoneNumber = Row.getString("PhoneNumber");
            Address = Row.getString("Address");
            Employment = Row.getString("Employment");
            JobTitle = Row.getString("JobTitle");
            SharedPreferences sharedPreferences = getSharedPreferences(UserPref.getSharedPrefName(), Context.MODE_PRIVATE);
            SharedPreferences.Editor prefEditor = sharedPreferences.edit();
            prefEditor.putString(UserPref.getFullnameSharedPref(), Fullname);
            prefEditor.putString(UserPref.getPhonenumberSharedPref(), PhoneNumber);
            prefEditor.putString(UserPref.getAddress(), Address);
            prefEditor.putString(UserPref.getEmploymentSharedPref(), Employment);;
            prefEditor.putString(UserPref.getJobtitleSharedPref(), JobTitle);
            prefEditor.commit();

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }


}
