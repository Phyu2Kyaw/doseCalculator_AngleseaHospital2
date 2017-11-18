package com.example.dosecalculator.dosecalculator_angleseahospital;

/**
 * Created by komyo on 18/11/2017.
 */

public class Result {
    String drugName;
    Integer drugMg;
    Integer drugMl;
    Integer standingOrder;
    Integer result;

    public Result(String drugName, Integer drugMg, Integer drugMl, Integer standingOrder, Integer result) {
        this.drugName = drugName;
        this.drugMg = drugMg;
        this.drugMl = drugMl;
        this.standingOrder = standingOrder;
        this.result = result;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public Integer getDrugMg() {
        return drugMg;
    }

    public void setDrugMg(Integer drugMg) {
        this.drugMg = drugMg;
    }

    public Integer getDrugMl() {
        return drugMl;
    }

    public void setDrugMl(Integer drugMl) {
        this.drugMl = drugMl;
    }

    public Integer getStandingOrder() {
        return standingOrder;
    }

    public void setStandingOrder(Integer standingOrder) {
        this.standingOrder = standingOrder;
    }

    public double getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
