package com.example.zmrs_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloController {
    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2;
    @FXML
    Label label1;



    @FXML
    public void onLoginClick(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
        Node n = (Node) actionEvent.getSource();
        Stage closeWindow = (Stage) n.getScene().getWindow();

        Person p = Person.login(textField1.getText(), textField2.getText());
        if(p instanceof User){
            closeWindow.close();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("userForm.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("User!");
            stage.setScene(scene);
            stage.show();
        } else if (p instanceof Admin) {
            closeWindow.close();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminForm.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("Admin!");
            stage.setScene(scene);
            stage.show();
        }
        else{
            label1.setText("the Account is Not Exist");
        }
    }
}