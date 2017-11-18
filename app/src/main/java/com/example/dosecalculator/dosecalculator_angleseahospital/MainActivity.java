package com.example.dosecalculator.dosecalculator_angleseahospital;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button addPatient;
    Button doCalculation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        addPatient=(Button)findViewById(R.id.btn_add_patient);
        doCalculation=(Button)findViewById(R.id.btn_calculate);


        addPatientClicked();
        doCalculation();
    }
    public void addPatientClicked(){
        addPatient.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                Intent patients = new Intent(MainActivity.this,ManagePatientsActivity.class);
                startActivity(patients);
            }
        });
    }

    public void doCalculation(){
        doCalculation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                Intent drugCalculate = new Intent(MainActivity.this,ManageCalculation.class);
                startActivity(drugCalculate);
            }
        });
    }

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
                Intent home = new Intent(MainActivity.this, MainActivity.class);
                startActivity(home);
                break;
            case R.id.action_add_room:
                //get the categories cursor for the
                Intent room = new Intent(MainActivity.this, ManageRoomsActivity.class);
                startActivity(room);
                break;
            case R.id.action_nurse:
                //get the categories cursor for the
                Intent nurses = new Intent(MainActivity.this, ManageNursesActivity.class);
                startActivity(nurses);
                break;
         /*   case R.id.action_drug:
                //get the categories cursor for the
                Intent drugs = new Intent(RegisterPatient.this, ManageDrugsActivity.class);
                startActivity(drugs);
                break;*/
        }
        return super.onOptionsItemSelected(item);
    }


}
