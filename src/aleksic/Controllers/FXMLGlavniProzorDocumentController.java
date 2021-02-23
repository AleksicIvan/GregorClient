package aleksic.Controllers;

import aleksic.Models.Karta;
import aleksic.Views.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

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

    @FXML
    private Label zivotIgracaGore;

    @FXML
    private Label zivotIgracaDole;

    @FXML
    private Button zavrsiPotez;

    public Label getZivotIgracaGore() {
        return zivotIgracaGore;
    }

    public void setZivotIgracaGore(Label zivotIgracaGore) {
        this.zivotIgracaGore = zivotIgracaGore;
    }

    public Label getZivotIgracaDole() {
        return zivotIgracaDole;
    }

    public void setZivotIgracaDole(Label zivotIgracaDole) {
        this.zivotIgracaDole = zivotIgracaDole;
    }

    public void postaviTextZivotIgracaGore (String zivot) {
        this.zivotIgracaGore.setText(zivot);
    }

    public void postaviTextZivotIgracaDole (String zivot) {
        this.zivotIgracaDole.setText(zivot);
    }

    public void postaviTextZivotIgracaDole () {}

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

    public void resetujNapadGornjegIgraca () {
        gornjIgracRedNapad.getChildren().clear();
    }

    public void resetujNapadDonjegIgraca () {
        donjIgracRedNapad.getChildren().clear();
    }

    public void resetujRukuGornjegIgraca () {
        gornjiIgracRuka.getChildren().clear();
    }

    public void resetujRukuDonjegIgraca () {
        donjiIgracRuka.getChildren().clear();
    }

    public void resetRedZlatnikaGornjiIgrac () {
        gornjIgracRedZlatnika.getChildren().clear();
    }

    public void resetRedZlatnikaDonjiIgrac () {
        donjIgracRedZlatnika.getChildren().clear();
    }

    public void resetRedVitezovaGornjiIgrac () {
        gornjIgracRedVitezova.getChildren().clear();
    }

    public void resetRedvitezovaDonjiIgrac () {
        donjIgracRedVitezova.getChildren().clear();
    }

    public void dodajZlatnikDonjiIgrac(Node karta) {
        donjIgracRedZlatnika.getChildren().add(karta);
    }

    public void dodajZlatnikGornjiIgrac(Node karta) {
        gornjIgracRedZlatnika.getChildren().add(karta);
    }

    public void dodajVitezaDonjiIgrac(Node karta) {
        donjIgracRedVitezova.getChildren().add(karta);
    }

    public void dodajVitezaGornjiIgrac(Node karta) {
        gornjIgracRedVitezova.getChildren().add(karta);
    }

    public void dodajVitezaUNapadDonjiIgrac(Node karta) {
        donjIgracRedNapad.getChildren().add(karta);
    }

    public void dodajVitezaUNapadGornjiIgrac(Node karta) {
        gornjIgracRedNapad.getChildren().add(karta);
    }

    public Button getZavrsiPotez() {
        return zavrsiPotez;
    }

    public void setZavrsiPotez(Button zavrsiPotez) {
        this.zavrsiPotez = zavrsiPotez;
    }

    public VBox getGornjIgracMeni() {
        return gornjIgracMeni;
    }

    public void setGornjIgracMeni(VBox gornjIgracMeni) {
        this.gornjIgracMeni = gornjIgracMeni;
    }

    public Label getGornjIgracKorisnickoIme() {
        return gornjIgracKorisnickoIme;
    }

    public void setGornjIgracKorisnickoIme(Label gornjIgracKorisnickoIme) {
        this.gornjIgracKorisnickoIme = gornjIgracKorisnickoIme;
    }

    public VBox getDonjIgracMeni() {
        return donjIgracMeni;
    }

    public void setDonjIgracMeni(VBox donjIgracMeni) {
        this.donjIgracMeni = donjIgracMeni;
    }

    public Label getDonjIgracKorisnickoIme() {
        return donjIgracKorisnickoIme;
    }

    public void setDonjIgracKorisnickoIme(Label donjIgracKorisnickoIme) {
        this.donjIgracKorisnickoIme = donjIgracKorisnickoIme;
    }

    public HBox getGornjIgracRedZlatnika() {
        return gornjIgracRedZlatnika;
    }

    public void setGornjIgracRedZlatnika(HBox gornjIgracRedZlatnika) {
        this.gornjIgracRedZlatnika = gornjIgracRedZlatnika;
    }

    public HBox getGornjIgracRedVitezova() {
        return gornjIgracRedVitezova;
    }

    public void setGornjIgracRedVitezova(HBox gornjIgracRedVitezova) {
        this.gornjIgracRedVitezova = gornjIgracRedVitezova;
    }

    public HBox getGornjIgracRedNapad() {
        return gornjIgracRedNapad;
    }

    public void setGornjIgracRedNapad(HBox gornjIgracRedNapad) {
        this.gornjIgracRedNapad = gornjIgracRedNapad;
    }

    public HBox getDonjIgracRedNapad() {
        return donjIgracRedNapad;
    }

    public void setDonjIgracRedNapad(HBox donjIgracRedNapad) {
        this.donjIgracRedNapad = donjIgracRedNapad;
    }

    public HBox getDonjIgracRedVitezova() {
        return donjIgracRedVitezova;
    }

    public void setDonjIgracRedVitezova(HBox donjIgracRedVitezova) {
        this.donjIgracRedVitezova = donjIgracRedVitezova;
    }

    public HBox getDonjIgracRedZlatnika() {
        return donjIgracRedZlatnika;
    }

    public void setDonjIgracRedZlatnika(HBox donjIgracRedZlatnika) {
        this.donjIgracRedZlatnika = donjIgracRedZlatnika;
    }

    public HBox getGornjiIgracRuka() {
        return gornjiIgracRuka;
    }

    public void setGornjiIgracRuka(HBox gornjiIgracRuka) {
        this.gornjiIgracRuka = gornjiIgracRuka;
    }

    public HBox getDonjiIgracRuka() {
        return donjiIgracRuka;
    }

    public void setDonjiIgracRuka(HBox donjiIgracRuka) {
        this.donjiIgracRuka = donjiIgracRuka;
    }

    public void oduzmiZlatnikIzRukeDonjegIgraca (Node zlatnik) {
        this.donjiIgracRuka.getChildren().remove(zlatnik);
    }

    public void oduzmiVitezaIzRukeDonjegIgraca (Node vitez) {
        this.donjiIgracRuka.getChildren().remove(vitez);
    }

    public void oduzmiVitezaIzRedaVitezovaDonjegIgraca (Node vitez) {
        this.donjiIgracRuka.getChildren().remove(vitez);
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