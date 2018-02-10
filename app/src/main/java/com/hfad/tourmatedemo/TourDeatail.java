package com.hfad.tourmatedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.hfad.tourmatedemo.JavaClass.ExpandAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TourDeatail extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private ExpandAdapter listAdapter;
    private List<String> listData;
    private HashMap<String,List<String>> hashList;
    private TextView tourName;
    private TextView budgetStatus;
    private SeekBar seekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_deatail);

        expandableListView = findViewById(R.id.lvExp);
        tourName=findViewById(R.id.tourName);
        budgetStatus=findViewById(R.id.budgetStatus);
        seekbar=findViewById(R.id.seekbar);

        double budget = getIntent().getDoubleExtra("budget",0);
        String name = getIntent().getStringExtra("name");

        tourName.setText(name);
        budgetStatus.setText("Budget Status: 100 / "+budget);

        init();
        listAdapter=new ExpandAdapter(TourDeatail.this,listData,hashList);
        expandableListView.setAdapter(listAdapter);
    }

    private void init() {
        listData=new ArrayList<>();
        hashList=new HashMap<>();

        listData.add("Expenditure");
        listData.add("Momments");
        listData.add("More on Event");

        List<String> expenditure = new ArrayList<>();

        expenditure.add("Add new Expense");
        expenditure.add("View All Expense");
        expenditure.add("Add More Budget");

        List<String> moments = new ArrayList<>();

        moments.add("Take A photo");
        moments.add("View Gallery");
        moments.add("View All moments");

        List<String> events = new ArrayList<>();

        events.add("Edit Event");
        events.add("Delete Event");

        hashList.put(listData.get(0),expenditure);
        hashList.put(listData.get(1),moments);
        hashList.put(listData.get(2),events);


    }
}
