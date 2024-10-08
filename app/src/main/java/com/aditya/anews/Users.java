package com.aditya.anews;

public class Users {
    String mail,name,password;

    public Users(String mobile, String name, String password) {
        this.mail = mobile;
        this.name = name;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
