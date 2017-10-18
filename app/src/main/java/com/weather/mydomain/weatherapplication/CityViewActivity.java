package com.weather.mydomain.weatherapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CityViewActivity extends AppCompatActivity {

    TextView villeTextView, paysTextView, ventTextView, temperatureTextView, pressionTextView, dateTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_view);

        villeTextView = (TextView) findViewById(R.id.villeTextView);
        paysTextView= (TextView) findViewById(R.id.paysTextView);
        ventTextView= (TextView) findViewById(R.id.ventTextView);
        temperatureTextView = (TextView) findViewById(R.id.temperatureTextView);
        pressionTextView = (TextView) findViewById(R.id.pressionTextView);
        dateTextView = (TextView) findViewById(R.id.dateTextView);

        Intent intent = getIntent();


        villeTextView.setText(intent.getStringExtra("ville"));
        paysTextView.setText(intent.getStringExtra("pays"));
        ventTextView.setText(intent.getStringExtra("vent"));
        temperatureTextView.setText(intent.getStringExtra("temperature"));
        pressionTextView.setText(intent.getStringExtra("pression"));
        dateTextView.setText(intent.getStringExtra("date"));


    }
}
