package com.example.carmate;
public class users {
    String name,email,password,phoneno;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
         this.phoneno = phoneno;
    }

    public users(String name, String email, String password, String phoneno) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneno = phoneno;
    }
    
    public users() {
    }

    
}
