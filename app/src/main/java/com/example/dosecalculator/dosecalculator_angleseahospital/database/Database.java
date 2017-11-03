package com.example.dosecalculator.dosecalculator_angleseahospital.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//import com.example.dosecalculator.dosecalculator_angleseahospital.database.Rooms.RoomsEntry;

/**
 * Created by komyo on 1/11/2017..
 */

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "phyu09.db";
    private static final int DATABASE_VERSION=1;

    public static final String TABLE_ROOMS="rooms";
    public static final String COLUMN_Room_ID="_id";
    public static final String COLUMN_ROOM_NAME="Room";
    public static final String COLUMN_ROOM_DETAILS="Details";
    public static final String COLUMN_ROOM_STATUS="Status";
    public static final String COLUMN_ROOM_TYPE="Type";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

 /*   private static final String TABLE_ROOMS_CREATE=
          "CREATE TABLE "+ TABLE_ROOMS + "(" +
            COLUMN_Room_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            COLUMN_ROOM_NAME+ " TEXT " +
            COLUMN_ROOM_DETAILS + " TEXT " +
            COLUMN_ROOM_TYPE + " TEXT " +
            COLUMN_ROOM_STATUS + " TEXT " +
            ");";*/


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_ROOMS + "(" +
                COLUMN_Room_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_ROOM_NAME+ " TEXT ," +
                COLUMN_ROOM_DETAILS + " TEXT ," +
                COLUMN_ROOM_TYPE + " TEXT ," +
                COLUMN_ROOM_STATUS + " TEXT " +
                ");");
        //db.execSQL("create table " + table_ +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, TIME TEXT, DATE TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROOMS);
        onCreate(db);
    }

    public boolean insertRoom(String roomName,String roomDetails, String type,String status){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_ROOM_NAME,roomName);
        values.put(COLUMN_ROOM_DETAILS,roomDetails);
        values.put(COLUMN_ROOM_TYPE,type);
        values.put(COLUMN_ROOM_STATUS,status);

        long result = db.insert(TABLE_ROOMS, null, values);
        if (result == -1)
            return  false;
        else
            return true ;

    }


}
