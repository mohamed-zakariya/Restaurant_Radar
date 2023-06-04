package com.example.zmrs_project;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
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

    @FXML
    private  Label labelAdmin;
    private Admin admin;

    private Restaurant restaurant;

    private ArrayList<String> locations;

    public Parent root;





    public void setAdmin(Admin admin) {
        this.admin = admin;
        labelAdmin.setText(admin.getUsername());
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

            Image image = new Image("D:\\Java\\Project\\ZMRS_System\\src\\main\\resources\\com\\example\\zmrs_project\\Restaurants\\" + restaurant.getRestaurantName() + ".jpg");
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
        String cuisine = textCusine.getText();
        String location = textLocation.getText();
        String phone = textphone.getText();

        boolean isLocationEmpty = location.isEmpty();
        boolean isCuisineEmpty = cuisine.isEmpty();
        boolean isPhoneEmpty = phone.isEmpty();



        textLocation.setPromptText(isLocationEmpty ? "Location cannot be empty" : "");
         textCusine.setPromptText(isCuisineEmpty ? "Cuisine cannot be empty" : "");
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
        alert.setHeaderText(null);
        alert.setContentText("Succesfully Updated ! ");
        alert.showAndWait();

        UpdateButton.setVisible(false);


    }

    public void returnBack(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        Node n = (Node) actionEvent.getSource();
        Stage closeWindow = (Stage) n.getScene().getWindow();
        closeWindow.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminForm.fxml"));
        root = fxmlLoader.load();

        AdminForm adminForm = fxmlLoader.getController();
        adminForm.setAdmin(admin);
        adminForm.showData();

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }
    public void signOut(ActionEvent actionEvent) throws IOException {
        Node n = (Node) actionEvent.getSource();
        Stage closeWindow = (Stage) n.getScene().getWindow();
        closeWindow.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginform.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}






