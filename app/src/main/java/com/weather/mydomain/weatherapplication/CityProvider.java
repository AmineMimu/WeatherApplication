package com.weather.mydomain.weatherapplication;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class CityProvider extends ContentProvider {

    public static final String authority = "com.weather.mydomain.weatherapplication";
    public static final String prefix = "content://";

    public static final String allCities = prefix + authority + "/weather";
    public static final Uri uriAllCities = Uri.parse(allCities);
    public static final String cityBase = allCities + "/";
    public static final Uri uriCity = Uri.parse(cityBase);

    public CityProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = DataBaseHandler.getInstance(getContext()).getWritableDatabase();
        try {
            long id = db.insertOrThrow("cities", null, values);

            if (id == -1) {
                throw new RuntimeException(String.format(
                        "%s : Failed to insert [%s] for unknown reasons.","CityProvider", values, uri));
            } else {
                return ContentUris.withAppendedId(uri, id);
            }

        } finally {
            db.close();
        }
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        return true;
    }

    /**
     * If the URI finished by weather --> search all cities data
     * Else if it contains the uri base and other parameters after "weather"
     * the method returns the city data
     * @param uri
     * @param projection
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @return
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor result = null;

        if (uriAllCities.equals(uri)) {
            result = DataBaseHandler
                    .getInstance(getContext())
                    .getReadableDatabase()
                    .query("cities", DataBaseHandler.allFields , null, null, null,
                            null, null, null);
            result.setNotificationUri(getContext().getContentResolver(), uriAllCities);
        }
        else if (uri.toString().startsWith(cityBase)) {
            final String town = uri.getLastPathSegment();
            result = DataBaseHandler
                    .getInstance(getContext())
                    .getReadableDatabase()
                    .query("cities", DataBaseHandler.allFields,
                            DataBaseHandler.KEY_CITY_NAME + " IS ?",
                            new String[] { String.valueOf(town) }, null, null,
                            null, null);
            result.setNotificationUri(getContext().getContentResolver(), uri);
        } else {
            throw new UnsupportedOperationException("Not yet implemented");
        }
        return result;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
