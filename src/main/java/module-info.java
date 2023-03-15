module com.example.incisivelabsbuild {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires commons.math3;


    opens com.example.incisivelabsbuild to javafx.fxml;
    exports com.example.incisivelabsbuild;
}