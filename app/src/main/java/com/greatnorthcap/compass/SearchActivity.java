package com.greatnorthcap.compass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dan on 7/20/2017.
 */

public class SearchActivity extends AppCompatActivity {
    private UserSharedPref UserPref = new UserSharedPref();
    private Button buttonAdminUser, buttonAdminLoan,buttonUser,buttonLoan;
    private String LenderType;
    private RequestQueue requestQueue;
    private TextView tvStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        buttonAdminUser = (Button) findViewById(R.id.buttonAdminUser);
        buttonAdminLoan = (Button) findViewById(R.id.buttonAdminLoan);
        buttonUser = (Button) findViewById(R.id.buttonUser);
        buttonLoan = (Button) findViewById(R.id.buttonLoan);
        tvStatus = (TextView) findViewById(R.id.textViewstatus);
        buttonAdminUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, UserSearchActivity.class));
            }
        });
        buttonAdminLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        }
        });
        buttonAdminUser.setVisibility(View.GONE);
        buttonAdminLoan.setVisibility(View.GONE);
        buttonUser.setVisibility(View.GONE);
        buttonLoan.setVisibility(View.GONE);
        buttonLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchActivity.this, LoanSearchActivity.class));
            }
        });
        SendRequest();

    }

    protected void SendRequest()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(UserPref.getSharedPrefName(), Context.MODE_PRIVATE);
        final String  ID = sharedPreferences.getString(UserPref.getKeyUserId(),"Null");
        StringRequest stringGetRequest = new StringRequest(Request.Method.POST, "https://greatnorthcap.000webhostapp.com/PHP/admincheck.php",
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response)
                    {
                        LenderType = response;
                        String AdminString = "3";
                        String Unscreened = "0";
                        if(LenderType.equalsIgnoreCase(AdminString))
                        {

                            buttonAdminUser.setVisibility(View.VISIBLE);
                        }
                        if (!LenderType.equalsIgnoreCase(Unscreened))
                        {
                            buttonLoan.setVisibility(View.VISIBLE);

                        }
                        else
                        {
                            tvStatus.setText("You do not have access to searching for loans");
                        }
                        SharedPreferences sharedPreferences = getSharedPreferences(UserPref.getSharedPrefName(), Context.MODE_PRIVATE);

                        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
                        prefEditor.putString(UserPref.getLendertypeSharedPref(), LenderType);
                        prefEditor.commit();

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(SearchActivity.this,error.toString(),Toast.LENGTH_SHORT).show();

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
}
