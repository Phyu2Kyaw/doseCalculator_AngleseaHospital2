package com.example.dosecalculator.dosecalculator_angleseahospital.database;



public class Rooms  {
    private int _id;
    private String rm_name;
    private String rm_details;
    private String rm_status;
    private String rm_type;

    public Rooms(){}
    public Rooms(String name){
        this.rm_name=name;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getRm_name() {
        return rm_name;
    }

    public void setRm_name(String rm_name) {
        this.rm_name = rm_name;
    }

    public String getRm_details() {
        return rm_details;
    }

    public void setRm_details(String rm_details) {
        this.rm_details = rm_details;
    }

    public String getRm_status() {
        return rm_status;
    }

    public void setRm_status(String rm_status) {
        this.rm_status = rm_status;
    }

    public String getRm_type() {
        return rm_type;
    }

    public void setRm_type(String rm_type) {
        this.rm_type = rm_type;
    }
}
