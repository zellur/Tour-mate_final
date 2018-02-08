package com.hfad.tourmatedemo.JavaClass;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hfad.tourmatedemo.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by rakib on 2/9/18.
 */


public class EventAdapter extends ArrayAdapter<Event> {

    private Context context;
    private ArrayList<Event> events;
    public EventAdapter(@NonNull Context context, ArrayList<Event> events) {
        super(context, R.layout.event_list,events);

        this.context = context;
        this.events=events;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.event_list,parent,false);

        TextView eventName = convertView.findViewById(R.id.name);
        TextView createdat = convertView.findViewById(R.id.created);
        TextView startdat = convertView.findViewById(R.id.startIn);
        TextView leftdays = convertView.findViewById(R.id.leftDays);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
          String today = dateFormat.format(new Date()).toString();

          Date startDate = new Date();
          Date now = new Date();

        try {
            startDate=dateFormat.parse(events.get(position).getEventStart());
            now = dateFormat.parse(today);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long leftTime = startDate.getTime()-now.getTime();
        long leftDay = (leftTime/84600000);

        eventName.setText(events.get(position).getEventName().toString());
        createdat.setText(events.get(position).getEventCreated().toString());
        startdat.setText(events.get(position).getEventStart().toString());

        leftdays.setText(String.valueOf(leftDay)+" days left");

        return convertView;
    }
}
