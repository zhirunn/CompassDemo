package com.greatnorthcap.compass;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by aspiree15 on 11/07/17.
 */

public class NintyDayBankStatementActivity extends AppCompatActivity {

    private UserSharedPref UserPref = new UserSharedPref();
    private static final int RESULT_BANK_STATEMENT_IMAGE_GALLERY = 1;
    private static final int RESULT_BANK_STATEMENT_IMAGE_CAMERA = 2;
    ImageView imageViewBankStatement;
    Button buttonBankStatementGallery, buttonBankStatementCamera, buttonUploadBankStatement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nintydaybankstatement);
        imageViewBankStatement = (ImageView) findViewById(R.id.bankstatementimage);
        buttonBankStatementGallery = (Button) findViewById(R.id.bankstatementgallerybutton);
        buttonBankStatementCamera = (Button) findViewById(R.id.bankstatementcamerabutton);
        buttonUploadBankStatement = (Button) findViewById(R.id.uploadbankstatementbutton);

        buttonBankStatementGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bank_statement_gallery_intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(bank_statement_gallery_intent, RESULT_BANK_STATEMENT_IMAGE_GALLERY);
            }
        });

        buttonBankStatementCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bank_statement_camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(bank_statement_camera_intent, RESULT_BANK_STATEMENT_IMAGE_CAMERA);
            }
        });

        buttonUploadBankStatement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadBankStatement();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_BANK_STATEMENT_IMAGE_GALLERY && resultCode == RESULT_OK && data!=null) {
            Uri selectedImage = data.getData();
            imageViewBankStatement.setImageURI(selectedImage);
        }
        if(requestCode == RESULT_BANK_STATEMENT_IMAGE_CAMERA && resultCode == RESULT_OK && data!=null) {
            Bitmap bitmapPhoto = (Bitmap)data.getExtras().get("data");
            imageViewBankStatement.setImageBitmap(bitmapPhoto);
        }
    }

    private void uploadBankStatement() {

    }


}
