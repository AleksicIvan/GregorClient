package aleksic.Controllers;

import aleksic.Views.CardTestComponent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLCardTest implements Initializable {
    @FXML
    Label kukiLabela;
    @FXML
    private Button cardClick;

//    public FXMLCardTestController(Socket soketK, ViewManager viewManager, String nazivFxml) {
//        super(soketK, viewManager, nazivFxml);
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            CardTestComponent knct = new CardTestComponent();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public String poruka(String poruka) {
//        return null;
//    }
}
