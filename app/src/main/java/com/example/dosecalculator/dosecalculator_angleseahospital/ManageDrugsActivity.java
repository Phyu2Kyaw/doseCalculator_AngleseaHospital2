package com.example.dosecalculator.dosecalculator_angleseahospital;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dosecalculator.dosecalculator_angleseahospital.database.Database;

import java.util.ArrayList;

public class ManageDrugsActivity extends AppCompatActivity {


    ListView lv_drugs;
    Database db;
    Cursor myCursor;
    ArrayList<Drugs> DrugList;
    ListView my_lv;
    Drugs drug;
    public String carryDrugName;
    public String carryTypePatient;
    EditText drugName;
    EditText typePatient;

    FloatingActionButton add;
    FloatingActionButton update;
    FloatingActionButton delete;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_drugs);

        db = new Database(this);


        add =(FloatingActionButton) findViewById(R.id.icon_nurse_add);
        update = (FloatingActionButton) findViewById(R.id.icon_update);
        delete = (FloatingActionButton) findViewById(R.id.icon_delete);
        lv_drugs = (ListView) findViewById(R.id.lst_view_drug);

        addBtnClicked();
        updateBtnClicked();
        deleteBtnClicked();
        displayDrugs();

        lv_drugs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                drug = DrugList.get(position);
                drugName =(EditText)findViewById(R.id.txt_drug_name);

                drugName.setText((drug.getDrugName()));
                typePatient.setText((drug.getTypePatient()));
                carryDrugName = drug.getDrugName();
                carryTypePatient= drug.getTypePatient();
            }}
        );


    }

    private void displayDrugs() {

        DrugList = new ArrayList<>();
        myCursor = db.getDrugData();

        if(myCursor.getCount()==0){
            Toast.makeText(ManageDrugsActivity.this, "No Drugs are Listed", Toast.LENGTH_LONG).show();
        }
        else{

            while(myCursor.moveToNext()){
                drug = new Drugs(myCursor.getString(0),myCursor.getString(1),myCursor.getString(2),
                        myCursor.getString(3),myCursor.getString(4),myCursor.getString(5));
                DrugList.add(drug);
               DrugAdapter myAdapter = new DrugAdapter(this,R.layout.activity_drug_adapter, DrugList);
               lv_drugs.setAdapter(myAdapter);

            }
        }
    }

    private void updateBtnClicked() {

        update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                Intent intent = new Intent(ManageDrugsActivity.this,UpdateDrugActivity.class);

                Bundle extras = new Bundle();
                extras.putString(" CarryDrugName ", carryDrugName);
                intent.putExtras(extras);

                startActivity(intent);
            }
        });
    }

    public void deleteBtnClicked() {

        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                new AlertDialog.Builder(ManageDrugsActivity.this)
                        .setTitle(getString(R.string.delete_rooms_dialog_title))
                        .setMessage(getString(R.string.delete_rooms_dialog))
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {

                                boolean isDeleted = db.deleteDrug(drugName.getText().toString());
                                if(isDeleted == true){
                                    Toast.makeText(ManageDrugsActivity.this, " Drug deleted ", Toast.LENGTH_LONG).show();
                                finish();
                                startActivity(getIntent());}
                                else
                                    Toast.makeText(ManageDrugsActivity.this, " Drug Details deleted ", Toast.LENGTH_LONG).show();

                               }})
                        .setNegativeButton(android.R.string.no, null).show();


            }
        });

    }

    public void addBtnClicked(){
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                Intent intent = new Intent(ManageDrugsActivity.this, AddDrugsActivity.class);
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
