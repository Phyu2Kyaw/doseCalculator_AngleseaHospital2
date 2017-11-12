package com.example.dosecalculator.dosecalculator_angleseahospital;

/**
 * Created by komyo on 5/11/2017.
 */

public class Nurse {
    public String nurseName;
    public String eId;
    public String status;

    public Nurse(){
        super();
    }

    public Nurse( String eId,String nurseName, String status){
        super();
        this.nurseName=nurseName;
        this.eId=eId;
        this.status=status;

    }

    public String getNurseName() {
        return nurseName;
    }

    public void setNurseName(String nurseName) {
        this.nurseName = nurseName;
    }

    public String geteId() {
        return eId;
    }

    public void seteId(String eId) {
        this.eId = eId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
