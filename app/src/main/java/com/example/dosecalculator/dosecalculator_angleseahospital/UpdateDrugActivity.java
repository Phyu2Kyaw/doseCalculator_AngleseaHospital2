package com.example.dosecalculator.dosecalculator_angleseahospital;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dosecalculator.dosecalculator_angleseahospital.database.Database;

public class UpdateDrugActivity extends AppCompatActivity {

    EditText drugName;
    public Boolean calcMethodSelection;
    public String typePatientSelection;
    EditText weight;
    EditText volume;
    EditText maxDosage;
    Button update;
    Database db;
    CheckBox calcMethod;
    CheckBox chk_method;

    String myDrugName;
    String a;
    String myDrugWeight;
    String myDrugVolume;
    String myMaxDosage;
    String myCalcMethod;
    public String selectCalcMethod;
    String drugId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_drug);

        db = new Database(this);
        CheckBox calcMethod = (CheckBox) findViewById(R.id.chk_weight_dep);
        drugName = (EditText)findViewById(R.id.txt_dname);
        drugName.setGravity(Gravity.TOP);
        weight = (EditText)findViewById(R.id.txt_dWeight);
        volume = (EditText)findViewById(R.id.txt_vol);
        maxDosage = (EditText)findViewById(R.id.txt_dMax);
        //final calcMethod = ((CheckBox) findViewById(R.id.chk_weight_dep));
        update =(Button)findViewById(R.id.btn_update_button);

        //to get passed string value
        Bundle extras = getIntent().getExtras();
        myDrugName = extras.getString("carryDrugName");
        myDrugWeight = extras.getString("weight");
        myDrugVolume = extras.getString("volume");
        myMaxDosage = extras.getString("maxDosage");
        myCalcMethod = extras.getString("method");
        drugId = extras.getString("CarryDrugId");


        drugName.setText(myDrugName);
        weight.setText(myDrugWeight);
        volume.setText(myDrugVolume);
        maxDosage.setText(myMaxDosage);



        if(myCalcMethod.equals("True")){
            calcMethod.setChecked(true);
            myCalcMethod="True";
        }
        else if(myCalcMethod.equals("False")){
            calcMethod.setChecked(false);
            myCalcMethod="False";
        }

        //checkbox();


        updateButtonClicked();
    }

    private void checkbox() {
        if (calcMethod.isChecked()){
            a="True";
        }
        else{
            a="False";
        }

    }

    public void updateButtonClicked(){
        update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){



                boolean isInserted=db.updateDrug(drugId,drugName.getText().toString(),weight.getText().toString(),
                        volume.getText().toString(), maxDosage.getText().toString(),myCalcMethod);
                if(isInserted==true)
                    Toast.makeText(UpdateDrugActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(UpdateDrugActivity.this, "Drug Details Updated", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(UpdateDrugActivity.this, ManageDrugsActivity.class);
                startActivity(intent);
            }
        });
    }




}
