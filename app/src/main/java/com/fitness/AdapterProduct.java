package com.fitness;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fitness.dbhelper.dbhelper;
import com.fitness.models.paymentmodel;
import com.fitness.models.productmodel;

import java.util.List;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder> {
    List<productmodel> products1;
    Context context;
    dbhelper db;
    String userid;


    public AdapterProduct(List<productmodel> products, Context context) {
        this.products1 = products;
        this.context = context;
        db = new dbhelper(context);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.productslist,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProduct.ViewHolder holder, int position) {
        final productmodel pro = products1.get(position);

        holder.first1.setText(pro.getPrice());
        holder.second1.setText(pro.getProdctname());
//        holder.imageview1.setImageBitmap(pro.getImage());

//        Bitmap bitmapImage = BitmapFactory.decodeByteArray(productmodel.getbmp(), 0 , productmodel.getbmp().length);
//
//        holder.imageview1.setImageBitmap(bitmapImage);

        holder.submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedpreferences = context.getSharedPreferences("my_preferences", MODE_PRIVATE);
                userid = sharedpreferences.getString("userid","");
                Toast.makeText(context,"adapter data"+userid,Toast.LENGTH_LONG).show();

                db.addpayment(pro.getId(), userid);
                notifyDataSetChanged();
//                ((Activity) context).finish();
//                context.startActivity(((Activity) context).getIntent());
            }
        });
    }

    @Override
    public int getItemCount() {
        return products1.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView first1;
        TextView second1;
        Button submit1;
         Button viewbutton;

         ImageView imageview1;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            first1 = itemView.findViewById(R.id.first1);
            second1 = itemView.findViewById(R.id.second1);
            submit1 = itemView.findViewById(R.id.submit1);
            viewbutton=itemView.findViewById(R.id.viewbutton);
            imageview1=itemView.findViewById(R.id.imageview1);

        }
    }
}
