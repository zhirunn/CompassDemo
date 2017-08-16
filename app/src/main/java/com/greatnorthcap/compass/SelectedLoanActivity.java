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
 * Created by Dan on 8/14/2017.
 */

public class SelectedLoanActivity extends AppCompatActivity {
    private TextView LoanIDTextView;
    private UserSharedPref UserPref = new UserSharedPref();
    private String ID;
    private Button buttonDocuments;
    private RequestQueue requestQueue;
    private String Borrower;
    private TextView tvBorrower;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectedbrokerloan);
        LoanIDTextView = (TextView) findViewById(R.id.tvLoanID);
        tvBorrower = (TextView) findViewById(R.id.tvBorrower);
        buttonDocuments = (Button) findViewById(R.id.buttonDocuments);
        SharedPreferences sharedPreferences = getSharedPreferences(UserPref.getSharedPrefName(), Context.MODE_PRIVATE);
        ID = sharedPreferences.getString(UserPref.getSearchedloanidSharedPref(), "Not Available");
        Borrower = sharedPreferences.getString(UserPref.getBorroweridSharedPref(),"Not Available");
        LoanIDTextView.setText("Loan ID: " + ID);
        SendRequest();
        buttonDocuments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectedLoanActivity.this, UploadLoanImagesActivity.class));
            }
        });
    }
    protected void SendRequest()
    {
        StringRequest stringGetRequest = new StringRequest(Request.Method.POST, "https://greatnorthcap.000webhostapp.com/PHP/usercheck.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        String Unscreened = "0";
                        String Lowrisk = "1";
                        String Highrisk = "2";
                        if (response.equalsIgnoreCase(Unscreened))
                        {
                            tvBorrower.setText("Borrower Type: Unscreened");
                        }
                        if (response.equalsIgnoreCase(Lowrisk))
                        {
                            tvBorrower.setText("Borrower Type: Low Risk Borrower");

                        }
                        if (response.equalsIgnoreCase(Highrisk))
                        {
                            tvBorrower.setText("Borrower Type: High Risk Borrower");
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(SelectedLoanActivity.this,error.toString(),Toast.LENGTH_SHORT).show();

            }
        }){            @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<>();
            params.put(UserPref.getKeyUserId(), Borrower);
            return params;
        }} ;
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringGetRequest);

    }
}
