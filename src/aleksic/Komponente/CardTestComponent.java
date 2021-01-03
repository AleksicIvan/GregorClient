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
import javafx.scene.Node;
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

    public void cardTestOnClick () throws IOException {
        switch (trenutnaKarta.vratiTipKarte()) {
            case ZLATNIK:
                if (guiKontroler.getVm().getToi().fazaPoteza.equals(Faza.IZBACI_ZLATNIK)) {
                    if (igracNaPotezu == "prviIgrac") {
                        guiKontroler.setIgracNaPotezuIfazaPotezaIndikatore(
                                guiKontroler.getVm().getToi().prviIgrac,
                                guiKontroler.getVm().getToi().drugiIgrac,
                                guiKontroler.getVm().getToi().fazaPoteza
                        );
                        guiKontroler.getVm().getToi().rukaPrvogIgraca.remove(trenutnaKarta);
                        guiKontroler.getVm().getToi().talonPrvogIgraca.getRedZlatnika().add(trenutnaKarta);
                    } else {
                        guiKontroler.setIgracNaPotezuIfazaPotezaIndikatore(
                                guiKontroler.getVm().getToi().drugiIgrac,
                                guiKontroler.getVm().getToi().prviIgrac,
                                guiKontroler.getVm().getToi().fazaPoteza
                        );
                        guiKontroler.getVm().getToi().rukaDrugogIgraca.remove(trenutnaKarta);
                        guiKontroler.getVm().getToi().talonDrugogIgraca.getRedZlatnika().add(trenutnaKarta);
                    }
                    if (guiKontroler.getVm().getToi().rukaPrvogIgraca != null) {
                        System.out.println("ruka prvog igraca: " + guiKontroler.getVm().getToi().rukaPrvogIgraca.size());
                    }

                    if (guiKontroler.getVm().getToi().rukaDrugogIgraca != null) {
                        System.out.println("ruka drugog igraca: " + guiKontroler.getVm().getToi().rukaDrugogIgraca.size());
                    }
                    guiKontroler.getFxml().dodajZlatnikDonjiIgrac(this);
                    for (Node guiElementKarta : guiKontroler.getFxml().getDonjiIgracRuka().getChildren()) {
                        guiElementKarta.setDisable(true);
                    }
                    for (Node guiElementKarta : guiKontroler.getFxml().getGornjiIgracRuka().getChildren()) {
                        guiElementKarta.setDisable(true);
                    }
                    guiKontroler.getVm().getToi().fazaPoteza = Faza.PLATI;
                    guiKontroler.getFxml().getFazaPotezaDole().setText(guiKontroler.getVm().getToi().fazaPoteza.toString());
                    guiKontroler.getVm().pozivSO("odigrajZlatnik");
                    break;
                }

                if (guiKontroler.getVm().getToi().fazaPoteza.equals(Faza.PLATI)) {
                    System.out.println("Faza poteza je PLATI i zlatnik u redu zlatnika je kliknut");
                    kartaVBox.getStyleClass().add("iskoriscena-karta");
                    kartaVBox.setDisable(true);
                    if (igracNaPotezu.equals("prviIgrac")) {
                        guiKontroler
                                .getVm()
                                .getToi()
                                .talonPrvogIgraca
                                .getRedZlatnika()
                                .stream()
                                .filter(k -> trenutnaKarta.getId().equals(k.getId()))
                                .findFirst()
                                .get()
                                .setIskoriscena(true);
                    } else {
                        guiKontroler
                                .getVm()
                                .getToi()
                                .talonDrugogIgraca
                                .getRedZlatnika()
                                .stream()
                                .filter(k -> trenutnaKarta.getId().equals(k.getId()))
                                .findFirst()
                                .get()
                                .setIskoriscena(true);
                    }
                    guiKontroler.getFxml().getFazaPotezaDole().setText(guiKontroler.getVm().getToi().fazaPoteza.toString());
                    for (Node guiElementKarta : guiKontroler.getFxml().getDonjiIgracRuka().getChildren()) {
                        guiElementKarta.setDisable(true);
                    }
                    guiKontroler.getVm().getToi().fazaPoteza = Faza.ODIGRAJ_VITEZA;
                    for (Node guiElementKarta : guiKontroler.getFxml().getDonjiIgracRuka().getChildren()) {
                        guiElementKarta.setDisable(false);
                    }
                    guiKontroler.getVm().pozivSO("plati");
                    break;
                }
            case VITEZ:
                if (guiKontroler.getVm().getToi().igra.vratiFazuPoteza().equals(Faza.ODIGRAJ_VITEZA)) {
                    guiKontroler.getVm().getToi().igra.postaviFazuPoteza(Faza.NAPAD);
                    guiKontroler.setIgracNaPotezuIfazaPotezaIndikatore(donjiIgrac, gornjiIgrac, Faza.NAPAD);
                    if (igracNaPotezu == "prviIgrac") {
                        guiKontroler.getVm().getToi().rukaPrvogIgraca.remove(trenutnaKarta);
                        guiKontroler.getVm().getToi().talonPrvogIgraca.getRedVitezova().add(trenutnaKarta);
                    } else {
                        guiKontroler.getVm().getToi().rukaDrugogIgraca.remove(trenutnaKarta);
                        guiKontroler.getVm().getToi().talonDrugogIgraca.getRedVitezova().add(trenutnaKarta);
                    }
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
                if (karta.isIskoriscena()) {
                    this.getStyleClass().add("iskoriscena-karta");
                }
                if (ulogovaniIgrac.vratiKorisnickoIme().equals(igracNaPotezu.vratiKorisnickoIme())) {
                    break;
                } else {
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
