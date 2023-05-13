package com.example.zmrs_project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

// controler form ro control create user form fxml
public class CreateuserForm {


    @FXML
    private TextField username_text;
    @FXML
    private TextField password_text;
    @FXML
    private TextField email_text;


    @FXML
    public void create1(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
        Node n = (Node) actionEvent.getSource();
        Stage closeWindow = (Stage) n.getScene().getWindow();

        closeWindow.close();
        User user1 = User.create(username_text.getText(), password_text.getText(),email_text.getText());
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("userForm.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("User!");
        stage.setScene(scene);
        stage.show();
    }


}







