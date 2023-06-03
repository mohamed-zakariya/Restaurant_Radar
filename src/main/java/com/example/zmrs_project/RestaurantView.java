package com.example.zmrs_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import org.controlsfx.glyphfont.FontAwesome;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class RestaurantView {

    @FXML
    Rating rating;
    @FXML
    Label label1, label2, label3, label4, label5;
    @FXML
    TextField textField;
    @FXML
    TextField  textField1;
    @FXML
    Label labelComment;
     @FXML
    FlowPane flowPane1;
    @FXML
    ImageView imageView;
    @FXML
    public Parent root;
    private Restaurant restaurant;
    private User user;
    private Review review;
    private LocalDateTime time;

    public void setRestaurant(Restaurant restaurant){
        this.restaurant = restaurant;
    }
    public void setUser(User user){ this.user = user;}

    public void showData() throws SQLException, ClassNotFoundException, IOException {
        label1.setText("");
        label2.setText("");
        label3.setText("");
        label5.setText("");
        flowPane1.getChildren().clear();


        review = new Review(user, restaurant);
        rating.setRating(review.getRate(user, restaurant));
        label1.setText(label1.getText() + restaurant.getRestaurantName());
        label3.setText(Restaurant.getReviews(restaurant).size()+" reviews");
        label5.setText(label5.getText()+restaurant.getLocationsOfRestaurant(new Restaurant(restaurant.getRestaurantName())));
        flowPane1.setPadding(new Insets(5,5,5,5));
        if(Restaurant.getReviews(restaurant).size() == 0){
            label2.setText(0+"");
        }else{
            label2.setText(String.format("%.1f",restaurant.getAvgReviews()));
            for(int i = 0; i < Restaurant.getReviews(restaurant).size(); i++){
                Review review1 = Restaurant.getReviews(restaurant).get(i);
                Label label = new Label();

                if(review1.getComment().equals(new ArrayList<>()))
                    continue;


                StringBuilder commentText = new StringBuilder();
                for (String comment : review1.getComment()) {
                    commentText.append(comment).append(System.lineSeparator());
                }


                if (user.getUsername().equals(review1.getUser().getUsername())){
                    label.setText("You commented: \n" + commentText.toString());
                    }
                else{
                    label.setText(review1.getUser().getUsername() + " commented: \n" + commentText.toString());
                }
                /*label.setText(label.getText()+"\t\t\t\t\t\t\t\t\t\t");*/
                label.setPrefWidth(250);
                label.setTextFill(Color.BLACK);
                label.setStyle("-fx-background-color: #FACDAB; -fx-background-radius: 5;");


                flowPane1.setVgap(10);
                flowPane1.getChildren().addAll(label);
                flowPane1.setVgap(10);
            }
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

    @FXML
    public void OpenMenu(ActionEvent actionEvent) throws  IOException{
        Node n = (Node) actionEvent.getSource();
        Stage closeWindow = (Stage) n.getScene().getWindow();
        closeWindow.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MenuForm.fxml"));
        root = fxmlLoader.load();

        MenuForm menuForm = fxmlLoader.getController();
        menuForm.setRestaurant(restaurant);
        menuForm.menuSlide();
        menuForm.setUser(user);

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void addComment(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        String commentt = textField1.getText();
        if (commentt.isEmpty()) {
            labelComment.setText("pls enter your comment");}
        else {
        time =LocalDateTime.now();
        review.addcomment(user,restaurant,commentt,time);
        showData();

        labelComment.setText("The comment added succsecfully");

        }
    }

}
