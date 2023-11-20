package com.fitness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.fitness.databinding.ActivityMainBinding;

public class contentmain extends AppCompatActivity {
    ImageView image1;
    String userid;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        image1=findViewById(R.id.image1);


        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedpreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
                userid = sharedpreferences.getString("userid","");
                if (userid.isEmpty()) {
                    Intent intent1 = new Intent(contentmain.this, login.class);
                    startActivity(intent1);
                } else {
                    Intent intent2 = new Intent(contentmain.this, MainActivity.class);
                    startActivity(intent2);
                }

//                Intent intent = new Intent(contentmain.this, login.class);
//                startActivity(intent);

            }

        });

    }
}