package com.fitness;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.fitness.dbhelper.dbhelper;
import com.fitness.models.paymentmodel;
import com.fitness.models.productmodel;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fitness.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class mycart extends AppCompatActivity {

    Button submit;

    private AppBarConfiguration mAppBarConfiguration;
    dbhelper dbhelper;
    RecyclerView recyclerView;
    Button paymentbutton1;
    EditText text1;
    String userid;
    EditText text2;
    EditText text3;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycart);
        paymentbutton1 = findViewById(R.id.paymentbutton1);
        dbhelper = new dbhelper(mycart.this);
        text1=findViewById(R.id.text1);
        text2=findViewById(R.id.text2);
        text3=findViewById(R.id.text3);



        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        dbhelper databaseHelperClass = new dbhelper(this);



//        List<paymentmodel> paymentmodelclasses = dbhelper.getallcart();
//        if (paymentmodelclasses.size() > 0) {
//
//            AdapterPayment paymentadaptar = new AdapterPayment(paymentmodelclasses, mycart.this);
//            recyclerView.setAdapter(paymentadaptar);
//        } else {
//            Toast.makeText(this, "There is no cart in the database", Toast.LENGTH_SHORT).show();
//        }

//        List<paymentmodel> productmodelList = dbhelper.getallcart();
//        if (productmodelList.size() > 0) {
//            MyAdapter adapter = new MyAdapter(productmodelList, mycart.this);
//            recyclerView.setAdapter(adapter);
//        } else {
//            Toast.makeText(this, "There is no cart in the database", Toast.LENGTH_SHORT).show();
//        }

       RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        dbhelper dbHelper = new dbhelper(this);
        SharedPreferences sharedpreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        userid = sharedpreferences.getString("userid","");
//        Toast.makeText(mycart.this,"data"+userid,Toast.LENGTH_LONG).show();
        List<paymentmodel> paymentList = dbHelper.getallcart(userid);


      //  Toast.makeText(mycart.this,"data"+userid,Toast.LENGTH_LONG).show();

        //Log.e("data", String.valueOf(totalSum));
        MyAdapter adapter = new MyAdapter(paymentList, this);
        recyclerView.setAdapter(adapter);

        paymentbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double totalSum = 0.0;

                String mode = text1.getText().toString();
                String couponcode = text2.getText().toString();
                String status = text3.getText().toString();


                for (paymentmodel payment : paymentList) {
                    totalSum += Double.parseDouble(payment.getPrice());

                }
                for (paymentmodel payment : paymentList) {


                    Toast.makeText(mycart.this,"data"+userid,Toast.LENGTH_LONG).show();

                    dbHelper.updatepayment(payment.getPid(),totalSum,mode,couponcode,status,userid);
                }
            }

        });
    }
}







