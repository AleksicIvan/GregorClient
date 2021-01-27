package aleksic.Komponente;

import aleksic.Controllers.KontrolerGUIGlavniprozor;
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
                    guiKontroler.getFxml().oduzmiZlatnikIzRukeDonjegIgraca(this);
                    guiKontroler.getFxml().dodajZlatnikDonjiIgrac(this);
                    guiKontroler.setIgracNaPotezuIfazaPotezaIndikatore(
                            guiKontroler.getVm().getToi().prviIgrac,
                            guiKontroler.getVm().getToi().drugiIgrac,
                            Faza.PLATI
                    );
                    guiKontroler.getVm().getToi().odigranaKarta = trenutnaKarta;
                    guiKontroler.getVm().pozivSO("odigrajZlatnik");
                    break;
                }
                if (guiKontroler.getVm().getToi().fazaPoteza.equals(Faza.PLATI)) {
                    kartaVBox.getStyleClass().removeAll();
                    kartaVBox.getStyleClass().add("iskoriscena-karta");
                    guiKontroler.setIgracNaPotezuIfazaPotezaIndikatore(
                            guiKontroler.getVm().getToi().prviIgrac,
                            guiKontroler.getVm().getToi().drugiIgrac,
                            Faza.PLATI
                    );
                    guiKontroler.getVm().getToi().odigranaKarta = trenutnaKarta;
                    guiKontroler.getVm().pozivSO("plati");
                    break;
                }
//                    if (igracNaPotezu == "prviIgrac") {
//                        guiKontroler.getFxml().oduzmiZlatnikIzRukeDonjegIgraca(this);
//                        guiKontroler.getFxml().dodajZlatnikDonjiIgrac(this);
//                        guiKontroler.setIgracNaPotezuIfazaPotezaIndikatore(
//                                guiKontroler.getVm().getToi().prviIgrac,
//                                guiKontroler.getVm().getToi().drugiIgrac,
//                                Faza.PLATI
//                        );
//                        guiKontroler.getVm().getToi().rukaPrvogIgraca.remove(trenutnaKarta);
//                        guiKontroler.getVm().getToi().talonPrvogIgraca.getRedZlatnika().add(trenutnaKarta);
//                    } else {
//                        guiKontroler.setIgracNaPotezuIfazaPotezaIndikatore(
//                                guiKontroler.getVm().getToi().drugiIgrac,
//                                guiKontroler.getVm().getToi().prviIgrac,
//                                guiKontroler.getVm().getToi().fazaPoteza
//                        );
//                        int indexOfCardToRemove = guiKontroler.getVm().getToi().rukaDrugogIgraca.indexOf(trenutnaKarta);
//                        guiKontroler.getVm().getToi().rukaDrugogIgraca.remove(indexOfCardToRemove);
//                        guiKontroler.getVm().getToi().talonDrugogIgraca.getRedZlatnika().add(trenutnaKarta);
//                    }
//                    guiKontroler.getFxml().dodajZlatnikDonjiIgrac(this);
//                    for (Node guiElementKarta : guiKontroler.getFxml().getDonjiIgracRuka().getChildren()) {
//                        guiElementKarta.setDisable(true);
//                    }
//                    for (Node guiElementKarta : guiKontroler.getFxml().getGornjiIgracRuka().getChildren()) {
//                        guiElementKarta.setDisable(true);
//                    }
//                    guiKontroler.getVm().getToi().fazaPoteza = Faza.PLATI;
//                    guiKontroler.getFxml().getFazaPotezaDole().setText(guiKontroler.getVm().getToi().fazaPoteza.toString());
//                    guiKontroler.getFxml().dodajZlatnikDonjiIgrac(this);
//                    guiKontroler.getVm().pozivSO("odigrajZlatnik");
//                }
//
//                if (guiKontroler.getVm().getToi().fazaPoteza.equals(Faza.PLATI)) {
//                    System.out.println("Faza poteza je PLATI i zlatnik u redu zlatnika je kliknut");
//                    kartaVBox.getStyleClass().add("iskoriscena-karta");
//                    kartaVBox.setDisable(true);
//                    if (igracNaPotezu.equals("prviIgrac")) {
//                        int brojZlatnikaUReduZlatnika = guiKontroler
//                                .getVm()
//                                .getToi()
//                                .talonPrvogIgraca
//                                .getRedZlatnika()
//                                .size();
//
//                        if (brojZlatnikaUReduZlatnika > 0) {
//                            guiKontroler
//                                    .getVm()
//                                    .getToi()
//                                    .talonPrvogIgraca
//                                    .getRedZlatnika()
//                                    .stream()
//                                    .filter(k -> trenutnaKarta.getId().equals(k.getId()))
//                                    .findFirst()
//                                    .get()
//                                    .setIskoriscena(true);
//                            brojZlatnikaUReduZlatnika--;
//                            for (Node guiElementKarta : guiKontroler.getFxml().getDonjiIgracRuka().getChildren()) {
//                                guiElementKarta.setDisable(false);
//                            }
//                            guiKontroler.getVm().getToi().fazaPoteza = Faza.IZBACI_VITEZA;
//                            guiKontroler.setIgracNaPotezuIfazaPotezaIndikatore(
//                                    guiKontroler.getVm().getToi().prviIgrac,
//                                    guiKontroler.getVm().getToi().drugiIgrac,
//                                    guiKontroler.getVm().getToi().fazaPoteza
//                            );
//                        } else {
//                            guiKontroler.getVm().getToi().fazaPoteza = Faza.IZBACI_VITEZA;
//                            for (Node guiElementKarta : guiKontroler.getFxml().getDonjiIgracRuka().getChildren()) {
//                                guiElementKarta.setDisable(false);
//                            }
//                            guiKontroler.getVm().pozivSO("promenaFaze");
//                            break;
//                        }
//
//                    } else {
//                        int brojZlatnikaUReduZlatnika = guiKontroler
//                                .getVm()
//                                .getToi()
//                                .talonDrugogIgraca
//                                .getRedZlatnika()
//                                .size();
//                        if (brojZlatnikaUReduZlatnika > 0) {
//                            guiKontroler
//                                    .getVm()
//                                    .getToi()
//                                    .talonDrugogIgraca
//                                    .getRedZlatnika()
//                                    .stream()
//                                    .filter(k -> trenutnaKarta.getId().equals(k.getId()))
//                                    .findFirst()
//                                    .get()
//                                    .setIskoriscena(true);
//                            brojZlatnikaUReduZlatnika--;
//                            for (Node guiElementKarta : guiKontroler.getFxml().getDonjiIgracRuka().getChildren()) {
//                                guiElementKarta.setDisable(false);
//                            }
//                            guiKontroler.getVm().getToi().fazaPoteza = Faza.IZBACI_VITEZA;
//                            guiKontroler.setIgracNaPotezuIfazaPotezaIndikatore(
//                                    guiKontroler.getVm().getToi().drugiIgrac,
//                                    guiKontroler.getVm().getToi().prviIgrac,
//                                    guiKontroler.getVm().getToi().fazaPoteza
//                            );
//                        } else {
//                            guiKontroler.getVm().getToi().fazaPoteza = Faza.IZBACI_VITEZA;
//                            for (Node guiElementKarta : guiKontroler.getFxml().getDonjiIgracRuka().getChildren()) {
//                                guiElementKarta.setDisable(false);
//                            }
//                            guiKontroler.getVm().pozivSO("promenaFaze");
//                            break;
//                        }
//                    }
//                    guiKontroler.getVm().getToi().fazaPoteza = Faza.IZBACI_VITEZA;
//                    guiKontroler.getVm().pozivSO("plati");
////                    guiKontroler.getFxml().getFazaPotezaDole().setText(guiKontroler.getVm().getToi().fazaPoteza.toString());
////                    for (Node guiElementKarta : guiKontroler.getFxml().getDonjiIgracRuka().getChildren()) {
////                        guiElementKarta.setDisable(true);
////                    }
//                    break;
//                }
            case VITEZ:
                if (guiKontroler.getVm().getToi().fazaPoteza.equals(Faza.IZBACI_VITEZA)) {
                    System.out.println("Faza poteza je IZBACI_VITEZA");
                    guiKontroler.getFxml().oduzmiVitezaIzRukeDonjegIgraca(this);
                    guiKontroler.getFxml().dodajVitezaDonjiIgrac(this);
                    guiKontroler.setIgracNaPotezuIfazaPotezaIndikatore(
                            guiKontroler.getVm().getToi().prviIgrac,
                            guiKontroler.getVm().getToi().drugiIgrac,
                            Faza.NAPAD
                    );
                    guiKontroler.getVm().getToi().odigranaKarta = trenutnaKarta;
                    guiKontroler.getVm().pozivSO("izbaciViteza");
                    break;
                }


                if (guiKontroler.getVm().getToi().fazaPoteza.equals(Faza.NAPAD)) {
                    System.out.println("Faza poteza je NAPAD");
                    guiKontroler.getFxml().oduzmiVitezaIzRedaVitezovaDonjegIgraca(this);
                    guiKontroler.getFxml().dodajVitezaUNapadDonjiIgrac(this);
                    guiKontroler.setIgracNaPotezuIfazaPotezaIndikatore(
                            guiKontroler.getVm().getToi().prviIgrac,
                            guiKontroler.getVm().getToi().drugiIgrac,
                            Faza.NAPAD
                    );
                    guiKontroler.getVm().getToi().odigranaKarta = trenutnaKarta;
                    guiKontroler.getVm().pozivSO("napad");
                    break;
                }

                if (guiKontroler.getVm().getToi().fazaPoteza.equals(Faza.ODBRANA)) {
                    System.out.println("Faza poteza je ODBRANA");
//                    guiKontroler.getFxml().oduzmiVitezaIzRedaVitezovaDonjegIgraca(this);
//                    guiKontroler.getFxml().dodajVitezaUNapadDonjiIgrac(this);
//                    guiKontroler.setIgracNaPotezuIfazaPotezaIndikatore(
//                            guiKontroler.getVm().getToi().prviIgrac,
//                            guiKontroler.getVm().getToi().drugiIgrac,
//                            Faza.NAPAD
//                    );
                    guiKontroler.getVm().getToi().odigranaKarta = trenutnaKarta;
                    guiKontroler.getVm().pozivSO("odbrana");
                    break;
                }
//                    System.out.println("Faza poteza je IZBACI_VITEZA");
//                    if (igracNaPotezu == "prviIgrac") {
//                        guiKontroler.getVm().getToi().rukaPrvogIgraca.remove(trenutnaKarta);
//                        guiKontroler.getVm().getToi().talonPrvogIgraca.getRedVitezova().add(trenutnaKarta);
//                        if (guiKontroler.getVm().getToi().prviPotez == true) {
//                            this.igracNaPotezu = "drugiIgrac";
//                            guiKontroler.getVm().getToi().prviPotez = false;
//                            guiKontroler.getVm().getToi().igracNaPotezu = guiKontroler.getVm().getToi().drugiIgrac;
//                            guiKontroler.getVm().getToi().fazaPoteza = Faza.IZBACI_ZLATNIK;
//                            guiKontroler.setIgracNaPotezuIfazaPotezaIndikatore(
//                                    guiKontroler.getVm().getToi().drugiIgrac,
//                                    guiKontroler.getVm().getToi().drugiIgrac,
//                                    guiKontroler.getVm().getToi().fazaPoteza
//                            );
//                        } else {
//                            guiKontroler.getVm().getToi().fazaPoteza = Faza.NAPAD;
//
//                            guiKontroler.setIgracNaPotezuIfazaPotezaIndikatore(
//                                    guiKontroler.getVm().getToi().prviIgrac,
//                                    guiKontroler.getVm().getToi().drugiIgrac,
//                                    guiKontroler.getVm().getToi().fazaPoteza
//                            );
//                        }
//                        guiKontroler.getFxml().dodajVitezaDonjiIgrac(this);
//                        guiKontroler.getVm().pozivSO("izbaciViteza");
//
////                        long kolicinaPlacenihZlatnika = guiKontroler
////                                .getVm()
////                                .getToi()
////                                .talonPrvogIgraca
////                                .getRedZlatnika()
////                                .stream()
////                                .filter(k -> k.isIskoriscena())
////                                .count();
////                        if (kolicinaPlacenihZlatnika >= 0) {
////                            guiKontroler.getVm().getToi().rukaPrvogIgraca.remove(trenutnaKarta);
////                            guiKontroler.getVm().getToi().talonPrvogIgraca.getRedVitezova().add(trenutnaKarta);
////                            kolicinaPlacenihZlatnika--;
////                            if (guiKontroler.getVm().getToi().prviPotez == true) {
////                                this.igracNaPotezu = "drugiIgrac";
////                                guiKontroler.getVm().getToi().prviPotez = false;
////                                guiKontroler.getVm().getToi().igracNaPotezu = guiKontroler.getVm().getToi().drugiIgrac;
////                                guiKontroler.getVm().getToi().fazaPoteza = Faza.IZBACI_ZLATNIK;
////                                guiKontroler.setIgracNaPotezuIfazaPotezaIndikatore(
////                                        guiKontroler.getVm().getToi().drugiIgrac,
////                                        guiKontroler.getVm().getToi().drugiIgrac,
////                                        guiKontroler.getVm().getToi().fazaPoteza
////                                );
////                            } else {
////                                guiKontroler.getVm().getToi().fazaPoteza = Faza.NAPAD;
////
////                                guiKontroler.setIgracNaPotezuIfazaPotezaIndikatore(
////                                        guiKontroler.getVm().getToi().prviIgrac,
////                                        guiKontroler.getVm().getToi().drugiIgrac,
////                                        guiKontroler.getVm().getToi().fazaPoteza
////                                );
////                            }
////                            guiKontroler.getVm().getToi().rukaPrvogIgraca.remove(trenutnaKarta);
////                            guiKontroler.getVm().getToi().talonPrvogIgraca.getRedVitezova().add(trenutnaKarta);
////                            guiKontroler.getFxml().dodajVitezaDonjiIgrac(this);
////                            guiKontroler.getVm().pozivSO("izbaciViteza");
////                            break;
////                        } else {
////                            System.out.println("Nemate vise resursa za odigravanje Vitez karte!!!");
////                            guiKontroler.getVm().getToi().fazaPoteza = Faza.NAPAD;
//////                            guiKontroler.getFxml().getFazaPotezaDole().setText(guiKontroler.getVm().getToi().fazaPoteza.toString());
////                            guiKontroler.getVm().pozivSO("promenaFaze");
////                            break;
////                        }
//                    } else {
//                        int indexOfCardToRemove = guiKontroler.getVm().getToi().rukaDrugogIgraca.indexOf(trenutnaKarta);
//                        guiKontroler.getVm().getToi().rukaDrugogIgraca.remove(indexOfCardToRemove);
//                        guiKontroler.getVm().getToi().talonDrugogIgraca.getRedVitezova().add(trenutnaKarta);
//                        if (guiKontroler.getVm().getToi().prviPotez == true) {
//                            this.igracNaPotezu = "drugiIgrac";
//                            guiKontroler.getVm().getToi().prviPotez = false;
//                            guiKontroler.getVm().getToi().igracNaPotezu = guiKontroler.getVm().getToi().prviIgrac;
//                            guiKontroler.getVm().getToi().fazaPoteza = Faza.IZBACI_ZLATNIK;
//                            guiKontroler.setIgracNaPotezuIfazaPotezaIndikatore(
//                                    guiKontroler.getVm().getToi().prviIgrac,
//                                    guiKontroler.getVm().getToi().prviIgrac,
//                                    guiKontroler.getVm().getToi().fazaPoteza
//                            );
//                        } else {
//                            guiKontroler.getVm().getToi().fazaPoteza = Faza.NAPAD;
//                            guiKontroler.setIgracNaPotezuIfazaPotezaIndikatore(
//                                    guiKontroler.getVm().getToi().drugiIgrac,
//                                    guiKontroler.getVm().getToi().prviIgrac,
//                                    guiKontroler.getVm().getToi().fazaPoteza
//                            );
//                        }
//                        guiKontroler.getFxml().dodajVitezaDonjiIgrac(this);
//                        guiKontroler.getVm().pozivSO("izbaciViteza");
//
//
////                        long kolicinaPlacenihZlatnika = guiKontroler
////                                .getVm()
////                                .getToi()
////                                .talonPrvogIgraca
////                                .getRedZlatnika()
////                                .stream()
////                                .filter(k -> k.isIskoriscena())
////                                .count();
////                        if (kolicinaPlacenihZlatnika >= 0) {
////                            guiKontroler.getVm().getToi().rukaDrugogIgraca.remove(trenutnaKarta);
////                            guiKontroler.getVm().getToi().talonDrugogIgraca.getRedVitezova().add(trenutnaKarta);
////                            kolicinaPlacenihZlatnika--;
////                            if (guiKontroler.getVm().getToi().prviPotez == true) {
////                                this.igracNaPotezu = "drugiIgrac";
////                                guiKontroler.getVm().getToi().prviPotez = false;
////                                guiKontroler.getVm().getToi().igracNaPotezu = guiKontroler.getVm().getToi().prviIgrac;
////                                guiKontroler.getVm().getToi().fazaPoteza = Faza.IZBACI_ZLATNIK;
////                                guiKontroler.setIgracNaPotezuIfazaPotezaIndikatore(
////                                        guiKontroler.getVm().getToi().prviIgrac,
////                                        guiKontroler.getVm().getToi().prviIgrac,
////                                        guiKontroler.getVm().getToi().fazaPoteza
////                                );
////                            } else {
////                                guiKontroler.getVm().getToi().fazaPoteza = Faza.NAPAD;
////                                guiKontroler.setIgracNaPotezuIfazaPotezaIndikatore(
////                                        guiKontroler.getVm().getToi().drugiIgrac,
////                                        guiKontroler.getVm().getToi().prviIgrac,
////                                        guiKontroler.getVm().getToi().fazaPoteza
////                                );
////                            }
////                            guiKontroler.getFxml().dodajVitezaDonjiIgrac(this);
////                            guiKontroler.getVm().pozivSO("izbaciViteza");
////                            break;
////                        } else {
////                            System.out.println("Nemate vise resursa za odigravanje Vitez karte!!!");
////                            guiKontroler.getVm().getToi().fazaPoteza = Faza.NAPAD;
////                            guiKontroler.getFxml().getFazaPotezaDole().setText(guiKontroler.getVm().getToi().fazaPoteza.toString());
////                            guiKontroler.getVm().pozivSO("promenaFaze");
////                        }
//                    }
//                    break;
//                }
//                if (guiKontroler.getVm().getToi().fazaPoteza.equals(Faza.NAPAD)) {
//                    System.out.println("Faza poteza je NAPAD");
//                    if (igracNaPotezu == "prviIgrac") {
//                        guiKontroler.getVm().getToi().talonPrvogIgraca.getRedVitezova().remove(trenutnaKarta);
//                        guiKontroler.getVm().getToi().talonPrvogIgraca.getRedNapad().add(trenutnaKarta);
////                        guiKontroler.getVm().getToi().fazaPoteza = Faza.ODBRANA;
////                        guiKontroler.getVm().getToi().igracNaPotezu = guiKontroler.getVm().getToi().drugiIgrac;
////                        igracNaPotezu = "drugiIgrac";
//
////                        guiKontroler.setIgracNaPotezuIfazaPotezaIndikatore(
////                                guiKontroler.getVm().getTrenutnoUlogovaniIgrac(),
////                                guiKontroler.getVm().getToi().prviIgrac,
////                                guiKontroler.getVm().getToi().fazaPoteza
////                        );
//                    } else {
//                        guiKontroler.getVm().getToi().talonDrugogIgraca.getRedVitezova().remove(trenutnaKarta);
//                        guiKontroler.getVm().getToi().talonDrugogIgraca .getRedNapad().add(trenutnaKarta);
//
////                        guiKontroler.getVm().getToi().fazaPoteza = Faza.ODBRANA;
////                        guiKontroler.getVm().getToi().igracNaPotezu = guiKontroler.getVm().getToi().prviIgrac;
////                        igracNaPotezu = "prviIgrac";
//
////                        guiKontroler.setIgracNaPotezuIfazaPotezaIndikatore(
////                                guiKontroler.getVm().getTrenutnoUlogovaniIgrac(),
////                                guiKontroler.getVm().getToi().drugiIgrac,
////                                guiKontroler.getVm().getToi().fazaPoteza
////                        );
//                    }
//
//                    guiKontroler.getFxml().dodajVitezaUNapadDonjiIgrac(this);
//
//                    guiKontroler.getVm().pozivSO("napad");
//                }

//                if (guiKontroler.getVm().getToi().fazaPoteza.equals(Faza.ODBRANA)) {
//                    System.out.println("Faza poteza je ODBRANA cardClick");
//                    System.out.println("igracNaPotezu cardClick: " + igracNaPotezu);
//                    System.out.println("trenuntna karta cardClick: " + trenutnaKarta);
//                    System.out.println("prviIgrac V talon cardClick: " + guiKontroler.getVm().getToi().talonPrvogIgraca.getRedVitezova());
//                    System.out.println("drugiIgrac V talon cardClick: " + guiKontroler.getVm().getToi().talonDrugogIgraca.getRedVitezova());
//                    guiKontroler.getFxml().resetRedvitezovaDonjiIgrac();
//                    guiKontroler.getFxml().resetujNapadDonjegIgraca();
//                    if (igracNaPotezu == "prviIgrac") {
//                        guiKontroler.getVm().getToi().talonPrvogIgraca.getRedVitezova().remove(trenutnaKarta);
//                        guiKontroler.getVm().getToi().talonPrvogIgraca.getRedOdbrana().add(trenutnaKarta);
//                    } else {
//                        guiKontroler.getVm().getToi().talonDrugogIgraca.getRedVitezova().remove(trenutnaKarta);
//                        guiKontroler.getVm().getToi().talonDrugogIgraca .getRedOdbrana().add(trenutnaKarta);
//                    }
//
//                    guiKontroler.getFxml().dodajVitezaUNapadDonjiIgrac(this);
//
//                    guiKontroler.getVm().pozivSO("odbrana");
//                }
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
