package com.hfad.tourmatedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hfad.tourmatedemo.JavaClass.Event;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class CreateEvent extends AppCompatActivity {

    private EditText eventName,eventStartDate,eventDestination,eventBudget,eventStartlocation;
    private Button addEvent;
    private DatabaseReference databaseEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        eventName=findViewById(R.id.eventName);
        eventStartDate=findViewById(R.id.startDate);
        eventDestination=findViewById(R.id.destination);
        eventBudget=findViewById(R.id.budget);
        eventStartlocation=findViewById(R.id.startLocation);

        addEvent=findViewById(R.id.addEvent);

        databaseEvent=FirebaseDatabase.getInstance().getReference("Events");


        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addEvents();
                startActivity(new Intent(CreateEvent.this,EventActivity.class));
            }
        });



    }

    private void addEvents() {

        String Name = eventName.getText().toString();
        String startDate=eventStartDate.getText().toString();
        String destination=eventDestination.getText().toString();
        double budget = Double.parseDouble(eventBudget.getText().toString());
        String startLocation = eventStartlocation.getText().toString();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String createDate = dateFormat.format(new Date()).toString();

        if(Name!=null&&startDate!=null&&destination!=null&&startLocation!=null){

            String id = databaseEvent.push().getKey();

            Event event = new Event(id,Name,startDate,createDate,destination,budget,startLocation);

            databaseEvent.child(id).setValue(event);

            Toast.makeText(this, "Event added Successfully", Toast.LENGTH_SHORT).show();

        }else{

            Toast.makeText(this, "Please enter the valid event information", Toast.LENGTH_SHORT).show();
        }
    }
}
