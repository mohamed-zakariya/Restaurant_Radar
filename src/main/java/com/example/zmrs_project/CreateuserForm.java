package com.example.zmrs_project;
import com.example.zmrs_project.classes.User;
import com.example.zmrs_project.test.JunitTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.SQLException;

// controler form ro control create user form fxml
public class CreateuserForm {

    UserForm userForm;
    @FXML
    private TextField username_text;
    @FXML
    private TextField password_text;
    @FXML
    private TextField email_text;

    private Parent root;

    @FXML
    public void create1(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {

        JunitTest test = new JunitTest();
        if(!test.testEmailFormat(email_text.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setHeaderText(null);
            alert.setContentText("please enter the email in the right format");
            alert.showAndWait();
        }

        else{
            Node n = (Node) actionEvent.getSource();
            Stage closeWindow = (Stage) n.getScene().getWindow();
            closeWindow.close();

            User user1 = new User();
            user1 = user1.create(username_text.getText(), password_text.getText(),email_text.getText());

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("userForm.fxml"));
            root = fxmlLoader.load();

            UserForm userForm = fxmlLoader.getController();
            userForm.setUser(user1);

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("User!");
            stage.setScene(scene);
            stage.show();
        }
    }
    @FXML
    public void returnBack(ActionEvent actionEvent) throws IOException {
        Node n = (Node)  actionEvent.getSource();
        Stage closeWindow = (Stage) n.getScene().getWindow();
        closeWindow.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginform.fxml"));
        root = fxmlLoader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

}







