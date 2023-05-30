package com.example.zmrs_project;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
public class ResturantesTypeSlideForm {

    @FXML
    public Parent root;
    @FXML
    Label label1, label2;

    @FXML
    public ScrollPane slidePane;

    @FXML
    public FlowPane flowPane;
    @FXML
    AnchorPane anchorPane1;
    public ArrayList <Restaurant> restaurants;
    public String cusine;

    public ResturantesTypeSlideForm(){
    }

    public void setCusinee(String cusine){
        this.cusine =cusine;
        label1.setText(cusine+" Restaurants");
    }

    public void getdataslide(String cusine) throws SQLException, ClassNotFoundException {
     //resturantes to add them to the array of resurantes of  cusine
        try {
            restaurants = Restaurant.DisplayCusineooRestaurant(cusine);
            if (!restaurants.isEmpty()) {

                // Create a VBox to hold the restaurant slides
                flowPane.setVgap(10);
                flowPane.setHgap(10);
                flowPane.setAlignment(Pos.CENTER);
                for (int i = 0; i < restaurants.size(); i++) {


                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("restaurantForm.fxml"));

                   AnchorPane anchorPane = fxmlLoader.load();

                    RestaurantForm restaurantForm = fxmlLoader.getController();
                    restaurantForm.setRestaurant(restaurants.get(i));
                    restaurantForm.getRestaurantSlideRestaurant();

                    flowPane.getChildren().add(anchorPane);
                    //slidePane.setContent(vbox);
                }

//                ScrollPane scrollPane = new ScrollPane(); // Wrap the VBox in a ScrollPane
//                scrollPane.setFitToWidth(true); // Enable horizontal scrolling
                slidePane.setContent(flowPane);


            } else {
                label2.setText("there is no restaurants with this cusine");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void returnBack(ActionEvent actionEvent) throws IOException {
        Node n = (Node) actionEvent.getSource();
        Stage closeWindow = (Stage) n.getScene().getWindow();
        closeWindow.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("userForm.fxml"));
        root = fxmlLoader.load();

        UserForm userForm = fxmlLoader.getController();

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




   /* public void showSlideForm(String cusine) {
        //resturantes to show them by the cusine

        for(int i = 0; i < restaurants.size(); i++)

        }

    */


}
