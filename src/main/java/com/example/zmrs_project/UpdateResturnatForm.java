package com.example.zmrs_project;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class UpdateResturnatForm {

    //public TextField textName;
    @FXML
    public TextField textCusine;
    @FXML
    public TextField textLocation;
    @FXML
    public TextField textphone;
    @FXML
    TextField searchText ;
    @FXML
    Button ButtonSearch ;
    @FXML
    Label labelName;
    @FXML
    Label labelCusine;
    @FXML
    Label labelphone;
    @FXML
    Label labelLocation;
    @FXML
    Button UpdateButton;
    @FXML
    Label labelErrornotify;
    @FXML
    Hyperlink UpdateResturanetData;
    @FXML
    ImageView imageView1;
    private Admin admin;

    private Restaurant restaurant;

    private ArrayList<String> locations;



    public void setAdmin(Admin admin) {
        this.admin = admin;
    }


    @FXML
    public Restaurant Search() throws SQLException, ClassNotFoundException, IOException {

        if(searchText.getText().isEmpty()){
        labelErrornotify.setText("Please enter the resuranet name");
        return null;
        }
        else {

            String searchname = searchText.getText();
            restaurant = admin.GetestaurantData(searchname);

        if (restaurant == null){ labelErrornotify.setText("Resturanet is not found try again");  return null;}
          else {
            labelName.setText("Name : " + restaurant.getRestaurantName());
            labelCusine.setText("Cusine : " + restaurant.getCusine());
            labelphone.setText("Phone Number : " + restaurant.getPhone());
            labelLocation.setText("Location: " + restaurant.getLocationsOfRestaurant(new Restaurant(restaurant.getRestaurantName())));

            Image image = new Image("E:\\6 term\\OOP\\project_zmrs_3\\ZMRS_System\\src\\main\\resources\\com\\example\\zmrs_project\\Restaurants\\" + restaurant.getRestaurantName() + ".jpg");
            imageView1.setImage(image);
            return restaurant;
            }
        }
    }

    @FXML
    public void UpdateResturanetData() throws SQLException, ClassNotFoundException, IOException {

        if ( Search() != null) {

            FadeTransition fadeInTransition1 = new FadeTransition(Duration.seconds(0.5), textCusine);
            fadeInTransition1.setFromValue(0.0);
            fadeInTransition1.setToValue(1.0);
            fadeInTransition1.play();
            FadeTransition fadeInTransition2 = new FadeTransition(Duration.seconds(0.5), textLocation);
            fadeInTransition2.setFromValue(0.0);
            fadeInTransition2.setToValue(1.0);
            fadeInTransition2.play();
            FadeTransition fadeInTransition3 = new FadeTransition(Duration.seconds(0.5), textphone);
            fadeInTransition3.setFromValue(0.0);
            fadeInTransition3.setToValue(1.0);
            fadeInTransition3.play();

            textCusine.setVisible(true);
            textLocation.setVisible(true);
            textphone.setVisible(true);
            UpdateButton.setVisible(true);
        }


    }

    @FXML
    public void Update(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Restaurant restaurant1;
        String location = textCusine.getText();
        String cuisine = textLocation.getText();
        String phone = textphone.getText();

        boolean isLocationEmpty = location.isEmpty();
        boolean isCuisineEmpty = cuisine.isEmpty();
        boolean isPhoneEmpty = phone.isEmpty();



         textCusine.setPromptText(isLocationEmpty ? "Location cannot be empty" : "");
         textLocation.setPromptText(isCuisineEmpty ? "Cuisine cannot be empty" : "");
         textphone.setPromptText(isPhoneEmpty ? "Phone cannot be empty" : "");


        if ( isLocationEmpty || isCuisineEmpty || isPhoneEmpty) {
            return;
        }

        locations = new ArrayList<String>();
        locations.add(location);

        restaurant1 = new Restaurant(restaurant.getRestaurantName(), locations, cuisine, phone);

        admin.UpdateRestaurant(restaurant1);


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Information Message");
        alert.setContentText("Succesfully updated");
        alert.showAndWait();



    }

}






