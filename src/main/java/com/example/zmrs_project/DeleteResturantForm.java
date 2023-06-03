package com.example.zmrs_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import java.io.IOException;
import java.sql.SQLException;

public class DeleteResturantForm {


    @FXML
   Button deleteButton;
    @FXML
    TextField searchText;
    @FXML
    Label erorrlabel;



   private Admin admin;
   private Restaurant restaurant;





   public void setAdmin(Admin admin){
       this.admin =admin;
   }






    @FXML
    public void Search() throws SQLException, ClassNotFoundException, IOException {

        if(searchText.getText().isEmpty()){
            erorrlabel.setText("Please enter the resuranet name");

        }
        else {

            String searchname = searchText.getText();
            restaurant = admin.GetestaurantData(searchname);

            if (restaurant == null){ erorrlabel.setText("Resturanet is not found try again"); }
            else {

            }
        }
    }



    public void Delete(ActionEvent actionEvent) {



    }





}
