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

import static android.content.Intent.FLAG_ACTIVITY_NO_HISTORY;

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
    private Button buttonAcceptLoan;
    private String LenderID;
    private int LenderMoney;
    private int BorrowerMoney;
    private int amountransfer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectedbrokerloan);
        LoanIDTextView = (TextView) findViewById(R.id.tvLoanID);
        tvBorrower = (TextView) findViewById(R.id.tvBorrower);
        buttonDocuments = (Button) findViewById(R.id.buttonDocuments);
        buttonAcceptLoan = (Button) findViewById(R.id.buttonAcceptLoan);
        buttonAcceptLoan.setVisibility( View.VISIBLE);
        SharedPreferences sharedPreferences = getSharedPreferences(UserPref.getSharedPrefName(), Context.MODE_PRIVATE);
        ID = sharedPreferences.getString(UserPref.getSearchedloanidSharedPref(), "Not Available");
        LenderID = sharedPreferences.getString(UserPref.getUseridSharedPref(),"Not Available");
        Borrower = sharedPreferences.getString(UserPref.getBorroweridSharedPref(),"Not Available");
        LoanIDTextView.setText("Loan ID: " + ID);
        SendRequest();
        buttonDocuments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectedLoanActivity.this, ViewLoanImagesActivity.class));
            }
        });
        buttonAcceptLoan.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public  void onClick(View v)
            {
                TransferRequest();



            }
        });
    }
    protected void GetAmount()
    {
        StringRequest stringgetfund = new StringRequest(Request.Method.POST, "https://greatnorthcap.000webhostapp.com/PHP/getloanammount.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        if(response.equalsIgnoreCase(""))
                        {
                            amountransfer =0;
                        }
                        else
                        {
                           amountransfer = Integer.parseInt(response);

                        }
                        if(LenderMoney - amountransfer < 0)
                        {
                            Toast.makeText(SelectedLoanActivity.this,"You have insufficient funds to lend money", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else {
                            LenderMoney = LenderMoney - amountransfer;
                            BorrowerMoney = BorrowerMoney + amountransfer;
                            UpdateLenderMoney();
                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(SelectedLoanActivity.this,error.toString(),Toast.LENGTH_SHORT).show();

            }
        } )
        {            @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<>();
            params.put("id",ID);
            return params;
        }} ;
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringgetfund);
    }
    protected void GetBorrowerMoney()
    {
        StringRequest stringgetfund = new StringRequest(Request.Method.POST, "https://greatnorthcap.000webhostapp.com/PHP/getmoney.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        if(response.equalsIgnoreCase(""))
                        {
                            BorrowerMoney = 0;
                        }
                        else
                        {
                            BorrowerMoney = Integer.parseInt(response);

                        }
                        GetAmount();
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(SelectedLoanActivity.this,error.toString(),Toast.LENGTH_SHORT).show();

            }
        } )
        {            @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<>();
            params.put("id", Borrower);
            return params;
        }} ;
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringgetfund);
    }
    protected void UpdateLoanStatus()
    {
        StringRequest stringgetfund = new StringRequest(Request.Method.POST, "https://greatnorthcap.000webhostapp.com/PHP/updateloanapproved.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        Toast.makeText(SelectedLoanActivity.this,response,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SelectedLoanActivity.this, UserProfileActivity.class);
                        intent.setFlags(FLAG_ACTIVITY_NO_HISTORY);
                        startActivity(intent);
                        finish();
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(SelectedLoanActivity.this,error.toString(),Toast.LENGTH_SHORT).show();

            }
        } )
        {            @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<>();
            params.put("id", ID);
            params.put("lender",LenderID);
            return params;
        }} ;
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringgetfund);
    }
    protected void UpdateBorrowerMoney()
    {
        StringRequest stringgetfund = new StringRequest(Request.Method.POST, "https://greatnorthcap.000webhostapp.com/PHP/setaccountmoney.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {

                       UpdateLoanStatus();
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(SelectedLoanActivity.this,error.toString(),Toast.LENGTH_SHORT).show();

            }
        } )
        {            @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<>();
            params.put("id", Borrower);
            params.put("money", Integer.toString( BorrowerMoney));
            return params;
        }} ;
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringgetfund);

    }
    protected void UpdateLenderMoney()
    {
        StringRequest stringgetfund = new StringRequest(Request.Method.POST, "https://greatnorthcap.000webhostapp.com/PHP/setaccountmoney.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                            UpdateBorrowerMoney();
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(SelectedLoanActivity.this,error.toString(),Toast.LENGTH_SHORT).show();

            }
        } )
        {            @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<>();
            params.put("id", LenderID);
            params.put("money", Integer.toString( LenderMoney));
            return params;
        }} ;
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringgetfund);
    }
    protected void TransferRequest()
    {
        StringRequest stringgetfund = new StringRequest(Request.Method.POST, "https://greatnorthcap.000webhostapp.com/PHP/getmoney.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        if(response.equalsIgnoreCase(""))
                        {
                            LenderMoney = 0;
                        }
                        else{
                            LenderMoney = Integer.parseInt(response);

                        }
                        GetBorrowerMoney();
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(SelectedLoanActivity.this,error.toString(),Toast.LENGTH_SHORT).show();

            }
        } )
        {            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<>();
            params.put("id", LenderID);
            return params;
        }} ;
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringgetfund);
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
                        String C = "4";
                        if (response.equalsIgnoreCase(Unscreened))
                        {
                            tvBorrower.setText("Borrower Type: D");
                        }
                        if (response.equalsIgnoreCase(Lowrisk))
                        {
                            tvBorrower.setText("Borrower Type: A");

                        }
                        if (response.equalsIgnoreCase(Highrisk))
                        {
                            tvBorrower.setText("Borrower Type: B");
                        }
                        if (response.equalsIgnoreCase(C))
                        {
                            tvBorrower.setText("Borrower Type: C");
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
