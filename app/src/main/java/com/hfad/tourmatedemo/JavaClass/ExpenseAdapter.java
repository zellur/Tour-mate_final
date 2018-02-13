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

import java.util.ArrayList;

/**
 * Created by rakib on 2/11/18.
 */

public class ExpenseAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Expense> expenses;
    public ExpenseAdapter(@NonNull Context context, ArrayList<Expense> expenses) {
        super(context,R.layout.list_exp,expenses);
        this.context=context;
        this.expenses=expenses;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_exp,parent,false);

        TextView exname = convertView.findViewById(R.id.expNam);
        TextView exAm = convertView.findViewById(R.id.expAm);
        TextView exDat = convertView.findViewById(R.id.createDateExp);

        exname.setText(expenses.get(position).getExpenseName().toString());
        exAm.setText(String.valueOf(expenses.get(position).getExpenseAmount()).toString());
        exDat.setText(expenses.get(position).getExpenseCreated().toString());
        return convertView;
    }
}
