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

public class DeleteRoomActivity extends AppCompatActivity{
    EditText roomId;
    Button myDeleteButton;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_room);

        db=new Database(this);
        roomId=(EditText)findViewById(R.id.txt_rm_Id);
        myDeleteButton=(Button)findViewById(R.id.btn_delete_room);

        deleteButtonClicked();
    }

    private void deleteButtonClicked() {
        myDeleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                boolean isDeleted=db.deleteRoom(roomId.getText().toString());
                if(isDeleted==true)
                    Toast.makeText(DeleteRoomActivity.this, "Data deleted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(DeleteRoomActivity.this, "Room Details deleted", Toast.LENGTH_LONG).show();
            }
        });

    }


}
