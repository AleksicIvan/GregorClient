package aleksic.Controllers;

import aleksic.Servis.Igra;
import aleksic.Views.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.Socket;

public class FXMLLoginController extends BaseController {
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

    public FXMLLoginController(Socket soketK, ViewManager viewManager, String nazivFxml) {
        super(soketK, viewManager, nazivFxml);
    }

    @FXML
    public void initialize() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, IOException {
        KontrolerGUILogin kngui = new KontrolerGUILogin(this, viewManager);
    }

    public Label getLoginError() {
        return loginError;
    }

    public void setLoginError(Label loginError) {
        this.loginError = loginError;
    }

    @Override
    public String poruka(String poruka) {
        System.out.println("Poruka iz LoginKontrolera " + poruka);
        return "Poruka iz LoginKontrolera " + poruka;
    }
}
