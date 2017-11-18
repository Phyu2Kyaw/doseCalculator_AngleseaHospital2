package com.example.dosecalculator.dosecalculator_angleseahospital;

import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dosecalculator.dosecalculator_angleseahospital.database.Database;

import java.util.ArrayList;
import java.util.List;

public class NurseConfirmation extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Database db;

    FloatingActionButton refresh;
    TextView nName;
    EditText nId;
    CheckBox agree;
    Button complete;
    Spinner status;

    Cursor cursorNurse;
    Cursor cursorRoomDetails;
    ArrayList<Nurse> nurseList;
    Cursor cursorNidFound;

    String pId;
    String standingOrder;
    String roomId;
    String nurseId;
    String myStatusSelection;
    String roomStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_confirmation);

        db = new Database(this);

        nId=(EditText) findViewById(R.id.txt_nurseId);
        nurseId=nId.getText().toString();
        refresh = (FloatingActionButton) findViewById(R.id.btn_nurse_refresh);
        nName=(TextView) findViewById(R.id.lbl_disp_nName);
        agree=(CheckBox) findViewById(R.id.chkbox_confirm);
        complete=(Button) findViewById(R.id.btn_finish);
        status=(Spinner) findViewById(R.id.spn_calc_status);

        //to get passed string value
        Bundle extras = getIntent().getExtras();
        pId = extras.getString("carryPatientId");
        standingOrder = extras.getString("carryStandingOrder");
        roomId = extras.getString("carryRoomId");


        displayNurseName();
      spinner();
       completeCalculation();
    }

    private void spinner() {

        ArrayAdapter<CharSequence> StatusAdapter = ArrayAdapter.createFromResource(this,
                R.array.calculation_status, android.R.layout.simple_spinner_item);
        StatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        status.setAdapter(StatusAdapter);
        status.setOnItemSelectedListener(this);

        myStatusSelection=String.valueOf(status.getSelectedItem());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void displayNurseName() {
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {

                if (nId.getText().length() > 0) {

                    nurseList = new ArrayList<>();
                    cursorNidFound = db.findNid(nId.getText().toString());

                    nName.setText(cursorNidFound.getString(1));
                }
            }
        });
    }

    private void completeCalculation() {
        complete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){

                roomStatus="Deactive";
                db.updateRoomStatus(roomId,roomStatus);
                /*boolean isInserted=db.insertNurse(empId.getText().toString(),nurseName.getText().toString(), myStatusSelection );
                if(isInserted==true)
                    Toast.makeText(AddNursesActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(AddNursesActivity.this, "Nurse Details inserted", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AddNursesActivity.this,ManageNursesActivity.class);
                startActivity(intent);*/
            }
        });

    }
}

