package com.example.dosecalculator.dosecalculator_angleseahospital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dosecalculator.dosecalculator_angleseahospital.database.Database;

public class AddDrugsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText drugName;
    public String calcMethodSelection;
    public String typePatientSelection;
    EditText weight;
    EditText volume;
    EditText maxDosage;
    Button myButton;
    Database db;
    Spinner calcMethod;
    Spinner typePatient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drugs);

        db=new Database(this);
        drugName = (EditText)findViewById(R.id.txt_drug_name);
        drugName.setGravity(Gravity.TOP);
        weight = (EditText)findViewById(R.id.mg);
        volume = (EditText)findViewById(R.id.mL);
        maxDosage = (EditText)findViewById(R.id.txt_maxDosage);
        calcMethod = (Spinner) findViewById(R.id.calcMethod);
        typePatient = (Spinner) findViewById(R.id.typePatient);
        myButton=(Button)findViewById(R.id.btn_add_drug);
        spinner();
        spinner2();
        addButtonClicked();
    }

    private void spinner() {
        // Create an ArrayAdapter using the string array and a default spinner layout

        ArrayAdapter<CharSequence> StatusAdapter = ArrayAdapter.createFromResource(this,
                R.array.type_patients, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        StatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        typePatient.setAdapter(StatusAdapter);
        typePatient.setOnItemSelectedListener(this);

        typePatientSelection = String.valueOf(typePatient.getSelectedItem());
    }

    private void spinner2() {
        // Create an ArrayAdapter using the string array and a default spinner layout

        ArrayAdapter<CharSequence> StatusAdapter = ArrayAdapter.createFromResource(this,
                R.array.calc_method, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        StatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        calcMethod.setAdapter(StatusAdapter);
        calcMethod.setOnItemSelectedListener(this);

        calcMethodSelection = String.valueOf(calcMethod.getSelectedItem());
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // myStatusSelection=parent.getItemAtPosition(pos).toString();
        //Rooms add_room=new Rooms();
        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();

    }

    public void addButtonClicked(){
        myButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                boolean isInserted=db.insertNewDrug(drugName.getText().toString(),weight.getText().toString(),
                        volume.getText().toString(), maxDosage.getText().toString(), calcMethodSelection, typePatientSelection );
                if(isInserted==true)
                    Toast.makeText(AddDrugsActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(AddDrugsActivity.this, "Drug Details inserted", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AddDrugsActivity.this, ManageDrugsActivity.class);
                startActivity(intent);
            }
        });
    }


    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
