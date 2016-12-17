package com.justino.macros.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class DBHandler extends SQLiteAssetHelper {

    private static DBHandler myDBHandler;
    private static SQLiteDatabase db;
    private final Context mCtx;

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "macros.db";
    private static final String TABLE_ENTRIES = "entries";
    private static final String TABLE_EITEMS = "entry_items";
    private static final String TABLE_FOOD = "foods";
    public static final String COLUMN_ID = "_id";
    private static final String COLUMN_EID = "entry_id";
    private static final String COLUMN_FID = "food_id";
    public static final String COLUMN_NAME = "name";



    private DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
        this.mCtx = context;
    }

    public DBHandler open() throws SQLException {
        db = myDBHandler.getWritableDatabase();
        return this;
    }

    public void close() {
        if (myDBHandler != null) {
            myDBHandler.close();
        }
    }


    // Returns the singleton instance of the database
    public static synchronized DBHandler getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        if (myDBHandler == null) {
            myDBHandler = new DBHandler(context.getApplicationContext(), DB_NAME, null, DB_VERSION);
        }
        return myDBHandler;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXIST " + TABLE_FOOD);
        db.execSQL("DROP TABLE IF EXIST " + TABLE_ENTRIES);
        db.execSQL("DROP TABLE IF EXIST " + TABLE_EITEMS);
        onCreate(db);
    }


    public void addEntry(String date){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, date);
        db = getWritableDatabase();
        db.insert(TABLE_ENTRIES, null, values);
    }


    public Cursor remove(String id) {

        db.delete(TABLE_ENTRIES, "_id = ?", new String[]{id});

        Cursor c = db.query(TABLE_ENTRIES, new String[]{COLUMN_ID,
                COLUMN_NAME}, null, null, null, null, null);

        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }


    public Cursor clearAllEntries() {

        db.execSQL("delete from "+ TABLE_ENTRIES);

        Cursor c = db.query(TABLE_ENTRIES, new String[]{COLUMN_ID,
                COLUMN_NAME}, null, null, null, null, null);

        if (c != null)
            c.moveToFirst();

        return c;
    }


    public Cursor retrieveEntries() {

        Cursor c = db.query(TABLE_ENTRIES, new String[]{COLUMN_ID,
                COLUMN_NAME}, null, null, null, null, null);

        if (c != null)
            c.moveToFirst();

        return c;
    }

}
