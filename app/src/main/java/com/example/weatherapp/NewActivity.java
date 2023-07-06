package com.example.weatherapp;

import android.widget.ImageView;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


import com.squareup.picasso.Picasso;

public class NewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity);

        String main=getIntent().getStringExtra("main_value");
        ((TextView)findViewById(R.id.weathertypeTextView)).setText(main);


        String description=getIntent().getStringExtra("description_value");
        ((TextView)findViewById(R.id.descriptionTextView)).setText(description);

        String iconCode=getIntent().getStringExtra("icon_value");
        String iconUrl = "https://openweathermap.org/img/wn/" + iconCode + "@2x.png";
        ImageView weatherIconImageView = (ImageView) findViewById(R.id.myImageView);
        Picasso.get()
                .load(iconUrl)
                .placeholder(R.drawable.placeholder_image)  // Replace with your own placeholder image
                .error(R.drawable.error_image)  // Replace with your own error image
                .into(weatherIconImageView);


        String temp =getIntent().getStringExtra("temperature_value");
        double kelvinTemp = Double.parseDouble(temp);
        double celsiusTemp = kelvinTemp - 273.15;
        String formattedTemp = String.format("%.2f", celsiusTemp);
        ((TextView)findViewById(R.id.temperatureTextView)).setText(formattedTemp+" °C");

        Double pressure= Double.valueOf(getIntent().getStringExtra("pressure_value"));
        pressure = pressure/10;
        ((TextView)findViewById(R.id.pressureTextView)).append(pressure+" kPa");

        String humidity=getIntent().getStringExtra("humidity_value");
       ((TextView)findViewById(R.id.humidityTextView)).append(humidity+"%");

        String windSpeed=getIntent().getStringExtra("speed_value");
        ((TextView)findViewById(R.id.windSpeedTextView)).append(windSpeed+" m/s");

        String windDir=getIntent().getStringExtra("deg_value");
        ((TextView)findViewById(R.id.windDirectionTextView)).append(windDir+"º");

        String cloudCoverage=getIntent().getStringExtra("cloud_coverage_value");
        ((TextView)findViewById(R.id.cloudTextView)).append(cloudCoverage+"%");


    }
}
