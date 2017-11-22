package com.weather.mydomain.weatherapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by M.A on 27/10/2017.
 */

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "weathersCities";    // Name of DB
    private static final int DATABASE_VERSION = 1;   // Version DB
    private static final String TABLE_CITY = "cities";    // Name Table

    // Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_CITY_NAME = "city_name";
    private static final String KEY_CITY_COUNTRY = "city_country";
    private static final String KEY_CITY_DATE = "city_date";
    private static final String KEY_CITY_WIND_SPEED = "city_wind_speed";
    private static final String KEY_CITY_WIND_DIRECTION = "city_wind_direction";
    private static final String KEY_CITY_PRESSURE = "city_pressure";
    private static final String KEY_CITY_TEMPERATURE = "city_temperature";

    /**
     * Constructor
     * @param context
     */
    public DataBaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * On creation
     * @param myDB
     */
    @Override
    public void onCreate(SQLiteDatabase myDB){
        // SQL request casted to create the table
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CITY + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_CITY_NAME + " TEXT,"
                + KEY_CITY_COUNTRY + " TEXT," + KEY_CITY_DATE + " TEXT,"
                + KEY_CITY_WIND_SPEED + " TEXT," + KEY_CITY_WIND_DIRECTION + " TEXT,"
                + KEY_CITY_PRESSURE + " TEXT," + KEY_CITY_TEMPERATURE + " TEXT"
                + ")";
        // Executing the request on my Database
        myDB.execSQL(CREATE_CONTACTS_TABLE);
    }

    /**
     * On updating
     * @param myDB
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase myDB, int oldVersion, int newVersion){
        // Drop older table if existed
        myDB.execSQL("DROP TABLE IF EXISTS " + TABLE_CITY);
        // Creating tables again with te new data
        onCreate(myDB);
    }

    // Adding new shop
    public void addNewCity(City city) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CITY_NAME, city.getVille()); // City Name
        values.put(KEY_CITY_COUNTRY, city.getPays()); // City Country
        values.put(KEY_CITY_DATE, city.getDate());
        values.put(KEY_CITY_WIND_SPEED, city.getVent()); // City Wind speed
        values.put(KEY_CITY_WIND_DIRECTION, city.getVent()); // City Wind direction
        values.put(KEY_CITY_PRESSURE, city.getPression());
        values.put(KEY_CITY_TEMPERATURE, city.getTemperature());
        // Inserting Row
        db.insert(TABLE_CITY, null, values);
        db.close(); // Closing database connection
    }
    /*
    public List<City> getAllCities(){
        List<City> citiesList = new ArrayList<City>();
        String sqlAllRequest = "SELECT * FROM " + TABLE_CITY;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlAllRequest, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setId(Integer.parseInt(cursor.getString(0)));
                shop.setName(cursor.getString(1));
                shop.setAddress(cursor.getString(2));
                // Adding contact to list
                shopList.add(shop);
            } while (cursor.moveToNext());
        }
        // return contact list
        return citiesList;
    }
    */
}

// http://mobilesiri.com/android-sqlite-database-tutorial-using-android-studio/