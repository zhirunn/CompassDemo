package com.greatnorthcap.compass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Dan on 8/1/2017.
 */

public class SelectedBrokerLoanActivity extends AppCompatActivity {
    private TextView LoanIDTextView;
    private UserSharedPref UserPref = new UserSharedPref();
    private String ID;
    private Button buttonDocuments;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectedbrokerloan);
        LoanIDTextView = (TextView) findViewById(R.id.tvLoanID);
        buttonDocuments = (Button) findViewById(R.id.buttonDocuments);
        SharedPreferences sharedPreferences = getSharedPreferences(UserPref.getSharedPrefName(), Context.MODE_PRIVATE);
        ID = sharedPreferences.getString(UserPref.getSearchedloanidSharedPref(), "Not Available");
        LoanIDTextView.setText("Loan ID: " + ID);

        buttonDocuments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectedBrokerLoanActivity.this, UploadLoanImagesActivity.class));
            }
        });

    }
}

