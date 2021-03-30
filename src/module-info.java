module GregorKlijentUI {
    requires javafx.web;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.sql;

    opens aleksic.Kontroleri.Osluskivaci to javafx.graphics, javafx.fxml;
    opens aleksic.Kontroleri to javafx.graphics, javafx.fxml;
    opens aleksic.DomenskiObjekat to javafx.graphics, javafx.fxml;
    opens aleksic.Komponente to javafx.graphics, javafx.fxml;
    opens aleksic.TransferObjekat to javafx.graphics, javafx.fxml;
    opens aleksic.Pogledi to javafx.graphics, javafx.fxml;
    opens aleksic to javafx.graphics, javafx.fxml;
}