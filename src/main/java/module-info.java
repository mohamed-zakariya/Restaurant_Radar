module com.example.zmrs_project {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.zmrs_project to javafx.fxml;
    exports com.example.zmrs_project;
}