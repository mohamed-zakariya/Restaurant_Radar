package com.example.zmrs_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import org.controlsfx.glyphfont.FontAwesome;

import java.io.IOException;
import java.sql.SQLException;

public class RestaurantView {

    @FXML
    Rating rating;
    @FXML
    Label label1, label2, label3, label4;
    @FXML
    ImageView imageView;
    @FXML
    public Parent root;
    private Restaurant restaurant;
    private User user;
    private Review review;

    public void setRestaurant(Restaurant restaurant){
        this.restaurant = restaurant;
    }
    public void setUser(User user){ this.user = user;}

    public void showData() throws SQLException, ClassNotFoundException {
        review = new Review(user, restaurant);
        rating.setRating(review.getRate(user, restaurant));
        label1.setText(label1.getText() + restaurant.getRestaurantName());
        label3.setText(Restaurant.getReviews(restaurant).size()+" reviews");
        if(Restaurant.getReviews(restaurant).size() == 0){
            label2.setText(0+"");
        }else{
            label2.setText(String.format("%.1f",restaurant.getAvgReviews()));
        }

        imageView.setImage(new Image("D:\\Java\\Project\\ZMRS_System\\src\\main\\resources\\com\\example\\zmrs_project\\Restaurants\\"+restaurant.getRestaurantName()+".jpg"));

    }
    @FXML
    public void doRate(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        boolean check = review.setRate(user, rating.getRating(), restaurant);
        double value = review.getRate(user, restaurant);

        if(check == true){
            review.setRate(user, rating.getRating(), restaurant);
            rating.setRating(value);
            label2.setText(String.format("%.1f",restaurant.getAvgReviews()));
            label3.setText(Restaurant.getReviews(restaurant).size()+" reviews");
        }
        else{
            label4.setText("you're already reviewed!");
        }
    }

    @FXML
    public void returnBack(ActionEvent actionEvent) throws IOException {
        Node n = (Node) actionEvent.getSource();
        Stage closeWindow = (Stage) n.getScene().getWindow();
        closeWindow.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("userForm.fxml"));
        root = fxmlLoader.load();

        UserForm userForm = fxmlLoader.getController();
        userForm.setUser(user);
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
