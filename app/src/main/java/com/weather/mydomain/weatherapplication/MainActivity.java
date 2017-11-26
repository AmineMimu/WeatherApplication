package com.weather.mydomain.weatherapplication;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

import static com.weather.mydomain.weatherapplication.YahooService.date;
import static com.weather.mydomain.weatherapplication.YahooService.pression;
import static com.weather.mydomain.weatherapplication.YahooService.temperature;
import static com.weather.mydomain.weatherapplication.YahooService.wind;

public class MainActivity extends AppCompatActivity {

    ListView townListView;
    static List<City> town;    // ArrayList of the cities
    static ArrayList<String> arrayCities;   // ArrayList of the cities displayed on the ListView
    static ArrayAdapter<String> adapter;
    YahooService yahooService;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        DataBaseHandler myDB = new DataBaseHandler(this);
        setContentView(R.layout.activity_main);
        yahooService = new YahooService();
        town = myDB.getAllCities();
        arrayCities = new ArrayList<>();


        for (City city : town){
            arrayCities.add(city.getVille());
        }

        townListView = (ListView) findViewById(R.id.townListView);
        adapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1, arrayCities);
        townListView.setAdapter(adapter);

        // When click on an element from the ListView displays the details of the clicked item
        townListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), CityViewActivity.class);

                String resultat = yahooService.getMeteo(town.get(position).getVille());

                // Send those data to CityViewActivity
                intent.putExtra("ville",town.get(position).getVille());
                intent.putExtra("pays",town.get(position).getPays());
                intent.putExtra("vent",wind);
                intent.putExtra("temperature",temperature);
                intent.putExtra("pression",pression);
                intent.putExtra("date",date);

                startActivityForResult(intent,0);
            }
        });

        // When long click on element to destroy element
        townListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long arg3){
                DataBaseHandler.getInstance(getApplicationContext()).deleteCity(arrayCities.get(pos));
                arrayCities.remove(pos);//where arg2 is position of item you click

                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    /**
     * Used a private class in the API class for background synchronisation
     * @param view
     */
    public void addCity(View view) {
        Intent intent = new Intent(view.getContext(), AddCityActivity.class);
        startActivityForResult(intent,0);
    }
}