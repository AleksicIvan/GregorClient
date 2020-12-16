package aleksic.Controllers;

import aleksic.Views.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.Socket;

public class FXMLGlavniProzorDocumentController extends BaseController {

    @FXML
    private VBox gornjIgracMeni;

    @FXML
    private Label gornjIgracKorisnickoIme;

    @FXML
    private VBox donjIgracMeni;

    @FXML
    private Label donjIgracKorisnickoIme;

    @FXML
    private HBox gornjIgracRedZlatnika;

    @FXML
    private HBox gornjIgracRedVitezova;

    @FXML
    private HBox gornjIgracRedNapad;

    @FXML
    private HBox donjIgracRedNapad;

    @FXML
    private HBox donjIgracRedVitezova;

    @FXML
    private HBox donjIgracRedZlatnika;

    @FXML
    private HBox gornjiIgracRuka;

    @FXML
    private HBox donjiIgracRuka;

    public void postaviImeGornjegIgraca (String imeIgraca) {
        gornjIgracKorisnickoIme.setText(imeIgraca);
    }

    public void postaviImeDonjegIgraca (String imeIgraca) {
        donjIgracKorisnickoIme.setText(imeIgraca);
    }

    public void dodajKartuDonjiIgracruka(Node karta) {
        donjiIgracRuka.getChildren().add(karta);
    }

    public FXMLGlavniProzorDocumentController(Socket soketK, ViewManager viewManager, String nazivFxml) {
        super(soketK, viewManager, nazivFxml);
    }

    @FXML
    public void initialize() throws IllegalArgumentException, IOException {
        KontrolerGUIGlavniprozor kngui = new KontrolerGUIGlavniprozor(this, viewManager);

    }

    @Override
    public String poruka(String poruka) {
        System.out.println("Poruka iz kontrolera glavnog prozora " + poruka);
        return poruka;
    }
}