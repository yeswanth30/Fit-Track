package com.fitness;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.fitness.dbhelper.dbhelper;
import com.fitness.models.paymentmodel;

import java.util.List;

public class AdapterPaymentHistory extends RecyclerView.Adapter<AdapterPaymentHistory.ViewHolder> {
    List<paymentmodel> paymentHistory;
    Context context;
    dbhelper db;

    public AdapterPaymentHistory(List<paymentmodel> paymentHistory, Context context) {
        this.paymentHistory = paymentHistory;
        this.context = context;
        db = new dbhelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.myaccount1, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final paymentmodel payment = paymentHistory.get(position);

        holder.paymentIdTextView.setText("Payment ID: " + payment.getPid());
        holder.productIdTextView.setText("Product ID: " + payment.getProid());
        holder.productIdTextView1.setText("Product name : " + payment.getProdctname());
        holder.productIdTextView2.setText("price: " + payment.getPrice());


    }

    @Override
    public int getItemCount() {
        return paymentHistory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView paymentIdTextView;
        TextView userIdTextView;
        TextView productIdTextView;
        TextView productIdTextView1;
        TextView productIdTextView2;

        TextView newTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            paymentIdTextView = itemView.findViewById(R.id.paymentIdTextView);
           // userIdTextView = itemView.findViewById(R.id.userIdTextView);
            productIdTextView = itemView.findViewById(R.id.productIdTextView);
            productIdTextView1 = itemView.findViewById(R.id.productIdTextView1);
            productIdTextView2 = itemView.findViewById(R.id.productIdTextView2);
            newTextView = itemView.findViewById(R.id.newTextView);

        }
    }
}

