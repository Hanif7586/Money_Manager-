package com.example.moneymanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.moneymanager.databinding.ActivityAddExpenseBinding;
import com.example.moneymanager.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.UUID;

public class AddExpenseActivity extends AppCompatActivity {
ActivityAddExpenseBinding binding;
private String type;
private ExpenseModel expenseModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAddExpenseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        type=getIntent().getStringExtra("type");
        expenseModel=(ExpenseModel) getIntent().getSerializableExtra("model");


        if (type==null){
            type=expenseModel.getType();
            binding.amount.setText(String.valueOf(expenseModel.getAmmount()));
            binding.category.setText(expenseModel.getCategory());
            binding.note.setText(expenseModel.getNote());
        }


        if (type.equals("Income")){
            binding.inocmeradio.setChecked(true);
        }else {
            binding.expenseradio.setChecked(true);
        }


        binding.inocmeradio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="Income";
            }
        });
        binding.expenseradio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="Expense";
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        if (expenseModel==null){
            menuInflater.inflate(R.menu.add_menu,menu);
        }else {
            menuInflater.inflate(R.menu.update_menu,menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.saveExpense){
            if (expenseModel==null){
                createExpance();
            }else {
                updateEXpance();
            }

            return true;
        }
        if (id==R.id.deleteExpense){
            deleteExpense();
        }
        return false;
    }

    private void deleteExpense() {
        FirebaseFirestore
                .getInstance()
                .collection("expenses")
                .document(expenseModel.getExpenseID())
                .delete();
        finish();
    }

    private void createExpance() {
        String expenseid= UUID.randomUUID().toString();
        String amount=binding.amount.getText().toString();
        String note=binding.note.getText().toString();

        String category=binding.category.getText().toString();

        boolean incomeChecked=binding.inocmeradio.isChecked();


        if (incomeChecked){
            type="Income";
        }else {
            type="Expense";
        }

        if (amount.trim().length()==0){
            binding.amount.setError("Empty");
            return;
        }
        ExpenseModel expenseModel=new ExpenseModel(expenseid,note,category,type,Long.parseLong(amount) ,Calendar.getInstance().getTimeInMillis()
        , FirebaseAuth.getInstance().getUid());

        FirebaseFirestore.getInstance().collection("expenses")
                .document(expenseid)
                .set(expenseModel);
        finish();

    }

    private void updateEXpance() {
        String expenseid=expenseModel.getExpenseID();
        String amount=binding.amount.getText().toString();
        String note=binding.note.getText().toString();

        String category=binding.category.getText().toString();

        boolean incomeChecked=binding.inocmeradio.isChecked();


        if (incomeChecked){
            type="Income";
        }else {
            type="Expense";
        }

        if (amount.trim().length()==0){
            binding.amount.setError("Empty");
            return;
        }
        ExpenseModel model=new ExpenseModel(expenseid,note,category,type,Long.parseLong(amount),expenseModel.getTime()
                , FirebaseAuth.getInstance().getUid());

        FirebaseFirestore.getInstance().collection("expenses")
                .document(expenseid)
                .set(model);
        finish();

    }
}