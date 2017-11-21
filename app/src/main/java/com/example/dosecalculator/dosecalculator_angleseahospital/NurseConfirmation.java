package com.example.dosecalculator.dosecalculator_angleseahospital;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
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

public class NurseConfirmation extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Database db;

    FloatingActionButton refresh;
    TextView nName;
    EditText nId;
    CheckBox agree;
    Button complete;
    Spinner status;
    Spinner time;

    Cursor cursorNurse;
    Cursor cursorRoomDetails;
    ArrayList<Nurse> nurseList;
    Cursor cursorNidFound;

    String pId;
    String pName;
    String standingOrder;
    String roomId;
    String nurseId;
    String myStatusSelection;
    String myTimeSelection;
    String roomStatus;
    Resources res = getResources();
    String[] notifyTime = res.getStringArray(R.array.time_status);
    int notiTime;

    NotificationCompat.Builder notification;
    public static final int uniqueID=4288;


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
        time=(Spinner) findViewById(R.id.spn_time);

        //to get passed string value
        Bundle extras = getIntent().getExtras();
        pId = extras.getString("carryPatientId");
        pName = extras.getString("carryPatientName");
        standingOrder = extras.getString("carryStandingOrder");
        roomId = extras.getString("carryRoomId");

        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);

        displayNurseName();
        spinnerStatus();
        spinnerTime();
        completeCalculation();
    }


    private void notification() {

        notification.setSmallIcon(R.drawable.update_icon);
        notification.setTicker("This is the ticker");
        notification.setWhen((System.currentTimeMillis()
                + ((60 * 1000) * notiTime)));
        notification.setContentTitle("Re-administer Medication");
        notification.setContentText(roomId + pName);
        notification.setSubText("Click to view details");

        Intent intent = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        //Builds notification and issues it
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(uniqueID, notification.build());
    }

    private void spinnerTime() {
        ArrayAdapter<CharSequence> StatusAdapter = ArrayAdapter.createFromResource(this,
                R.array.time_status, android.R.layout.simple_spinner_item);
        StatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        time.setAdapter(StatusAdapter);
        time.setOnItemSelectedListener(this);

        myTimeSelection = String.valueOf(time.getSelectedItem());

    }

    private void spinnerStatus() {

        ArrayAdapter<CharSequence> StatusAdapter = ArrayAdapter.createFromResource(this,
                R.array.calculation_status, android.R.layout.simple_spinner_item);
        StatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        status.setAdapter(StatusAdapter);
        status.setOnItemSelectedListener(this);

        myStatusSelection= String.valueOf(status.getSelectedItem());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();

        Spinner spinner = (Spinner) parent;
        if (spinner.getId() == R.id.spn_time) {
            switch (pos) {
                case 0:
                    notification();
                    notiTime = 15;
                    break;
                case 1:
                    notification();
                    notiTime = 30;
                    break;
                case 2:
                    notification();
                    notiTime = 60;
                    break;
                case 3:
                    notification();
                    notiTime = 120;
                    break;
                case 4:
                    notification();
                    notiTime = 180;
                    break;
                case 5:
                    notification();
                    notiTime = 240;
                    break;
                case 6:
                    notification();
                    notiTime = 300;
                    break;
                case 7:
                    notification();
                    notiTime = 360;
                    break;
                case 8:
                    notification();
                    notiTime = 420;
                    break;
                case 9:
                    notification();
                    notiTime = 480;
                    break;
            }
        }
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

                db.getWritableDatabase();
                ContentValues values = new ContentValues();
                roomStatus="Deactive";
                db.updateRoomStatus(roomId,roomStatus);

                db.insertCalculation("hi","hi","hi","hi",1,2,"hi");
            }
        });
    }
}

