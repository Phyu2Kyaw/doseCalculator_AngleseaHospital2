package com.example.dosecalculator.dosecalculator_angleseahospital;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by komyo on 5/11/2017.
 */

public class NurseAdapter extends ArrayAdapter<Nurse>{

    private LayoutInflater myInflater;
    private ArrayList<Nurse> nurses;
    private int mViewResourceId;


    public NurseAdapter(Context context, int resource, ArrayList<Nurse> nurses) {
        super(context, resource, nurses);
        this.nurses=nurses;
        myInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId= resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView =myInflater.inflate(mViewResourceId,null);
        Nurse nurse=nurses.get(position);

        if(nurse!=null){
            TextView nurseName=(TextView) convertView.findViewById(R.id.lbl_result_nurse_name);
            TextView eId=(TextView) convertView.findViewById(R.id.lbl_result_nurse_id);
            TextView status=(TextView) convertView.findViewById(R.id.lbl_result_nurse_status);

            if(nurseName!=null){
                nurseName.setText((nurse.getNurseName()));
            }
            if(eId!=null){
                eId.setText((nurse.geteId()));
            }
            if(status!=null){
                status.setText((nurse.getStatus()));
            }

        }
        return convertView;

    }

}
