package com.greatnorthcap.compass;

import android.app.AlertDialog;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

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

import static android.content.Intent.FLAG_ACTIVITY_NO_HISTORY;

/**
 * Created by aspiree15 on 16/07/17.
 */

public class UserProfileActivity extends AppCompatActivity {
    private UserSharedPref UserPref = new UserSharedPref();
    public static final String KEY_USERID = "UserID";

    private TextView textViewDisplayUser, textViewDisplayUserID, textViewDisplayUserFunds, textViewDisplayUserGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setContentView(R.layout.activity_userprofile);

        textViewDisplayUser = (TextView) findViewById(R.id.displayuser);
        textViewDisplayUserID = (TextView) findViewById(R.id.displayuserID);
        textViewDisplayUserFunds = (TextView) findViewById(R.id.displayuserFunds);
        textViewDisplayUserGrade = (TextView) findViewById(R.id.displayuserGrade);

        SharedPreferences sharedPreferences = getSharedPreferences(UserPref.getSharedPrefName(), Context.MODE_PRIVATE);
        String email = sharedPreferences.getString(UserPref.getEmailSharedPref(), "Not Available");
        String userid = sharedPreferences.getString(UserPref.getUseridSharedPref(), "Not Available");
        String money = sharedPreferences.getString(UserPref.getMoneySharedPref(), "Not Available");
        String grade = sharedPreferences.getString(UserPref.getUserGradeSharedPref(), "Not Available");

        textViewDisplayUser.setText("Current User: " + email);
        textViewDisplayUserID.setText("Current UserID: " + userid);
        textViewDisplayUserFunds.setText("Funds: " + money);
        textViewDisplayUserGrade.setText("Grade: " + grade);


        displaymoney();

    }

    private void displaymoney() {
        SharedPreferences sharedPreferences = getSharedPreferences(UserPref.getSharedPrefName(), Context.MODE_PRIVATE);
        final String userid = sharedPreferences.getString(UserPref.getUseridSharedPref(), "Not Available");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://greatnorthcap.000webhostapp.com/PHP/displaymoney.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String money;
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray result = jsonObject.getJSONArray(UserPref.getJsonArray());
                            JSONObject UserData = result.getJSONObject(0);
                            money = UserData.getString(UserPref.getKeyMoney());
                            SharedPreferences sharedPreferences = getSharedPreferences(UserPref.getSharedPrefName(), Context.MODE_PRIVATE);
                            SharedPreferences.Editor prefEditor = sharedPreferences.edit();
                            prefEditor.putString(UserPref.getMoneySharedPref(), money);
                            prefEditor.commit();
                            textViewDisplayUserFunds.setText("Funds: " + money);
                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UserProfileActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put(KEY_USERID, userid);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void logoutUser() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setNegativeButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        SharedPreferences sharedPreferences = getSharedPreferences(UserPref.getSharedPrefName(), Context.MODE_PRIVATE);
                        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
                        prefEditor.putBoolean(UserPref.getLoggedinSharedPref(), false);
                        prefEditor.putString(UserPref.getEmailSharedPref(), "");
                        prefEditor.putString(UserPref.getUseridSharedPref(), "");
                        prefEditor.putString(UserPref.getSearchedloanidSharedPref(), "");
                        prefEditor.putString(UserPref.getUploadtypeSharedPref(), "");
                        prefEditor.putString(UserPref.getMoneySharedPref(), "");
                        prefEditor.commit();
                        Intent intent = new Intent(UserProfileActivity.this, MainActivity.class);
                        intent.setFlags(FLAG_ACTIVITY_NO_HISTORY);
                        startActivity(intent);
                        finish();
                    }
                });

        alertDialogBuilder.setPositiveButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    private void search()
    {
        startActivity(new Intent(UserProfileActivity.this, SearchActivity.class));
    }
    private void accountProfile()
    {
        startActivity(new Intent(UserProfileActivity.this, AccountProfileActivity.class));
    }
    private void apply()
    {
        startActivity(new Intent(UserProfileActivity.this, ApplyActivity.class));
    }
    private void loanstatus()
    {
        startActivity(new Intent(UserProfileActivity.this, LoanStatusActivity.class));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuLogout) {
            logoutUser();
        }
        if (id == R.id.action_search)
        {
            search();
        }
        if( id==R.id.action_profile)
        {
            accountProfile();
        }
        if ( id==R.id.action_apply)
        {
            apply();
        }
        if (id == R.id.action_loanstatus)
        {
            loanstatus();
        }
        return super.onOptionsItemSelected(item);
    }
}
