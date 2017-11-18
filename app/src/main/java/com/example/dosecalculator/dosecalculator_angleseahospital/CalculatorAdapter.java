package com.example.dosecalculator.dosecalculator_angleseahospital;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CalculatorAdapter extends ArrayAdapter<Patient> {

    private LayoutInflater myInflater;
    private ArrayList<Patient> patients;
    private int mViewResourceId;


    public CalculatorAdapter(Context context, int resource, ArrayList<Patient> patients) {
        super(context, resource, patients);
        this.patients=patients;
        myInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId= resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView =myInflater.inflate(mViewResourceId,null);
        Patient ary_list_patient=patients.get(position);

        Log.d("fuck", "tired");

        if(ary_list_patient!=null){
            TextView pName=(TextView) convertView.findViewById(R.id.lbl_calc_name);
            TextView pDob=(TextView) convertView.findViewById(R.id.lbl_calc_dob);
            TextView pWeight=(TextView) convertView.findViewById(R.id.lbl_calc_weight);

            if(pName==null){
               pName.setText("Tiredddd");
                //pName.setText((ary_list_patient.getpName()));
            }
            if(pDob!=null){
                pDob.setText((ary_list_patient.getpDob()));
            }
            if(pWeight!=null){
                pWeight.setText((ary_list_patient.getpWeight()));
            }

        }
        return convertView;

    }
}
