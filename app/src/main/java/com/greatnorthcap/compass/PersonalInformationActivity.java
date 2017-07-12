package com.greatnorthcap.compass;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

/**
 * Created by aspiree15 on 11/07/17.
 */

public class PersonalInformationActivity extends Activity {
    Button personal_information_button;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalinformation);
        personal_information_button = findViewById(R.id.updatepersonalinformation);
    }

}
