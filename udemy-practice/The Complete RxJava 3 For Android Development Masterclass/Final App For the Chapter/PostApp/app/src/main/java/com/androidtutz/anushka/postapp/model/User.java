package com.androidtutz.anushka.postapp.model;



/**
 * Created by K. A. ANUSHKA MADUSANKA on 6/25/2018.
 */
public class User {

    private String userEmail;
    private String passWord;
    private  int id;


    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
