module ni.edu.uam.cajaconcurrencia {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.cajaconcurrencia to javafx.fxml;
    exports ni.edu.uam.cajaconcurrencia;
}