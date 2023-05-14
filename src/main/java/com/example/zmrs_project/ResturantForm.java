package com.example.zmrs_project;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ResturantForm {

    @FXML
    AnchorPane anchorPane;
    private Resturant resturant;

    @FXML
    Label label1;
    @FXML
    Label label2;
    public void setResturant(Resturant resturant){
        this.resturant = resturant;
    }

    public void getResturantData(){

        label1.setText(label1.getText() + resturant.getResturantName());
        label2.setText(label2.getText() + resturant.getLocation());
    }
}
