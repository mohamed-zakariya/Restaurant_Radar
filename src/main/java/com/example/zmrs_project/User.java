package com.example.zmrs_project;

import java.sql.Connection;
import java.sql.SQLException;

public class User extends Person{
    private String email;
    private static int visitors;

    public User(){
    }
    public User(String username, String password){
        super(username, password);

    }
    public User(String username, String password, String email) throws SQLException {
        super(username, password);
        this.email = email;
    }
    public int getVisitors() throws SQLException, ClassNotFoundException {
        MyJDBC jdbc = MyJDBC.getInstance();
        visitors = jdbc.noOfUsers();
        return visitors;
    }

    public String getEmail(){
        return email;
    }

    @Override
    public String toString() {
        return super.toString()+
                "My email is: "+getEmail()+"\n";
    }
}
