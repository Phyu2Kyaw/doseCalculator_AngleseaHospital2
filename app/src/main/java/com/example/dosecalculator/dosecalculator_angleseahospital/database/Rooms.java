package com.example.dosecalculator.dosecalculator_angleseahospital.database;



public class Rooms  {
   /* private int _id;
    private String rm_name;
    private String rm_details;
    private String rm_status;
    private String rm_type;

    public static final class NotesEntry implements BaseColumns {

        // Table name
        public static final String TABLE_NAME = "notes";
        //column (field) names
        public static final String _ID= "ID" ;
        public static final String COLUMN_TEXT = "text";
        public static final String COLUMN_CREATED = "created";
        public static final String COLUMN_CATEGORY = "category";

    };*/
   public static final class RoomsEntry{
    public static final String TABLE_ROOMS="rooms";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_ROOM_NAME="roomname";
    public static final String COLUMN_ROOM_DETAILS="roomdetails";
    public static final String COLUMN_ROOM_STATUS="active";
    public static final String COLUMN_ROOM_TYPE="day";
   };

    public Rooms() {
    }


}
