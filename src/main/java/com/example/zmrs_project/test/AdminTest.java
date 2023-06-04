package com.example.zmrs_project.test;
import com.example.zmrs_project.classes.Admin;
import com.example.zmrs_project.classes.Restaurant;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AdminTest {
    private Admin admin;
    private Restaurant restaurant;

    @Before
    public void setUp() {
        admin = new Admin();
        restaurant = new Restaurant("Example Restaurant", "123 Main St");
    }

    @Test
    public void testAddRestaurant() throws SQLException, ClassNotFoundException {
        admin.add(restaurant);
        List<Restaurant> restaurants = admin.getallrestaurant();
        assertTrue(restaurants.contains(restaurant));
    }

    @Test
    public void testRemoveRestaurant() throws SQLException, ClassNotFoundException {
        admin.add(restaurant);
        admin.RemoveResturant(restaurant.getRestaurantName());
        List<Restaurant> restaurants = admin.getallrestaurant();
        assertFalse(restaurants.contains(restaurant));
    }

    @Test
    public void testGetRestaurants() throws SQLException, ClassNotFoundException {
        admin.add(restaurant);
        List<Restaurant> restaurants = admin.getallrestaurant();
        assertEquals(1, restaurants.size());
        assertEquals(restaurant, restaurants.get(0));
    }

    @Test
    public void testSearchRestaurantByName() throws SQLException, ClassNotFoundException {
        admin.add(restaurant);
        List<Restaurant> searchResults = null;
        searchResults.add(admin.GetestaurantData("Example Restaurant"));
        assertEquals(1, searchResults.size());
        assertEquals(restaurant, searchResults.get(0));
    }

   
}
