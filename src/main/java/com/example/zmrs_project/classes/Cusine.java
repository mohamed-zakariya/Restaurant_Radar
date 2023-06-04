package com.example.zmrs_project.classes;

import java.sql.SQLException;
import java.util.ArrayList;

public interface  Cusine {
    public final ArrayList<Restaurant> restaurants = new ArrayList<>();

    public ArrayList<Restaurant> DisplayCusineRestaurant(String cusine, String location) throws SQLException, ClassNotFoundException;
}
