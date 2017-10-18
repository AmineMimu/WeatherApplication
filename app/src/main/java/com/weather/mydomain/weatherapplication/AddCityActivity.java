package com.weather.mydomain.weatherapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import static com.weather.mydomain.weatherapplication.MainActivity.adapter;
import static com.weather.mydomain.weatherapplication.MainActivity.arrayCities;
import static com.weather.mydomain.weatherapplication.MainActivity.town;
import static com.weather.mydomain.weatherapplication.YahooService.synchronizedCityItems;

public class AddCityActivity extends AppCompatActivity {

    EditText villeEditText, paysEditText;

    private String ville,pays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);

        villeEditText = (EditText) findViewById(R.id.editTextVille);
        paysEditText = (EditText) findViewById(R.id.editTextPays);
    }

    // The method's called when the button "Enregistrer" is clicked
    public void saveCity(View view) {

        ville = villeEditText.getText().toString();
        pays = paysEditText.getText().toString();

        // Adding new City
        town.add(new City(ville,pays));
        arrayCities.add(town.get(town.size()-1).getVille() + " ( " + town.get(town.size()-1).getPays().toUpperCase() + " )");

        // Notifying the adapter about receiving new data
        adapter.notifyDataSetChanged();
        //Toast.makeText(AddCityActivity.this, ville + " " + pays, Toast.LENGTH_LONG).show();
    }
}
