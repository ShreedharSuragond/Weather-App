package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {
    EditText etCity, etCountry;
    TextView tvResult;
    private final String url = "https://api.openweathermap.org/data/2.5/weather";
    private final String appid = "0a20f080da3f2c076086e3f085d3b5c3";
        //"0a20f080da3f2c076086e3f085d3b5c3"
        //"6a5c813a6fc6c0fa7cd9f91920b13c00";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etCity = findViewById(R.id.etCity);
        etCountry = findViewById(R.id.etCountry);

    }

    public void getWeatherDetails(View view) {
        String tempUrl = "";
        String city = etCity.getText().toString().trim();
        String country = etCountry.getText().toString().trim();
        if (city.equals("")) {
            tvResult=(findViewById(R.id.tvResult));
            tvResult.setText("City field cannot be empty!");
            tvResult.setVisibility(View.VISIBLE);
        }
        else {
            if (!country.equals("")) {
                tempUrl = url + "?q=" + city + "," + country + "&appid=" + appid;
            } else {
                tempUrl = url + "?q=" + city + "&appid=" + appid;
            }
            StringRequest stringRequest = new StringRequest(Request.Method.GET, tempUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray weatherArray = jsonObject.getJSONArray("weather");

                        JSONObject weatherObject = weatherArray.getJSONObject(0);

                        JSONObject mainObject = jsonObject.getJSONObject("main");
                        JSONObject windObject=jsonObject.getJSONObject("wind");
                        JSONObject cloudObject=jsonObject.getJSONObject("clouds");

                        String main = weatherObject.getString("main");
                        String description = weatherObject.getString("description");
                        String iconCode = weatherObject.getString("icon");
                        String temp = mainObject.getString("temp");
                        String pressure = mainObject.getString("pressure");
                        String humidity = mainObject.getString("humidity");
                        String speed=windObject.getString("speed");
                        String deg=windObject.getString("deg");
                        String cloud_coverage=cloudObject.getString("all");



                        Intent intent=new Intent(MainActivity.this,NewActivity.class);
                        intent.putExtra("main_value",main);
                        intent.putExtra("description_value",description);
                        intent.putExtra("icon_value",iconCode);
                        intent.putExtra("temperature_value",temp);
                        intent.putExtra("pressure_value",pressure);
                        intent.putExtra("humidity_value",humidity);
                        intent.putExtra("speed_value",speed);
                        intent.putExtra("deg_value",deg);
                        intent.putExtra("cloud_coverage_value", cloud_coverage);
                        startActivity(intent);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            });

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }
}