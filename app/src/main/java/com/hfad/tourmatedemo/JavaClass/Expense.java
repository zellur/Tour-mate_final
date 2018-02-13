package com.hfad.tourmatedemo.JavaClass;

import android.support.annotation.NonNull;

/**
 * Created by rakib on 2/11/18.
 */

public class Expense {

    private String id;
    private String expenseName;
    private String expenseCreated;
    private double expenseAmount;

    public Expense() {


    }

    public Expense(String id, String expenseName, String expenseCreated,double expenseAmount) {
        this.id = id;
        this.expenseName = expenseName;
        this.expenseCreated = expenseCreated;
        this.expenseAmount=expenseAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public String getExpenseCreated() {
        return expenseCreated;
    }

    public void setExpenseCreated(String expenseCreated) {
        this.expenseCreated = expenseCreated;
    }

    public double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }
}
