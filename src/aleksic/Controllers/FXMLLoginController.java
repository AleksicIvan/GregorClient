package aleksic.Controllers;

import aleksic.Views.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class FXMLLoginController extends OsnovniFXMLKontroler {
    @FXML
    public TextField korisnickoIme;

    @FXML
    public PasswordField korisnickaSifra;

    @FXML
    public Button login;

    @FXML
    public Button cancel;

    @FXML
    public Label loginError;

    public FXMLLoginController(ViewManager viewManager, String nazivFxml) {
        super(viewManager, nazivFxml);
    }

    @FXML
    public void initialize() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, IOException {
        new KontrolerGUILogin(this, viewManager);
    }

    public Label getLoginError() {
        return loginError;
    }

    public void setLoginError(Label loginError) {
        this.loginError = loginError;
    }
}
