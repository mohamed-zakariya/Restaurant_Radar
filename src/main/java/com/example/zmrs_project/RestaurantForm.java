package com.example.zmrs_project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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
    public void setRestaurant(Restaurant restaurant){
        this.restaurant = restaurant;
    }

    public void getRestaurantData(){

        label1.setText(label1.getText() + restaurant.getRestaurantName());
        label2.setText(label2.getText() + restaurant.getLocation());
        Image image = new Image("D:\\Java\\Project\\ZMRS_System\\src\\main\\resources\\com\\example\\zmrs_project\\Restaurants\\"+restaurant.getRestaurantName()+".jpg");
        imageView1.setImage(image);
    }
}
