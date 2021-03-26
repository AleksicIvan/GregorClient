package aleksic.Komponente;

import aleksic.Controllers.KontrolerGUIGlavniprozor;
import aleksic.DomenskiObjekat.Igrac;
import aleksic.DomenskiObjekat.Karta;
import aleksic.DomenskiObjekat.Vitez;
import aleksic.DomenskiObjekat.Zlatnik;
import aleksic.TransferObjekat.TransferObjekatIgra;
import aleksic.Views.ViewManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CardTestComponent extends VBox {
    FXMLCardTest fxmlCardTest;
    ViewManager vm;
    TransferObjekatIgra toi;
    Karta trenutnaKarta;
    KontrolerGUIGlavniprozor guiKontroler;
    Igrac gornjiIgrac;
    Igrac donjiIgrac;
    String igracNaPotezu; // prvi | drugi
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

    public String getIgracNaPotezu() {
        return igracNaPotezu;
    }

    public void setIgracNaPotezu(String igracNaPotezu) {
        this.igracNaPotezu = igracNaPotezu;
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

    public void cardTestOnClick () {
        switch (trenutnaKarta.vratiTipKarte()) {
            case ZLATNIK:
                new ObradiKlikNaKartuZlatnik(this);
//                guiKontroler.getTransferObjekatIgra().kliknutiZlatnici.add((Zlatnik) trenutnaKarta);
//                if (guiKontroler.getTransferObjekatIgra().fazaPoteza.equals(Faza.IZBACI_ZLATNIK)) {
//                    guiKontroler.getFxml().oduzmiZlatnikIzRukeDonjegIgraca(this);
//                    guiKontroler.getFxml().dodajZlatnikDonjiIgrac(this);
////                    guiKontroler.setIgracNaPotezuIfazaPotezaIndikatore(
////                            guiKontroler.getTransferObjekatIgra().prviIgrac,
////                            guiKontroler.getTransferObjekatIgra().drugiIgrac,
////                            Faza.PLATI
////                    );
//                    guiKontroler.getTransferObjekatIgra().odigranaKarta = trenutnaKarta;
////                    guiKontroler.getVm().pozivSO("odigrajZlatnik");
//                    break;
//                }
//                if (guiKontroler.getTransferObjekatIgra().fazaPoteza.equals(Faza.PLATI)) {
//                    kartaVBox.getStyleClass().removeAll();
//                    kartaVBox.getStyleClass().add("iskoriscena-karta");
////                    guiKontroler.setIgracNaPotezuIfazaPotezaIndikatore(
////                            guiKontroler.getTransferObjekatIgra().prviIgrac,
////                            guiKontroler.getTransferObjekatIgra().drugiIgrac,
////                            Faza.PLATI
////                    );
//                    guiKontroler.getTransferObjekatIgra().odigranaKarta = trenutnaKarta;
////                    guiKontroler.getVm().pozivSO("plati");
//                    break;
//                }
                break;
            case VITEZ:
                new ObradiKlikNaKartuVitez(this);
//                guiKontroler.getTransferObjekatIgra().kliknutiVItezovi.add((Vitez) trenutnaKarta);
//                if (guiKontroler.getTransferObjekatIgra().fazaPoteza.equals(Faza.IZBACI_VITEZA)) {
//                    System.out.println("Faza poteza je IZBACI_VITEZA");
//                    guiKontroler.getFxml().oduzmiVitezaIzRukeDonjegIgraca(this);
//                    guiKontroler.getFxml().dodajVitezaDonjiIgrac(this);
////                    guiKontroler.setIgracNaPotezuIfazaPotezaIndikatore(
////                            guiKontroler.getTransferObjekatIgra().prviIgrac,
////                            guiKontroler.getTransferObjekatIgra().drugiIgrac,
////                            Faza.NAPAD
////                    );
//                    guiKontroler.getTransferObjekatIgra().odigranaKarta = trenutnaKarta;
////                    guiKontroler.getVm().pozivSO("izbaciViteza");
//                    break;
//                }
//
//
//                if (guiKontroler.getTransferObjekatIgra().fazaPoteza.equals(Faza.NAPAD)) {
//                    System.out.println("Faza poteza je NAPAD");
//                    guiKontroler.getFxml().oduzmiVitezaIzRedaVitezovaDonjegIgraca(this);
//                    guiKontroler.getFxml().dodajVitezaUNapadDonjiIgrac(this);
////                    guiKontroler.setIgracNaPotezuIfazaPotezaIndikatore(
////                            guiKontroler.getTransferObjekatIgra().prviIgrac,
////                            guiKontroler.getTransferObjekatIgra().drugiIgrac,
////                            Faza.NAPAD
////                    );
//                    guiKontroler.getTransferObjekatIgra().odigranaKarta = trenutnaKarta;
////                    guiKontroler.getVm().pozivSO("napad");
//                    break;
//                }
//
//                if (guiKontroler.getTransferObjekatIgra().fazaPoteza.equals(Faza.ODBRANA)) {
//                    System.out.println("Faza poteza je ODBRANA");
//                    guiKontroler.getTransferObjekatIgra().odigranaKarta = trenutnaKarta;
////                    guiKontroler.getVm().pozivSO("odbrana");
//                    break;
//                }
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
