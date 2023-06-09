package com.example.zmrs_project;

import com.example.zmrs_project.classes.Review;
import com.example.zmrs_project.classes.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

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

               //Label commentLabel = new Label("comments: " + reviews.get(i).getComment());
                //commentLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: #333333;");

                Label commentLabel = new Label("comments:");
                commentLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: #333333;");
                Label commentText = new Label(String.join("\n", reviews.get(i).getComment()));
                commentText.setStyle("-fx-font-size: 14px; -fx-text-fill: #333333; -fx-background-color: #D4E2E8; -fx-padding: 10px;");






                VBox reviewCard = new VBox(rateLabel, restaurantLabel,commentLabel,commentText);
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
