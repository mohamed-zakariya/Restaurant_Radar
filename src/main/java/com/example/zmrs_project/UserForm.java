package com.example.zmrs_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserForm {


    private User user;

    @FXML
    TextField textfield1;
    @FXML
    Label label1;
    @FXML
    Label label2;
    @FXML
    HBox hbox1;
    @FXML
    AnchorPane anchorPane2;
    @FXML
    AnchorPane anchorPane1;

    public UserForm(){

    }
    @FXML
    public void setUser(User user){
        this.user = user;
        label1.setText("Hello " + user.getUsername());
        label2.setText(user.getUsername());
    }
    @FXML
    public void search() throws SQLException, ClassNotFoundException, IOException {
        hbox1.getChildren().clear();
        Location location = Location.getResturantsLocation(textfield1.getText());

        ArrayList<Restaurant> restaurants = location.getResturants();


        for(int i = 0; i < restaurants.size(); i++){

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("resturantForm.fxml"));

            AnchorPane anchorPane = fxmlLoader.load();

            RestaurantForm restaurantForm = fxmlLoader.getController();
            restaurantForm.setRestaurant(restaurants.get(i));
            restaurantForm.getRestaurantData();


            hbox1.getChildren().add(anchorPane);
        }

    }
    @FXML
    public void Home(){
        if(!anchorPane1.getChildren().contains(anchorPane2))
            anchorPane1.getChildren().add(anchorPane2);
    }



}
