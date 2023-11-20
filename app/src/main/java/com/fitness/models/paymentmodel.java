package com.fitness.models;

public class paymentmodel {

    private int pid;
    private int proid;
    private int userid;
    private int payment;
    private String prodctname;
    private String price;
    private String imageurl;
    private String Fullname;
    private String addressline1;
    private String addressline2;
    private String mode;
    private String couponcode;
    private String username;

    public paymentmodel(String prodctname, String price) {
        this.prodctname = prodctname;
        this.price = price;
        this.imageurl = imageurl;
    }
    public paymentmodel(String prodctname, String price,Integer id) {
        this.prodctname = prodctname;
        this.price = price;
        this.pid=id;
    }
    public paymentmodel(Integer pid, Integer proid,String prodctname, String price ) {
        this.pid = pid;
        this.proid = proid;
//        this.userid=userid;
        this.prodctname = prodctname;
        this.price = price;
    //    this.payment = payment;
//        this.username = username;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getAddressline1() {
        return addressline1;
    }

    public void setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
    }

    public String getAddressline2() {
        return addressline2;
    }

    public void setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getCouponcode() {
        return couponcode;
    }

    public void setCouponcode(String couponcode) {
        this.couponcode = couponcode;
    }

    public String getProdctname() {
        return prodctname;
    }

    public void setProdctname(String prodctname) {
        this.prodctname = prodctname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

//    public String getImageurl() {
//        return imageurl;
//    }
//
//    public void setImageurl(String imageurl) {
//        this.imageurl = imageurl;
//    }


    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }


    public int getProid() {
        return proid;
    }

    public void setProid(int proid) {
        this.proid = proid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }
}



