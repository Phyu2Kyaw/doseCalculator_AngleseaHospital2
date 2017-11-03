package com.example.dosecalculator.dosecalculator_angleseahospital.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

public class MyDBHandler extends SQLiteOpenHelper {

    /*private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="drugcalculator.db";
    public static final String TABLE_ROOMS="rooms";
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_ROOM_NAME="roomname";
    private static final String COLUMN_ROOM_DETAILS="roomdetails";
    private static final String COLUMN_ROOM_STATUS="active";
    private static final String COLUMN_ROOM_TYPE="day";*/

    public static final String DATABASE_NAME = "Note.db";
    public static final String table_name = "Note_Table";
    public static final String col_1 = "ID";
    public static final String col_2 = "DATE";
    public static final String col_3 = "TIME";
    public static final String col_4 = "NAME";


    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       /* String query="CREATE TABLE "+ TABLE_ROOMS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_ROOM_NAME + " TEXT " +
                COLUMN_ROOM_DETAILS + " TEXT " +
                COLUMN_ROOM_STATUS + " TEXT " +
                COLUMN_ROOM_TYPE + " TEXT " +
                ");";
        //build the query
        db.execSQL(query);*/

        db.execSQL("create table " + table_name +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, TIME TEXT, DATE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
       // db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROOMS);
        onCreate(db);
    }

    /*//Add a new row to the database
    public void addRoom(Rooms rm){
        ContentValues values=new ContentValues();
        values.put(COLUMN_ROOM_NAME, rm.getRm_name());
        values.put(COLUMN_ROOM_DETAILS, rm.getRm_details());
        values.put(COLUMN_ROOM_STATUS, rm.getRm_status());
        values.put(COLUMN_ROOM_TYPE, rm.getRm_type());
        SQLiteDatabase db=getWritableDatabase();
        db.insert(TABLE_ROOMS, null, values);
    }

    //Delete a product from the database
    public void deleteProduct(String roomName){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_ROOMS+ " WHERE " + COLUMN_ROOM_NAME + "=\""+roomName+ "\";" );
    }

    //Print out the database as a string
    public String databaseToString(){
        String dbString="";
        SQLiteDatabase db=getWritableDatabase();
        String query="SELECT * FROM " + TABLE_ROOMS + " WHERE 1";

        //Cursor point to a location in the results
        Cursor c=db.rawQuery(query, null);
        c.moveToFirst();

        //loop until the cursor is the last
        while (!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("rm_name"))!=null){
                dbString+=c.getString(c.getColumnIndex("rm_name"));
                dbString +="\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }*/

}
