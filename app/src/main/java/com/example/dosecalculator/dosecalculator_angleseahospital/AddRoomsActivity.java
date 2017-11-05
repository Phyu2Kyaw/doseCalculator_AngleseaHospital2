package com.example.dosecalculator.dosecalculator_angleseahospital;

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

public class AddRoomsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText roomId;
    public String myStatusSelection;
    public String myRoomType;
    EditText roomDetails;
    Button myButton;
    Database db;
    Spinner status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_room);

        db=new Database(this);
        roomId=(EditText)findViewById(R.id.txt_rm_Id);
        roomDetails=(EditText)findViewById(R.id.txt_rm_details);
        status = (Spinner) findViewById(R.id.spn_status);
        myButton=(Button)findViewById(R.id.btn_add_room);

        addButtonClicked();
        spinner();
    }

    private void spinner() {
        // Create an ArrayAdapter using the string array and a default spinner layout

        ArrayAdapter<CharSequence> StatusAdapter = ArrayAdapter.createFromResource(this,
                R.array.room_status, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        StatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        status.setAdapter(StatusAdapter);
        status.setOnItemSelectedListener(this);


        myStatusSelection=String.valueOf(status.getSelectedItem());
    }


    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
       // myStatusSelection=parent.getItemAtPosition(pos).toString();
        //Rooms add_room=new Rooms();
        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();

    }

    public void addButtonClicked(){
        myButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                boolean isInserted=db.insertRoom(roomId.getText().toString(), roomDetails.getText().toString(),myStatusSelection );
                if(isInserted==true)
                    Toast.makeText(AddRoomsActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(AddRoomsActivity.this, "Room Details inserted", Toast.LENGTH_LONG).show();
            }
        });
    }


    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }


}
