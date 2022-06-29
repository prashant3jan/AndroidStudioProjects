package com.jurrius.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ContactsDB {
    public static final String KEY_ROWID="_id";
    public static final String KEY_NAME="person_name";
    public static final String KEY_CELL="_cell";

    private final String DATABASE_NAME="Contacts_DB";
    private final String DATABASE_TABLE="ContactsTable";
    private final int DATABASE_VERSION=2;

    private DBHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;
    public ContactsDB(Context context)
    {
        ourContext=context;
    }

    private class DBHelper extends SQLiteOpenHelper
    {
        public DBHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            /*
            CREATE TABLE ContactsTable(_id INTEGER PRIMARY KEY AUTOINCREMENT,
            person_name TEXT NON NULL, _cell TEXT NOT NULL)
             */

            String sqlCode =
                    "CREATE TABLE " + DATABASE_TABLE +
                            "("+ KEY_ROWID + " INTEGER PRIMARY KEY, " +
                            KEY_NAME + " TEXT NOT NULL, " +
                            KEY_CELL+ " TEXT NOT NULL);";
            db.execSQL(sqlCode);
            System.out.println("table created");



        }
    }//end of DBHelper class

    public ContactsDB open() throws SQLException
    {
        ourHelper = new DBHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        ourHelper.close();
    }

    public long createEntry(String name, String cell)
    {
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, name);
        cv.put(KEY_CELL, cell);
        return ourDatabase.insert(DATABASE_TABLE, null, cv);
    }

    public String getData()
    {
        String [] columns = new String []{KEY_ROWID, KEY_NAME, KEY_CELL};

        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
        String result = "";
        int iRowID = c.getColumnIndex(KEY_ROWID);
        System.out.println("iRowID"+iRowID);
        int iName = c.getColumnIndex(KEY_NAME);
        System.out.println("iName"+iName);
        int iCell = c.getColumnIndex(KEY_CELL);
        System.out.println("iCell"+iCell);
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            result = result + c.getString(iRowID)+ ": " + c.getString(iName)+ " " + c.getString(iCell) + "\n";
        }
        c.close();
        System.out.println("result"+result);
        return result;
    }

    public long deleteEntry(String rowId){
        return ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=?", new String[]{rowId});
    }

    public long updateEntry(String rowId, String name, String cell){
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, name);
        cv.put(KEY_CELL, cell);

        return ourDatabase.update(DATABASE_TABLE, cv, KEY_ROWID + "=?", new String[]{rowId});
    }
}
