package com.example.zmrs_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Loginform {
    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2;
    @FXML
    Label label1;
    public Parent root;


    @FXML
    public void onLoginClick(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
        Node n = (Node) actionEvent.getSource();
        Stage closeWindow = (Stage) n.getScene().getWindow();

        Person p = Person.login(textField1.getText(), textField2.getText());
        if(p instanceof User){
            System.out.println(((User) p).getVisitors());
            closeWindow.close();

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("userForm.fxml"));
            root = fxmlLoader.load();

            UserForm userForm = fxmlLoader.getController();
            User user1 = (User) p;
            userForm.setUser(User.getData(user1.getUsername(), user1.getPassword()));

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("User!");
            stage.setScene(scene);
            stage.show();
        } else if (p instanceof Admin) {
            closeWindow.close();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminForm.fxml"));
            root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Admin!");
            stage.setScene(scene);
            stage.show();
        }
        else{
            label1.setText("the Account is Not Exist");
        }
    }
    @FXML
    public void CreateForm(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
        Node n = (Node) actionEvent.getSource();
        Stage closeWindow = (Stage) n.getScene().getWindow();
        closeWindow.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("createuserForm.fxml"));
        root = fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("CreatUser!");
        stage.setScene(scene);
        stage.setTitle("Hello!");
        stage.show();
    }
}
