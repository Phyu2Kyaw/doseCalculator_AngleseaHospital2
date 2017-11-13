package com.example.dosecalculator.dosecalculator_angleseahospital;

/**
 * Created by komyo on 13/11/2017.
 */

public class Patient {
    public String pName;
    public String nhi_no;
    public String pDob;
    public String pType;
    public String pStatus;
    public String pWeight;

    public Patient(String pName, String nhi_no, String pDob, String pWeight,String pType, String pStatus) {
        this.pName = pName;
        this.nhi_no = nhi_no;
        this.pDob = pDob;
        this.pWeight = pWeight;
        this.pType = pType;
        this.pStatus = pStatus;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getNhi_no() {
        return nhi_no;
    }

    public void setNhi_no(String nhi_no) {
        this.nhi_no = nhi_no;
    }

    public String getpDob() {
        return pDob;
    }

    public void setpDob(String pDob) {
        this.pDob = pDob;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public String getpWeight() {
        return pWeight;
    }

    public void setpWeight(String pWeight) {
        this.pWeight = pWeight;
    }

    public String getpStatus() {
        return pStatus;
    }

    public void setpStatus(String pStatus) {
        this.pStatus = pStatus;
    }
}
