package com.hfad.tourmatedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hfad.tourmatedemo.JavaClass.Event;
import com.hfad.tourmatedemo.JavaClass.EventAdapter;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity {

    private ListView listView;
    private Button createEvent;
    private EventAdapter adapter;
    private DatabaseReference myRef;
    private ArrayList<Event> events = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        createEvent = findViewById(R.id.createEvent);
        listView = findViewById(R.id.eventList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                double budget = events.get(position).getEventBudget();
                String tourName=events.get(position).getEventName();
                String idd = events.get(position).getId();

                Intent intent = new Intent(EventActivity.this,TourDeatail.class);
                intent.putExtra("budget",budget);
                intent.putExtra("name",tourName);
                intent.putExtra("id",idd);
                startActivity(intent);

            }
        });

        myRef= FirebaseDatabase.getInstance().getReference("Events");


        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventActivity.this,CreateEvent.class));
            }
        });
    }



    @Override
    protected void onStart() {
        super.onStart();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                events.clear();
                for(DataSnapshot even : dataSnapshot.getChildren()){

                    Event event = even.getValue(Event.class);
                    events.add(event);
                }
                adapter=new EventAdapter(EventActivity.this,events);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
