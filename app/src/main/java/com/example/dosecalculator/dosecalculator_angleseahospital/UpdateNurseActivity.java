package com.example.dosecalculator.dosecalculator_angleseahospital;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dosecalculator.dosecalculator_angleseahospital.database.Database;

public class UpdateNurseActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText nurseName;
    EditText nurseId;
    Button btnUpdate;
    Spinner status;

    String myNurseName;
    String myNurseId;
    public String myStatusSelection;

    Database db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_nurse);

        db=new Database(this);
        nurseName=(EditText)findViewById(R.id.txt_nurse_name_upd);
        nurseId=(EditText)findViewById(R.id.txt_nurse_id_upd);

        //to get passed string value
        Bundle extras = getIntent().getExtras();
        myNurseId = extras.getString("carryNurseId");
        myNurseName = extras.getString("carryNurseName");
        nurseId.setText(myNurseId);
        nurseName.setText(myNurseName);

        status = (Spinner) findViewById(R.id.spn_status);
        btnUpdate=(Button)findViewById(R.id.btn_nurse_update);


        updateButtonClickedNurse();
        spinner();
    }


    private void spinner() {
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(this,
                R.array.nurse_status, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> StatusAdapter = ArrayAdapter.createFromResource(this,
                R.array.nurse_status, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        StatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        status.setAdapter(StatusAdapter);
        status.setOnItemSelectedListener(this);
        myStatusSelection=String.valueOf(status.getSelectedItem());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
        myStatusSelection=parent.getItemAtPosition(pos).toString();
        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void updateButtonClickedNurse() {
        btnUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                boolean isInserted=db.updateNurse(nurseId.getText().toString(),nurseName.getText().toString(), myStatusSelection );
                if(isInserted==true)
                    Toast.makeText(UpdateNurseActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(UpdateNurseActivity.this, "Nurse Details inserted", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(UpdateNurseActivity.this,ManageNursesActivity.class);
                startActivity(intent);
            }
        });

    }
}
