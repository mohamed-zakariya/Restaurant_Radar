module com.example.zmrs_project {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires junit;
    

    opens com.example.zmrs_project to javafx.fxml;
    exports com.example.zmrs_project;
    exports com.example.zmrs_project.classes;
    opens com.example.zmrs_project.classes to javafx.fxml;
    exports com.example.zmrs_project.test;
    opens com.example.zmrs_project.test to javafx.fxml;
}