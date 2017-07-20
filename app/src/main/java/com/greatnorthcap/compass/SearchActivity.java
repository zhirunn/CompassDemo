package com.greatnorthcap.compass;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Dan on 7/20/2017.
 */

public class SearchActivity extends AppCompatActivity {
    private UserSharedPref UserPref = new UserSharedPref();
    private Button buttonUser, buttonLoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        buttonUser = (Button) findViewById(R.id.buttonUser);
        buttonLoan = (Button) findViewById(R.id.buttonLoan);

        buttonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, UserSearchActivity.class));
            }
        });
        buttonLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, LoanSearchActivity.class));
            }
        });

    }
}
