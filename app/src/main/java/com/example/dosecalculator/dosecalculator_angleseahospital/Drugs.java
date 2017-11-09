package com.example.dosecalculator.dosecalculator_angleseahospital;


/**
 * Created by cutem on 7/11/2017.
 */

public class Drugs {

    private int drug_ID;
    private String drug_Name;
    private String drug_Weight;
    private String drug_Volume;
    private String max_Dosage;
    private String calc_Method;
    private String type_Patient;

    public Drugs() {

    }

    public Drugs ( String name, String weight, String volume, String mDosage, String cMethod, String patient) {

        this.drug_Name = name;
        this.drug_Weight = weight;
        this.drug_Volume = volume;
        this.max_Dosage = mDosage;
        this.calc_Method = cMethod;
        this.type_Patient = patient;

    }

    public int getDrugId() {
        return drug_ID;
    }

    public String getDrugName() {
        return drug_Name;
    }

    public String getDrugWeight() {
        return drug_Weight;
    }

    public String getDrugVolume() {
        return drug_Volume;
    }

    public String getMaxDosage() { return max_Dosage; }

    public String getCalcMethod() {
        return calc_Method;
    }

    public String getTypePatient() {
        return type_Patient;
    }

    public void setDrugId(int id) {
        this.drug_ID = id;
    }

    public void setDrugName(String drugName) {
        this.drug_Name = drugName;
    }

    public void setDrugWeight(String drugWeight) { this.drug_Weight = drugWeight; }

    public void setDrugVolume(String drugVolume) {
        this.drug_Volume = drugVolume;
    }

    public void setMaxDosage(String maxDosage) {
        this.max_Dosage = maxDosage;
    }

    public void setCalcMethod(String calMethod) {
        this.calc_Method = calMethod;
    }

    public void setTypePatient(String typePatient) {
        this.type_Patient = typePatient;
    }
}

