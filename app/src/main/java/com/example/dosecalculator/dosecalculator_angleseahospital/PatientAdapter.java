package com.example.dosecalculator.dosecalculator_angleseahospital;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class PatientAdapter extends ArrayAdapter<Patient> {

    private LayoutInflater myInflater;
    private ArrayList<Patient> patients;
    private int mViewResourceId;


    public PatientAdapter(Context context, int resource, ArrayList<Patient> patients) {
        super(context, resource, patients);
        this.patients=patients;

        myInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView=myInflater.inflate(mViewResourceId,null);
        Patient patient=patients.get(position);

        if(patient!=null){
            TextView patientId=(TextView) convertView.findViewById(R.id.adapter_patient_id);
            TextView patientName=(TextView) convertView.findViewById(R.id.adapter_patient_name);
            TextView patientDob=(TextView) convertView.findViewById(R.id.adapter_patient_dob);
            TextView patientWeight=(TextView) convertView.findViewById(R.id.adapter_patient_weight);
            TextView patientType=(TextView) convertView.findViewById(R.id.adapter_patient_type);

            if(patientId!=null){
                patientId.setText((patient.getNhi_no()));
            }
            if(patientName!=null){
                patientName.setText((patient.getpName()));
            }
            if(patientDob!=null){
                patientDob.setText((patient.getpDob()));
            }
            if(patientWeight!=null){
                patientWeight.setText((patient.getpWeight()));
            }
            if(patientType!=null){
                patientType.setText((patient.getpType()));
            }
        }
        return convertView;
    }
}
