package aleksic.Komponente;

import aleksic.Kontroleri.KontrolerGUIGlavniprozor;
import aleksic.DomenskiObjekat.Igrac;
import aleksic.DomenskiObjekat.Karta;
import aleksic.TransferObjekat.TransferObjekatIgra;
import aleksic.Pogledi.ViewManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CardComponent extends VBox {
    FXMLCard fxmlCardTest;
    ViewManager vm;
    Karta trenutnaKarta;
    KontrolerGUIGlavniprozor guiKontroler;
    Igrac gornjiIgrac;
    Igrac donjiIgrac;
    String igracNaPotezu; // prvi | drugi

    @FXML
    VBox kartaVBox;

    @FXML
    Label tipKarte;

    @FXML
    Label napad;

    @FXML
    Label odbrana;

    public CardComponent() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "FXMLCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void setGuiKontroler (KontrolerGUIGlavniprozor guiKontroler) {
        this.guiKontroler = guiKontroler;
    }

    public void setIgracNaPotezu(String igracNaPotezu) {
        this.igracNaPotezu = igracNaPotezu;
    }

    public Label getNapad() {
        return napad;
    }

    public Label getOdbrana() {
        return odbrana;
    }

    public Label getTipKarte() {
        return tipKarte;
    }

    public void cardTestOnClick () {
        switch (trenutnaKarta.vratiTipKarte()) {
            case ZLATNIK:
                new ObradiKlikNaKartuZlatnik(this);
                break;
            case VITEZ:
                new ObradiKlikNaKartuVitez(this);
                break;
            default:
                break;
        }
    }

    public void prikazikartu(Karta karta, TransferObjekatIgra toi) {
        trenutnaKarta = karta;
        Igrac ulogovaniIgrac = toi.igr;
        Igrac igracNaPotezu = toi.igracNaPotezu;

//        System.out.println("ime prvog igraca je: " + ulogovaniIgrac.vratiKorisnickoIme());
//        System.out.println("ime igracNaPotezu je " + igracNaPotezu.vratiKorisnickoIme());
//        System.out.println("is equal " + ulogovaniIgrac.vratiKorisnickoIme().equals(igracNaPotezu.vratiKorisnickoIme()));

        if (karta.isPokaziKartu() == false) {
            this.getStyleClass().add("karta-pozadina");
            karta.setIsDisabled(true);
            kartaVBox.setDisable(true);
            return;
        }

        switch (karta.vratiTipKarte()) {
            case VITEZ:
                this.getStyleClass().removeAll();
                this.getStyleClass().add("karta-plava");
                this.getTipKarte().setText("V");
                this.getNapad().setText(karta.getNapad().toString());
                this.getOdbrana().setText(karta.getOdbrana().toString());
                if (karta.isIskoriscena()) {
                    this.getStyleClass().add("iskoriscena-karta");
                }
                if (ulogovaniIgrac.vratiKorisnickoIme().equals(igracNaPotezu.vratiKorisnickoIme())) {
                    break;
                } else {
//                    karta.setIsDisabled(true);
//                    kartaVBox.setDisable(true);
                    break;
                }
            case ZLATNIK:
                this.getStyleClass().removeAll();
                this.getStyleClass().add("karta-zlatna");
                this.getTipKarte().setText("Z");
                this.getNapad().setText("");
                this.getOdbrana().setText("");
                if (karta.isIskoriscena()) {
                    this.getStyleClass().add("iskoriscena-karta");
                }
                if (ulogovaniIgrac.vratiKorisnickoIme().equals(igracNaPotezu.vratiKorisnickoIme())) {
                    break;
                } else {
//                    karta.setIsDisabled(true);
//                    kartaVBox.setDisable(true);
                    break;
                }
            default:
                this.getStyleClass().removeAll();
                break;
        }
    }

    public void setGornjiIgrac(Igrac igrac) {
        gornjiIgrac = igrac;
    }

    public void setDonjiIgrac(Igrac igrac) {
        donjiIgrac = igrac;
    }
}
