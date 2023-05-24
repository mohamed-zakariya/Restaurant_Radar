package com.example.zmrs_project;

import java.sql.SQLException;
import java.util.ArrayList;

public class Location {

    private String location;

    public ArrayList<Restaurant> restaurants = new ArrayList<>();

    public ArrayList<Restaurant> getResturants() {
        return restaurants;
    }

    public Location(){

    }
    public Location(String location, ArrayList<Restaurant> restaurants){
        this.location = location;
        this.restaurants = restaurants;
    }

    public static Location getResturantsLocation(String location) throws SQLException, ClassNotFoundException {
        MyJDBC jdbc = MyJDBC.getInstance();
        return jdbc.searchLocation(location);
    }
}
