package com.hfad.tourmatedemo;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hfad.tourmatedemo.JavaClass.ExpandAdapter;
import com.hfad.tourmatedemo.JavaClass.Expense;
import com.hfad.tourmatedemo.JavaClass.ExpenseAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class TourDeatail extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private ExpandAdapter listAdapter;
    private List<String> listData;
    private HashMap<String,List<String>> hashList;
    private TextView tourName;
    private TextView budgetStatus,percentageStart;
    private ProgressBar progressBar;
    private String id;
    private DatabaseReference expenseData;
    private ArrayList<Expense> expenses=new ArrayList<>();
    private ExpenseAdapter expenseAdapter;
    private double budget;
    private double exp = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_deatail);

        expandableListView = findViewById(R.id.lvExp);
        tourName=findViewById(R.id.tourName);
        budgetStatus=findViewById(R.id.budgetStatus);
        progressBar=findViewById(R.id.progressbarStat);
        percentageStart=findViewById(R.id.percentage);

        progressBar.setMax(100);

        double pogres= ((exp*100) / budget);

        progressBar.setProgress(40);
        if(progressBar.getProgress()<50){

            progressBar.setBackgroundColor(111111);
        }


        percentageStart.setText(progressBar.getProgress()+"%");

        budget = getIntent().getDoubleExtra("budget",0);
        String name = getIntent().getStringExtra("name");
        id=getIntent().getStringExtra("id");


        expenseData = FirebaseDatabase.getInstance().getReference("Expenses").child(id);
        init();

        expenseData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Expense expense = snapshot.getValue(Expense.class);
                    if(expense !=null) {
                        double sss = expense.getExpenseAmount();
                        exp = exp + sss;
                    }else{
                        Toast.makeText(TourDeatail.this, "nullllll", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        tourName.setText(name);
        budgetStatus.setText("Budget Status: "+exp+" / "+budget);

        listAdapter=new ExpandAdapter(TourDeatail.this,listData,hashList);
        expandableListView.setAdapter(listAdapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, final long id) {

                //Toast.makeText(TourDeatail.this, hashList.get(listData.get(groupPosition)).get(childPosition)+" is selected", Toast.LENGTH_SHORT).show();

                String childName =  hashList.get(listData.get(groupPosition)).get(childPosition);

                switch(childName){

                    case "Add new Expense":

                        AlertDialog.Builder daialog = new AlertDialog.Builder(TourDeatail.this);

                        daialog.setTitle("Add New Expense");
                        LayoutInflater inflater = LayoutInflater.from(TourDeatail.this);

                        LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.expense_add,null);
                        final EditText exName = ll.findViewById(R.id.expenseName);
                        final EditText exAmount = ll.findViewById(R.id.amount);
                        daialog.setView(ll);
                        daialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String expName = exName.getText().toString();
                                double amount = Double.parseDouble(exAmount.getText().toString());

                                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
                                String created = dateFormat.format(new Date()).toString();

                                if(expName!=null&&amount>0.0){

                                    String eId= expenseData.push().getKey();
                                    Expense expense = new Expense(eId,expName,created,amount);

                                    expenseData.child(eId).setValue(expense);
                                    Toast.makeText(TourDeatail.this, "expense added successfully", Toast.LENGTH_SHORT).show();
                                }else {

                                    Toast.makeText(TourDeatail.this, "An error occured", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });


                        daialog.setNegativeButton("Cancel",null);
                        daialog.show();
                        break;

                    case "View All Expense":

                        expenseData.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                expenses.clear();
                                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                                    Expense expense = ds.getValue(Expense.class);
                                    expenses.add(expense);

                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                        AlertDialog.Builder daialog2 = new AlertDialog.Builder(TourDeatail.this);

                        daialog2.setTitle("All Expenses");
                        LayoutInflater inflater1 = LayoutInflater.from(TourDeatail.this);

                        LinearLayout linearLayout = (LinearLayout) inflater1.inflate(R.layout.expense_list,null);
                        final ListView listView = linearLayout.findViewById(R.id.expenseList);
                        expenseAdapter = new ExpenseAdapter(TourDeatail.this,expenses);
                        listView.setAdapter(expenseAdapter);

                        daialog2.setView(linearLayout);

                        daialog2.setNegativeButton("Close",null);
                        daialog2.show();

                        break;

                    case "Add More Budget":

                        break;

                    case "Take A photo":

                        break;

                    case "View Gallery":

                        break;

                    case "View All moments":

                        break;

                    case "Edit Event":

                        break;

                    case "Delete Event":

                        break;
                }
                return false;
            }
        });
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
