package be.ap.edu.veloapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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
import java.util.List;

public class MainActivity extends Activity {

    public static String getAssetJsonData(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("velostation.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        Log.e("data", json);
        return json;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            JSONObject obj = new JSONObject(getAssetJsonData(getApplicationContext()));
            JSONArray m_jArry = obj.getJSONArray("velostations");
            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                Log.d("Details-->", jo_inside.getString("naam"));
                String naam_value = jo_inside.getString("naam");
                String lat_value = jo_inside.getString("point_lat");
                String lng_value = jo_inside.getString("point_lng");

                //Add your values in your `ArrayList` as below:
                m_li = new HashMap<String, String>();
                m_li.put("lat_value", lat_value);
                m_li.put("lng_value", lng_value);
                formList.add(m_li);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListView listView = findViewById(R.id.veloListView);
        listView.setAdapter(new ListAdapter(this, lst));
    }
}



