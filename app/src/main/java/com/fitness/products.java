package com.fitness;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fitness.dbhelper.dbhelper;
import com.fitness.models.productmodel;

import androidx.navigation.ui.AppBarConfiguration;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class products extends AppCompatActivity {

    Button viewbutton;
    Button submit1;
    TextView text1;
    TextView text2;
    ImageView imageview1;

    private AppBarConfiguration mAppBarConfiguration;
    dbhelper dbhelper;
    RecyclerView recyclerView;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.products);
        viewbutton = findViewById(R.id.viewbutton);

        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        imageview1=findViewById(R.id.imageview1);


        dbhelper = new dbhelper(products.this);
//        Cursor res = dbhelper.getproduct();
//        StringBuffer buffer = new StringBuffer();
//              while(res.moveToNext()){
//                  text1.setText(res.getString(1));
//                  text2.setText(res.getString(2));
//
//         buffer.append("ProductName "+res.getString(0)+"\n");
//                  buffer.append("LastName "+res.getString(1)+"\n");
//                  buffer.append("Email "+res.getString(2)+"\n");
//                  buffer.append("Mobile "+res.getString(3)+"\n");
//              }
//                AlertDialog.Builder builder = new AlertDialog.Builder(products.this);
//                builder.setCancelable(true);
//                builder.setTitle("Data");
//                builder.setMessage(buffer.toString());
//                builder.show();


            recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setHasFixedSize(true);

            dbhelper databaseHelperClass = new dbhelper(this);
            List<productmodel> productsmodelcalsses = databaseHelperClass.getproductslist();

            if (productsmodelcalsses.size() > 0) {

                AdapterProduct productsadaptar = new AdapterProduct(productsmodelcalsses, products.this);
                recyclerView.setAdapter(productsadaptar);
            } else {
                Toast.makeText(this, "There is no employee in the database", Toast.LENGTH_SHORT).show();
            }

        viewbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(products.this, "yeswanth", Toast.LENGTH_LONG).show();
                Intent intent12 = new Intent(products.this, mycart.class);
                startActivity(intent12);
            }
        });



    }

}
