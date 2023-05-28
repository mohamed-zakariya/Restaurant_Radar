package com.example.zmrs_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserForm {

    public Parent root;
    private User user;

    @FXML
    TextField textfield1;
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

        label2.setText(user.getUsername());
    }
    @FXML
    public void search() throws SQLException, ClassNotFoundException, IOException {
        hbox1.getChildren().clear();
        Location location = Location.getResturantsLocation(textfield1.getText());

        ArrayList<Restaurant> restaurants = location.getResturants();


        for(int i = 0; i < restaurants.size(); i++){

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("restaurantForm.fxml"));

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


    @FXML
    public void  OpenFriedChiken(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
        Node n = (Node) actionEvent.getSource();
        Stage closeWindow = (Stage) n.getScene().getWindow();
        closeWindow.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ResturantesTypeSlide.fxml"));
        root = fxmlLoader.load();
        // its to connect the slide form with the method
        ResturantesTypeSlideForm resturantesTypeSlideForm=fxmlLoader.getController();
        resturantesTypeSlideForm.setCusinee("FriedChiken");
        resturantesTypeSlideForm.getdataslide("FriedChiken");

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Fried Chiken");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void OpenPizza(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
        Node n = (Node) actionEvent.getSource();
        Stage closeWindow = (Stage) n.getScene().getWindow();
        closeWindow.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ResturantesTypeSlide.fxml"));
        root = fxmlLoader.load();
        // its to connect the slide form with the method
        ResturantesTypeSlideForm resturantesTypeSlideForm=fxmlLoader.getController();
        resturantesTypeSlideForm.setCusinee("Pizza");
        resturantesTypeSlideForm.getdataslide("Pizza");

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Pizza");
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    public void OpenBurger(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
        Node n = (Node) actionEvent.getSource();
        Stage closeWindow = (Stage) n.getScene().getWindow();
        closeWindow.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ResturantesTypeSlide.fxml"));
        root = fxmlLoader.load();
        // its to connect the slide form with the method
        ResturantesTypeSlideForm resturantesTypeSlideForm=fxmlLoader.getController();
        resturantesTypeSlideForm.setCusinee("Burger");
        resturantesTypeSlideForm.getdataslide("Burger");

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Burgers");
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    public void OpenSeaFood(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
        Node n = (Node) actionEvent.getSource();
        Stage closeWindow = (Stage) n.getScene().getWindow();
        closeWindow.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ResturantesTypeSlide.fxml"));
        root = fxmlLoader.load();
        // its to connect the slide form with the method
        ResturantesTypeSlideForm resturantesTypeSlideForm=fxmlLoader.getController();
        resturantesTypeSlideForm.setCusinee("SeaFood");
        resturantesTypeSlideForm.getdataslide("SeaFood");

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Sea Food");
        stage.setScene(scene);
        stage.show();
    }



}
