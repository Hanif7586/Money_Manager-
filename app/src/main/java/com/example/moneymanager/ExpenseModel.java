package com.example.moneymanager;

import java.io.Serializable;

public class ExpenseModel implements Serializable {
    private String expenseID;
    private String note;
    private String category;
    private String type;
    private long ammount;
    private long time;
    private String uid;

    public ExpenseModel() {
    }

    public ExpenseModel(String expenseID, String note, String category, String type, long ammount, long time, String uid) {
        this.expenseID = expenseID;
        this.note = note;
        this.category = category;
        this.type = type;
        this.ammount = ammount;
        this.time = time;
        this.uid = uid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExpenseID() {
        return expenseID;
    }

    public void setExpenseID(String expenseID) {
        this.expenseID = expenseID;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getAmmount() {
        return ammount;
    }

    public void setAmmount(long ammount) {
        this.ammount = ammount;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
