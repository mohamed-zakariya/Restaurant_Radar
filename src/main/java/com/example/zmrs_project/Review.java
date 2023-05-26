package com.example.zmrs_project;

import java.sql.ResultSet;

public class Review {

    private User user;
    private double rate;
    private Restaurant restaurant;

    public Review(User user, Double rate, Restaurant restaurant){
        this.user = user;
        this.rate = rate;
        this.restaurant = restaurant;
    }



    public double getRate() {
        return rate;
    }


}
