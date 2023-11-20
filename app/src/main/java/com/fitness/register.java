package com.fitness;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fitness.dbhelper.dbhelper;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.fitness.databinding.ActivityMainBinding;

public class register extends AppCompatActivity {
    Button registerButton;
    TextView firstNameEditText;
    TextView lastNameEditText;

    TextView emailEditText;
    TextView  passwordEditText;

    private AppBarConfiguration mAppBarConfiguration;
    dbhelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        registerButton=findViewById(R.id.registerButton);
        firstNameEditText=findViewById(R.id.firstNameEditText);
        lastNameEditText=findViewById(R.id.lastNameEditText);
        emailEditText=findViewById(R.id.emailEditText);
        passwordEditText=findViewById(R.id.passwordEditText);

        dbhelper = new dbhelper(register.this);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent4 = new Intent(register.this, login.class);
               startActivity(intent4);
                dbhelper.register(
                        firstNameEditText.getText().toString(),
                        lastNameEditText.getText().toString(),
                        emailEditText.getText().toString(),
                        passwordEditText.getText().toString()
                );
                Toast.makeText(register.this,"Succesfully Registered",Toast.LENGTH_LONG).show();
            }

        });


    }
}