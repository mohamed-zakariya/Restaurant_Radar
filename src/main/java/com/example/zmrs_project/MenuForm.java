package com.example.zmrs_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;

import java.net.URL;
import java.sql.SQLException;
public class MenuForm {

    @FXML
    ImageView ImageView1;
    @FXML
    ImageView ImageView2;
    @FXML
    ImageView ImageView3;
    @FXML
    private Slider slider;
    public Parent root;
    int i = 0;

    private Restaurant restaurant;
    private User user;

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    public void setUser(User user){
        this.user = user;
    }

    public void menuSlide() {
        Menu menu = new Menu();
        Menu.setMenuImagesFromFolder(restaurant);
        ImageView1.setImage(menu.getMainplates());
        centerImage(ImageView1);
    }
 /*    slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int imageIndex = newValue.intValue();
            updateImageViewVisibility(imageIndex);
        });

        ImageView1.setImage(menu.getDesserts());
        ImageView2.setImage(menu.getJuiceHotDrinks());
        ImageView3.setImage(menu.getMainplates());

        centerImage(ImageView1);
        centerImage(ImageView2);
        centerImage(ImageView3);


    private void updateImageViewVisibility(int imageIndex) {
       ImageView1.setVisible(imageIndex == 0);
       ImageView2.setVisible(imageIndex == 1);
       ImageView3.setVisible(imageIndex == 2);
    }*/
    private void centerImage(ImageView imageView) {
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(500);
        imageView.setFitHeight(400);
        imageView.setSmooth(true);
        imageView.setCache(true);
        imageView.setLayoutX(67);
        imageView.setLayoutY(30);
    }

    @FXML
    public void returnBack(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        Node n = (Node) actionEvent.getSource();
        Stage closeWindow = (Stage) n.getScene().getWindow();
        closeWindow.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("RestaurantView.fxml"));
        root = fxmlLoader.load();

        RestaurantView restaurantView = fxmlLoader.getController();
        restaurantView.setUser(user);
        restaurantView.setRestaurant(restaurant);
        restaurantView.showData();

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void change(ActionEvent actionEvent){
        Menu menu = new Menu();
        Menu.setMenuImagesFromFolder(restaurant);

        Button button = (Button) actionEvent.getSource();
        String name = button.getText();


        if(name.equals("right")){
            i++;
        }
        if(name.equals("left")){
            i--;
        }
        if(i==0){
            i=3;
        }
        if(i==4){
            i=1;
        }
        if(i == 1){
            ImageView1.setImage(menu.getDesserts());
        }
        if(i == 2){
            ImageView1.setImage(menu.getJuiceHotDrinks());
        }
        if(i == 3){
            ImageView1.setImage(menu.getMainplates());
        }
        centerImage(ImageView1);
    }
}
