package com.greatnorthcap.compass;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by aspiree15 on 19/07/17.
 */

public class GetuseridtestActivity extends AppCompatActivity {

    private EditText editTextId;
    private Button buttonGet;
    private TextView textViewResult;
    private UserSharedPref UserPref = new UserSharedPref();

    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getuseridtest);

        editTextId = (EditText) findViewById(R.id.editTextId);
        buttonGet = (Button) findViewById(R.id.buttonGet);
        textViewResult = (TextView) findViewById(R.id.textViewResult);

        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }

    private void getData() {
        String id = editTextId.getText().toString().trim();
        if (id.equals("")) {
            Toast.makeText(this, "Please enter an id", Toast.LENGTH_LONG).show();
            return;
        }
        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        String url = UserPref.getDatauseridUrl()+editTextId.getText().toString().trim();
        String url2 = "https://greatnorthcap.000webhostapp.com/PHP/getuserid.php?Email=DirtyDan@gmail.com";

        StringRequest stringRequest = new StringRequest(url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GetuseridtestActivity.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response){
        String UserID="";
        String Email="";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(UserPref.getJsonArray());
            JSONObject collegeData = result.getJSONObject(0);
            Email = collegeData.getString(UserPref.getKeyEmail());
            UserID = collegeData.getString(UserPref.getKeyUserId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        textViewResult.setText("UserID:\t"+UserID+"\nEmail:\t" +Email+ "\nPassword:\t");
    }
}

