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

import static android.content.Intent.FLAG_ACTIVITY_NO_HISTORY;

/**
 * Created by aspiree15 on 16/07/17.
 */

public class UserProfileActivity extends AppCompatActivity {
    private UserSharedPref UserPref = new UserSharedPref();

    private TextView textViewDisplayUser, textViewDisplayUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setContentView(R.layout.activity_userprofile);

        textViewDisplayUser = (TextView) findViewById(R.id.displayuser);
        textViewDisplayUserID = (TextView) findViewById(R.id.displayuserID);

        SharedPreferences sharedPreferences = getSharedPreferences(UserPref.getSharedPrefName(), Context.MODE_PRIVATE);
        String email = sharedPreferences.getString(UserPref.getEmailSharedPref(), "Not Available");
        String userid = sharedPreferences.getString(UserPref.getUseridSharedPref(), "Not Available");

        textViewDisplayUser.setText("Current User: " + email);
        textViewDisplayUserID.setText("Current UserID: " + userid);
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
        return super.onOptionsItemSelected(item);
    }
}
