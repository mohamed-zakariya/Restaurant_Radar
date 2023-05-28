package com.example.zmrs_project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.MalformedURLException;

import java.net.URL;
import java.sql.SQLException;

public class RestaurantForm {

    @FXML
    AnchorPane anchorPane;
    private Restaurant restaurant;

    @FXML
    Label label1;
    @FXML
    Label label2;

    @FXML
    ImageView imageView1;
    @FXML
    ImageView imageView2;
    String location = null;
    public void setRestaurant(Restaurant restaurant){
        this.restaurant = restaurant;
    }
    public void setLocation(String location){
        this.location = location;
    }

    public void getRestaurantData() throws MalformedURLException {

        label1.setText(label1.getText() + restaurant.getRestaurantName());
        label2.setText(label2.getText() + location);
        Image image = new Image("D:\\Java\\Project\\ZMRS_System\\src\\main\\resources\\com\\example\\zmrs_project\\Restaurants\\"+restaurant.getRestaurantName()+".jpg");
        imageView1.setImage(image);
    }
        public void getRestaurantSlideResturanet() throws MalformedURLException, SQLException, ClassNotFoundException {
        anchorPane.setPrefHeight(310);
        anchorPane.setPrefWidth(297);
        imageView1.setFitWidth(277);
        imageView1.setFitHeight(179);

        label2.setLayoutY(280);
        label1.setText(label1.getText() + restaurant.getRestaurantName());
        label2.setText("Location:" + restaurant.getLocationsOfRestaurant());

        Image image = new Image("D:\\Java\\Project\\ZMRS_System\\src\\main\\resources\\com\\example\\zmrs_project\\Restaurants\\"+restaurant.getRestaurantName()+".jpg");
        imageView1.setImage(image);
    }


}
