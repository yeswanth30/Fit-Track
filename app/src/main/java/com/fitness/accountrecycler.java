package com.fitness;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fitness.dbhelper.dbhelper;
import com.fitness.models.paymentmodel;

import java.util.List;

public class accountrecycler extends AppCompatActivity {

    TextView newTextView;
    RecyclerView recyclerView;


    private dbhelper dbhelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accountrecycler);

        newTextView=findViewById(R.id.newTextView);


        dbhelper = new dbhelper(accountrecycler.this);
        recyclerView = findViewById(R.id.paymentHistoryRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        SharedPreferences sharedpreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        String userid = sharedpreferences.getString("userid","");

        List<paymentmodel> paymentHistoryData = dbhelper.getPaymentHistory(userid);
        AdapterPaymentHistory adapter = new AdapterPaymentHistory(paymentHistoryData, this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);


        Toast.makeText(accountrecycler.this,"data"+userid,Toast.LENGTH_LONG).show();

        double totalPayment = dbhelper.getTotalPayment(userid);

        newTextView.setText("TOTAL: " + totalPayment);


    }

}
