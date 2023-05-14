package com.example.zmrs_project;

import java.sql.SQLException;
import java.util.ArrayList;

public class Location {

    private String location;

    public ArrayList<Resturant> resturants = new ArrayList<>();

    public ArrayList<Resturant> getResturants() {
        return resturants;
    }

    public Location(){

    }
    public Location(String location, ArrayList<Resturant> resturants){
        this.location = location;
        this.resturants = resturants;
    }

    public static Location getResturantsLocation(String location) throws SQLException, ClassNotFoundException {
        MyJDBC jdbc = MyJDBC.getInstance();
        return jdbc.searchLocation(location);
    }
}
