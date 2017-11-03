package com.example.dosecalculator.dosecalculator_angleseahospital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dosecalculator.dosecalculator_angleseahospital.database.Database;

public class ManageRoomsActivity extends AppCompatActivity {

    EditText roomName;
    EditText roomDetails;
    Button myButton;
    Button myUpdateButton;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_room);

        db=new Database(this);


        myButton=(Button)findViewById(R.id.btn_add_room);
        myUpdateButton=(Button)findViewById(R.id.btn_update_room);

        addBtnClicked();
        updateBtnClicked();
    }

    public void updateBtnClicked() {

        myUpdateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                Intent intent = new Intent(ManageRoomsActivity.this,RegisterPatient.class);
                startActivity(intent);
            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
   /*     String myStatusSelection=parent.getItemAtPosition(pos).toString();
        Rooms room=new Rooms();*/

    }

    public void addBtnClicked(){
        myButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                Intent intent = new Intent(ManageRoomsActivity.this,RoomsActivity.class);
                startActivity(intent);
            }
        });
    }


    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }


}
