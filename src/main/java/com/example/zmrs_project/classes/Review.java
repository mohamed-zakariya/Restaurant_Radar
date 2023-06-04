package com.example.zmrs_project.classes;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Review {

    private User user;
    private double rate;
    private Restaurant restaurant;

    private ArrayList<String> comment ;


    public Review(User user, Restaurant restaurant){
        this.user = user;
        this.restaurant = restaurant;
    }
    public Review(User user, Double rate, Restaurant restaurant,ArrayList<String> comment ){
        this.user = user;
        this.rate = rate;
        this.restaurant = restaurant;
        this.comment=comment;
    }


    public User getUser(){
        return user;
    }
    public Restaurant getRestaurant(){
        return restaurant;
    }
    public ArrayList<String> getComment(){
        return comment;
    }


    public Double getRatee(){
        return rate;
    }  //to get the rate from only the Reviw

    public double getRate(User user, Restaurant restaurant) throws SQLException, ClassNotFoundException {
        MyJDBC myJDBC = MyJDBC.getInstance();
        return rate = myJDBC.getRate(user, restaurant);
    }

    public  boolean setRate(User user, double rate, Restaurant restaurant) throws SQLException, ClassNotFoundException {
        MyJDBC myJDBC = MyJDBC.getInstance();
       return myJDBC.setRate(user, rate, restaurant);
    }

    public  void addcomment(User user, Restaurant restaurant, String COMMENT, LocalDateTime time) throws SQLException, ClassNotFoundException {
        MyJDBC myJDBC = MyJDBC.getInstance();
        myJDBC. addComment(user,restaurant,COMMENT,time);
    }

}
