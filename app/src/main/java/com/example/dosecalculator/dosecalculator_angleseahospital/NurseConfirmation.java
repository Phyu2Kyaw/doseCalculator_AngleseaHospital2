package com.example.dosecalculator.dosecalculator_angleseahospital;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
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
    Double fiften_mins;
    Integer thirty_mins;
    Integer timeInSecond;
    String roomStatus;

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
        //startAlert();
    }

    private void startAlert() {


        if (myTimeSelection == "15 Minutes") {
            fiften_mins = 0.25;

            Double i = fiften_mins;

            notification.setSmallIcon(R.drawable.update_icon);
        notification.setTicker("this is the ticker");
        notification.setWhen( (System.currentTimeMillis()
                + ((60 * 1000) * 15)));
        notification.setContentTitle("Re-administer Medication");
        notification.setContentText(roomId + pName);
        notification.setSubText("Click to view details");

            Intent intent = new Intent(this, MainActivity.class);

            PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            notification.setContentIntent(pendingIntent);


            //Builds notification and issues it
            NotificationManager nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            nm.notify(uniqueID,notification.build());



            /*PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 234324243, intent, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, (long) (System.currentTimeMillis()
                                + (i * 1000)), pendingIntent);
            Toast.makeText(this, "Alarm set in " + i + " seconds",
                    Toast.LENGTH_LONG).show();*/
        }

        /*else if (myTimeSelection == "30 Minutes") {
            thirty_mins = 1;

            int i = Integer.parseInt(myTimeSelection);
            Intent intent = new Intent(this, MyBroadcastReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 234324243, intent, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                    + (i * 1000), pendingIntent);
            Toast.makeText(this, "Alarm set in " + i + " seconds",
                    Toast.LENGTH_LONG).show();
        }

        else {
            timeInSecond = Integer.valueOf(myTimeSelection);
            timeInSecond*=60;
            int i = Integer.parseInt(myTimeSelection);
            Intent intent = new Intent(this, MyBroadcastReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 234324243, intent, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                    + (i * 1000), pendingIntent);
            Toast.makeText(this, "Alarm set in " + i + " seconds",
                    Toast.LENGTH_LONG).show();
        }*/
    }

    private void spinnerTime() {
        ArrayAdapter<CharSequence> StatusAdapter = ArrayAdapter.createFromResource(this,
                R.array.time_status, android.R.layout.simple_spinner_item);
        StatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        time.setAdapter(StatusAdapter);
        time.setOnItemSelectedListener(this);

        myTimeSelection=String.valueOf(time.getSelectedItem());
    }


    private void spinnerStatus() {

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

                db.getWritableDatabase();
                ContentValues values = new ContentValues();
                roomStatus="Deactive";
                db.updateRoomStatus(roomId,roomStatus);

                db.insertCalculation("hi","hi","hi","hi",1,2,"hi");

                startAlert();
            }
        });

    }
}

