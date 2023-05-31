package com.example.zmrs_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;

import java.net.URL;
import java.sql.SQLException;
public class MenuForm {

    @FXML
    ImageView ImageView1;
    @FXML
    ImageView ImageView2;
    @FXML
    ImageView ImageView3;
    @FXML
    private Slider slider;


    private Restaurant restaurant;

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }


    public void menuSlide(Restaurant restaurant1) {
        Menu menu = new Menu();
        menu.setMenuImagesFromFolder(restaurant1);


        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int imageIndex = newValue.intValue();
            updateImageViewVisibility(imageIndex);
        });

        ImageView1.setImage(menu.getDesserts());
        ImageView2.setImage(menu.getJuiceHotDrinks());
        ImageView3.setImage(menu.getMainplates());

        centerImage(ImageView1);
        centerImage(ImageView2);
        centerImage(ImageView3);

    }

    private void updateImageViewVisibility(int imageIndex) {
       ImageView1.setVisible(imageIndex == 0);
       ImageView2.setVisible(imageIndex == 1);
       ImageView3.setVisible(imageIndex == 2);
    }
    private void centerImage(ImageView imageView) {
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(500);
        imageView.setFitHeight(400);
        imageView.setSmooth(true);
        imageView.setCache(true);
    }
}
