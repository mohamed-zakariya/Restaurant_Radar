package com.example.zmrs_project;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class User extends Person{
    private String email;
    private static int visitors;

    private  ArrayList<Review> userReviews;



    public User(){
    }
    public User(String username){
        super(username);
    }
    public User(String username, String password) throws SQLException, ClassNotFoundException {
        super(username, password);
        MyJDBC jdbc = MyJDBC.getInstance();
        jdbc.getUsersData(username, password);
    }
    public User(String username, String password, String email, ArrayList<Review> userReviews) throws SQLException {
        super(username, password);
        this.email = email;
        this.userReviews = userReviews;
    }
    public int getVisitors() throws SQLException, ClassNotFoundException {
        MyJDBC jdbc = MyJDBC.getInstance();
        visitors = jdbc.noOfUsers();
        return visitors;
    }
    public static User getData(String username, String password) throws SQLException, ClassNotFoundException {
        MyJDBC jdbc = MyJDBC.getInstance();

        return jdbc.getUsersData(username, password);
    }
    public  User create(String username, String password,String email) throws SQLException, ClassNotFoundException {
        MyJDBC jdbc = MyJDBC.getInstance();

        jdbc.insertUser(username, password, email);

        return new User(username, password,email, userReviews);
    }
    public  ArrayList<Review> getReviews() throws SQLException, ClassNotFoundException {
        MyJDBC jdbc = MyJDBC.getInstance();
        return userReviews = jdbc.getUserReviews(this);
    }
    public void addReview(Restaurant restaurant, double rate) throws SQLException, ClassNotFoundException {
        MyJDBC jdbc = MyJDBC.getInstance();

        jdbc.addUserReview(this.getUsername(), restaurant.getRestaurantName(), rate);
    }

    public String getEmail(){ return email; }
    public void setEmail(String email){
        this.email = email;
    }
    @Override
    public String toString() {
        return super.toString()+
                "My email is: "+getEmail()+"\n";
    }
}
