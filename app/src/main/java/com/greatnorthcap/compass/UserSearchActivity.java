package com.greatnorthcap.compass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

/**
 * Created by Dan on 7/20/2017.
 */

public class UserSearchActivity extends AppCompatActivity{
    String JSONstring;
    private ListView listViewusers;
    private UserSharedPref UserPref = new UserSharedPref();
    public static String[] userIds;
    public static String[] userEmails;
    public static String[] borrowerTypes;
    public static String[] lenderTypes;
    public  String JSON_Array = UserPref.getJsonArray();
    public  String KEY_ID = UserPref.getSearchUserID();
    public String KEY_Email = UserPref.getSearchEmail();
    public String borrowerType = UserPref.getborrowerType();
    public  String lenderType = UserPref.getlenderType();
    private JSONArray users = null;
    public RequestQueue requestQueue = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersearch);
        listViewusers = (ListView) findViewById(R.id.userslistView);
        SendRequest();

        listViewusers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String SearchedID = userIds[position];
                SharedPreferences sharedPreferences = getSharedPreferences(UserPref.getSharedPrefName(), Context.MODE_PRIVATE);
                SharedPreferences.Editor prefEditor = sharedPreferences.edit();
                prefEditor.putString(UserPref.getSearchedIDSharedPref(), SearchedID);
                prefEditor.commit();
                startActivity(new Intent(UserSearchActivity.this, SearchedAccountProfileActivity.class));


            }
        });


    }

    protected void SendRequest()
    {
        StringRequest stringGetRequest = new StringRequest(Request.Method.GET, UserPref.getUserSearchURL(),
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response)
                    {
                        ParseJSON(response);
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(UserSearchActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringGetRequest);

    }
    protected void ParseJSON(String response)
    {
        JSONObject JSONobj = null;
        try {
            JSONobj = new JSONObject(response);

            users = JSONobj.getJSONArray(JSON_Array);
            userIds = new String[users.length()];
            userEmails = new String[users.length()];
            borrowerTypes = new String[users.length()];
            lenderTypes = new String[users.length()];
            for (int i=0; i < users.length();i++)
            {
                JSONObject JO = users.getJSONObject(i);
                userIds[i] = JO.getString(KEY_ID);
                userEmails[i] =  JO.getString(KEY_Email);
                borrowerTypes[i] =  JO.getString(borrowerType);
                lenderTypes[i] = JO.getString(lenderType);

            }
            User ShowUsers = new User(this,userIds,userEmails,borrowerTypes,lenderTypes);
            listViewusers.setAdapter(ShowUsers);

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
};

