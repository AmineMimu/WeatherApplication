package com.weather.mydomain.weatherapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by M.A on 27/10/2017.
 */

public class DataBaseHandler extends SQLiteOpenHelper {

    // Name of DB
    private static final String DATABASE_NAME = "weathersCities";

    // Version DB
    private static final int DATABASE_VERSION = 1;

    // Name Table
    private static final String TABLE_CITY = "cities";

    // Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_CITY_NAME = "city_name";
    private static final String KEY_CITY_COUNTRY = "city_country";
    private static final String KEY_CITY_WIND = "city_wind";
    private static final String KEY_CITY_TEMPERATURE = "city_temperature";
    private static final String KEY_CITY_PRESSURE = "city_pressure";
    private static final String KEY_CITY_DATE = "city_date";


    /**
     * On creation
     * @param myDB
     */
    @Override
    public void onCreate(SQLiteDatabase myDB){
        // SQL request casted to create the table
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CITY + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_CITY_NAME + " TEXT,"
                + KEY_CITY_COUNTRY + " TEXT," + KEY_CITY_WIND + " TEXT,"
                + KEY_CITY_TEMPERATURE + " TEXT," + KEY_CITY_PRESSURE + " TEXT,"
                + KEY_CITY_DATE + " TEXT" + ")";
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

    /**
     * Constructor
     * @param context
     */
    public DataBaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Adding new shop
    public void addNewCity(City city) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CITY_NAME, city.getVille()); // City Name
        values.put(KEY_CITY_COUNTRY, city.getPays()); // City Country
        values.put(KEY_CITY_WIND, city.getVent()); // City Wind
        values.put(KEY_CITY_TEMPERATURE, city.getTemperature());
        values.put(KEY_CITY_PRESSURE, city.getPression());
        values.put(KEY_CITY_DATE, city.getDate());
        // Inserting Row
        db.insert(TABLE_CITY, null, values);
        db.close(); // Closing database connection
    }
    // http://mobilesiri.com/android-sqlite-database-tutorial-using-android-studio/
}