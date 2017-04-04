package com.example.sarfraz.sarfarz;

/**
 * Created by Adnan Ahmed on 2/10/2017.
 */

public class user {

    String name;
    String picurl;
    String email;
    String cnic;
    String batch;
    String depart;
    String pass;
    String id;
    String type;

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPicurl() {
        return picurl;
    }

    public String getEmail() {
        return email;
    }

    public String getCnic() {
        return cnic;
    }

    public String getBatch() {
        return batch;
    }

    public user() {
    }

    public String getDepart() {
        return depart;
    }

    public String getPass() {
        return pass;
    }
    public user(String name, String picurl, String email, String cnic, String batch, String depart, String pass, String id, String type) {
        this.name = name;
        this.picurl = picurl;
        this.email = email;
        this.cnic = cnic;
        this.batch = batch;
        this.depart = depart;
        this.pass = pass;
        this.id = id;
        this.type = type;

    }
}
//name,picurl,email,cnic,batch,depart,pass,id,type
