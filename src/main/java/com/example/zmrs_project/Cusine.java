package com.example.zmrs_project;

import java.util.ArrayList;

public interface  Cusine {
    public final ArrayList<Restaurant> restaurants = new ArrayList<>();

    public ArrayList<Restaurant> DisplayCusineRestaurant(String cusine, String location);
}
