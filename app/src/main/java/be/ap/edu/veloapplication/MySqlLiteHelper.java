package be.ap.edu.veloapplication;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "map.db";
    private static final String TABLE_LOCATIONS = "locations";
    private static final int DATABASE_VERSION = 5;
    final ArrayList<VeloStation> veloStationList = new ArrayList<VeloStation>();

    public MySQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Android expects _id to be the primary key
        String CREATE_LOCATIONS_TABLE = "CREATE TABLE " + TABLE_LOCATIONS + "(_id INTEGER PRIMARY KEY, longitude REAL, latitude REAL, beschrijving TEXT)";
        db.execSQL(CREATE_LOCATIONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATIONS);
        onCreate(db);
    }

    public void addLocation(Double latitude, Double longitude, String beschrijving) {
        SQLiteDatabase db = this.getWritableDatabase();
        //db.delete(TABLE_CONTACTS, null, null);

        ContentValues values = new ContentValues();
        values.put("latitude", latitude);
        values.put("longitude", longitude);

        db.insert(TABLE_LOCATIONS, null, values);
        db.close();
    }

    public ArrayList<VeloStation> getAllVeloStations() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor  cursor = db.rawQuery("select longitude,latitude from " + TABLE_LOCATIONS,null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Double lon = cursor.getDouble(cursor.getColumnIndex("longitude"));
                Double lat = cursor.getDouble(cursor.getColumnIndex("latitude"));

                veloStationList.add(new VeloStation(lat, lon));
                cursor.moveToNext();
            }
        }
        return veloStationList;
    }
}