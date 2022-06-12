module projet.echecmartien {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;
    requires com.google.gson;
    requires jdk.jfr;
    requires java.naming;

    opens projet.echecmartien to javafx.fxml;
    exports projet.echecmartien;
}