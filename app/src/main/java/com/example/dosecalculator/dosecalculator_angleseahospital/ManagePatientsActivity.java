package com.example.dosecalculator.dosecalculator_angleseahospital;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;


import com.example.dosecalculator.dosecalculator_angleseahospital.database.Database;


public class ManagePatientsActivity extends AppCompatActivity {

    ListView lv_patients;
    Database db;
    Cursor myCursor;
    ArrayList<Patient> patientList;
    ListView my_lv;
    Patient patient;
    public String carryPatientId;
    public String carryPatientName;
    public String carryPatientDob;
    public String carryPatientWeight;
    public String carryPatientType;
    EditText patientId;
    String myPatientId;

    FloatingActionButton add;
    FloatingActionButton update;
    FloatingActionButton delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_patient);

        /*Toolbar myToolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);*/

        db=new Database(this);
        add=(FloatingActionButton) findViewById(R.id.ficon_add_patients);
        update=(FloatingActionButton) findViewById(R.id.ficon_update_patients);
        delete=(FloatingActionButton) findViewById(R.id.ficon_delete_patients);
        lv_patients=(ListView) findViewById(R.id.lv_patient);

      // txtSearch=(EditText)findViewById(R.id.txt_patient_names);


        addBtnClicked();
        updateBtnClicked();
        deleteBtnClicked();
        displayPatients();

        lv_patients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                patient =patientList.get(position);
                patientId=(EditText)findViewById(R.id.txt_patient_names);

                patientId.setText((patient.getpName()));
                //nurseId=nurse.geteId();
                carryPatientId=patient.getNhi_no();
                carryPatientName=patient.getpName();
                carryPatientDob=patient.getpDob();
                carryPatientWeight=patient.getpWeight();
                carryPatientType=patient.getpType();
            }}
        );
    }

    private void displayPatients() {
        patientList=new ArrayList<>();
        myCursor= db.getPatientData();

        if(myCursor.getCount()==0){
            Toast.makeText(ManagePatientsActivity.this, "No Data", Toast.LENGTH_LONG).show();
        }
        else{

            while(myCursor.moveToNext()){
                patient=new Patient(myCursor.getString(0),myCursor.getString(1),myCursor.getString(2),myCursor.getString(3),myCursor.getString(4), myCursor.getString(5));
                patientList.add(patient);
                PatientAdapter myAdapter=new PatientAdapter(this,R.layout.patient_adapter_view,patientList);
                lv_patients.setAdapter(myAdapter);

            }

        }
    }


    private void deleteBtnClicked() {
        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                new AlertDialog.Builder(ManagePatientsActivity.this)
                        .setTitle(getString(R.string.delete_rooms_dialog_title))
                        .setMessage(getString(R.string.delete_rooms_dialog))
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {

                                boolean isDeleted=db.deletePatient(patient.getNhi_no().toString());
                                if(isDeleted==true){
                                    Toast.makeText(ManagePatientsActivity.this, "Data deleted", Toast.LENGTH_LONG).show();
                                    finish();
                                    startActivity(getIntent());}
                                else
                                    Toast.makeText(ManagePatientsActivity.this, "Patient Details deleted", Toast.LENGTH_LONG).show();

                            }})
                        .setNegativeButton(android.R.string.no, null).show();


            }
        });
    }

    private void updateBtnClicked() {
        update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                Intent intent = new Intent(ManagePatientsActivity.this,UpdatePatientActivity.class);

                Bundle extras = new Bundle();
                extras.putString("carryPatientId", carryPatientId);
                extras.putString("carryPatientName", carryPatientName);
                extras.putString("carryPatientDob", carryPatientDob);
                extras.putString("carryPatientWeight", carryPatientWeight);
                extras.putString("carryPatientType", carryPatientType);
                intent.putExtras(extras);

                startActivity(intent);
            }
        });
    }

    private void addBtnClicked() {
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                Intent intent = new Intent(ManagePatientsActivity.this,AddPatientActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater myMenuInflater=getMenuInflater();
        myMenuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_home:
                //get the home
                Intent home = new Intent(ManagePatientsActivity.this, MainActivity.class);
                startActivity(home);
                break;
            case R.id.action_add_room:
                //get the categories cursor for the
                Intent room = new Intent(ManagePatientsActivity.this, ManageRoomsActivity.class);
                startActivity(room);
                break;
            case R.id.action_nurse:
                //get the categories cursor for the
                Intent nurses = new Intent(ManagePatientsActivity.this, ManageNursesActivity.class);
                startActivity(nurses);
                break;
         /*   case R.id.action_drug:
                //get the categories cursor for the
                Intent drugs = new Intent(RegisterPatient.this, ManageDrugsActivity.class);
                startActivity(drugs);
                break;*/
        }
        return super.onOptionsItemSelected(item);
    }
}
