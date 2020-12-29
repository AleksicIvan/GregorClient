package aleksic.Komponente;

import aleksic.Controllers.KontrolerGUIGlavniprozor;
import aleksic.Controllers.OpstoGUIKontroler;
import aleksic.Models.Igrac;
import aleksic.Models.Karta;
import aleksic.Servis.Faza;
import aleksic.TransferObjekat.TransferObjekatIgrac;
import aleksic.Views.ViewManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CardTestComponent extends VBox {
    FXMLCardTest fxmlCardTest;
    ViewManager vm;
    TransferObjekatIgrac toi;
    Karta trenutnaKarta;
    KontrolerGUIGlavniprozor guiKontroler;
    Igrac gornjiIgrac;
    Igrac donjiIgrac;
    String pozicijaIgraca;
    boolean pokaziKartu;

    @FXML
    VBox kartaVBox;

    @FXML
    Label tipKarte;

    @FXML
    Label napad;

    @FXML
    Label odbrana;

    public CardTestComponent() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "FXMLCardTest.fxml"));
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


    public Label getNapad() {
        return napad;
    }

    public void setNapad(Label napad) {
        this.napad = napad;
    }

    public Label getOdbrana() {
        return odbrana;
    }

    public void setOdbrana(Label odbrana) {
        this.odbrana = odbrana;
    }

    public Label getTipKarte() {
        return tipKarte;
    }

    public void setTipKarte(Label tipKarte) {
        this.tipKarte = tipKarte;
    }

    public ViewManager getVm() {
        return vm;
    }

    public void setVm(ViewManager vm) {
        this.vm = vm;
    }

    public void cardTestOnClick () throws IOException {
        switch (trenutnaKarta.vratiTipKarte()) {
            case ZLATNIK:
                guiKontroler.getVm().getToi().igra.postaviFazuPoteza(Faza.ODIGRAJ_VITEZA);
                guiKontroler.getVm().getToi().igra.odigrajPotezIzbaciZlatnik(trenutnaKarta);
                guiKontroler.getFxml().dodajZlatnikDonjiIgrac(this);
                guiKontroler.setIgracNaPotezuIfazaPotezaIndikatore(donjiIgrac, gornjiIgrac, Faza.ODIGRAJ_VITEZA);
//                guiKontroler.getVm().pozivSO("odigrajZlatnika");
                break;
            case VITEZ:
                if (guiKontroler.getVm().getToi().igra.vratiFazuPoteza().equals(Faza.ODIGRAJ_VITEZA)) {
                    guiKontroler.getVm().getToi().igra.postaviFazuPoteza(Faza.NAPAD);
                    guiKontroler.setIgracNaPotezuIfazaPotezaIndikatore(donjiIgrac, gornjiIgrac, Faza.NAPAD);
                    guiKontroler.getVm().getToi().igra.odigrajPotezIzbaciViteza(trenutnaKarta);
                    guiKontroler.getFxml().dodajVitezaDonjiIgrac(this);
//                    guiKontroler.getVm().pozivSO("odigrajViteza");
                } else {
                    guiKontroler.getFxml().dodajVitezaUNapadDonjiIgrac(this);
//                    guiKontroler.getVm().getToi().igra.postaviFazuPoteza(Faza.NAPAD);
                    guiKontroler.getVm().getToi().igra.odigrajPotezNapadniVitezom(trenutnaKarta);
//                    guiKontroler.getVm().pozivSO("napadniVitezom");
                }
                break;
            default:
                break;
        }
    }

    public void prikazikartu(Karta karta, TransferObjekatIgrac toi) {
        trenutnaKarta = karta;
        Igrac ulogovaniIgrac = toi.igr;
        Igrac igracNaPotezu = toi.igra.vratiIgracaNaPotezu();

        System.out.println("ime prvog igraca je: " + ulogovaniIgrac.vratiKorisnickoIme());
        System.out.println("ime igracNaPotezu je " + igracNaPotezu.vratiKorisnickoIme());
        System.out.println("is equal " + ulogovaniIgrac.vratiKorisnickoIme().equals(igracNaPotezu.vratiKorisnickoIme()));

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
                if (ulogovaniIgrac.vratiKorisnickoIme().equals(igracNaPotezu.vratiKorisnickoIme())) {
                    break;
                } else {
                    System.out.println("Karta iz prikazikartu ako nije na potezu ulogovani igrac: " + karta.toString());
                    karta.setIsDisabled(true);
                    kartaVBox.setDisable(true);
                    break;
                }
            case ZLATNIK:
                this.getStyleClass().removeAll();
                this.getStyleClass().add("karta-zlatna");
                this.getTipKarte().setText("Z");
                this.getNapad().setText("");
                this.getOdbrana().setText("");
                if (ulogovaniIgrac.vratiKorisnickoIme().equals(igracNaPotezu.vratiKorisnickoIme())) {
                    break;
                } else {
                    System.out.println("Karta iz prikazikartu ako nije na potezu ulogovani igrac: " + karta.toString());
                    karta.setIsDisabled(true);
                    kartaVBox.setDisable(true);
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
