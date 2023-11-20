package com.fitness;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fitness.dbhelper.dbhelper;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.fitness.databinding.ActivityMainBinding;

import java.util.List;

public class login extends AppCompatActivity {
    EditText email;
    EditText password;
    Button login;
    dbhelper dbhelper;
    TextView text1,text2;
    String name1,password1;
    private AppBarConfiguration mAppBarConfiguration;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        text1 =findViewById(R.id.text1);
        text2 =findViewById(R.id.text2);

        email =findViewById(R.id.usernameTextView);
        password =findViewById(R.id.passwordEditText);
        login=findViewById(R.id.login);

        dbhelper = new dbhelper(login.this);

//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Boolean data =  dbhelper.login(username.getText().toString(), password.getText().toString());
//                if(data == true){
//                    Toast.makeText(login.this,"Successfully Login",Toast.LENGTH_LONG).show();
//                                    Intent intent4 = new Intent(login.this, MainActivity.class);
//                                    startActivity(intent4);
//                }else{
//                    Toast.makeText(login.this,"Wrong Email and Password",Toast.LENGTH_LONG).show();
//                }
//            }
//        });

//              Cursor res = dbhelper.login();
//              if(res.getCount() == 0){
//                  Toast.makeText(login.this,"no data",Toast.LENGTH_LONG).show();
//                  return;
//              }
//                textview.setText(res.getString(0));
//              StringBuffer buffer = new StringBuffer();
//              while(res.moveToNext()){
//                  buffer.append("ProductName"+res.getString(0)+"\n");
//                  buffer.append("LastName"+res.getString(1)+"\n");
//                  buffer.append("Email"+res.getString(2)+"\n");
//                  buffer.append("Mobile"+res.getString(3)+"\n");
//              }
//                AlertDialog.Builder builder = new AlertDialog.Builder(com.fitness.login.this);
//                builder.setCancelable(true);
//                builder.setTitle("Data");
//                builder.setMessage(buffer.toString());
//                builder.show();
//
//            }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(login.this, MainActivity.class);
//                startActivity(intent);\\

                 name1 = email.getText().toString();
                 password1= password.getText().toString();
                if(name1.isEmpty() && password1.isEmpty()){
                    Toast.makeText(login.this, "Please Enter Values", Toast.LENGTH_LONG).show();
                    // }
                    //if(password.getText().toString().isEmpty()){
//                   Toast.makeText(login.this, "Please Enter Values", Toast.LENGTH_LONG).show();
                } else {
                    String data = "";
                    data = dbhelper.login(name1, password1);
                    Log.e("email", String.valueOf(data));
                    if (data.isEmpty()) {
                        Toast.makeText(login.this, "username not found. Please sign up", Toast.LENGTH_LONG).show();
                    } else {
                        SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
                        SharedPreferences.Editor myedit = sharedPreferences.edit();
                        myedit.putString("userid",data);
//                        myedit.putString("email",password1);
                        myedit.commit();
                        Toast.makeText(login.this , "Successfully login", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(login.this, MainActivity.class);
                        startActivity(intent);

                    }
                }
//                storetosharepreference();
        }
        });

        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(login.this, password.class);
                startActivity(intent2);
            }

        });

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(login.this, register.class);
                startActivity(intent3);
            }

        });

    }


}
