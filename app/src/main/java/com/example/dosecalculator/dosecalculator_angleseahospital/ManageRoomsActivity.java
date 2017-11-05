package com.example.dosecalculator.dosecalculator_angleseahospital;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dosecalculator.dosecalculator_angleseahospital.database.Database;

import java.util.ArrayList;

public class ManageRoomsActivity extends AppCompatActivity {
    Button myAddButton;
    Button myUpdateButton;
    Button myDeleteButton;

    ListView lv_room;
    Database db;
    Cursor myCursor;
    ArrayList<Room> RoomList;
    ListView my_lv;
    Room room;

    String rmId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_room);

        db=new Database(this);


        myAddButton=(Button)findViewById(R.id.btn_add_room);
        myUpdateButton=(Button)findViewById(R.id.btn_update_room);
        myDeleteButton=(Button)findViewById(R.id.btn_delete_room);
        lv_room=(ListView) findViewById(R.id.lst_view_room);


        addBtnClicked();
        updateBtnClicked();
        deleteBtnClicked();
        displayRooms();




    }

    private void displayRooms() {

        RoomList=new ArrayList<>();
        myCursor= db.getRoomData();

        if(myCursor.getCount()==0){
            Toast.makeText(ManageRoomsActivity.this, "No Data", Toast.LENGTH_LONG).show();
        }
        else{

            while(myCursor.moveToNext()){
                room=new Room(myCursor.getString(0),myCursor.getString(1),myCursor.getString(2));
                RoomList.add(room);
                RoomAdapter myAdapter=new RoomAdapter(this,R.layout.room_adapter_view,RoomList);
                lv_room.setAdapter(myAdapter);
            }
        }
    }

    private void deleteBtnClicked() {

        myDeleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                Intent intent = new Intent(ManageRoomsActivity.this,DeleteRoomActivity.class);
                startActivity(intent);
            }
        });
    }

    public void updateBtnClicked() {

        myUpdateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                Intent intent = new Intent(ManageRoomsActivity.this,UpdateRoomActivity.class);
                startActivity(intent);
            }
        });
    }

    public void addBtnClicked(){
        myAddButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                Intent intent = new Intent(ManageRoomsActivity.this,AddRoomsActivity.class);
                startActivity(intent);
            }
        });
    }




}
