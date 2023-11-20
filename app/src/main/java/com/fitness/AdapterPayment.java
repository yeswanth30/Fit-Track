package com.fitness;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fitness.dbhelper.dbhelper;
import com.fitness.models.paymentmodel;
import com.fitness.models.productmodel;

import java.util.List;

public class AdapterPayment extends RecyclerView.Adapter<AdapterPayment.ViewHolder> {
    List<paymentmodel> payment1;
    Context context;
    dbhelper db;
    public AdapterPayment(List<paymentmodel> payment, Context context) {
        this.payment1 = payment;
        this.context = context;
        db = new dbhelper(context);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.paymentlist,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPayment.ViewHolder holder, int position) {
        final paymentmodel pay = payment1.get(position);

        holder.first.setText(pay.getPrice());
        holder.second.setText(pay.getProdctname());

    }

    @Override
    public int getItemCount() {
        return payment1.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView first;
        TextView second;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            first = itemView.findViewById(R.id.first2);
            second = itemView.findViewById(R.id.second2);

        }
    }
}
