package com.greatnorthcap.compass;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by aspiree15 on 11/07/17.
 */

public class NintyDayBankStatementActivity extends AppCompatActivity {

    private UserSharedPref UserPref = new UserSharedPref();
    private static final int RESULT_BANK_STATEMENT_IMAGE_GALLERY = 1;
    private static final int RESULT_BANK_STATEMENT_IMAGE_CAMERA = 2;
    private Bitmap bitmap;
    private String KEY_IMAGE = "Image";
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
                bank_statement_gallery_intent.setType("image/*");
                startActivityForResult(bank_statement_gallery_intent, RESULT_BANK_STATEMENT_IMAGE_GALLERY);
            }
        });

        buttonBankStatementCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bank_statement_camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //bank_statement_camera_intent.setType("image/*");
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

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();

        //The below code will go and convert the BLOB to a String.
        //
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadBankStatement() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UserPref.getImageuploadUrl(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(NintyDayBankStatementActivity.this, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(NintyDayBankStatementActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String image = getStringImage(bitmap);
                Map<String,String> params = new Hashtable<>();
                params.put(KEY_IMAGE, image);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_BANK_STATEMENT_IMAGE_GALLERY && resultCode == RESULT_OK && data!=null) {
            try {
                Uri selectedImage = data.getData();
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                imageViewBankStatement.setImageBitmap(bitmap);
            } catch(IOException ex) {
                throw new RuntimeException("The selected image size might be too large", ex);
            }
        }
        if(requestCode == RESULT_BANK_STATEMENT_IMAGE_CAMERA && resultCode == RESULT_OK && data!=null) {
            bitmap = (Bitmap)data.getExtras().get("data");
            imageViewBankStatement.setImageBitmap(bitmap);
        }
    }

}
