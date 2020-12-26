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
    private Label igracNaPotezuGore;

    @FXML
    private Label fazaPotezaGore;

    @FXML
    private Label gornjIgracKorisnickoIme;

    @FXML
    private VBox donjIgracMeni;

    @FXML
    private Label igracNaPotezuDole;

    @FXML
    private Label fazaPotezaDole;

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

    public Label getIgracNaPotezuGore() {
        return igracNaPotezuGore;
    }

    public void setIgracNaPotezuGore(Label igracNaPotezuGore) {
        this.igracNaPotezuGore = igracNaPotezuGore;
    }

    public Label getIgracNaPotezuDole() {
        return igracNaPotezuDole;
    }

    public void setIgracNaPotezuDole(Label igracNaPotezuDole) {
        this.igracNaPotezuDole = igracNaPotezuDole;
    }

    public Label getFazaPotezaDole() {
        return fazaPotezaDole;
    }

    public void setFazaPotezaDole(Label fazaPotezaDole) {
        this.fazaPotezaDole = fazaPotezaDole;
    }

    public Label getFazaPotezaGore() {
        return fazaPotezaGore;
    }

    public void setFazaPotezaGore(Label fazaPotezaGore) {
        this.fazaPotezaGore = fazaPotezaGore;
    }

    public void postaviImeGornjegIgraca (String imeIgraca) {
        gornjIgracKorisnickoIme.setText(imeIgraca);
    }

    public void postaviImeDonjegIgraca (String imeIgraca) {
        donjIgracKorisnickoIme.setText(imeIgraca);
    }

    public void postaviIzvuceneKarteDonjiIgrac(Node karta) {
        donjiIgracRuka.getChildren().add(karta);
    }

    public void postaviIzvuceneKarteGornjiIgrac(Node karta) {
        gornjiIgracRuka.getChildren().add(karta);
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