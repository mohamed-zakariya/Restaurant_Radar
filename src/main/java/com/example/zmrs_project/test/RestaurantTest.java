package com.example.zmrs_project.test;

import com.example.zmrs_project.classes.Restaurant;
import com.example.zmrs_project.classes.Review;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class RestaurantTest {

    private Restaurant restaurant;
    private ArrayList < String> locations;

    @Before
    public void setUp() {
        String restaurantName = "Delicious Bites";
        String location = "123 Main Street";
        String cuisine = "Italian";
        String phone = "555-1234";
        locations.add(location);
        restaurant = new Restaurant(restaurantName, locations, cuisine, phone);
    }


    @Test
    public void testRestaurantCreation() {
        String expectedRestaurantName = "Delicious Bites";
        String expectedLocation = "123 Main Street";
        String expectedCuisine = "Italian";
        String expectedPhone = "555-1234";

        Assert.assertEquals(expectedRestaurantName, restaurant.getRestaurantName());
        Assert.assertEquals(expectedLocation, restaurant.getLocation());
        Assert.assertEquals(expectedCuisine, restaurant.getCusine());
        Assert.assertEquals(expectedPhone, restaurant.getPhone());

    }

    @Test
    public void testGetLocationsOfRestaurant() {
        try {
            ArrayList<String> actualLocations = restaurant.getLocationsOfRestaurant(restaurant);
            Assert.assertEquals(locations, actualLocations);
        } catch (Exception e) {
            Assert.fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    public void testGetReviews() {
        try {
            ArrayList<Review> reviews = Restaurant.getReviews(restaurant);
            Assert.assertNotNull(reviews);

        } catch (Exception e) {
            Assert.fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    public void testGetAvgReviews() {
        try {
            double avgRate = restaurant.getAvgReviews();
            Assert.assertTrue(avgRate >= 0.0 && avgRate <= 5.0);
        } catch (Exception e) {
            Assert.fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    public void testDisplayCusineRestaurant() {
        try {
            String cusine = "Italian";
            String location = "New York";
            ArrayList<Restaurant> restaurants = restaurant.DisplayCusineRestaurant(cusine, location);

        } catch (Exception e) {
            Assert.fail("Exception should not be thrown: " + e.getMessage());
        }

    }




}
