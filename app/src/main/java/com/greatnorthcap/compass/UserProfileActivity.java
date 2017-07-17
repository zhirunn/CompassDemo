package com.greatnorthcap.compass;

import android.app.AlertDialog;
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

import static android.content.Intent.FLAG_ACTIVITY_NO_HISTORY;

/**
 * Created by aspiree15 on 16/07/17.
 */

public class UserProfileActivity extends AppCompatActivity {
    private UserSharedPref UserPref = new UserSharedPref();

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        textView = (TextView) findViewById(R.id.textView);

        SharedPreferences sharedPreferences = getSharedPreferences(UserPref.getSharedPrefName(), Context.MODE_PRIVATE);
        String email = sharedPreferences.getString(UserPref.getEmailSharedPref(),"Not Available");

        textView.setText("Current User: " + email);
    }

    private void logoutUser(){
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
        return super.onOptionsItemSelected(item);
    }
}
