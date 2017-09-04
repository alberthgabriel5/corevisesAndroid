package com.example.alfonso.beargamer.Domain;

/**
 * Created by Alfonso on 26/8/2017.
 */

public class Administrator {
    private String user;
    private String pass;

    public Administrator(){
        this.setUser("user");
        this.setPass("pass");
    }//default

    public Administrator(String user,String pass){
        this.user = user;
        this.pass = pass;
    }//constructor

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}//end class
