package com.example.wang.mywether;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView mTitle;
    TextView mDescription;
    TextView mDateLabel0, mDateLabel1;
    TextView mTelop0, mTelop1;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDescription = (TextView) findViewById(R.id.description);
        mTitle = (TextView) findViewById(R.id.title1);
        mDateLabel0 = (TextView) findViewById(R.id.dateLabel0);
        mDateLabel1 = (TextView) findViewById(R.id.dateLabel1);
        mTelop0 = (TextView) findViewById(R.id.telop0);
        mTelop1 = (TextView) findViewById(R.id.telop1);


        String id = "130010";
        String url = "http://weather.livedoor.com/forecast/webservice/json/v1?city=" + id;
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            mTitle.setText(response.getString("title"));
                            mDescription.setText(response.getJSONObject("description").getString("text"));
                            mDateLabel0.setText(response.getJSONArray("forecasts").getJSONObject(0).getString("dateLabel"));
                            mTelop0.setText(response.getJSONArray("forecasts").getJSONObject(0).getString("telop"));
                            mDateLabel1.setText(response.getJSONArray("forecasts").getJSONObject(1).getString("dateLabel"));
                            mTelop1.setText(response.getJSONArray("forecasts").getJSONObject(1).getString("telop"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, error.toString());
                    }
                }

        );
        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);

    }
}
