module GregorKlijentUI {
    requires javafx.web;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.sql;

    opens aleksic.Controllers.Osluskivaci to javafx.graphics, javafx.fxml;
    opens aleksic.Controllers to javafx.graphics, javafx.fxml;
    opens aleksic.Models to javafx.graphics, javafx.fxml;
    opens aleksic.Komponente to javafx.graphics, javafx.fxml;
    opens aleksic.TransferObjekat to javafx.graphics, javafx.fxml;
    opens aleksic.Views to javafx.graphics, javafx.fxml;
    opens aleksic to javafx.graphics, javafx.fxml;
}