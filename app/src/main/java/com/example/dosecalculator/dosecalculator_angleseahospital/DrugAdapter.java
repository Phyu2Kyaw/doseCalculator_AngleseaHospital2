package com.example.dosecalculator.dosecalculator_angleseahospital;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;


public class DrugAdapter extends ArrayAdapter<Drugs>  {

    private LayoutInflater myInflater;
    private ArrayList<Drugs> drugs;
    private int mViewResourceId;


    public DrugAdapter(Context context, int resource, ArrayList<Drugs> drug) {
        super(context, resource, drug);
        this.drugs = drug;
        myInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = myInflater.inflate(mViewResourceId,null);
        Drugs drug = drugs.get(position);

        if(drug != null){
            TextView drugName =(TextView) convertView.findViewById(R.id.lbl_result_drug_name);
            TextView weight = (TextView) convertView.findViewById(R.id.lbl_result_weight_mg);
            TextView volume = (TextView) convertView.findViewById(R.id.lbl_result_volume_mL);
            TextView maxDosage = (TextView) convertView.findViewById(R.id.lbl_result_max_Dosage);
            TextView calcMethod = (TextView) convertView.findViewById(R.id.lbl_result_calc_method);

            if(drugName != null){
                drugName.setText((drug.getDrugName()));
            }
            if(weight != null){
                weight.setText((drug.getDrugWeight()));
            }
            if(volume != null){
                volume.setText((drug.getDrugVolume()));
            }
            if(maxDosage != null){
                maxDosage.setText((drug.getMaxDosage()));
            }
            if(calcMethod != null){
                calcMethod.setText((drug.getCalcMethod()));
            }


        }
        return convertView;

    }
}
