package com.example.dosecalculator.dosecalculator_angleseahospital;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dosecalculator.dosecalculator_angleseahospital.database.Database;

public class RoomsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText roomName;
    public String myStatusSelection;
    public String myRoomType;
    EditText roomDetails;
    Button myButton;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room);

        db=new Database(this);

        roomName=(EditText)findViewById(R.id.txt_rm_name);
        roomDetails=(EditText)findViewById(R.id.txt_rm_details);
        myButton=(Button)findViewById(R.id.btn_add_room);



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

        type.setOnItemSelectedListener(this);

        myStatusSelection=String.valueOf(status.getSelectedItem());
        myRoomType=String.valueOf(type.getSelectedItem());


        addButtonClicked();
    }



    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
       // myStatusSelection=parent.getItemAtPosition(pos).toString();
        //Rooms room=new Rooms();
        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();

    }

    public void addButtonClicked(){
        myButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                boolean isInserted=db.insertRoom(roomName.getText().toString(), roomDetails.getText().toString(),myStatusSelection, myRoomType );
                if(isInserted==true)
                    Toast.makeText(RoomsActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(RoomsActivity.this, "Room Details inserted", Toast.LENGTH_LONG).show();
            }
        });
    }


    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }


}
