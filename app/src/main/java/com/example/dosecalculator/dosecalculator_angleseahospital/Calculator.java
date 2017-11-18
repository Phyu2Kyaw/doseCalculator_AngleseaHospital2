package com.example.dosecalculator.dosecalculator_angleseahospital;

/**
 * Created by komyo on 17/11/2017.
 */

public class Calculator {
    String pId;
    String rId;
    String dId;
    String nId;
    Integer standing_order;
    Integer result;
    String status;

    public Calculator(Integer cId,String pId, String rId, String dId, String nId, Integer standing_order, Integer result, String status) {
        this.pId = pId;
        this.rId = rId;
        this.dId = dId;
        this.nId = nId;
        this.standing_order = standing_order;
        this.result = result;
        this.status = status;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    public String getdId() {
        return dId;
    }

    public void setdId(String dId) {
        this.dId = dId;
    }

    public String getnId() {
        return nId;
    }

    public void setnId(String nId) {
        this.nId = nId;
    }

    public Integer getStanding_order() {
        return standing_order;
    }

    public void setStanding_order(Integer standing_order) {
        this.standing_order = standing_order;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
