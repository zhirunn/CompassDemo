package com.greatnorthcap.compass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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
import org.w3c.dom.Text;

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
    private TextView TextViewLenderType;
    private TextView TextViewBorrowerType;
    private String Fullname;
    private String PhoneNumber;
    private String Address;
    private String Employment;
    private String JobTitle;
    private Spinner spinnerBorrower;
    private Spinner spinnerLender;
    private String BorrowerType;
    private String LenderType;
    private Button UpdateUser;
    private String[] Options = {"Unscreened","Low Risk", "High Risk"};
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
        TextViewBorrowerType = (TextView) findViewById(R.id.textBorrowerType);
        TextViewLenderType = (TextView) findViewById(R.id.textLenderType);
        spinnerLender = (Spinner) findViewById(R.id.spinnerLenderType);
        spinnerBorrower = (Spinner) findViewById(R.id.spinnerBorrowerType);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Options);
        spinnerLender.setAdapter(adapter);
        spinnerBorrower.setAdapter(adapter);
        SendRequest();
        spinnerLender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch(position)
            {
                case 0:
                    LenderType = "0";
                    break;
                case 1:
                    LenderType = "1";
                    break;
                case 2:
                    LenderType = "2";
                    break;
            }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                LenderType = "0";
            }
        });
        spinnerBorrower.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position)
                {
                    case 0:
                        BorrowerType = "0";
                        break;
                    case 1:
                        BorrowerType = "1";
                        break;
                    case 2:
                        BorrowerType = "2";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                BorrowerType = "0";
            }
        });
        UpdateUser = (Button) findViewById(R.id.buttonUpdateUser);
        UpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateUser();
            }
        });

    }
    protected void UpdateUser()
    {
        StringRequest stringGetRequest = new StringRequest(Request.Method.POST, "https://greatnorthcap.000webhostapp.com/PHP/updateuser.php",
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response)
                    {
                        Toast.makeText(SearchedAccountProfileActivity.this, response, Toast.LENGTH_SHORT).show();
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
            params.put("BorrowerType", BorrowerType);
            params.put("LenderType", LenderType);
            return params;
        }}
                ;
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringGetRequest);
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


        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }


}
