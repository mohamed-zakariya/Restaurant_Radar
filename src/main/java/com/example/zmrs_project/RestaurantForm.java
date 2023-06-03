package com.example.zmrs_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;

import java.net.URL;
import java.sql.SQLException;

public class RestaurantForm {

    @FXML
    AnchorPane anchorPane, anchorPane2;
    private Restaurant restaurant;
    private User user;
    @FXML
    Label label1, label2;

    @FXML
    Button button1;
    @FXML
    ImageView imageView1;

    @FXML
    public Parent root;

    String location = null;
    public void setRestaurant(Restaurant restaurant){
        this.restaurant = restaurant;
    }
    public void setUser(User user){this.user = user;}
    public void setLocation(String location){
        this.location = location;
    }

    public void getRestaurantData() throws MalformedURLException {

        label1.setText(label1.getText() + restaurant.getRestaurantName());
        label2.setText(label2.getText() + location);
        Image image = new Image("D:\\Java\\Project\\ZMRS_System\\src\\main\\resources\\com\\example\\zmrs_project\\Restaurants\\"+restaurant.getRestaurantName()+".jpg");

        imageView1.setImage(image);
    }
        public void getRestaurantSlideRestaurant() throws MalformedURLException, SQLException, ClassNotFoundException {
        anchorPane.setPrefHeight(310);
        anchorPane.setPrefWidth(297);
        imageView1.setFitWidth(277);
        imageView1.setFitHeight(179);

        anchorPane2.setLayoutX(258);
        anchorPane2.setLayoutY(275);
        label2.setLayoutY(280);
        label1.setText(label1.getText() + restaurant.getRestaurantName());
        label2.setText("Location:" + restaurant.getLocationsOfRestaurant(new Restaurant(restaurant.getRestaurantName())));

        Image image = new Image("D:\\Java\\Project\\ZMRS_System\\src\\main\\resources\\com\\example\\zmrs_project\\Restaurants\\"+restaurant.getRestaurantName()+".jpg");

        imageView1.setImage(image);
    }
    @FXML
    public void viewRestaurnat(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        Node node = (Node) actionEvent.getSource();

        Stage closewindow = (Stage) node.getScene().getWindow();
        closewindow.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("restaurantView.fxml"));
        root = fxmlLoader.load();

        RestaurantView restaurantView = fxmlLoader.getController();
        restaurantView.setUser(user);
        restaurantView.setRestaurant(restaurant);
        restaurantView.showData();

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(restaurant.getRestaurantName());
        stage.show();
    }



}
