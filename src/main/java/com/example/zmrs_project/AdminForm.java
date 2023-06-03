package com.example.zmrs_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminForm {




    @FXML
    private TableView<Restaurant> tableView;
    @FXML
    private TableColumn<Restaurant, String> col1;

    @FXML
    private TableColumn<Restaurant, String> col2;

    @FXML
    private TableColumn<Restaurant, String> col3;
    @FXML
    Label label1;
    @FXML
    public Parent root;
    private Admin admin;

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }


    public void showData() throws SQLException, ClassNotFoundException {

        label1.setText(admin.getUsername());
        col1.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("restaurantName"));
        col2.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("cusine"));
        col3.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("location"));


        ArrayList<Restaurant> restaurants = admin.getallrestaurant();
        ObservableList<Restaurant> restaurantsMain = FXCollections.observableArrayList();
        for (int i = 0; i < restaurants.size(); i++){
            for (int j = 0; j < (restaurants.get(i).getLocations()).size(); j++){
                Restaurant restaurant = new Restaurant(restaurants.get(i).getRestaurantName(),
                        restaurants.get(i).getLocations().get(j),
                        restaurants.get(i).getCusine());
                restaurantsMain.add(restaurant);
            }
        }
        tableView.setItems(restaurantsMain);

    }




    public void ADD(ActionEvent actionEvent) throws IOException {
        Node n = (Node) actionEvent.getSource();
        Stage closeWindow = (Stage) n.getScene().getWindow();
        closeWindow.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addRestaurantForm.fxml"));
        root = fxmlLoader.load();
        AddResturanetForm addResturanetForm= fxmlLoader.getController();
        addResturanetForm.setAdmin(admin);

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void DELETE() throws SQLException, ClassNotFoundException {


            Restaurant selectedRestaurant = tableView.getSelectionModel().getSelectedItem();

            if (selectedRestaurant != null) {
                admin.RemoveResturant(selectedRestaurant.getRestaurantName());

                tableView.getItems().remove(selectedRestaurant);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Restaurant deleted successfully!");
                alert.showAndWait();
            }
        }



    public void UPDATAE(ActionEvent actionEvent) throws IOException {
        Node n = (Node) actionEvent.getSource();
        Stage closeWindow = (Stage) n.getScene().getWindow();
        closeWindow.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("UpdateResturnatForm.fxml"));
        root = fxmlLoader.load();
        UpdateResturnatForm updateResturnatForm= fxmlLoader.getController();



        updateResturnatForm.setAdmin(admin);
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void signOut(ActionEvent actionEvent) throws IOException {
        Node n = (Node) actionEvent.getSource();
        Stage closeWindow = (Stage) n.getScene().getWindow();
        closeWindow.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginform.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }


}
