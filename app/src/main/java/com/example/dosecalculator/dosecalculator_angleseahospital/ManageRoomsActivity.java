package com.example.dosecalculator.dosecalculator_angleseahospital;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dosecalculator.dosecalculator_angleseahospital.database.Database;

import java.util.ArrayList;

public class ManageRoomsActivity extends AppCompatActivity {


    ListView lv_room;
    Database db;
    Cursor myCursor;
    ArrayList<Room> RoomList;
    ListView my_lv;
    Room room;
    public String carryRoomId;
    public String carryRoomDetails;
    public String carryRoomStatus;
    EditText roomId;

    FloatingActionButton add;
    FloatingActionButton update;
    FloatingActionButton delete;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_room);

        db=new Database(this);


        add=(FloatingActionButton) findViewById(R.id.icon_room_add);
        update=(FloatingActionButton) findViewById(R.id.icon_update);
        delete=(FloatingActionButton) findViewById(R.id.icon_delete);
        lv_room=(ListView) findViewById(R.id.lst_view_room);





        addBtnClicked();
       updateBtnClicked();
        deleteBtnClicked();
       displayRooms();

        lv_room.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                //roomId=(EditText)findViewById(R.id.txt_nurse_name);
                room =RoomList.get(position);
                roomId=(EditText)findViewById(R.id.txt_room_name);
                roomId.setText((room.getRoomId()));
                carryRoomId=room.getRoomId();
                carryRoomDetails=room.getDetails();
                carryRoomStatus=room.getStatus();

            }}
            );


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

    private void updateBtnClicked() {

        update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                Intent intent = new Intent(ManageRoomsActivity.this,UpdateRoomActivity.class);

                Bundle extras = new Bundle();
                extras.putString("CarryRoomId", carryRoomId);
                extras.putString("carryRoomDetails", carryRoomDetails);
                extras.putString("carryRoomStatus", carryRoomStatus);
                intent.putExtras(extras);

                startActivity(intent);
            }
        });
    }

    public void deleteBtnClicked() {

        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                new AlertDialog.Builder(ManageRoomsActivity.this)
                        .setTitle(getString(R.string.delete_rooms_dialog_title))
                        .setMessage(getString(R.string.delete_rooms_dialog))
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {

                                boolean isDeleted=db.deleteRoom(roomId.getText().toString());
                                if(isDeleted==true){
                                    Toast.makeText(ManageRoomsActivity.this, "Data deleted", Toast.LENGTH_LONG).show();
                                finish();
                                startActivity(getIntent());}
                                else
                                    Toast.makeText(ManageRoomsActivity.this, "Room Details deleted", Toast.LENGTH_LONG).show();

                               }})
                        .setNegativeButton(android.R.string.no, null).show();


            }
        });

    }

    public void addBtnClicked(){
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                Intent intent = new Intent(ManageRoomsActivity.this,AddRoomsActivity.class);
                startActivity(intent);
            }
        });
    }

   /* @Override
    protected void onResume() {

        super.onResume();
        this.onCreate(null);
    }*/




}
