package com.example.zmrs_project;

public class Resturant {

    private String resturantName;
    private String location;
    private String cusine;
    private Double avgReview;

    public String getResturantName() {
        return resturantName;
    }

    public String getLocation() {
        return location;
    }

    public String getCusine() {
        return cusine;
    }

    public Resturant(){

    }
    public Resturant(String resturantName, String location, String cusine){
        this.resturantName = resturantName;
        this.location = location;
        this.cusine = cusine;
    }


}
