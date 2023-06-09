package com.example.zmrs_project;

import com.example.zmrs_project.classes.Location;
import com.example.zmrs_project.classes.Restaurant;
import com.example.zmrs_project.classes.User;
import com.example.zmrs_project.test.JunitTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserForm {

    public Parent root;
    private User user;

    @FXML
    TextField textfield1;
    @FXML
    VBox vboxMain;
    @FXML
    Label label2;
    @FXML
    AnchorPane anchorPane1, anchorPane2;
    @FXML
    ImageView imageView1;
    @FXML
    FlowPane flowPane1;
    @FXML
    BorderPane borderPane;
    @FXML
    HBox hbox1, hbox2;

    VBox vboxTemp;

    public UserForm(){

    }
    @FXML
    public void setUser(User user){
        this.user = user;
        hbox1.setStyle("-fx-background-color: brown;");
        hbox2.setStyle("-fx-background-color: #3B3131;");
        label2.setText(user.getUsername());
    }

    @FXML
    public void search() throws SQLException, ClassNotFoundException, IOException {
        flowPane1.getChildren().clear();
        flowPane1.setPadding(new Insets(0,0,0,0));
        if(textfield1.getText().equals("")){
            imageView1.setImage(new Image("D:\\Java\\Project\\ZMRS_System\\src\\main\\resources\\com\\example\\zmrs_project\\images\\1.jpg"));
            flowPane1.getChildren().add(imageView1);
            flowPane1.setPadding(new Insets(0,175,0,0));

        }
        else{
            JunitTest junitTest = new JunitTest();
            if(!junitTest.isLettersOnly(textfield1.getText())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setHeaderText(null);
                alert.setContentText("please enter the search in a right format");
                alert.showAndWait();
            }

            Location location = Location.getResturantsLocation(textfield1.getText());

            ArrayList<Restaurant> restaurants = location.getRestaurants();

            flowPane1.setHgap(10);
            flowPane1.setVgap(10);
            flowPane1.setAlignment(Pos.CENTER);
            for(int i = 0; i < restaurants.size(); i++){

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("restaurantForm.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                RestaurantForm restaurantForm = fxmlLoader.getController();
                restaurantForm.setUser(user);
                restaurantForm.setRestaurant(restaurants.get(i));
                restaurantForm.setLocation(textfield1.getText());
                restaurantForm.getRestaurantData();


                flowPane1.getChildren().add(anchorPane);
            }

        }

    }
    @FXML
    public void Home(){
        borderPane.setCenter(anchorPane2);
        hbox1.setStyle("-fx-background-color: brown;");
        hbox2.setStyle("-fx-background-color: #3B3131;");
    }


    @FXML
    public void  OpenCusineForm(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
        hbox1.setStyle("-fx-background-color: #3B3131;");
        hbox2.setStyle("-fx-background-color: #3B3131;");

        Button button = (Button) actionEvent.getSource();
        String buttontext = button.getText();

//        Node n = (Node) actionEvent.getSource();
//        Stage closeWindow = (Stage) n.getScene().getWindow();
//        closeWindow.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ResturantesTypeSlide.fxml"));


        AnchorPane anchorPane3 = fxmlLoader.load();
        ResturantesTypeSlideForm resturantesTypeSlideForm = fxmlLoader.getController();

        resturantesTypeSlideForm.setUser(user);
        resturantesTypeSlideForm.setCusinee(buttontext);
        resturantesTypeSlideForm.getdataslide(buttontext);


        borderPane.setCenter(anchorPane3);


    }
    @FXML
    public void returnBack(ActionEvent actionEvent) throws IOException {
        Node n = (Node) actionEvent.getSource();
        Stage closeWindow = (Stage) n.getScene().getWindow();
        closeWindow.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginform.fxml"));
        root = fxmlLoader.load();

        Loginform loginform = fxmlLoader.getController();

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    public void reviwHistory(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        hbox1.setStyle("-fx-background-color: #3B3131;");
        hbox2.setStyle("-fx-background-color: brown;");
        Button button = (Button) actionEvent.getSource();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("reviwHistoryForm.fxml"));


        AnchorPane anchorPane4 = fxmlLoader.load();
         ReviwHistoryForm reviwHistoryForm = fxmlLoader.getController();

        reviwHistoryForm.setUser(user);
        reviwHistoryForm.ShowHistory(user);
        anchorPane4.setPrefSize(588, 590);


      borderPane.setCenter(anchorPane4);
    }


}
