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

    private static final String DATABASE_NAME = "weathersCities2";    // Name of DB
    private static final int DATABASE_VERSION = 1;   // Version DB
    private static final String TABLE_CITY = "cities";    // Table Name
    // Columns
    private static final String KEY_ID = "id";
    public static final String KEY_CITY_NAME = "city_name";
    private static final String KEY_CITY_COUNTRY = "city_country";
    private static final String KEY_CITY_DATE = "city_date";
    private static final String KEY_CITY_WIND = "city_wind";
    public static final String KEY_CITY_PRESSURE = "city_pressure";
    private static final String KEY_CITY_TEMPERATURE = "city_temperature";

    public static final String[] allFields = { KEY_CITY_NAME, KEY_CITY_COUNTRY, KEY_CITY_DATE, KEY_CITY_WIND, KEY_CITY_PRESSURE, KEY_CITY_TEMPERATURE};


    /**
     * Constructor
     * @param context
     */
    public DataBaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Method to use the same DB instance inside other classes
     * @param context
     * @return
     */
    public static DataBaseHandler getInstance(final Context context){
        return new DataBaseHandler(context);
    }

    /**
     * On creation
     * @param myDB
     */
    @Override
    public void onCreate(SQLiteDatabase myDB){
        // Creating table request casted
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CITY + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_CITY_NAME + " TEXT,"
                + KEY_CITY_COUNTRY + " TEXT," + KEY_CITY_DATE + " TEXT,"
                + KEY_CITY_WIND + " TEXT," + KEY_CITY_PRESSURE + " TEXT,"
                + KEY_CITY_TEMPERATURE + " TEXT,"
                + "unique ("+KEY_CITY_NAME+","+ KEY_CITY_COUNTRY+"));";
        // Executing the request
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
        // Erase old table
        myDB.execSQL("DROP TABLE IF EXISTS " + TABLE_CITY);
        // Using the newer data, create the table again
        onCreate(myDB);
    }

    // Add a new city
    public void addNewCity(City city) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CITY_NAME, city.getVille()); // City Name
        values.put(KEY_CITY_COUNTRY, city.getPays()); // City Country
        values.put(KEY_CITY_DATE, city.getDate());
        values.put(KEY_CITY_WIND, city.getVent()); // City Wind speed & direction
        values.put(KEY_CITY_PRESSURE, city.getPression());
        values.put(KEY_CITY_TEMPERATURE, city.getTemperature());
        // Insert a row
        db.insert(TABLE_CITY, null, values);
        db.close();
    }
    // return all the registered cities
    public List<City> getAllCities(){
        List<City> citiesList = new ArrayList<City>();
        String sqlAllRequest = "SELECT * FROM " + TABLE_CITY;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlAllRequest, null);
        // Add every DB entity to the list
        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setId(Integer.parseInt(cursor.getString(0)));
                city.setVille(cursor.getString(1));
                city.setPays(cursor.getString(2));
                city.setDate(cursor.getString(3));
                city.setVent(cursor.getString(4));
                city.setPression(cursor.getString(5));
                city.setTemperature(cursor.getString(6));
                // Adding contact to list
                citiesList.add(city);
            } while (cursor.moveToNext());
        }
        // return city entities list
        return citiesList;
    }
    // delete a city
    public void deleteCity(String city){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CITY, KEY_CITY_NAME + " = ?", new String[] {city});
        db.close();
    }
}