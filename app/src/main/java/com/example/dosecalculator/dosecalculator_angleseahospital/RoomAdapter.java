package com.example.dosecalculator.dosecalculator_angleseahospital;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dosecalculator.dosecalculator_angleseahospital.database.Database;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by komyo on 5/11/2017.
 */

public class RoomAdapter  extends ArrayAdapter<Room>{

    private LayoutInflater myInflater;
    private ArrayList<Room> rooms;
    private int mViewResourceId;


    public RoomAdapter(Context context, int resource, ArrayList<Room> rooms) {
        super(context, resource, rooms);
        this.rooms=rooms;
        myInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId= resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView =myInflater.inflate(mViewResourceId,null);
        Room room=rooms.get(position);

        if(room!=null){
            TextView roomId=(TextView) convertView.findViewById(R.id.txt_room_name);
            TextView roomStatus=(TextView) convertView.findViewById(R.id.txt_room_status);

            if(roomId!=null){
                roomId.setText((room.getRoomId()));
            }
            if(roomStatus!=null){
                roomStatus.setText((room.getStatus()));
            }

        }
        return convertView;

    }

}
