//package com.fitness;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.fitness.dbhelper.dbhelper;
//import com.fitness.models.paymentmodel;
//import com.fitness.models.productmodel;
//
//import java.util.List;
//
//public  class  MyAdapter  extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
//    List<paymentmodel> products2;
//    Context context;
//    dbhelper db;
//
//    public MyAdapter(List<paymentmodel> products, Context context) {
//        this.products2 = products;
//        this.context = context;
//        db = new dbhelper(context);
//    }
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.addproducts, parent, false);
//        ViewHolder viewHolder = new ViewHolder(view);
//        return viewHolder;
//
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        final paymentmodel pro = products2.get(position);
//
//        holder.first.setText(pro.getProdctname());
//        holder.second.setText(pro.getPrice());
//      //  holder.imageview1.setImageResource(pro.getImageurl());
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public EditText first;
//        public EditText second;
//        public ImageView imageview1;
//   Button submit;
//
//
//        public ViewHolder(View view) {
//            super(view);
//            first = view.findViewById(R.id.first);
//            second = view.findViewById(R.id.second);
//            submit=view.findViewById(R.id.submit);
//            imageview1 = view.findViewById(R.id.imageview1);
//        }
//    }
//}
package com.fitness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fitness.dbhelper.dbhelper;
import com.fitness.models.paymentmodel;

import java.util.List;
//
//public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
//    List<paymentmodel> products2;
//    Context context;
//
//    public MyAdapter(List<paymentmodel> products, Context context) {
//        this.products2 = products;
//        this.context = context;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        // Inflate the item layout
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.addproducts, parent, false);
//        ViewHolder viewHolder = new ViewHolder(view);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        final paymentmodel pro = products2.get(position);
//
//        // Bind data to the ViewHolder
//        holder.first.setText(pro.getProdctname());
//        holder.second.setText(pro.getPrice());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return products2.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public EditText first;
//        public EditText second;
//        public ImageView imageview1;
//        public Button submit;
//
//        public ViewHolder(View view) {
//            super(view);
//            first = view.findViewById(R.id.first);
//            second = view.findViewById(R.id.second);
//            submit = view.findViewById(R.id.submit);
//            imageview1 = view.findViewById(R.id.imageview1);
//        }
//    }
//}
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    List<paymentmodel> products2;
    Context context;
    dbhelper db;

    public MyAdapter(List<paymentmodel> products, Context context) {
        this.products2 = products;
        this.context = context;
        db = new dbhelper(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartwraper, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final paymentmodel pro = products2.get(position);

        holder.first.setText(pro.getProdctname());
        holder.second.setText(pro.getPrice());
        Integer payment =0;
         payment = payment + Integer.parseInt(pro.getPrice());


    }

    @Override
    public int getItemCount() {
        return products2.size(); // Return the number of items in the list
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView first;
        public TextView second;





        public ViewHolder(View view) {
            super(view);
            first = view.findViewById(R.id.first);
            second = view.findViewById(R.id.second);
        }
    }
}
