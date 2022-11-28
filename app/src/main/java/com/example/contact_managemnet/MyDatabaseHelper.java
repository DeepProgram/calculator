package com.example.contact_managemnet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "user.db";
    private static final String TABLE_NAME = "user_details";
    private static final String ID = "_id";
    private static final String FIRST_NAME = "first_Name";
    private static final String LAST_NAME = "last_name";
    private static final String PHONE_NO = "phone_no";
    private static final String EMAIL = "email";
    private static final String COUNTRY = "country";
    private static final String BIRTHDATE = "birthdate";
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+ID+ " INTEGER PRIMARY KEY AUTOINCREMENT"+","+FIRST_NAME+" VARCHAR(255)"+","+LAST_NAME+" VARCHAR(255)"+","+PHONE_NO+" VARCHAR(255)"+","+EMAIL+" VARCHAR(255)"+","+COUNTRY+" VARCHAR(255)"+","+BIRTHDATE+" VARCHAR(255));";

    private static final int VERSION_NUMBER = 1;
    private Context context;

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            Toast.makeText(context,"onCreate is created", Toast.LENGTH_SHORT);
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }
        catch (Exception e){
            Toast.makeText(context, "Exception: "+e, Toast.LENGTH_SHORT);
        }

    }

    public long insertData(String first_name, String last_name, String phone_no, String email, String country, String birth_date){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(FIRST_NAME,first_name);
        contentValues.put(LAST_NAME,last_name);
        contentValues.put(PHONE_NO,phone_no);
        contentValues.put(EMAIL,email);
        contentValues.put(COUNTRY,country);
        contentValues.put(BIRTHDATE,birth_date);
        return sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
    }

    public ArrayList<List<String>> getAllRowNameAndNumber(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        String searchQuery = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(searchQuery, null);
        ArrayList<List<String>> userInfoList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                userInfoList.add(Arrays.asList(cursor.getString(0),cursor.getString(1) + " "+cursor.getString(2),
                        cursor.getString(3)));
            } while (cursor.moveToNext());
            // moving our cursor to next.
        }
        cursor.close();
        return userInfoList;
    }

    public ArrayList<String> getRowById(String id){
        ArrayList<String> userInfo = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        String searchQuery = "SELECT * FROM "+TABLE_NAME+" WHERE "+ID+"="+id;
        Cursor cursor = sqLiteDatabase.rawQuery(searchQuery, null);
        cursor.moveToFirst();
        userInfo.add(cursor.getString(1)+" "+cursor.getString(2));
        userInfo.add(cursor.getString(3));
        userInfo.add(cursor.getString(4));
        userInfo.add(cursor.getString(5));
        userInfo.add(cursor.getString(6));
        return userInfo;
    }

    public long deleteRowById(String id){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, ID+"="+id,null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
