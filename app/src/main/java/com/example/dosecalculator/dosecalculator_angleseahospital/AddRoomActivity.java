package com.example.dosecalculator.dosecalculator_angleseahospital;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.dosecalculator.dosecalculator_angleseahospital.database.MyDBHandler;
import com.example.dosecalculator.dosecalculator_angleseahospital.database.Rooms;

public class AddRoomActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText roomName;
    EditText roomDetails;
    Button myButton;
    MyDBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room);

        Spinner status = (Spinner) findViewById(R.id.spn_status);
      Spinner type = (Spinner) findViewById(R.id.spn_type);
    // Create an ArrayAdapter using the string array and a default spinner layout
       ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(this,
         R.array.room_categories, android.R.layout.simple_spinner_item);

    ArrayAdapter<CharSequence> StatusAdapter = ArrayAdapter.createFromResource(this,
            R.array.room_status, android.R.layout.simple_spinner_item);
    // Specify the layout to use when the list of choices appears
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        StatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    // Apply the adapter to the spinner
       type.setAdapter(typeAdapter);
        status.setAdapter(StatusAdapter);
        status.setOnItemSelectedListener(this);

        roomName=(EditText)findViewById(R.id.txt_rm_name);
        roomDetails=(EditText)findViewById(R.id.txt_rm_details);
        myButton=(Button)findViewById(R.id.btn_add_room);

        MyDBHandler myDb=new MyDBHandler(this, null, null, 1);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
   /*     String myStatusSelection=parent.getItemAtPosition(pos).toString();
        Rooms room=new Rooms();*/

    }

    public void addButtonClicked(View view){

        Rooms newRoom=new Rooms(roomName.getText().toString());
        db.addRoom(newRoom);


    }


    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }


}
