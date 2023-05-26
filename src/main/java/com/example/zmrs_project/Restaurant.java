package com.example.zmrs_project;

import java.sql.SQLException;
import java.util.ArrayList;

public class Restaurant implements Cusine{

    private String restaurantName;
    private String location;
    private String cusine;
    private Double avgRate;

    private ArrayList<Review> restaurantReviews;

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getLocation() {
        return location;
    }

    public String getCusine() {
        return cusine;
    }

    public Restaurant(){

    }
    public Restaurant(String restaurantName){
        this.restaurantName = restaurantName;
    }
    public Restaurant(String restaurantName, String cusine){
        this.restaurantName = restaurantName;
        this.cusine = cusine;
    }
    public Restaurant(String restaurantName, String location, String cusine, ArrayList<Review> restaurantReviews){
        this.restaurantName = restaurantName;
        this.location = location;
        this.cusine = cusine;
        this.restaurantReviews = restaurantReviews;
    }

    public ArrayList<Review> getReviews() throws SQLException, ClassNotFoundException {
        MyJDBC jdbc = MyJDBC.getInstance();
        return restaurantReviews = jdbc.getRestaurantReviews(this);
    }
    public double getAvgReviews(){
        for(int i = 0; i < restaurantReviews.size(); i++){
            avgRate += restaurantReviews.get(i).getRate();
        }
        return  avgRate;
    }

    @Override
    public ArrayList<Restaurant> DisplayCusineRestaurant(String cusine, String location) throws SQLException, ClassNotFoundException {
        MyJDBC jdbc = new MyJDBC().getInstance();

        return jdbc.getCusineRestaurant(cusine, location);
    }
}
