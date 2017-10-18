package com.weather.mydomain.weatherapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static com.weather.mydomain.weatherapplication.YahooService.date;
import static com.weather.mydomain.weatherapplication.YahooService.pression;
import static com.weather.mydomain.weatherapplication.YahooService.temperature;
import static com.weather.mydomain.weatherapplication.YahooService.wind;

public class MainActivity extends AppCompatActivity {

    ListView townListView;

    static ArrayList<City> town;

    static ArrayList<String> arrayCities;

    static ArrayAdapter<String> adapter;

    YahooService yahooService;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //town.add(new City("Alger", "Algérie", "20Km/h", "NE", "27c", "1040hPa", "10/10/2017"));

        //arrayCities.add(town.get(0).getVille() + " ( " + town.get(0).getPays().toUpperCase() + " )");

        //Log.d("LOL","Main");

        yahooService = new YahooService();

        town = new ArrayList<>();
        arrayCities = new ArrayList<>();

        townListView = (ListView) findViewById(R.id.townListView);
        adapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1, arrayCities);
        townListView.setAdapter(adapter);

        // Clique sur un élément de la listView
        townListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), CityViewActivity.class);
                    /*intent.putExtra("ville",town.get(position).getVille());
                    intent.putExtra("pays",town.get(position).getPays());
                    intent.putExtra("vent",town.get(position).getVent());
                    intent.putExtra("temperature",town.get(position).getTemperature());
                    intent.putExtra("pression",town.get(position).getPression());
                    intent.putExtra("date",town.get(position).getDate());*/

                String resultat = yahooService.getMeteo(town.get(position).getVille());

                Log.d("LOL",resultat);

                intent.putExtra("ville",town.get(position).getVille());
                intent.putExtra("pays",town.get(position).getPays());
                intent.putExtra("vent",wind);
                intent.putExtra("temperature",temperature);
                intent.putExtra("pression",pression);
                intent.putExtra("date",date);

                startActivityForResult(intent,0);
            }
        });
    }

    public void addCity(View view) {
        Intent intent = new Intent(view.getContext(), AddCityActivity.class);
        startActivityForResult(intent,0);
    }

}