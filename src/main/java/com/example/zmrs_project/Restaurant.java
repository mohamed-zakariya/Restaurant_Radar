package com.example.zmrs_project;

import java.sql.SQLException;
import java.util.ArrayList;

public class Restaurant implements Cusine{

    private String restaurantName;
    private ArrayList<String> locations;
    private String cusine;
    private String phone;
    private Double avgRate = (double) 0;

    private static ArrayList<Review> restaurantReviews;

    public String getRestaurantName() {
        return restaurantName;
    }
    public String getPhone(){return phone;}


    public ArrayList<String> getLocationsOfRestaurant() throws SQLException, ClassNotFoundException {
        MyJDBC myJDBC = MyJDBC.getInstance();
        return locations = myJDBC.getRestaurantBranches(this);
    }
    public ArrayList<String> getLocation(){
        return locations;
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
    public Restaurant(String restaurantName, ArrayList<String> locations, String cusine, ArrayList<Review> restaurantReviews){
        this.restaurantName = restaurantName;
        this.locations = locations;
        this.cusine = cusine;
        this.restaurantReviews = restaurantReviews;
    }
    public Restaurant(String restaurantName, ArrayList<String> locations, String cusine,String phone){
        this.restaurantName = restaurantName;
        this.locations = locations;
        this.cusine = cusine;
        this.phone= phone;
    }

    public Restaurant(String restaurantName, ArrayList<String> locations, String cusine){
        this.restaurantName = restaurantName;
        this.locations = locations;
        this.cusine = cusine;
    }
    public  void setReview(){

    }

    public static ArrayList<Review> getReviews(Restaurant restaurant) throws SQLException, ClassNotFoundException {
        MyJDBC jdbc = MyJDBC.getInstance();

        return restaurantReviews = jdbc.getRestaurantReviews(restaurant);
    }
    public double getAvgReviews() throws SQLException, ClassNotFoundException {
        avgRate = 0.0;
        restaurantReviews = this.getReviews(this);
        for(int i = 0; i < restaurantReviews.size(); i++){

            avgRate += restaurantReviews.get(i).getRate(restaurantReviews.get(i).getUser(), restaurantReviews.get(i).getRestaurant());
        }
        avgRate /= restaurantReviews.size();
        return  avgRate;
    }

    @Override
    public ArrayList<Restaurant> DisplayCusineRestaurant(String cusine, String location) throws SQLException, ClassNotFoundException {
        MyJDBC jdbc = new MyJDBC().getInstance();

        return jdbc.getCusineRestaurant(cusine, location);
    }
    // this the same as the above but only use it to the cusine only
    public static ArrayList<Restaurant> DisplayCusineRestaurant(String cusine) throws SQLException, ClassNotFoundException {
        MyJDBC jdbc = new MyJDBC().getInstance();

        return jdbc. getCusineRestaurant(cusine);
    }
}
