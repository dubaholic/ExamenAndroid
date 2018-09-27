package be.ap.edu.veloapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity {


    // ArrayList for person names, email Id's and mobile numbers
    ArrayList<String> naam = new ArrayList<>();
    ArrayList<String> lng = new ArrayList<>();
    ArrayList<String> lat = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // get the reference of RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        try {
            // get JSONObject from JSON file
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            // fetch JSONArray named users
            JSONArray stationArray = obj.getJSONArray("velostation");
            // implement for loop for getting users list data
            for (int i = 0; i < stationArray.length(); i++) {
                // create a JSONObject for fetching single user data
                JSONObject velostationDetail = stationArray.getJSONObject(i);
                // fetch email and name and store it in arraylist
                naam.add(velostationDetail.getString("naam"));
                lng.add(velostationDetail.getString("lng"));
                lat.add(velostationDetail.getString("lat"));
                // create a object for getting contact data from JSONObject
                JSONObject velostation = velostationDetail.getJSONObject("velostation");
                // fetch mobile number and store it in arraylist

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, naam, lng, lat);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("users_list.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    }



