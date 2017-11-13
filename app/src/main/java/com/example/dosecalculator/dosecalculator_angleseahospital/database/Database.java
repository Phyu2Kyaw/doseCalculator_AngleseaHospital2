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
    public static final String DATABASE_NAME = "anglesea_hospital03.db";
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
    //public static final String COLUMN_nurse_ID="_id";
    public static final String COLUMN_NURSE_NAME="Name";
    public static final String COLUMN_NURSE_EMPLOYMENT_ID="Nurse_id";
    public static final String COLUMN_NURSE_STATUS="Status";

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

        db.execSQL("CREATE TABLE "+ TABLE_PATIENT + "(" +
                COLUMN_patient_ID + " TEXT PRIMARY KEY ," +
                COLUMN_patient_NAME + " TEXT ," +
                COLUMN_patient_DOB + " TEXT ," +
                COLUMN_patient_WEIGHT + " INTEGER ," +
                COLUMN_patient_STATUS + " TEXT " +
                ");");

        db.execSQL("CREATE TABLE "+ TABLE_NURSE + "(" +
                //COLUMN_nurse_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_NURSE_EMPLOYMENT_ID + " TEXT PRIMARY KEY ," +
                COLUMN_NURSE_NAME + " TEXT ," +
                COLUMN_NURSE_STATUS + " TEXT " +
                ");");

        /** Create TABLE_DRUGS */
        db.execSQL(" Create table " + TABLE_DRUGS + " ( " +
                drug_ID + " Integer Primary Key Autoincrement, " +
                drug_Name + " text, " +
                drug_Weight + " text, " +
                drug_Volume + " text, " +
                max_Dosage + " text, " +
                calc_Method + " text, " +
                type_Patient + " text " +
               " ); " );
        //addDrug();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROOM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NURSE);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRUG);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_DRUGS);
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
    public boolean insertPatient(String patient_ID, String patient_NAME, String patient_DOB, Integer patient_WEIGHT,String patient_STATUS){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_patient_ID,patient_ID);
        values.put(COLUMN_patient_NAME,patient_NAME);
        values.put(COLUMN_patient_DOB,patient_DOB);
        values.put(COLUMN_patient_WEIGHT,patient_WEIGHT);
        values.put(COLUMN_patient_STATUS,patient_STATUS);

        long result = db.insert(TABLE_PATIENT, null, values);
        if (result == -1)
            return  false;
        else
            return true ;

    }

    public boolean updatePatient(String patient_ID, String patient_NAME, String patient_DOB, Integer patient_WEIGHT,String patient_STATUS){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_patient_ID,patient_ID);
        values.put(COLUMN_patient_NAME,patient_NAME);
        values.put(COLUMN_patient_DOB,patient_DOB);
        values.put(COLUMN_patient_WEIGHT,patient_WEIGHT);
        values.put(COLUMN_patient_STATUS,patient_STATUS);

        long result = db.update(TABLE_PATIENT, values,"NHI_id = ?",new String[] { patient_ID });
        if (result == -1)
            return  false;
        else
            return true ;

    }

    public boolean deletePatient(String patient_ID){
        SQLiteDatabase db= this.getWritableDatabase();
        long result = db.delete(TABLE_PATIENT, "NHI_id = ?",new String[] { patient_ID });
        if (result == -1)
            return  false;
        else
            return true ;
    }

    public Cursor getPatientData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor patientCursors = db.rawQuery("select * from "+TABLE_PATIENT,null);
        return patientCursors;
    }

    //Nurse
    public boolean insertNurse(String nurse_EMPLOYMENT_ID,String nurse_NAME,  String nurse_STATUS ){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_NURSE_EMPLOYMENT_ID,nurse_EMPLOYMENT_ID);
       values.put(COLUMN_NURSE_NAME,nurse_NAME);
        values.put(COLUMN_NURSE_STATUS,nurse_STATUS);

        long result = db.insert(TABLE_NURSE, null, values);
        if (result == -1)
            return  false;
        else
            return true ;
    }

    public boolean updateNurse( String nurse_EMPLOYMENT_ID,String nurse_NAME,  String nurse_STATUS){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_NURSE_EMPLOYMENT_ID,nurse_EMPLOYMENT_ID);
       values.put(COLUMN_NURSE_NAME,nurse_NAME);
        values.put(COLUMN_NURSE_STATUS,nurse_STATUS);

       // String [] args = new String[nurse_NAME];

       long result = db.update(TABLE_NURSE, values,"Nurse_id=?", new String[] { nurse_EMPLOYMENT_ID });
        //long result = db.update(TABLE_NURSE, values,"_id = ?",new String[] { roomId });

        if (result == -1)
            return  false;
        else
            return true ;

    }

    public boolean deleteNurse(String nurseEid){
        SQLiteDatabase db= this.getWritableDatabase();
        long result = db.delete(TABLE_NURSE, "Nurse_id = ?",new String[] { nurseEid });
        if (result == -1)
            return  false;
        else
            return true ;
    }

    public Cursor getNurseData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor roomCursors = db.rawQuery("select * from "+TABLE_NURSE,null);
        return roomCursors;
    }

    /** TABLE_DRUGS Details */

     public void addDrugs(Drugs drug) {
                SQLiteDatabase db= this.getWritableDatabase();
                ContentValues values = new ContentValues();
                //values.put(drug_ID, drug.getDrugId());
                values.put(drug_Name, drug.getDrugName());
                values.put(drug_Weight, drug.getDrugWeight());
                values.put(drug_Volume, drug.getDrugVolume());
                values.put(max_Dosage, drug.getMaxDosage());
                values.put(calc_Method, drug.getCalcMethod());
                values.put(type_Patient, drug.getTypePatient());
                db.insert(TABLE_DRUGS, null, values);
            }

     /* private void addDrug() {
          Drugs d1 = new Drugs(" Paracetamol (120/5) ", " 120 ", " 5 ", " 60 ", " p ", " p ");
          this.addDrugs(d1);
          Drugs d2 = new Drugs(" Paracetamol (250/5) ", " 250 ", " 5 ", " 60 ", " p ", " p ");
          this.addDrugs(d2);
          Drugs d3 = new Drugs(" Ibuprofen (100/5) ", " 100 ", " 5 ", " 40 ", " p ", " p ");
          this.addDrugs(d3);
          Drugs d4 = new Drugs(" Cyclizine (50/1) ", " 50 ", " 1 ", " 150 ", " a ", " a ");
          this.addDrugs(d4);
          Drugs d5 = new Drugs(" Droperidol (2.5/1) ", " 2.5 ", " 1 ", "  ", " a ", " a ");
          this.addDrugs(d5);
          Drugs d6 = new Drugs(" Morphine (100/5) ", " 100 ", " 5 ", "  ", " a ", " p ");
          this.addDrugs(d6);
      }*/

    public boolean insertNewDrug(String name, String weight, String volume, String mDosage, String cMethod, String patientType){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(drug_ID, drugId);
        values.put(drug_Name, name);
        values.put(drug_Weight, weight);
        values.put(drug_Volume, volume);
        values.put(max_Dosage, mDosage);
        values.put(calc_Method, cMethod);
        values.put(type_Patient, patientType);

        long result = db.update(TABLE_DRUGS, values," DrugName = ?",new String[] { drug_Name });
        if (result == -1)
            return  false;
        else
            return true ;

    }

    public boolean updateDrug(String name, String weight, String volume, String mDosage, String cMethod, String patientType){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();
       // values.put(drug_ID, drugId);
        values.put(drug_Name, name);
        values.put(drug_Weight, weight);
        values.put(drug_Volume, volume);
        values.put(max_Dosage, mDosage);
        values.put(calc_Method, cMethod);
        values.put(type_Patient, patientType);

        long result = db.update(TABLE_DRUGS, values," DrugName = ?",new String[] { drug_Name });
        if (result == -1)
            return  false;
        else
            return true ;

    }

    public boolean deleteDrug(String drug_Name){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_DRUGS, " DrugName = ?",new String[] { drug_Name });
        if (result == -1)
            return  false;
        else
            return true ;
    }

    public Cursor getDrugData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor drugCursor = db.rawQuery(" select * from " + TABLE_DRUGS, null);
        return drugCursor;
    }

}