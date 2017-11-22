package com.weather.mydomain.weatherapplication;

import android.net.Uri;
import android.os.AsyncTask;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by azzed on 17/10/2017.
 */

public class YahooService {

    // Declare an object where to recover data from the Yahoo API
    private JSONResponseHandler jsonResponseHandler = new JSONResponseHandler();

    private Uri.Builder uriBuilder;

    static List<String> synchronizedCityItems = new ArrayList<>();

    static String wind, temperature, pression, date;

    String resultat = "";

    /**
     * The method requesting the data from the Yahoo API
     * @param ville
     * @return
     */
    public String getMeteo(String ville){

        uriBuilder = new Uri.Builder();
        uriBuilder.scheme("https");
        String urlAuth = "query.yahooapis.com";
        uriBuilder.authority(urlAuth);
        String urlPath = "/v1/public/yql";
        uriBuilder.path(urlPath);
        uriBuilder.appendQueryParameter("format", "json");
        // Here use only the town name as a parameter for the API
        uriBuilder.appendQueryParameter("q", "select * from weather.forecast where woeid in " +
                "(select woeid from geo.places(1) where text=\"" + ville + "\")");


        //Log.d("LOL","Yahoo Api");
        // Create the object who's gonna do the background work for each time requesting data about cities
        new SynchronizeWithYahooApi().execute();

        return resultat;
    }

    /**
     * Used a private class in the API class for background synchronisation
     */
    private class SynchronizeWithYahooApi extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            String queryUrl = uriBuilder.build().toString();
            try {
                URL url = new URL(queryUrl);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout(5000);
                urlConnection.connect();

                InputStream content = new BufferedInputStream(urlConnection.getInputStream());

                String encoding = "UTF-8";
                synchronizedCityItems = jsonResponseHandler.handleResponse(content, encoding);

                //Log.d("LOL ","DAMN! "+synchronizedCityItems.toString());

                wind = synchronizedCityItems.get(0);
                temperature = synchronizedCityItems.get(1);
                pression = synchronizedCityItems.get(2);
                date = synchronizedCityItems.get(3);


                //Log.d("LOL","avant catch bzzt");

            } catch (Exception e) {
                System.out.println("LOL" + e.toString());
            }

            return null;
        }
    }
}