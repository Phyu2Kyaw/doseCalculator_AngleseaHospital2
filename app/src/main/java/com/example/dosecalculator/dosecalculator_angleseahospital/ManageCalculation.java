package com.example.dosecalculator.dosecalculator_angleseahospital;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.dosecalculator.dosecalculator_angleseahospital.database.Database;

public class ManageCalculation extends AppCompatActivity {
    FloatingActionButton add;
    FloatingActionButton update;
    FloatingActionButton delete;

    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_calculation);

        db = new Database(this);


        add=(FloatingActionButton) findViewById(R.id.btn_calc_add);
     /*   update=(FloatingActionButton) findViewById(R.id.icon_update);
        delete=(FloatingActionButton) findViewById(R.id.icon_delete);*/

        addBtnClicked();
    }

    private void addBtnClicked() {
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                Intent intent = new Intent(ManageCalculation.this,AddCalculation.class);
                startActivity(intent);
            }
        });
    }
}
