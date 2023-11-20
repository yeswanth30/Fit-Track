package com.fitness;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fitness.dbhelper.dbhelper;
import com.fitness.models.productmodel;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.fitness.databinding.ActivityMainBinding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class adddetails  extends AppCompatActivity {

    EditText first1;
    EditText first2;
    EditText first3;
    EditText first4;
    EditText first5;
    EditText first6;
    Button submit2;
    private dbhelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adddetails);

        first1 = findViewById(R.id.first1);
        first2 = findViewById(R.id.first2);
        first3 = findViewById(R.id.first3);
        first4 = findViewById(R.id.first4);
        first5 = findViewById(R.id.first5);
        first6 = findViewById(R.id.first6);
        submit2 = findViewById(R.id.submit2);

        dbHelper = new dbhelper(this);

        submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mode = first1.getText().toString();
                String couponcode = first2.getText().toString();
                String status = first3.getText().toString();
                double payment = Double.parseDouble(first4.getText().toString());
                String orderdate = first5.getText().toString();
                String deliverydate = first6.getText().toString();

                long result = dbHelper.insertPayment(mode, couponcode, status, payment, orderdate, deliverydate);

                if (result != -1) {
                    Toast.makeText(adddetails.this, "Data added to the database", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(adddetails.this, "Error inserting data", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}