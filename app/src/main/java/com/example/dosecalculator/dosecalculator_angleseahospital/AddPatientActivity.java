package com.example.dosecalculator.dosecalculator_angleseahospital;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.example.dosecalculator.dosecalculator_angleseahospital.database.Database;

import java.util.ArrayList;
import java.util.Calendar;


public class AddPatientActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    RadioGroup rg;
    Database db;
    RadioButton rb;

    EditText txtId;
    EditText txtName;
    EditText txtWeight;
    Button btnAdd;
    Spinner spnStatus;

    String rdType;
    String date;
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
        setContentView(R.layout.activity_add_patient);
        db=new Database(this);

        rg=(RadioGroup) findViewById(R.id.rgroup);
        spnStatus=(Spinner) findViewById(R.id.my_patient_status_spinner);
        btnAdd=(Button) findViewById(R.id.btn_add_patient);
        txtId=(EditText) findViewById(R.id.txt_patient_id);
        txtName=(EditText) findViewById(R.id.txt_patient_name);
        txtWeight=(EditText) findViewById(R.id.txt_patient_weight);


        addButtonClicked();
        spinner();

        setCurrentDateOnView();
        addListenerOnButton();

    }

    private void addListenerOnButton() {
        btnChangeDate = (Button) findViewById(R.id.btnChangeDate);

        btnChangeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
    }

    private void setCurrentDateOnView() {
        tvDisplayDate = (TextView) findViewById(R.id.tvDate);
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

    private void addButtonClicked() {
        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                boolean isInserted=db.insertPatient(txtId.getText().toString(), txtName.getText().toString(), date,txtWeight.getText().toString() , rdType,myStatusSelection );
                if(isInserted==true)
                    Toast.makeText(AddPatientActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(AddPatientActivity.this, "Room Details inserted", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AddPatientActivity.this,ManagePatientsActivity.class);
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
