package com.greatnorthcap.compass;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Dan on 8/1/2017.
 */

public class SelectedBrokerLoanActivity extends AppCompatActivity {
    private TextView LoanIDTextView;
    private UserSharedPref UserPref = new UserSharedPref();
    private String ID;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectedbrokerloan);
        LoanIDTextView = (TextView) findViewById(R.id.tvLoanID);
        SharedPreferences sharedPreferences = getSharedPreferences(UserPref.getSharedPrefName(), Context.MODE_PRIVATE);
        ID = sharedPreferences.getString(UserPref.getSearchedloanidSharedPref(), "Not Available");
        LoanIDTextView.setText("Loan ID: " + ID);

    }
}

