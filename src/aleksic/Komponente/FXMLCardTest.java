package aleksic.Komponente;

import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLCardTest implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            CardComponent knct = new CardComponent();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
