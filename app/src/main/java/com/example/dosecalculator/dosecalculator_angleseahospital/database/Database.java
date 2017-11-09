package com.example.dosecalculator.dosecalculator_angleseahospital.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//import com.example.dosecalculator.dosecalculator_angleseahospital.database.Rooms.RoomsEntry;

/**
 * Created by komyo on 1/11/2017..
 */

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "phyu11.db";
    private static final int DATABASE_VERSION=1;

    public static final String TABLE_ROOM="room";
    public static final String COLUMN_Room_ID="_id";
    public static final String COLUMN_ROOM_DETAILS="Details";
    public static final String COLUMN_ROOM_STATUS="Status";

    public static final String TABLE_PATIENT="patient";
    public static final String COLUMN_patient_ID="NHI_id";
    public static final String COLUMN_patient_NAME="Name";
    public static final String COLUMN_patient_DOB="DOB";
    public static final String COLUMN_patient_WEIGHT="Weight";
    public static final String COLUMN_patient_STATUS="Status";

    public static final String TABLE_NURSE="nurse";
    public static final String COLUMN_nurse_ID="_id";
    public static final String COLUMN_nurse_NAME="Name";
    public static final String COLUMN_nurse_EMPLOYMENT_ID="Nurse_id";
    public static final String COLUMN_nurse_STATUS="Status";

    public static final String TABLE_DRUG="drug";
    public static final String COLUMN_Drug_ID="_id";
    public static final String COLUMN_DRUG_NAME="Name";
    public static final String COLUMN_DRUG_MG="Mg";
    public static final String COLUMN_DRUG_ML="Ml";
    public static final String COLUMN_DRUG_STATUS="Status";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

 /*   private static final String TABLE_ROOM_CREATE=
          "CREATE TABLE "+ TABLE_ROOM + "(" +
            COLUMN_Room_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            COLUMN_ROOM_NAME+ " TEXT " +
            COLUMN_ROOM_DETAILS + " TEXT " +
            COLUMN_ROOM_TYPE + " TEXT " +
            COLUMN_ROOM_STATUS + " TEXT " +
            ");";*/


 /*   @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_ROOM + "(" +
                COLUMN_Room_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_ROOM_NAME+ " TEXT ," +
                COLUMN_ROOM_DETAILS + " TEXT ," +
                COLUMN_ROOM_TYPE + " TEXT ," +
                COLUMN_ROOM_STATUS + " TEXT " +
                ");");
        //db.execSQL("create table " + table_ +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, TIME TEXT, DATE TEXT)");

    }*/

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_ROOM + "(" +
                COLUMN_Room_ID + " TEXT PRIMARY KEY ," +
                COLUMN_ROOM_DETAILS + " TEXT ," +
                COLUMN_ROOM_STATUS + " TEXT " +
                ");");
        //db.execSQL("create table " + table_ +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, TIME TEXT, DATE TEXT)");

        /*db.execSQL("CREATE TABLE "+ TABLE_PATIENT + "(" +
                COLUMN_patient_ID + " TEXT PRIMARY KEY ," +
                COLUMN_patient_NAME + " TEXT ," +
                COLUMN_patient_DOB + " TEXT ," +
                COLUMN_patient_WEIGHT + " INTEGER ," +
                COLUMN_patient_STATUS + " TEXT " +
                ");");

        db.execSQL("CREATE TABLE "+ TABLE_NURSE + "(" +
                COLUMN_nurse_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_nurse_NAME + " TEXT ," +
                COLUMN_nurse_EMPLOYMENT_ID + " TEXT ," +
                COLUMN_nurse_STATUS + " TEXT " +
                ");");

        db.execSQL("CREATE TABLE "+ TABLE_DRUG + "(" +
                COLUMN_Drug_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_DRUG_NAME + " TEXT ," +
                COLUMN_DRUG_MG + " INTEGER ," +
                COLUMN_DRUG_ML + " INTEGER ," +
                COLUMN_DRUG_STATUS + " TEXT " +
                ");");*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROOM);
      /*  db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NURSE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRUG);*/
        onCreate(db);
    }

    //Room
    public boolean insertRoom(String roomId, String roomDetails, String status){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_Room_ID,roomId);
        values.put(COLUMN_ROOM_DETAILS,roomDetails);
        values.put(COLUMN_ROOM_STATUS,status);

        long result = db.insert(TABLE_ROOM, null, values);
        if (result == -1)
            return  false;
        else
            return true ;

    }

    public boolean updateRoom(String roomId, String roomDetails, String status){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_Room_ID,roomId);
        values.put(COLUMN_ROOM_DETAILS,roomDetails);
        values.put(COLUMN_ROOM_STATUS,status);

        long result = db.update(TABLE_ROOM, values,"_id = ?",new String[] { roomId });
        if (result == -1)
            return  false;
        else
            return true ;

    }

    public boolean deleteRoom(String roomId){
        SQLiteDatabase db= this.getWritableDatabase();
        long result = db.delete(TABLE_ROOM, "_id = ?",new String[] { roomId });
        if (result == -1)
            return  false;
        else
            return true ;
    }

    public Cursor getRoomData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor roomCursors = db.rawQuery("select * from "+TABLE_ROOM,null);
        return roomCursors;
    }


    //Patient
    public boolean insertPatient(String roomId, String roomDetails, String status){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_Room_ID,roomId);
        values.put(COLUMN_ROOM_DETAILS,roomDetails);
        values.put(COLUMN_ROOM_STATUS,status);

        long result = db.insert(TABLE_ROOM, null, values);
        if (result == -1)
            return  false;
        else
            return true ;

    }

    public boolean updatePatient(String roomId, String roomDetails, String status){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_Room_ID,roomId);
        values.put(COLUMN_ROOM_DETAILS,roomDetails);
        values.put(COLUMN_ROOM_STATUS,status);

        long result = db.update(TABLE_ROOM, values,"_id = ?",new String[] { roomId });
        if (result == -1)
            return  false;
        else
            return true ;

    }

    public boolean deletePatient(String roomId){
        SQLiteDatabase db= this.getWritableDatabase();
        long result = db.delete(TABLE_ROOM, "_id = ?",new String[] { roomId });
        if (result == -1)
            return  false;
        else
            return true ;
    }

    public Cursor getPatientData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor roomCursors = db.rawQuery("select * from "+TABLE_ROOM,null);
        return roomCursors;
    }

    //Nurse
    public boolean insertNurse(String roomId, String roomDetails, String status){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_Room_ID,roomId);
        values.put(COLUMN_ROOM_DETAILS,roomDetails);
        values.put(COLUMN_ROOM_STATUS,status);

        long result = db.insert(TABLE_ROOM, null, values);
        if (result == -1)
            return  false;
        else
            return true ;

    }

    public boolean updateNurse(String roomId, String roomDetails, String status){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_Room_ID,roomId);
        values.put(COLUMN_ROOM_DETAILS,roomDetails);
        values.put(COLUMN_ROOM_STATUS,status);

        long result = db.update(TABLE_ROOM, values,"_id = ?",new String[] { roomId });
        if (result == -1)
            return  false;
        else
            return true ;

    }

    public boolean deleteNurse(String roomId){
        SQLiteDatabase db= this.getWritableDatabase();
        long result = db.delete(TABLE_ROOM, "_id = ?",new String[] { roomId });
        if (result == -1)
            return  false;
        else
            return true ;
    }

    public Cursor getNurseData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor roomCursors = db.rawQuery("select * from "+TABLE_ROOM,null);
        return roomCursors;
    }

    //Drug
    public boolean insertDrug(String roomId, String roomDetails, String status){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_Room_ID,roomId);
        values.put(COLUMN_ROOM_DETAILS,roomDetails);
        values.put(COLUMN_ROOM_STATUS,status);

        long result = db.insert(TABLE_ROOM, null, values);
        if (result == -1)
            return  false;
        else
            return true ;

    }

    public boolean updateDrug(String roomId, String roomDetails, String status){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_Room_ID,roomId);
        values.put(COLUMN_ROOM_DETAILS,roomDetails);
        values.put(COLUMN_ROOM_STATUS,status);

        long result = db.update(TABLE_ROOM, values,"_id = ?",new String[] { roomId });
        if (result == -1)
            return  false;
        else
            return true ;

    }

    public boolean deleteDrug(String roomId){
        SQLiteDatabase db= this.getWritableDatabase();
        long result = db.delete(TABLE_ROOM, "_id = ?",new String[] { roomId });
        if (result == -1)
            return  false;
        else
            return true ;
    }

    public Cursor getDrugData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor roomCursors = db.rawQuery("select * from "+TABLE_ROOM,null);
        return roomCursors;
    }



}
