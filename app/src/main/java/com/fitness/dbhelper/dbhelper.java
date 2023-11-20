package com.fitness.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;

import com.fitness.models.paymentmodel;
import com.fitness.models.productmodel;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class dbhelper extends SQLiteOpenHelper {
    private SQLiteDatabase sqLiteDatabase;

    private static final String LOG = "DatabaseHelper";
    private static final int DATABASE_VERSION = 6;

    private static final String DATABASE_NAME = "fitness";
    private static final String TABLE_USER = "user";
    public static final String TABLE_PRODUCTS = "products";
    private static final String TABLE_PAYMENT = "payment";
    private static final String TABLE_ADDRESS = "address";


//USER TABLE

    private static final String USER_ID = "userid";
    private static final String USER_FIRSTNAME = "Firstname";
    private static final String USER_LASTNAME = "Lastname";
    private static final String USER_USERNAME = "username";
    private static final String USER_PASSWORD = "password";
    private static final String USER_EMAIL = "email";
    private static final String USER_MOBILE = "mobile";
    private static final String USER_PHOTO = "photo";

    //PRODUCTS TABLE

    private static final String PRODUCT_ID = "proid";
    public static final String PRODUCT_PRODUCTNANE = "prodctname";
    public static final String PRODUCT_PRICE = "price";
    public static final String PRODUCT_IMAGE_URL = "imageurl";



    //PAYMENT TABLE

    private static final String PAYMENT_ID = "pid";
    private static final String PAYMENT_USERID = "userid";
    private static final String PAYMENT_PRODUCTID = "proid";
    private static final String PAYMENT_QUANTITY = "Quantity";

    private static final String PAYMENT_ADDRESSID = "addid";

    private static final String PAYMENT_MODE = "mode";

    private static final String PAYMENT_COUPONCODE = "couponcode";
    private static final String PAYMENT_PAYMENT = "payment";
    private static final String PAYMENT_STATUS = "status";

    private static final String PAYMENT_ORDERDATE = "orderdate";
    private static final String PAYMENT_DELIVERYDATE = "deliverydate";

    //ADDRESS TABLE

    private static final String ADDRESS_ID = "addid";
    private static final String ADDRESS_ADDRESSLINE1 = "addressline1";
    private static final String ADDRESS_ADDRESSLINE2 = "addressline2";

    private static final String ADDRESS_CITY = "city";
    private static final String ADDRESS_COUNTRY = "country";

    byte[] imageInbytes;

    //USERTABLE CREATE STATEMENT

    private static final String CREATE_TABLE_USER = " CREATE TABLE " + TABLE_USER + "(" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + USER_FIRSTNAME + " TEXT," + USER_LASTNAME + " TEXT," + USER_USERNAME + " TEXT," + USER_PASSWORD + " TEXT," + USER_EMAIL + " TEXT," + USER_MOBILE + " TEXT," + USER_PHOTO + " TEXT" + ")";

    //PRODUCTTABLE CREATE STATEMENT

    private static final String CREATE_TABLE_PRODUCTS = " CREATE TABLE " + TABLE_PRODUCTS + "(" + PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + PRODUCT_PRODUCTNANE + " TEXT," + PRODUCT_PRICE + " TEXT," + PRODUCT_IMAGE_URL + " TEXT "  +")";

    //PAYMENT CREATE STATEMENT
    private static final String CREATE_TABLE_PAYMENT = " CREATE TABLE " + TABLE_PAYMENT + "(" + PAYMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + PAYMENT_USERID + " INTEGER," + PAYMENT_ADDRESSID + " INTEGER," + PAYMENT_PRODUCTID + " INTEGER," + PAYMENT_QUANTITY + " INTEGER," + PAYMENT_MODE + " TEXT," + PAYMENT_COUPONCODE + " TEXT," + PAYMENT_STATUS + " TEXT," + PAYMENT_PAYMENT + " TEXT," + PAYMENT_ORDERDATE + " DATETIME," + PAYMENT_DELIVERYDATE + " DATETIME" + ")";

    // ADDRESS CREATE STATEMENT
    private static final String CREATE_TABLE_ADDRESS = " CREATE TABLE " + TABLE_ADDRESS + "(" + ADDRESS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + ADDRESS_ADDRESSLINE1 + " TEXT," + ADDRESS_ADDRESSLINE2 + " TEXT," + ADDRESS_CITY + " TEXT," + ADDRESS_COUNTRY + " TEXT" + ")";

    public dbhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_PRODUCTS);
        db.execSQL(CREATE_TABLE_PAYMENT);
        db.execSQL(CREATE_TABLE_ADDRESS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAYMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDRESS);
        onCreate(db);
    }

    // this method is use to add new course to our sqlite database.
    public void addNewUser(String FIRSTNAME, String LASTNAME, String USERNAME, String EMAIL, String MOBILE, String USERID,
                           String PHOTO, String PASSWORD) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_FIRSTNAME, FIRSTNAME);
        values.put(USER_LASTNAME, LASTNAME);
        values.put(USER_USERNAME, USERNAME);
        values.put(USER_EMAIL, EMAIL);
        values.put(USER_MOBILE, MOBILE);
        values.put(USER_ID, USERID);
        values.put(USER_PHOTO, PHOTO);
        values.put(USER_PASSWORD, PASSWORD);
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public void register(String firstname, String lastname, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_FIRSTNAME, firstname);
        values.put(USER_LASTNAME, lastname);
        values.put(USER_EMAIL, email);
        values.put(USER_PASSWORD, password);
        db.insert(TABLE_USER, null, values);
        db.close();
    }


// correct code to fetch the values from db
//    public Cursor getallproductdata(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = " SELECT * FROM product " ;
//        Cursor res = db.rawQuery(query , null );
//        return res;
//
//    }


    public Cursor getproduct() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " SELECT * FROM products ";
        Cursor res = db.rawQuery(query, null);
        return res;
    }


//    public Boolean login(String username, String password) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        Cursor cursor = db.rawQuery(" SELECT * FROM user WHERE username =  username AND password = password ", null);
////        if(cursor.getCount() > 0) {
//
//        if (cursor.getCount() == -1) {
//            return false;
//        } else {
//            return true;
//        }
////        }else{
////            return false;
////        }
//    }

    public String login(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String id=null;
        String sql = " SELECT * FROM user WHERE email = '" + email + "'  AND password = '" + password + "' ;";
        Cursor cursor = db.rawQuery(sql, null);
          if(cursor.moveToFirst()) {
//             id = cursor.getInt(cursor.getColumnIndexOrThrow("userid"));
              id = cursor.getString(0);
          }
        return id;
    }


//    public void products(String pid, String userid, String proid) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(PAYMENT_ID, pid);
//        values.put(PAYMENT_USERID, userid);
//        values.put(PAYMENT_PRODUCTID, proid);
//        db.insert(TABLE_PAYMENT, null, values);
//        db.close();
//    }

//    public void products(String PRODUCTNANE, String PRICE ,byte[] imageBytes ) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(PRODUCT_PRODUCTNANE, PRODUCTNANE);
//        values.put(PRODUCT_PRICE, PRICE);
//        values.put("image", imageBytes);
//        long newRowId = db.insert("Products", null, values);
//        db.insert(TABLE_PRODUCTS, null, values);
//         db.close();
//    }
public long insertProduct(String productName, String Price) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put("prodctname", productName);
    values.put("price", Price);

//     ByteArrayOutputStream objectByteOutputStream = new ByteArrayOutputStream();
//    imageUrl.compress(Bitmap.CompressFormat.JPEG,100,objectByteOutputStream);
//    imageInbytes = objectByteOutputStream.toByteArray();
//    values.put(dbhelper.PRODUCT_IMAGE_URL,imageInbytes);


    long newRowId = -1;
    try {
        newRowId = db.insert("products", null, values);
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        db.close();
    }

    return newRowId;
}






    public List<productmodel> getproductslist() {
        String sql = "select * from " + TABLE_PRODUCTS;
        SQLiteDatabase SQLiteDatabase = this.getReadableDatabase();
        List<productmodel> storeproducts = new ArrayList<>();
        Cursor cursor = SQLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("proid"));
                //Integer ids = cursor.getString(0);
                String price = cursor.getString(2);
                String productname = cursor.getString(1);
               // byte[] bmp = cursor.getBlob(3);
                storeproducts.add(new productmodel(id,productname, price));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return storeproducts;
    }


    public void addpayment(Integer pro , String userid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        Log.e("product id",pro.toString());
       // values.put(PAYMENT_ID,1);
        values.put(PRODUCT_ID, pro);
        values.put(USER_ID, userid);
        values.put(PAYMENT_QUANTITY, 2);
        db.insert(TABLE_PAYMENT, null, values);
        db.close();
    }



    public List<paymentmodel> getallcart( String userid) {
        SQLiteDatabase SQLiteDatabase = this.getReadableDatabase();
        String sql = " SELECT products.prodctname, products.price, payment.pid " +
                "FROM products " +
                "INNER JOIN payment ON payment.proid = products.proid" + " WHERE userid = '" +userid+ "' ;";

        //   String sql = "select * from " + TABLE_PAYMENT;
      // Log.e("query",sql);

        List<paymentmodel> returnproducts = new ArrayList<>();
        Cursor cursor = SQLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                String prodctname = cursor.getString(0);
                String price = cursor.getString(1);
                Integer id = cursor.getInt(2);
              //  String imageurl = cursor.getBlob(4);
                returnproducts.add(new paymentmodel(prodctname, price,id));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return returnproducts;
    }


    public long insertPayment(String mode, String couponcode,String status, double payment, String orderdate, String deliverydate) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("mode", mode);
        values.put("couponcode", couponcode);
        values.put("status", status);
        values.put("payment", payment);
        values.put("orderdate", orderdate);
        values.put("deliverydate", deliverydate);

//
        long newRowId = -1;
        try {
            newRowId = db.insert("payment", null, values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        return newRowId;
    }

    public List<paymentmodel> getPaymentHistory(String userid) {


             String sql =  " SELECT payment.*,  products.prodctname, products.price "+
       " FROM payment " + "INNER JOIN products ON payment.proid = products.proid" + " WHERE userid = '" + userid +"' ;";


        //   String sql = "SELECT * FROM " + TABLE_PAYMENT;
        SQLiteDatabase db = this.getReadableDatabase();
        List<paymentmodel> paymentHistory = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int pid = cursor.getInt(cursor.getColumnIndexOrThrow("pid"));
                int proid = cursor.getInt(cursor.getColumnIndexOrThrow("proid"));
              //  int userid = cursor.getInt(cursor.getColumnIndexOrThrow("userid"));
                int indexpro = cursor.getColumnIndex("prodctname");
                String prodctname = cursor.getString(indexpro);
                int indexpri = cursor.getColumnIndex("price");
                String price = cursor.getString(indexpri);
//                int payment = cursor.getInt(cursor.getColumnIndexOrThrow("payment"));

                paymentHistory.add(new paymentmodel(pid, proid,prodctname,price));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return paymentHistory;
    }

    public double getTotalPayment(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = " SELECT SUM(payment) FROM Payment WHERE userid = '" +id+"';";
        Cursor cursor = db.rawQuery(query, null);

        double totalPayment = 0.0;
        if (cursor.moveToFirst()) {
            totalPayment = cursor.getDouble(0);
        }
        cursor.close();

        return totalPayment;
    }




















    // Update Image Data
        public void updatepayment(int id, double totalSum,String mode,String couponcode,String status, String userid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PAYMENT_PAYMENT,totalSum );
            values.put(PAYMENT_MODE, mode);
            values.put(PAYMENT_COUPONCODE, couponcode);
            values.put(PAYMENT_STATUS, status);
            values.put(PAYMENT_STATUS, status);
            values.put(PAYMENT_USERID, userid);

            String whereClause = PAYMENT_ID + "=?";
        String[] whereArgs = {String.valueOf(id)};
        db.update(TABLE_PAYMENT, values, whereClause, whereArgs);
        db.close();
    }



//    public void insertTextData(String mode,String couponcode,String status ) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(PAYMENT_MODE, mode);
//        values.put(PAYMENT_COUPONCODE, couponcode);
//        values.put(PAYMENT_STATUS, status);
//        db.insert(TABLE_PAYMENT, null, values);
//        db.close();
//    }

//
//    // Select Image Data
//    public byte[] getImageData(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String[] columns = {PRODUCT_IMAGE};
//        String selection = PRODUCT_ID + "=?";
//        String[] selectionArgs = {String.valueOf(id)};
//        Cursor cursor = db.query(TABLE_PRODUCTS, columns, selection, selectionArgs, null, null, null);
//        if (cursor.moveToFirst()) {
//            byte[] imageBytes = cursor.getBlob(0);
//            cursor.close();
//            db.close();
//            return imageBytes;
//        } else {
//            cursor.close();
//            db.close();
//            return null;
//        }
//    }

//        public List<paymentmodel> getproductsid(){
//            String sql = "select * from " + TABLE_PAYMENT;
//            SQLiteDatabase SQLiteDatabase = this.getReadableDatabase();
//            List<paymentmodel> storeids = new ArrayList<>();
//            Cursor cursor = SQLiteDatabase.rawQuery(sql, null);
//            if (cursor.moveToFirst()) {
//                do {
//                    String id = cursor.getString(4);
//
//                    storeids.add(new paymentmodel(id));
//                } while (cursor.moveToNext());
//            }
//            cursor.close();
//            return storeids;
//
//
//}


}