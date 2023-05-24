package com.example.zmrs_project;

public class Restaurant {

    private String resturantName;
    private String location;
    private String cusine;
    private Double avgReview;

    public String getRestaurantName() {
        return resturantName;
    }

    public String getLocation() {
        return location;
    }

    public String getCusine() {
        return cusine;
    }

    public Restaurant(){

    }
    public Restaurant(String resturantName, String location, String cusine){
        this.resturantName = resturantName;
        this.location = location;
        this.cusine = cusine;
    }


}
