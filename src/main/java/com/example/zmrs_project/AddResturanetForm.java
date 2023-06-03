package com.example.zmrs_project;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;



public class AddResturanetForm {
    private ArrayList<String> locations;
    @FXML
    private TextField NameText;
    @FXML
    private Label labeltocheck;
    @FXML
    private TextField LocationText;
    @FXML
    private TextField CusineText;
    @FXML
    private Button ButtonText;
    @FXML
    private TextField phoneText;
    @FXML
    private VBox imageContainer;
    @FXML
    private Label checckName;
    @FXML
    private Label checkLocation;
    @FXML
    private Label checkCusine;
    @FXML
    private Label checkPhone, label1;




    private Restaurant restaurant;

    private Admin admin;

    private boolean isPhotoDragged = false;
    public Parent root;

    @FXML
    public void setAdmin(Admin admin) {
        this.admin = admin;
        label1.setText(admin.getUsername());
    }


    @FXML
    public void onClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        String name = NameText.getText();
        String location = LocationText.getText();
        String cuisine = CusineText.getText();
        String phone = phoneText.getText();

        boolean isNameEmpty = name.isEmpty();
        boolean isLocationEmpty = location.isEmpty();
        boolean isCuisineEmpty = cuisine.isEmpty();
        boolean isPhoneEmpty = phone.isEmpty();


        checckName.setText(isNameEmpty ? "Name cannot be empty" : "");
        checkLocation.setText(isLocationEmpty ? "Location cannot be empty" : "");
        checkCusine.setText(isCuisineEmpty ? "Cuisine cannot be empty" : "");
        checkPhone.setText(isPhoneEmpty ? "Phone cannot be empty" : "");


        if (isNameEmpty || isLocationEmpty || isCuisineEmpty || isPhoneEmpty) {
            return;
        }

        locations = new ArrayList<String>();
        locations.add(location);
        restaurant = new Restaurant(name, locations, cuisine, phone);

        if (!isPhotoDragged) {
            labeltocheck.setText("Please drag and drop a photo");
            return;
        }

        admin.AddResturant(restaurant);
        ButtonText.setDisable(true);
        labeltocheck.setText("");
        NameText.clear();
        LocationText.clear();
        CusineText.clear();
        phoneText.clear();
    }


    @FXML
    public void onDragOver(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        if (dragboard.hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY);
            isPhotoDragged = true;
        }
        event.consume();
    }

    @FXML
    public void onDragDropped(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        boolean success = false;
        if (dragboard.hasFiles()) {
            List<File> files = dragboard.getFiles();
            for (File file : files) {
                String sourcePath = file.getAbsolutePath();
                String fileName = file.getName();


                String restaurantName = NameText.getText();
                String destinationPath = "E:\\6 term\\OOP\\project_zmrs_3\\ZMRS_System\\src\\main\\resources\\com\\example\\zmrs_project\\Restaurants\\" + restaurantName + ".jpg";

                try {
                    Files.copy(file.toPath(), Paths.get(destinationPath), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
            success = true;
        }
        if (success) {
            labeltocheck.setText("Picture added");
            ButtonText.setDisable(false);
        }
    }
    public void returnBack(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        Node n = (Node) actionEvent.getSource();
        Stage closeWindow = (Stage) n.getScene().getWindow();
        closeWindow.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminForm.fxml"));
        root = fxmlLoader.load();

        AdminForm adminForm = fxmlLoader.getController();
        adminForm.setAdmin(admin);
        adminForm.showData();

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Hello!");
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
