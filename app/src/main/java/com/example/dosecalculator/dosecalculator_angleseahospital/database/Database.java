package com.example.dosecalculator.dosecalculator_angleseahospital.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dosecalculator.dosecalculator_angleseahospital.Drugs;

//import com.example.dosecalculator.dosecalculator_angleseahospital.database.Rooms.RoomsEntry;

/**
 * Created by komyo on 1/11/2017..
 */

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "phyu10.db";
    private static final int DATABASE_VERSION=1;

    public static final String TABLE_ROOMS="rooms";
    public static final String COLUMN_Room_ID="_id";
    public static final String COLUMN_ROOM_DETAILS="Details";
    public static final String COLUMN_ROOM_STATUS="Status";
    public static final String COLUMN_ROOM_TYPE="Type";

    public static final String TABLE_DRUGS = " drugs ";
    public static final String drug_ID = " _dId ";
    public static final String drug_Name = " Drug_Name ";
    public static final String drug_Weight = " Drug_Weight ";    // mg
    public static final String drug_Volume = " Drug_Volume ";    // mL
    public static final String max_Dosage = " Max_Dosage ";
    public static final String calc_Method = " Calc_Method ";    // calculation method - adult or pediatrics
    public static final String type_Patient = " Type_Patient ";  // type of patient

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
        /** Create TABLE_ROOMS */
        db.execSQL("CREATE TABLE "+ TABLE_ROOMS + "(" +
                COLUMN_Room_ID + " TEXT PRIMARY KEY ," +
                COLUMN_ROOM_DETAILS + " TEXT ," +
                COLUMN_ROOM_STATUS + " TEXT " +
                ");");
        //db.execSQL("create table " + table_ +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, TIME TEXT, DATE TEXT)");

        /** Create TABLE_DRUGS */
        db.execSQL(" Create table if not exists " + TABLE_DRUGS + " ( " + drug_ID +
                " Integer Primary Key Autoincrement, " + drug_Name + " text, " + drug_Weight +
                " text, " + drug_Volume + " text, " + max_Dosage + " text, " + calc_Method + " text, " +
                type_Patient + " text ) " );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROOMS);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_DRUGS);
        onCreate(db);
    }

    /** TABLE_ROOMS Details */
    public boolean insertRoom(String roomId, String roomDetails, String status){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_Room_ID,roomId);
        values.put(COLUMN_ROOM_DETAILS,roomDetails);
        values.put(COLUMN_ROOM_STATUS,status);

        long result = db.insert(TABLE_ROOMS, null, values);
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

        long result = db.update(TABLE_ROOMS, values,"_id = ?",new String[] { roomId });
        if (result == -1)
            return  false;
        else
            return true ;

    }

    public boolean deleteRoom(String roomId){
        SQLiteDatabase db= this.getWritableDatabase();
        long result = db.delete(TABLE_ROOMS, "_id = ?",new String[] { roomId });
        if (result == -1)
            return  false;
        else
            return true ;
    }

    public Cursor getRoomData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor roomCursors = db.rawQuery("select * from "+TABLE_ROOMS,null);
        return roomCursors;
    }

    /** TABLE_DRUGS Details */

    public void addDrugs(Drugs drug) {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(drug_ID, drug.getDrugId());
        values.put(drug_Name, drug.getDrugName());
        values.put(drug_Weight, drug.getDrugWeight());
        values.put(drug_Volume, drug.getDrugVolume());
        values.put(max_Dosage, drug.getMaxDosage());
        values.put(calc_Method, drug.getCalcMethod());
        values.put(type_Patient, drug.getTypePatient());

        db.insert(TABLE_DRUGS, null, values);
    }

}
