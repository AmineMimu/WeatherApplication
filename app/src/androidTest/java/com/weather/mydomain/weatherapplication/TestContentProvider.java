package com.weather.mydomain.weatherapplication;

import android.database.Cursor;
import android.test.ProviderTestCase2;

/**
 * Created by M.A on 27/11/2017.
 */

public class TestContentProvider extends ProviderTestCase2<CityProvider> {

    public TestContentProvider() {
        super(CityProvider.class, CityProvider.authority);
    }

    public void test1(){
        City city = new City("Tunis","Tunisie","02/12/2014","25km/h","41","20°C");

        DataBaseHandler.getInstance(getContext()).addNewCity(city);
        Cursor c = getContext().getContentResolver().query(CityProvider.uriAllCities,null,null,null,null);
        c.moveToLast();
        assertEquals(c.getString(c.getColumnIndex(DataBaseHandler.KEY_CITY_PRESSURE)),city.getPression());
        c.close();
    }
}
