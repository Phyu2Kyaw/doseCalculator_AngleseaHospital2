package com.example.dosecalculator.dosecalculator_angleseahospital;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dosecalculator.dosecalculator_angleseahospital.database.Database;

import java.util.ArrayList;

public class ManageNursesActivity extends AppCompatActivity {


    ListView lv_nurse;
    Database db;
    Cursor myCursor;
    ArrayList<Nurse> NurseList;
    ListView my_lv;
    Nurse nurse;
    public String carryNurseName;
    public String carryNurseId;
    EditText nurseName;
    String nurseId;
    EditText status;


    FloatingActionButton add;
    FloatingActionButton update;
    FloatingActionButton delete;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_nurses);

        /*Toolbar myToolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);*/

       db=new Database(this);


        add=(FloatingActionButton) findViewById(R.id.icon_nurse_add);
        update=(FloatingActionButton) findViewById(R.id.icon_update);
        delete=(FloatingActionButton) findViewById(R.id.icon_delete);
        lv_nurse=(ListView) findViewById(R.id.lst_view_nurse);

       //txtSearch=(EditText)findViewById(R.id.txt_nurse_name);


        addBtnClicked();
      updateBtnClicked();
        deleteBtnClicked();
      displayNurse();

        lv_nurse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                nurse =NurseList.get(position);
                nurseName=(EditText)findViewById(R.id.txt_nurse_name);

                nurseName.setText((nurse.getNurseName()));
                nurseId=nurse.geteId();
                carryNurseId=nurse.geteId();
                carryNurseName=nurse.getNurseName();
            }}
            );
    }

    private void displayNurse() {

        NurseList=new ArrayList<>();
        myCursor= db.getNurseData();

        if(myCursor.getCount()==0){
            Toast.makeText(ManageNursesActivity.this, "No Data", Toast.LENGTH_LONG).show();
        }
        else{

            while(myCursor.moveToNext()){
                nurse=new Nurse(myCursor.getString(0),myCursor.getString(1),myCursor.getString(2));
                NurseList.add(nurse);
                NurseAdapter myAdapter=new NurseAdapter(this,R.layout.nurse_adapter_view,NurseList);
                lv_nurse.setAdapter(myAdapter);

            }

        }
    }

    private void updateBtnClicked() {

        update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                Intent intent = new Intent(ManageNursesActivity.this,UpdateNurseActivity.class);

                Bundle extras = new Bundle();
                extras.putString("carryNurseId", carryNurseId);
                extras.putString("carryNurseName", carryNurseName);
                intent.putExtras(extras);

                startActivity(intent);
            }
        });
    }

    public void deleteBtnClicked() {

        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                new AlertDialog.Builder(ManageNursesActivity.this)
                        .setTitle(getString(R.string.delete_rooms_dialog_title))
                        .setMessage(getString(R.string.delete_rooms_dialog))
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {

                                boolean isDeleted=db.deleteNurse(nurseId);
                                if(isDeleted==true){
                                    Toast.makeText(ManageNursesActivity.this, "Data deleted", Toast.LENGTH_LONG).show();
                                finish();
                                startActivity(getIntent());}
                                else
                                    Toast.makeText(ManageNursesActivity.this, "Room Details deleted", Toast.LENGTH_LONG).show();

                               }})
                        .setNegativeButton(android.R.string.no, null).show();


            }
        });

    }

    public void addBtnClicked(){
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                Intent intent = new Intent(ManageNursesActivity.this,AddNursesActivity.class);
                startActivity(intent);
            }
        });
    }

   /* @Override
    protected void onResume() {

        super.onResume();
        this.onCreate(null);
    }*/
   @Override
   public boolean onCreateOptionsMenu (Menu menu){
       MenuInflater myMenuInflater=getMenuInflater();
       myMenuInflater.inflate(R.menu.menu_main, menu);
       return true;
   }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_home:
                //get the home
                Intent home = new Intent(ManageNursesActivity.this, MainActivity.class);
                startActivity(home);
                break;
            case R.id.action_add_room:
                //get the categories cursor for the rooms
                Intent room = new Intent(ManageNursesActivity.this, ManageRoomsActivity.class);
                startActivity(room);
                break;
            case R.id.action_nurse:
                //get the categories cursor for the nurses
                Intent nurses = new Intent(ManageNursesActivity.this, ManageNursesActivity.class);
                startActivity(nurses);
                break;
            case R.id.action_drug:
                //get the categories cursor for the drugs
                Intent drugs = new Intent(ManageNursesActivity.this, ManageDrugsActivity.class);
                startActivity(drugs);
                break;
            case R.id.action_patients:
                //get the categories cursor for the patients
                Intent patients = new Intent(ManageNursesActivity.this, ManagePatientsActivity.class);
                startActivity(patients);
                break;
        }
        return super.onOptionsItemSelected(item);
    }



}
