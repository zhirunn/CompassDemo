package com.greatnorthcap.compass;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
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
 * Created by Dan on 7/26/2017.
 */

public class BrokerLoanActivity extends AppCompatActivity {
    private UserSharedPref UserPref = new UserSharedPref();
    private ListView listViewBrokerLoans;
    private String[] LoanIds;
    private String[] AmountsApproved;
    private String[] APR;
    private String[] TermDate;
    private String[] PaymentDue;
    private String[] Principal;
    private String[] Interest;
    private String[] Status;
    private String[] BorrowerID;
    private String[] LenderID;
    private String[] DateLastModified;
    public  String JSON_Array = UserPref.getJsonArray();
    private JSONArray loans = null;
    public RequestQueue requestQueue = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brokerloan);
        listViewBrokerLoans = (ListView) findViewById(R.id.brokerloanlistView);
        SendRequest();
    }


    protected void SendRequest()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(UserPref.getSharedPrefName(), Context.MODE_PRIVATE);
        final String  ID = sharedPreferences.getString(UserPref.getKeyUserId(),"Null");
        StringRequest stringGetRequest = new StringRequest(Request.Method.POST, UserPref.getBrokerloansUrl(),
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
                Toast.makeText(BrokerLoanActivity.this,error.toString(),Toast.LENGTH_SHORT).show();

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
    protected void ParseJSON(String response)
    {
        JSONObject JSONobj;
        try {
            JSONobj = new JSONObject(response);

            loans = JSONobj.getJSONArray(JSON_Array);
            LoanIds= new String[loans.length()];
            AmountsApproved = new String[loans.length()];
            APR = new String[loans.length()];
            TermDate = new String[loans.length()];
            PaymentDue = new String[loans.length()];
            Principal = new String[loans.length()];
            Interest = new String[loans.length()];
            Status = new String[loans.length()];
            BorrowerID = new String[loans.length()];
            LenderID = new String[loans.length()];
            DateLastModified = new String[loans.length()];
            for (int i=0; i < loans.length();i++)
            {
                JSONObject JO = loans.getJSONObject(i);
                LoanIds[i] = JO.getString("LoanID");
                AmountsApproved[i] =  JO.getString("AmountApproved");
                APR[i] =  JO.getString("APR");
                TermDate[i] = JO.getString("TermDate");
                PaymentDue[i] = JO.getString("PaymentDue");
                Principal[i] = JO.getString("Principal");
                Interest[i] = JO.getString("Interest");
                Status[i] = JO.getString("Status");
                BorrowerID[i] = JO.getString("BorrowerID");
                LenderID[i] = JO.getString("LenderID");
                DateLastModified[i] = JO.getString("DateLastModified");
            }
            Loan ShowLoans = new Loan(this,LoanIds,AmountsApproved,APR,TermDate,PaymentDue,Principal,Interest,Status,BorrowerID,LenderID,DateLastModified);
            listViewBrokerLoans.setAdapter(ShowLoans);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
