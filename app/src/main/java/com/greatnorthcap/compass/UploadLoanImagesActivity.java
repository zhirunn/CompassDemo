package com.greatnorthcap.compass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by aspiree15 on 02/08/17.
 */

public class UploadLoanImagesActivity extends Activity {
    private ImageButton imageBNinetyDayBankStatement;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadloanimages);
        imageBNinetyDayBankStatement = findViewById(R.id.ninetyDayBankStatementImageButton);

        imageBNinetyDayBankStatement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UploadLoanImagesActivity.this, NinetyDayBankStatementActivity.class));
            }
        });

    }
}
