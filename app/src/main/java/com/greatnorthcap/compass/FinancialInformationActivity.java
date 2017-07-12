package com.greatnorthcap.compass;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by aspiree15 on 11/07/17.
 */

public class FinancialInformationActivity extends Activity {

    private static final int RESULT_BANK_STATEMENT_IMAGE = 1;
    ImageView bank_statement_image;
    Button bank_statement_button;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financialinformation);
        bank_statement_image = (ImageView) findViewById(R.id.bankstatementimage);
        bank_statement_button = (Button) findViewById(R.id.bankstatementbutton);

        bank_statement_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bank_statement_gallery_intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(bank_statement_gallery_intent, RESULT_BANK_STATEMENT_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_BANK_STATEMENT_IMAGE && resultCode == RESULT_OK && data!=null) {
            Uri selectedImage = data.getData();
            bank_statement_image.setImageURI(selectedImage);
        }
    }


}
