package com.example.dosecalculator.dosecalculator_angleseahospital;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dosecalculator.dosecalculator_angleseahospital.database.Database;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

//import static com.example.dosecalculator.dosecalculator_angleseahospital.R.id.lv_cal_pDetails;

public class AddCalculation extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Database db;
    FloatingActionButton pIdRefresh;
    EditText pId;
    EditText standingOrder;
    Cursor cursorPidFound;
    Cursor cursorRoomList;

    ArrayList<Patient> patientList;
    String pName;
    String myRoom;
    String myPatientId;
    String myStandingOrder;
    String pType;
    Patient patientObj;
    ListView lv_pDetails;
    TextView pname;
    TextView pDob;

    Spinner selectRoom;
    Button calculate;
    List<String> roomList;
    ArrayList<String> listResult;
    String[] ITEMS;

    public String carryPatientId;
    public String carryDrugId;
    public String carryStandingOrder;
    public String carryRoomId;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_calculation);

        db = new Database(this);

        pIdRefresh = (FloatingActionButton) findViewById(R.id.btn_cal_pid);
        pId=(EditText) findViewById(R.id.txt_cal_pid);
        myPatientId=pId.getText().toString();
        carryPatientId=myPatientId;

        standingOrder=(EditText) findViewById(R.id.txt_standing_order);
        myStandingOrder=standingOrder.getText().toString();
        carryStandingOrder=myStandingOrder;

        selectRoom=(Spinner) findViewById(R.id.spn_room);
        calculate=(Button) findViewById(R.id.btn_calculate);

        selectRoom();

        showPatientDetails();
        calculate();

        carryRoomId=myRoom;

    }

    private void calculate() {
        calculate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View V){

                /*listResult =new ArrayList<>();

                Result myResult=new Result(myRoom,null,12,null,45);
                listResult.add(myResult.toString());*/


                ITEMS=new String[] {myRoom,"12","45"};
                LayoutInflater factory = LayoutInflater.from(AddCalculation.this);
                View content = factory.inflate(R.layout.result_diaglog, null);

                ListView lv = (ListView) content.findViewById(R.id.list);

                lv.setAdapter(new ArrayAdapter<String>(AddCalculation.this,
                        android.R.layout.simple_list_item_1, ITEMS));

                new AlertDialog.Builder(AddCalculation.this)
                        .setView(content)
                        .setTitle("Hello, title!")
                        .setView(content)
                        .setPositiveButton(android.R.string.yes,
                                new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        Intent intent = new Intent(AddCalculation.this,NurseConfirmation.class);

                                        Bundle extras = new Bundle();
                                        extras.putString("carryPatientId", carryPatientId);
                                        extras.putString("carryStandingOrder", carryStandingOrder);
                                        extras.putString("carryRoomId", carryRoomId);
                                        intent.putExtras(extras);

                                        startActivity(intent);

                                    }
                                })
                        .setNegativeButton(android.R.string.no,null).show();
            }
        });
    }


    private void showDrugDetails() {
    }

    private void selectRoom() {

        roomList= new ArrayList<String>();
        cursorPidFound= db.getActiveRoom();

        if(cursorPidFound.getCount()==0){
            Toast.makeText(AddCalculation.this, "No Datsa", Toast.LENGTH_LONG).show();
        }
        else {

            if (cursorPidFound.moveToFirst()) {
                do {
                    roomList.add(cursorPidFound.getString(0));
                } while (cursorPidFound.moveToNext());
            }
        }
        cursorPidFound.close();
        db.close();
        ArrayAdapter<String> StatusAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, roomList);
        // Specify the layout to use when the list of choices appears
        StatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        selectRoom.setAdapter(StatusAdapter);
        selectRoom.setOnItemSelectedListener(this);

        myRoom=String.valueOf(selectRoom.getSelectedItem());
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // On selecting a spinner item
        String label = parent.getItemAtPosition(pos).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "You selected: " + label,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void showPatientDetails() {
        pIdRefresh.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){

                if(pId.getText().length() > 0 ){

                pname=(TextView) findViewById(R.id.dis_pname);
                pDob=(TextView) findViewById(R.id.disp_dob);
                patientList=new ArrayList<>();
                cursorPidFound= db.findPid(pId.getText().toString());

                pname.setText(cursorPidFound.getString(1));
                pDob.setText("( "+cursorPidFound.getString(2)+" )");

                }

                /*if(cursorPidFound!= null) {
                    Log.d("name", cursorPidFound.getString(1));
                }

                if(cursorPidFound.getCount()==0){
                    Toast.makeText(AddCalculation.this, "No Datsa", Toast.LENGTH_LONG).show();
                }
                else{

                    while(cursorPidFound.moveToNext()){
                        patientObj=new Patient(null,cursorPidFound.getString(1),cursorPidFound.getString(2),cursorPidFound.getString(3),null,null);


*/
                        /* patientList.add(patientObj);

                        Log.d("phyu",patientList.toString());


                        CalculatorAdapter myAdapter=new CalculatorAdapter(AddCalculation.this,R.layout.calculator_adapter_view,patientList);


                        lv_pDetails.setAdapter(myAdapter);

                    }
                }*/
            }
        });
    }
}
