package com.example.components.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.components.model.Person;

import java.util.ArrayList;

public class FeedReaderDbHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
            FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
            FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " TEXT," +
            FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE + " TEXT)";

    private static final String SQL_CREATE_PERSONS = "CREATE TABLE " + FeedReaderContract.FeedPerson.TABLE_NAME + " (" +
            FeedReaderContract.FeedPerson._ID + " INTEGER PRIMARY KEY," +
            FeedReaderContract.FeedPerson.COLUMN_NAME_FIRSTNAME + " TEXT," +
            FeedReaderContract.FeedPerson.COLUMN_NAME_LASTNAME + " TEXT)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME;
    private static final String SQL_DELETE_PERSONS = "DROP TABLE IF EXISTS " + FeedReaderContract.FeedPerson.TABLE_NAME;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_PERSONS);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_PERSONS);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void insertEntry(String title, String subtitle) {
        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, title);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, subtitle);

// Insert the new row, returning the primary key value of the new row
        db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
    }
    public void insertPerson(String fistname, String lastname) {
        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedPerson.COLUMN_NAME_FIRSTNAME, fistname);
        values.put(FeedReaderContract.FeedPerson.COLUMN_NAME_LASTNAME, lastname);

// Insert the new row, returning the primary key value of the new row
        db.insert(FeedReaderContract.FeedPerson.TABLE_NAME, null, values);
    }

    public void updateEntry(String title){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, title);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, title);

        String selection = FeedReaderContract.FeedEntry._ID + "=?";
        String[] selectionArgs = {String.valueOf(2)};

        db.update(
                FeedReaderContract.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

    }

    public void deleteEntry(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(FeedReaderContract.FeedEntry.TABLE_NAME,FeedReaderContract.FeedEntry._ID + " = ?",new String[]{String.valueOf(id)});
    }

    @SuppressLint("Range")
    public String getEntry(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(FeedReaderContract.FeedEntry.TABLE_NAME,
                new String[]{FeedReaderContract.FeedEntry._ID, FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE},
                FeedReaderContract.FeedEntry._ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

//        StringBuilder entry = new StringBuilder();
//        entry.append(cursor.getInt(cursor.getColumnIndex(FeedReaderContract.FeedEntry._ID)));
//        entry.append(cursor.getString(cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE)));
//        entry.append(cursor.getString(cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE)));

        if (cursor != null)
            cursor.moveToFirst();

        String title = cursor.getString(cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE));

        cursor.close();

        return title;
    }

    public ArrayList<Person> getPersonList(Person person){
        ArrayList<Person> list = new ArrayList<Person>();
        String[] projection = new String[]{FeedReaderContract.FeedPerson._ID, FeedReaderContract.FeedPerson.COLUMN_NAME_FIRSTNAME,FeedReaderContract.FeedPerson.COLUMN_NAME_LASTNAME};
//        String selection = FeedReaderContract.FeedPerson._ID + " = ?";
//        String[] selectionArgs = new String[]{String.valueOf(person.getName())};

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(FeedReaderContract.FeedPerson.TABLE_NAME,
                projection,
                null,
                null, null, null, "_id ASC", null);

        cursor.close();
        return list;
    }

}