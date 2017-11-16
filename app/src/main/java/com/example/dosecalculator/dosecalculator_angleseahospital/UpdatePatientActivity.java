package com.example.dosecalculator.dosecalculator_angleseahospital;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import com.example.dosecalculator.dosecalculator_angleseahospital.database.Database;

public class UpdatePatientActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    RadioGroup rg;
    Database db;
    RadioButton rb;

    EditText txtId;
    EditText txtName;
    EditText txtWeight;
    TextView txtDob;
    Button btnAdd;
    Spinner spnStatus;

    String rdType;
    String date;

    String myPatientId;
    String myPatientName;
    String myPatientDob;
    String myPatientWeight;
    String myPatientType;

    Integer weight;
    public String myStatusSelection;

    private TextView tvDisplayDate;
    private DatePicker dpResult;
    private Button btnChangeDate;

    private int year;
    private int month;
    private int day;
    static final int DATE_DIALOG_ID = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_patient);
        db=new Database(this);

        rg=(RadioGroup) findViewById(R.id.rd_upd_group);
        spnStatus=(Spinner) findViewById(R.id.spn_upd);
        btnAdd=(Button) findViewById(R.id.btn_update);
        txtId=(EditText) findViewById(R.id.txt_upd_pId);
        txtName=(EditText) findViewById(R.id.txt_update_pName);
        txtWeight=(EditText) findViewById(R.id.txt_upd_weight);
        txtDob=(TextView) findViewById(R.id.lbl_upd_display_date);

        //to get passed string value
        Bundle extras = getIntent().getExtras();
        myPatientId = extras.getString("carryPatientId");
        myPatientName = extras.getString("carryPatientName");
        myPatientDob = extras.getString("carryPatientDob");
        myPatientWeight = extras.getString("carryPatientWeight");
        //myPatientType = extras.getString("carryPatientType");

        txtId.setText(myPatientId);
        txtName.setText(myPatientName);
        txtDob.setText(myPatientDob);
        txtWeight.setText(myPatientWeight);

     /*   if(myPatientType=="Child"){
            if(rb.getText()=="Child"){
                rb.setChecked(true);
            }
        }
        else if (myPatientType=="Adult"){
            if(rb.getText()=="Adult"){
                rb.setChecked(true);
            }
        }*/
        spinner();
        setCurrentDateOnView();
        addListenerOnButton();
        upateButtonClicked();

    }

    private void addListenerOnButton() {
        btnChangeDate = (Button) findViewById(R.id.btn_upd_date);

        btnChangeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
    }

    private void setCurrentDateOnView() {
        tvDisplayDate = (TextView) findViewById(R.id.lbl_upd_display_date);
        /*tvDisplayDate.setText("");
        tvDisplayDate.setOnClickListener(yourClickListener);
        DisplayDate.getText().clear();
        tvDisplayDate.setVisibility(TextView.INVISIBLE);*/
        dpResult = (DatePicker) findViewById(R.id.dpResult);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // set current date into textview
        tvDisplayDate.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));
        date=tvDisplayDate.getText().toString();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener,
                        year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // set selected date into textview
            tvDisplayDate.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));

            // set selected date into datepicker also
            dpResult.init(year, month, day, null);

        }
    };

    private void spinner() {
        ArrayAdapter<CharSequence> StatusAdapter = ArrayAdapter.createFromResource(this,
                R.array.patient_status, android.R.layout.simple_spinner_item);
        StatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnStatus.setAdapter(StatusAdapter);
        spnStatus.setOnItemSelectedListener(this);

        myStatusSelection=String.valueOf(spnStatus.getSelectedItem());
    }

    private void upateButtonClicked() {
        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){

                if(txtId!= null) {
                    Log.d("id", txtId.getText().toString());
                }

                if(txtName!= null) {
                    Log.d("name", txtName.getText().toString());
                }

                if(date!= null) {
                    Log.d("date", date);
                }

                if(txtWeight!= null) {
                    Log.d("weight", txtWeight.getText().toString());
                }

                if(rdType!= null) {
                    Log.d("type", rdType);
                }
                if(myStatusSelection!= null) {
                    Log.d("type", myStatusSelection);
                }


                boolean isInserted=db.updatePatient(txtId.getText().toString(), txtName.getText().toString(),date, txtWeight.getText().toString(), rdType,myStatusSelection );
                if(isInserted==true)
                    Toast.makeText(UpdatePatientActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(UpdatePatientActivity.this, "Patient Details inserted", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(UpdatePatientActivity.this,ManagePatientsActivity.class);
                startActivity(intent);
            }
        });
    }

    public void rbclick(View v){
        int radiobuttonid=rg.getCheckedRadioButtonId();
        rb=(RadioButton) findViewById(radiobuttonid);
        rdType=rb.getText().toString();
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
}
