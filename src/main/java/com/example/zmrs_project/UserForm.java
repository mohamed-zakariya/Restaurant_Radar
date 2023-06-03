package com.example.zmrs_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Flow;
import java.util.concurrent.atomic.AtomicReference;

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
    VBox vboxTemp;

    public UserForm(){

    }
    @FXML
    public void setUser(User user){
        this.user = user;

        label2.setText(user.getUsername());
    }
    @FXML
    public void search() throws SQLException, ClassNotFoundException, IOException {
        flowPane1.getChildren().clear();
        flowPane1.setPadding(new Insets(0,0,0,0));
        if(textfield1.getText().equals("")){
            imageView1.setImage(new Image("E:\\6 term\\OOP\\project_zmrs_3\\ZMRS_System\\src\\main\\resources\\com\\example\\zmrs_project\\images\\1.jpg"));
            flowPane1.getChildren().add(imageView1);
            flowPane1.setPadding(new Insets(0,175,0,0));

        }
        else{
            Location location = Location.getResturantsLocation(textfield1.getText());

            ArrayList<Restaurant> restaurants = location.getResturants();

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
    }


    @FXML
    public void  OpenCusineForm(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {

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
