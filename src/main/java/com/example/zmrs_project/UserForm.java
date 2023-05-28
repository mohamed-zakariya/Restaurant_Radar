package com.example.zmrs_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

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
    @FXML
    ImageView imageView1;

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
            restaurantForm.setLocation(textfield1.getText());
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
    public void  OpenCusineForm(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
        Button button = (Button) actionEvent.getSource();
        String buttontext = button.getText();

        Node n = (Node) actionEvent.getSource();
        Stage closeWindow = (Stage) n.getScene().getWindow();
        closeWindow.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ResturantesTypeSlide.fxml"));
        root = fxmlLoader.load();
        // its to connect the slide form with the method
        ResturantesTypeSlideForm resturantesTypeSlideForm = fxmlLoader.getController();

        resturantesTypeSlideForm.setCusinee(buttontext);
        resturantesTypeSlideForm.getdataslide(buttontext);

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle(buttontext);
        stage.setScene(scene);
        stage.show();

    }




}
