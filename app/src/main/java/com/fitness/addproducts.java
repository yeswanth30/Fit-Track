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


//public class addproducts extends AppCompatActivity {
//
//
//    EditText first;
//    EditText second;
//    Button submit;
//    ImageView imageview1;
//    Button SelectImage;
//
//    private final int GALLERY_REQ_CODE = 1000;
//
//    private AppBarConfiguration mAppBarConfiguration;
//    dbhelper dbhelper;
//
//
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.addproducts);
//        first = findViewById(R.id.first);
//        second = findViewById(R.id.second);
//        imageview1 = findViewById(R.id.imageview1);
//
//
//        SelectImage = findViewById(R.id.SelectImage);
//        submit = findViewById(R.id.submit);
//        dbhelper = new dbhelper(addproducts.this);
//
////         imageview1.setImageBitmap(product.getBitmap());
//
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent4 = new Intent(register.this, MainActivity.class);
//                //  startActivity(intent4);
//                dbhelper.products(
//                        first.getText().toString(),
//                        second.getText().toString(),
//                        imageview1.getBLOB().toString()
//                );
//                Toast.makeText(addproducts.this, "data added", Toast.LENGTH_LONG).show();
//            }
//
//        });
//        SelectImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent iGallery = new Intent(Intent.ACTION_PICK);
//                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(iGallery, GALLERY_REQ_CODE);
//            }
//        });
//
//    }
//
//
//
//    protected void onActivityResult(int requestcode, int resultcode, @Nullable Intent data) {
//        super.onActivityResult(requestcode, resultcode, data);
//        if (resultcode == RESULT_OK) {
//            if (requestcode == GALLERY_REQ_CODE) {
//                imageview1.setImageURI(data.getData());
//            }
//        }
//    }
//
//
//
//}
public class  addproducts extends AppCompatActivity {

    EditText first;
    EditText second;
    Button submit;
    ImageView imageview1;
    Button SelectImage;
   private   Uri selectedImage;


    private final int GALLERY_REQ_CODE = 1000;
    private Bitmap selectedImageBitmap;
    private dbhelper dbHelper;

    private String generateImageURL() {
        // Generate a unique image URL, e.g., based on timestamp
        long timestamp = System.currentTimeMillis();
        return "https://example.com/images/" + timestamp + ".jpg";
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addproducts);

        first = findViewById(R.id.first);
        second = findViewById(R.id.second);
        imageview1 = findViewById(R.id.imageview1);
        SelectImage = findViewById(R.id.SelectImage);
        submit = findViewById(R.id.submit);

        dbHelper = new dbhelper(this);

        SelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the image gallery
                Intent iGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, GALLERY_REQ_CODE);
            }
        });


//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String productName = first.getText().toString();
//                String Price = second.getText().toString();
//                String imageUrl = generateImageURL();
//
//                if (selectedImageBitmap != null) {
//                    // Convert the selected image to a byte array
//                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                    selectedImageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                    byte[] imageByteArray = stream.toByteArray();
//
//                    // Insert data into the database
//                    long result = dbHelper.insertProduct(productName, Price,imageUrl );
//
//                    if (result != -1) {
//                        Toast.makeText(addproducts.this, "Data added to the database", Toast.LENGTH_LONG).show();
//                    } else {
//                        Toast.makeText(addproducts.this, "Error inserting data", Toast.LENGTH_LONG).show();
//                    }
//                } else {
//                    Toast.makeText(addproducts.this, "Please select an image", Toast.LENGTH_LONG).show();
//                }
//            }
//        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = first.getText().toString();
                String secondName = second.getText().toString();
               // String imageUrl = generateImageURL(); // Call the method to generate the URL

//                long result = dbHelper.insertProduct(firstName, secondName, selectedImageBitmap);
                long result = dbHelper.insertProduct(firstName, secondName);
                if (result != -1) {
                    Toast.makeText(addproducts.this, "Data added to the database", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(addproducts.this, "Error inserting data", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == GALLERY_REQ_CODE) {
            selectedImage = data.getData();
            imageview1.setImageURI(selectedImage);
            try {
                // Load the selected image into the ImageView
                selectedImageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
