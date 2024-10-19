package com.example.firstapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DataBaseHelper extends SQLiteOpenHelper {

    //database name and version
    private static final String DATABASE_NAME = "phones.db";
    private  static final int DATABASE_VERSION = 1;

    //table and column name
    private static final  String Table_Name = "login";
    private static final  String Column_UserName = "username";
    private static final  String Column_Password = "password";
    private static final  String Column_Date_Creation = "date_creation";
    private static final String Column_Last_Login = "last_login";

    //query to create the table

    private static final String Create_Table_Login = "CREATE TABLE IF NOT EXISTS " + Table_Name + "("
            + Column_UserName + " TEXT PRIMARY KEY, "
            + Column_Password + " TEXT, "
            + Column_Date_Creation + " TEXT, "
            + Column_Last_Login + " TEXT );";

    public DataBaseHelper (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //execute the query to create the table
        db.execSQL(Create_Table_Login);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // methods to get the current date and time
    public String getCurrentDate(){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss", Locale.getDefault());
        return date.format(new Date());
    }


    //insert a new user, on register button
    public void insertUser(String username, String password){
        String currentDate = getCurrentDate();
        SQLiteDatabase db = this.getWritableDatabase();

        //use contentValues to store data
        ContentValues values = new ContentValues();
        values.put(Column_UserName,username);
        values.put(Column_Password,password);
        values.put(Column_Date_Creation,currentDate);
        values.put(Column_Last_Login,currentDate);

        //insert a new row to the database
        db.insert(Table_Name,null,values);

        //close connection
        db.close();
    }

    public void updateLastLogin(String username){
        String currentDate = getCurrentDate();
        SQLiteDatabase db = this.getWritableDatabase();

         ContentValues values = new ContentValues();
         values.put(Column_Last_Login, currentDate);

         db.update(Table_Name,values,Column_UserName + " =?", new String[]{username});
         db.close();
    }

    public boolean checkUser(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {Column_UserName};
        String selection = Column_UserName + " = ? AND " + Column_Password + " = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(Table_Name, columns, selection, selectionArgs,null,null,null);
        int cursorCount = cursor.getCount();

        return cursorCount > 0;
    }


}
