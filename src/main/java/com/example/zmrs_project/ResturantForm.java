package com.example.zmrs_project;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ResturantForm {

    @FXML
    AnchorPane anchorPane;
    private Resturant resturant;

    @FXML
    Label label1;
    @FXML
    Label label2;
    @FXML
    ImageView imageView1;
    public void setResturant(Resturant resturant){
        this.resturant = resturant;
    }

    public void getResturantData(){

        label1.setText(label1.getText() + resturant.getResturantName());
        label2.setText(label2.getText() + resturant.getLocation());
        Image image = new Image("https://github.com/mohamed-zakariya/ZMRS_System/tree/Main/src/main/resources/com/example/zmrs_project/Restaurants/"+resturant.getResturantName()+".jpg");
        imageView1.setImage(image);
    }
}
