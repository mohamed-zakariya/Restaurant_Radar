package com.example.zmrs_project;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Review {

    private User user;
    private double rate;
    private Restaurant restaurant;

    public Review(User user, Restaurant restaurant){
        this.user = user;
        this.restaurant = restaurant;
    }
    public Review(User user, Double rate, Restaurant restaurant){
        this.user = user;
        this.rate = rate;
        this.restaurant = restaurant;
    }


    public User getUser(){
        return user;
    }
    public Restaurant getRestaurant(){
        return restaurant;
    }
    public double getRate(User user, Restaurant restaurant) throws SQLException, ClassNotFoundException {
        MyJDBC myJDBC = MyJDBC.getInstance();
        return rate = myJDBC.getRate(user, restaurant);
    }
    public  boolean setRate(User user, double rate, Restaurant restaurant) throws SQLException, ClassNotFoundException {
        MyJDBC myJDBC = MyJDBC.getInstance();
       return myJDBC.setRate(user, rate, restaurant);
    }

}
