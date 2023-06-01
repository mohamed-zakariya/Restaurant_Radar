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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import org.controlsfx.glyphfont.FontAwesome;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReviwHistoryForm {



    @FXML
    private VBox reviewContainer;


    private User user;
    private ArrayList<Review> reviews;

    public void setUser(User user){
        this.user = user;
    }




    public void ShowHistory(User user) throws SQLException, ClassNotFoundException {


        try {
            reviews=user.getReviews();
            for (int i = 0; i <  reviews.size(); i++) {

                Label rateLabel = new Label("Rate: " + reviews.get(i).getRatee());
                rateLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #003366;");
                Label restaurantLabel = new Label("Restaurant: " + reviews.get(i).getRestaurant().getRestaurantName());
                restaurantLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: #666666;");
                Label commentLabel = new Label("comments: " + reviews.get(i).getComment());
                commentLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: #333333;");



                VBox reviewCard = new VBox(rateLabel, restaurantLabel,commentLabel);
                reviewCard.getStyleClass().add("review-card");
                reviewCard.setStyle("-fx-background-color: #f2f2f2; -fx-padding: 10px;");
                reviewCard.setSpacing(8);
                reviewCard.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));

                reviewContainer.getChildren().add(reviewCard);

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }







}
