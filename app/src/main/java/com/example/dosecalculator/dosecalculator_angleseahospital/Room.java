package com.example.dosecalculator.dosecalculator_angleseahospital;

import android.support.v7.app.AppCompatActivity;

import com.example.dosecalculator.dosecalculator_angleseahospital.database.Database;

/**
 * Created by komyo on 5/11/2017.
 */

public class Room {
    public String roomId;
    public String details;
    public String status;

    public Room(){
        super();
    }

    public Room(String rmId, String details, String status){
        super();
        this.roomId=rmId;
        this.details=details;
        this.status=status;

    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
