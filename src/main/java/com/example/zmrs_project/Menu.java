package com.example.zmrs_project;

import javafx.scene.image.Image;

import java.io.File;

public class Menu {

    private static Image desserts;
    private static Image juiceHotDrinks;
    private static Image mainplates;

    public static String path ="D:\\Java\\Project\\ZMRS_System\\src\\main\\resources\\com\\example\\zmrs_project\\Menu\\";

public  Menu (){

}

public Menu(Image desserts, Image juiceHotDrinks, Image mainplates) {

        this.desserts = desserts;
        this.juiceHotDrinks = juiceHotDrinks;
        this.mainplates = mainplates;
    }

    public Image getDesserts() {
        return desserts;
    }

    public Image getJuiceHotDrinks() {
        return juiceHotDrinks;
    }

    public Image getMainplates() {
        return mainplates;
    }

    public static void setMenuImagesFromFolder(Restaurant restaurant) {

    desserts = new Image(path+restaurant.getRestaurantName()+"\\"+"1"+".jpg");
    juiceHotDrinks   = new Image(path+restaurant.getRestaurantName()+"\\"+"2"+".jpg");
    mainplates   = new Image(path+restaurant.getRestaurantName()+"\\"+"3"+".jpg");
    }
}
