package com.example.zmrs_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminForm {




    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    public Parent root;
    private Admin admin;
    @FXML
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }


    public void ADD(ActionEvent actionEvent) throws IOException {
        Node n = (Node) actionEvent.getSource();
        Stage closeWindow = (Stage) n.getScene().getWindow();
        closeWindow.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addResturanetForm.fxml"));//3lashan neft7 safha tanya
        root = fxmlLoader.load();//el makn el ana wa2f feh
        AddResturanetForm addResturanetForm= fxmlLoader.getController();


        addResturanetForm.setAdmin(admin);
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void DELETE(){

    }
    public void UPDATAE(ActionEvent actionEvent) throws IOException {
        Node n = (Node) actionEvent.getSource();
        Stage closeWindow = (Stage) n.getScene().getWindow();
        closeWindow.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addResturanetForm.fxml"));//3lashan neft7 safha tanya
        root = fxmlLoader.load();//el makn el ana wa2f feh
        AddResturanetForm addResturanetForm= fxmlLoader.getController();


        addResturanetForm.setAdmin(admin);
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }




}
