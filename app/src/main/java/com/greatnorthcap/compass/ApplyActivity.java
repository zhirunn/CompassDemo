package com.greatnorthcap.compass;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import static android.content.Intent.FLAG_ACTIVITY_NO_HISTORY;

/**
 * Created by Dan on 7/22/2017.
 */

public class ApplyActivity extends AppCompatActivity {
    private Button updateButton;
    private Button createLoanButton;
    private Button uploadDocumentsButton;
    private EditText editTextFullName;
    private EditText editTextPhoneNumber;
    private EditText editTextAddress;
    private EditText editTextEmployment;
    private EditText editTextJobTitle;
    private UserSharedPref UserPref = new UserSharedPref();
    private String LenderType;
    String Fullname = "null";
    String PhoneNumber = "null";
    String Address = "null";
    String Employment = "null";
    String JobTitle = "null";
    public RequestQueue requestQueue = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalinformation);
        updateButton = (Button) findViewById(R.id.updatepersonalinformation);
        editTextFullName = (EditText) findViewById(R.id.fullname);
        editTextPhoneNumber = (EditText) findViewById(R.id.phonenumber);
        editTextAddress = (EditText) findViewById(R.id.streetaddress);
        editTextEmployment = (EditText) findViewById(R.id.placeofemployment);
        editTextJobTitle = (EditText) findViewById(R.id.jobtitle);
        createLoanButton = (Button) findViewById(R.id.createloan);
        uploadDocumentsButton = (Button) findViewById(R.id.uploaddocuments);
        SendRequest();

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
        createLoanButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
            loanDialog();
            }
        });
        uploadDocumentsButton.setOnClickListener( new View.OnClickListener()
                                                  {
                                                      @Override
                                                      public  void onClick(View v) {
                                                          startActivity(new Intent(ApplyActivity.this, BrokerLoanActivity.class));
                                                      }

                                                  }

        );

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
    protected void SendRequest()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(UserPref.getSharedPrefName(), Context.MODE_PRIVATE);
        final String  ID = sharedPreferences.getString(UserPref.getKeyUserId(),"Null");
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
                Toast.makeText(ApplyActivity.this,error.toString(),Toast.LENGTH_SHORT).show();

            }
        }){            @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<>();
            params.put(UserPref.getKeyUserId(), ID);
            return params;
        }} ;
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringGetRequest);

    }
    protected void CheckAdmin()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(UserPref.getSharedPrefName(), Context.MODE_PRIVATE);
        final String  ID = sharedPreferences.getString(UserPref.getKeyUserId(),"Null");
        StringRequest stringGetRequest = new StringRequest(Request.Method.POST, "https://greatnorthcap.000webhostapp.com/PHP/usercheck.php",
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response)
                    {
                        LenderType = response;

                        String Unscreened = "0";
                        if (LenderType.equalsIgnoreCase(Unscreened))
                        {
                            Toast.makeText(ApplyActivity.this,"You have an unscreened account. You cannot do this.",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            insertLoan();
                        }
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
            params.put(UserPref.getKeyUserId(), ID);
            return params;
        }} ;

        requestQueue.add(stringGetRequest);
    }
    protected void loanDialog()
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to apply for a new loan?");
        alertDialogBuilder.setNegativeButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        CheckAdmin();

                    }
                });

        alertDialogBuilder.setPositiveButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void insertLoan()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(UserPref.getSharedPrefName(), Context.MODE_PRIVATE);
        final String ID = sharedPreferences.getString(UserPref.getUseridSharedPref(), "Null");
        StringRequest stringGetRequest = new StringRequest(Request.Method.POST, UserPref.getInsertbrokerloanUrl(),
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response)
                    {
                        Toast.makeText(ApplyActivity.this, response, Toast.LENGTH_SHORT).show();
                        getRequest();
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
            return params;
        }};

        requestQueue.add(stringGetRequest);
    }
protected void getRequest()
{
    StringRequest stringGetRequest = new StringRequest(Request.Method.GET, UserPref.getNewfolderUrl(),
            new Response.Listener<String>(){
                @Override
                public void onResponse(String response)
                {

                }

            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error)
        {
            Toast.makeText(ApplyActivity.this,error.toString(),Toast.LENGTH_SHORT).show();

        }
    });
    requestQueue.add(stringGetRequest);
}

    protected void ParseJSON(String response)
    {
        JSONObject JSONobj = null;
        try {
            JSONobj = new JSONObject(response);
            JSONArray JSONAr=  JSONobj.getJSONArray(UserPref.getJsonArray());
            JSONObject Row = JSONAr.getJSONObject(0);
            Fullname = Row.getString("FullName");
            PhoneNumber = Row.getString("PhoneNumber");
            Address = Row.getString("Address");
            Employment = Row.getString("Employment");
            JobTitle = Row.getString("JobTitle");
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


        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
