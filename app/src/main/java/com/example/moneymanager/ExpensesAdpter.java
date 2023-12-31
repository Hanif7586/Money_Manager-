package com.example.moneymanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ExpensesAdpter extends RecyclerView.Adapter<ExpensesAdpter.MyViewHolser> {
    private Context context;
    private  OnItemCLick onItemCLick;
    private List<ExpenseModel>expenseModelList;

    public ExpensesAdpter(Context context,OnItemCLick onItemCLick) {
        this.context = context;
        expenseModelList=new ArrayList<>();
        this.onItemCLick=onItemCLick;
    }
public void add(ExpenseModel expenseModel){
       expenseModelList.add(expenseModel);
       notifyDataSetChanged();
}
public void clear(){
        expenseModelList.clear();
        notifyDataSetChanged();
}
    @NonNull
    @Override
    public MyViewHolser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_row,parent,false);

        return new MyViewHolser(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolser holder, int position) {

        ExpenseModel expenseModel=expenseModelList.get(position);
        holder.note.setText(expenseModel.getNote());
        holder.category.setText(expenseModel.getCategory());
        holder.amount.setText(String.valueOf(expenseModel.getAmmount()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemCLick.onClick(expenseModel);
            }
        });
    }

    @Override
    public int getItemCount() {

        return expenseModelList.size();
    }

    public  class MyViewHolser extends RecyclerView.ViewHolder{

        private TextView note,category,amount,date;
        public MyViewHolser(@NonNull View itemView) {
            super(itemView);
            note=itemView.findViewById(R.id.note);
            category=itemView.findViewById(R.id.category);
            amount=itemView.findViewById(R.id.amount);
            date=itemView.findViewById(R.id.date);
        }
    }
}
