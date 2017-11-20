package com.example.dosecalculator.dosecalculator_angleseahospital;


/**
 * Created by cutem on 7/11/2017.
 */

public class Drugs {

    public Integer drug_ID;
    public String drug_Name;
    public String drug_Weight;
    public String drug_Volume;
    public String max_Dosage;
    public String calc_Method;
    public String type_Patient;

    public Drugs() { super();}

    public Drugs (Integer id, String dName, String dWeight, String dVolume, String mDosage, String cMethod) {

        super();
        this.drug_ID=id;
        this.drug_Name = dName;
        this.drug_Weight = dWeight;
        this.drug_Volume = dVolume;
        this.max_Dosage = mDosage;
        this.calc_Method = cMethod;

    }

   public Integer getDrugId() { return drug_ID;    }

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

   public void setDrugId(Integer id) {  this.drug_ID = id;}

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

}

