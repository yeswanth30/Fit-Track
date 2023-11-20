package com.fitness.models;

import android.graphics.Bitmap;

public class productmodel {

    private Integer id;

    private String prodctname;
    private String price;
    private byte[] imageurl;
    private static byte[] bmp;

    public productmodel(Integer id,String prodctname , String price){
this.prodctname=prodctname;
this.price=price;
this.id=id;
this.bmp = bmp;
//this.imageurl=imageurl;

}

    public productmodel(Integer id){
    this.id = id;
    }

    public static byte[] getbmp() {
        return bmp;
    }

    public String getProdctname() {
        return prodctname;
    }

    public void setProdctname(String productname) {
        this.prodctname = productname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

//    public byte[] getImageurl() {
//        return imageurl;
//    }
//
//    public void setImageurl(byte[] imageurl) {
//        this.imageurl = imageurl;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public void setBmp(byte[] bmp) {
        this.bmp = bmp;
    }
}
