package aleksic.Kontroleri;

import aleksic.Kontroleri.Osluskivaci.OsluskivacKrajIgre;
import aleksic.Kontroleri.Osluskivaci.OsluskivacPokaziPravila;
import aleksic.Kontroleri.Osluskivaci.OsluskivacPreskociFazu;
import aleksic.Kontroleri.Osluskivaci.OsluskivacZavrsiPotez;
import aleksic.DomenskiObjekat.Igrac;
import aleksic.DomenskiObjekat.Karta;
import aleksic.Niti.OsluskivanjeObavestenja;
import aleksic.DomenskiObjekat.Faza;
import aleksic.TransferObjekat.TransferObjekatIgra;
import aleksic.Komponente.CardComponent;
import aleksic.Pogledi.ViewManager;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class KontrolerGUIGlavniprozor extends OpstiGUIKontroler {
    FXMLGlavniProzorDocumentController fxml;
    ViewManager vm;

    public FXMLGlavniProzorDocumentController getFxml() {
        return fxml;
    }

    public void postaviLabeluIgracNaPotezuGore (String text) {
        this.fxml.getIgracNaPotezuGore().setText(text);
    }

    public void postaviLabeluIgracNaPotezuDole (String text) {
        this.fxml.getIgracNaPotezuDole().setText(text);
    }

    public void postaviLabeluFazaPotezaGore (String text) {
        this.fxml.getFazaPotezaGore().setText(text);
    }

    public void postaviLabeluFazaPotezaDole (String text) {
        this.fxml.getFazaPotezaDole().setText(text);
    }

    public KontrolerGUIGlavniprozor(FXMLGlavniProzorDocumentController fxmlGlavniProzorDocumentController, ViewManager viewManager) throws IOException {
        OsluskivanjeObavestenja osluskivacObavestenja = OsluskivanjeObavestenja.getInstance();
        osluskivacObavestenja.setOpstiGUIKontroler(this);
        this.fxml = fxmlGlavniProzorDocumentController;
        this.vm = viewManager;
        this.fxml.getZavrsiPotez().setOnAction(new OsluskivacZavrsiPotez(this));
        this.fxml.getPreskociFazu().setOnAction(new OsluskivacPreskociFazu(this));
        this.fxml.getPravila().setOnAction(new OsluskivacPokaziPravila(this));
        this.fxml.getKrajIgre().setOnAction(new OsluskivacKrajIgre(this));
        this.fxml.getZavrsiPotez().setVisible(false);
        this.fxml.getPreskociFazu().setVisible(false);
        setTransferObjekatIgra(viewManager.getToi());

        fxml.postaviImeDonjegIgraca(toi.igr.vratiKorisnickoIme());
        if (toi.igra != null && toi.igra.getIgraci().size() == 0) {
            fxml.postaviImeGornjegIgraca("Čeka se igrač...");
        }

        if (toi.brojigraca == 2) {
            if (rerender(toi)) return;
        }
    }

    @Override
    public void setToi(TransferObjekatIgra toi) throws IOException {
        // TODO implementiraj freezeRow feature, posle svakog poteza onemoguci elemente/karte u odgovarajucem redu da budu kliknute
        System.out.println("setToi je pozvan");

        vm.setToi(toi);
        setTransferObjekatIgra(toi);
        if (toi.poruka.contains("Kraj igre")) {
            Alert krajIgreAlert = new Alert(Alert.AlertType.CONFIRMATION);
            krajIgreAlert.setTitle(null);
            krajIgreAlert.setHeaderText(null);
            krajIgreAlert.setContentText(toi.poruka);
            Optional<ButtonType> result = krajIgreAlert.showAndWait();
            if (result.get() == ButtonType.OK){
               toi.nazivOperacije = "novaIgra";
               pozivSO("novaIgra");
               krajIgreAlert.close();
            } else {
                // ... user chose CANCEL or closed the dialog
                krajIgreAlert.close();
            }
        }
        if (rerender(toi)) return;

        System.out.println("setToi izvrsen");
    }

    private boolean rerender(TransferObjekatIgra toi) throws IOException {
        System.out.println("rerender je pozvan");
        System.out.println("sizeRedVitezovaPrviIgrac " + toi.sizeRedVitezovaPrviIgrac);
        System.out.println("redVitezovaPrviIgrac " + toi.redVitezovaPrviIgrac);
        System.out.println("sizeRedVitezovaDrugiIgrac " + toi.sizeRedVitezovaDrugiIgrac);
        System.out.println("redRedVitezovaDrugiIgrac " + toi.redRedVitezovaDrugiIgrac);
        if (toi.nazivOperacije.equals("kreirajIgraca") && toi.brojigraca == 1) {
            System.out.println("kreirajIgraca nazivoperacije jedan igrac");
            fxml.postaviTextZivotIgracaGore("");
            fxml.postaviTextZivotIgracaDole("");
            fxml.postaviImeGornjegIgraca("Čeka se igrač...");
            return true;
        }
        System.out.println("naziv sistemske operacije: " + toi.nazivOperacije);

        System.out.println("pre reseta");
        fxml.resetujRukuGornjegIgraca();
        fxml.resetujNapadDonjegIgraca();
        fxml.resetRedZlatnikaDonjiIgrac();
        fxml.resetRedvitezovaDonjiIgrac();
        fxml.getSpilDonjiIgrac().setText("");
        fxml.getSpilGornjiIgrac().setText("");
        fxml.resetujRukuDonjegIgraca();
        fxml.resetRedZlatnikaGornjiIgrac();
        fxml.resetRedVitezovaGornjiIgrac();
        fxml.resetujNapadGornjegIgraca();
        System.out.println("posle reseta");

        if (vm.getTrenutnoUlogovaniIgrac().vratiKorisnickoIme().equals(toi.igracNaPotezu.vratiKorisnickoIme())) {
            fxml.getPreskociFazu().setVisible(true);
            fxml.getZavrsiPotez().setVisible(true);
        } else {
            fxml.getPreskociFazu().setVisible(false);
            fxml.getZavrsiPotez().setVisible(false);
        }

        if (vm.getTrenutnoUlogovaniIgrac().vratiKorisnickoIme().equals(toi.prviIgrac.vratiKorisnickoIme())) {
            System.out.println("prvi je na potezu pa je drugi igrac gore");
            // prvi je na potezu pa je drugi igrac gore

            fxml.postaviTextZivotIgracaGore("\u2665 " + toi.drugiIgrac.getZivot());
            fxml.postaviTextZivotIgracaDole("\u2665 " + toi.prviIgrac.getZivot());
            fxml.postaviImeGornjegIgraca(toi.drugiIgrac.vratiKorisnickoIme());
            fxml.postaviImeDonjegIgraca(toi.prviIgrac.vratiKorisnickoIme());
            fxml.getSpilDonjiIgrac().setText("\uD83C\uDCCF " + toi.spilPrvogIgraca.size());
            fxml.getSpilGornjiIgrac().setText("\uD83C\uDCCF " + toi.spilDrugogIgraca.size());


            List<Karta> rukaGornjiIgrac = toi.rukaDrugogIgraca;
            System.out.println("rukaGornjiIgrac: " + rukaGornjiIgrac.size());
            for (Karta k : rukaGornjiIgrac) {
                setRukuGornjegIgraca(k, toi, toi.drugiIgrac, toi.prviIgrac, "prviIgrac");
            }
            List<Karta> zlatniciGornjiRed = toi.redZlatnikaDrugiIgrac;
            System.out.println("zlatniciGornjiRed: " + zlatniciGornjiRed.size());
            for (Karta k : zlatniciGornjiRed) {
                setZlatnikeGornjegIgraca(k, toi, toi.drugiIgrac, toi.prviIgrac, "prviIgrac");
            }
            List<Karta> vitezoviGornjired = toi.redRedVitezovaDrugiIgrac;
            for (Karta k : vitezoviGornjired) {
                System.out.println("vitezoviGornjired: " + vitezoviGornjired.size());
                setVitezoveGornjegIgraca(k, toi, toi.drugiIgrac, toi.prviIgrac, "prviIgrac");
            }
            List<Karta> redNapadGornjegIgraca = toi.redNapadDrugiIgrac;
            for (Karta k : redNapadGornjegIgraca) {
                setRedNapadGornjiIgrac(k, toi, toi.drugiIgrac, toi.prviIgrac, "prviIgrac");
            }
            // postavi za trenutno ulogovanog koji je uvek dole
            System.out.println("postavi za trenutno ulogovanog koji je uvek dole");
            List<Karta> rukaDonjiIgrac = toi.rukaPrvogIgraca;
            System.out.println("rukaDonjiIgrac: " + rukaDonjiIgrac.size());
            for (Karta k : rukaDonjiIgrac) {
                setRukuDonjegIgraca(k, toi, toi.prviIgrac, toi.drugiIgrac, "prviIgrac");
            }
            List<Karta> zlatniciDonjiRed = toi.redZlatnikaPrviIgrac;
            System.out.println("zlatniciDonjiRed: " + zlatniciDonjiRed.size());
            for (Karta k : zlatniciDonjiRed) {
                setZlatnikeDonjegIgraca(k, toi, toi.prviIgrac, toi.drugiIgrac, "prviIgrac");
            }
            List<Karta> vitezoviDonjired = toi.redVitezovaPrviIgrac;
            System.out.println("vitezoviDonjired: " + vitezoviDonjired.size());
            for (Karta k : vitezoviDonjired) {
                setVitezoveDonjegIgraca(k, toi, toi.prviIgrac, toi.drugiIgrac, "prviIgrac");
            }
            List<Karta> redNapadDonjegIgraca = toi.redNapadPrviIgrac;
            for (Karta k : redNapadDonjegIgraca) {
                setRedNapadDonjiIgrac(k, toi, toi.prviIgrac, toi.drugiIgrac, "prviIgrac");
            }
            setIgracNaPotezuIfazaPotezaIndikatore(toi.igracNaPotezu, toi.drugiIgrac, toi.fazaPoteza);
        } else {
            System.out.println("drugi je na potezu pa je prvi igrac gore");
            // drugi je na potezu pa je prvi igrac gore
            fxml.postaviTextZivotIgracaGore("\u2665 " + toi.prviIgrac.getZivot());
            fxml.postaviTextZivotIgracaDole("\u2665 " + toi.drugiIgrac.getZivot());
            fxml.postaviImeGornjegIgraca(toi.prviIgrac.vratiKorisnickoIme());
            fxml.postaviImeDonjegIgraca(toi.drugiIgrac.vratiKorisnickoIme());
            fxml.getSpilDonjiIgrac().setText("\uD83C\uDCCF " + toi.spilDrugogIgraca.size());
            fxml.getSpilGornjiIgrac().setText("\uD83C\uDCCF " + toi.spilPrvogIgraca.size());

            List<Karta> rukaGornjiIgrac = toi.rukaPrvogIgraca;
            System.out.println("rukaGornjiIgrac: " + rukaGornjiIgrac.size());
            for (Karta k : rukaGornjiIgrac) {
                setRukuGornjegIgraca(k, toi, toi.prviIgrac, toi.drugiIgrac, "drugiIgrac");
            }
            List<Karta> zlatniciGornjiRed = toi.redZlatnikaPrviIgrac;
            System.out.println("zlatniciGornjiRed: " + zlatniciGornjiRed.size());
            for (Karta k : zlatniciGornjiRed) {
                setZlatnikeGornjegIgraca(k, toi, toi.prviIgrac, toi.drugiIgrac, "prviIgrac");
            }
            List<Karta> vitezoviGornjired = toi.redVitezovaPrviIgrac;
            System.out.println("vitezoviGornjired: " + vitezoviGornjired.size());
            for (Karta k : vitezoviGornjired) {
                setVitezoveGornjegIgraca(k, toi, toi.prviIgrac, toi.drugiIgrac, "drugiIgrac");
            }
            List<Karta> redNapadGornjegIgraca = toi.redNapadPrviIgrac;
            for (Karta k : redNapadGornjegIgraca) {
                setRedNapadGornjiIgrac(k, toi, toi.prviIgrac, toi.drugiIgrac, "drugiIgrac");
            }
            // postavi za trenutno ulogovanog koji je uvek dole
            System.out.println("postavi za trenutno ulogovanog koji je uvek dole");
            List<Karta> rukaDonjiIgrac = toi.rukaDrugogIgraca;
            System.out.println("rukaDonjiIgrac: " + rukaDonjiIgrac.size());
            for (Karta k : rukaDonjiIgrac) {
                setRukuDonjegIgraca(k, toi, toi.drugiIgrac, toi.prviIgrac, "drugiIgrac");
            }
            List<Karta> zlatniciDonjiRed = toi.redZlatnikaDrugiIgrac;
            System.out.println("zlatniciDonjiRed: " + zlatniciDonjiRed.size());
            for (Karta k : zlatniciDonjiRed) {
                setZlatnikeDonjegIgraca(k, toi, toi.drugiIgrac, toi.prviIgrac, "prviIgrac");
            }
            List<Karta> vitezoviDonjired = toi.redRedVitezovaDrugiIgrac;
            System.out.println("vitezoviDonjired: " + vitezoviDonjired.size());
            for (Karta k : vitezoviDonjired) {
                setVitezoveDonjegIgraca(k, toi, toi.drugiIgrac, toi.prviIgrac, "drugiIgrac");
            }
            List<Karta> redNapadDonjegIgraca = toi.redNapadDrugiIgrac;
            for (Karta k : redNapadDonjegIgraca) {
                setRedNapadDonjiIgrac(k, toi, toi.drugiIgrac, toi.prviIgrac, "drugiIgrac");
            }
            setIgracNaPotezuIfazaPotezaIndikatore(toi.igracNaPotezu, toi.prviIgrac, toi.fazaPoteza);
        }
        System.out.println("rerender je zavrsen");

        return false;
    }

    public void onPreskociFazu () {
        System.out.println("preskacem fazu...");
        pozivSO("preskociFazu");
    }

    public void onPokaziPravila () {
        System.out.println("prikazujem pravila...");
        Alert pravilaAlert = new Alert(Alert.AlertType.INFORMATION);
        pravilaAlert.setTitle("MTG Pravila");
        pravilaAlert.setHeaderText(null);
        pravilaAlert.setContentText("Igra je za 2 igrača. \n" +
                "Svaki igrač ima po 16 karata u špilu. \n" +
                "Špil čine dve vrste karata: \n" +
                "Zlatnici, kojima se plaća odigravanje Vitezova i\n" +
                "Vitezovi, kao osnovne borbene jednice. \n" +
                "Svaki Vitez ima tri karakteristike: Napad: 1, Odbrana: 1 i Cena: 1. \n" +
                "Svaki igrač ima 10 jednica života na početku. \n" +
                "Svakom igraču je na početku podeljeno po 5 karata iz špila. \n" +
                "Igra autonomno odlučuje koji je igrač prvi na potezu. \n" +
                "Igra se tako što se igrači smenjuju u odigravanju poteza. \n" +
                "Svaki potez se sastoji iz 7 faza: \n" +
                "Dodeli kartu, Izbaci Zlatnik, Plati, Izbaci Viteza, Napad, Odbrana i Izračunaj ishod. \n" +
                "Izuzetak od pomenutog pravila je prvi potez igre, kada igrač na potezu preksače faze Dodeli kartu i Napad. \n" +
                "U slučaju da igrač nema Zlatnik u ruci, preskače fazu Izbaci zlatnik. \n" +
                "U slučaju da igrač nema Zlatnik u redu Zlatnika, preskače fazu Plati. \n" +
                "U slučaju da igrač nema Vitezova u ruci, preskače fazu Izbaci Viteza. \n" +
                "U slučaju da igrač nema Vitezova u redu Vitezova, preskače fazu Napad ili Odbrana. \n" +
                "U fazi Izbaci Viteza, igrač može izbaciti na talon onoliko Vitezova koliko je platio Zlatnicima u prethodnoj fazi. \n" +
                "Igrač je dužan da nakon svakog odigravanja karata obavesti protivnika klikom na dugme Završi ili Preskoči. \n" +
                "U fazi Izračunaj ishod igra će suočiti svakog Viteza iz reda Napad sa Vitezom iz reda Odbrana i \n" +
                "upoređivanjem karakteristika Napad/Odbrana svakog Viteza pojedinačno izbaciti iz igre. \n" +
                "Ukoliko nakon izbacivanja iz igre u redu Napad preostane neki Vitez, njihove karakteristke Napad \n" +
                "se sabiraju i ukupan zbir se oduzima od ukupnog broja jedinica života igrača u odbrani. \n" +
                "Igra se završava ukoliko bilo koji od igrača ima 0 ili manje jedinica života \n" +
                "odnosno ostane bez karata u špilu. \n"
        );
        Optional<ButtonType> result = pravilaAlert.showAndWait();
        if (result.get() == ButtonType.OK){
            pravilaAlert.close();
        }
    }

    public void onKrajIgre () {
        pozivSO("odustanak");
        this.vm.zatvoriPozornicu(fxml.getViewManager().getCurrentStage());
    }
    public void onZavrsiPotez () {
        // TODO dodati mogucnost da igrac preskoci fazu
//        TransferObjekatIgra toi = toi;
        if (toi.fazaPoteza.equals(Faza.IZRACUNAJ_ISHOD)) {
            pozivSO("izracunajIshod");
            return;
        }
        if (toi.fazaPoteza.equals(Faza.DODELI_KARTU)) {
            pozivSO("dodeliKartu");
            return;
        }
        if (toi.fazaPoteza.equals(Faza.IZBACI_ZLATNIK)) {
            pozivSO("odigrajZlatnik");
            return;
        }
        if (toi.fazaPoteza.equals(Faza.PLATI)) {
            pozivSO("plati");
            return;
        }
        if (toi.fazaPoteza.equals(Faza.IZBACI_VITEZA)) {
            pozivSO("izbaciViteza");
            return;
        }
        if (toi.fazaPoteza.equals(Faza.NAPAD)) {
            pozivSO("napad");
            return;
        }
        if (toi.fazaPoteza.equals(Faza.ODBRANA)) {
            pozivSO("odbrana");
            return;
        }
    }

    private void setRedNapadGornjiIgrac(Karta k, TransferObjekatIgra toi, Igrac gornjiIgrac, Igrac donjiIgrac, String igracNaPotezu) throws IOException {
        CardComponent kontrolerCardTest = new CardComponent();
        kontrolerCardTest.setGuiKontroler(this);
        kontrolerCardTest.setGornjiIgrac(gornjiIgrac);
        kontrolerCardTest.setDonjiIgrac(donjiIgrac);
        kontrolerCardTest.setIgracNaPotezu(igracNaPotezu);
        k.setPokaziKartu(true);
        kontrolerCardTest.prikazikartu(k, toi);
        fxml.dodajVitezaUNapadGornjiIgrac(kontrolerCardTest);
    }

    private void setRedNapadDonjiIgrac(Karta k, TransferObjekatIgra toi, Igrac gornjiIgrac, Igrac donjiIgrac, String igracNaPotezu) throws IOException {
        CardComponent kontrolerCardTest = new CardComponent();
        kontrolerCardTest.setGuiKontroler(this);
        kontrolerCardTest.setGornjiIgrac(gornjiIgrac);
        kontrolerCardTest.setDonjiIgrac(donjiIgrac);
        kontrolerCardTest.setIgracNaPotezu(igracNaPotezu);
        if (igracNaPotezu == "donjiIgrac") {
            k.setIsDisabled(false);
        } else {
            k.setIsDisabled(true);
        }
        k.setPokaziKartu(true);
        kontrolerCardTest.prikazikartu(k, toi);
        fxml.dodajVitezaUNapadDonjiIgrac(kontrolerCardTest);
    }

    @Override
    public ViewManager getVm() {
        return vm;
    }

    @Override
    public void pozivSO(String nazivSO) {
        toi.nazivOperacije = nazivSO;

        try {
            System.out.println("Saljem TOI iz Kontroler GUI Glavni");
            out.reset();
            out.writeObject(toi);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void setRukuGornjegIgraca(Karta k, TransferObjekatIgra toi, Igrac gornjiIgrac, Igrac donjiIgrac, String igracNaPotezu) throws IOException {
        CardComponent kontrolerCardTest = new CardComponent();
        kontrolerCardTest.setGuiKontroler(this);
        kontrolerCardTest.setGornjiIgrac(gornjiIgrac);
        kontrolerCardTest.setDonjiIgrac(donjiIgrac);
        kontrolerCardTest.setIgracNaPotezu(igracNaPotezu);
        k.setPokaziKartu(false);
        kontrolerCardTest.prikazikartu(k, toi);
        fxml.postaviIzvuceneKarteGornjiIgrac(kontrolerCardTest);
    }

    private void setRukuDonjegIgraca(Karta k, TransferObjekatIgra toi, Igrac gornjiIgrac, Igrac donjiIgrac, String igracNaPotezu) throws IOException {
        CardComponent kontrolerCardTest = new CardComponent();
        kontrolerCardTest.setGuiKontroler(this);
        kontrolerCardTest.setGornjiIgrac(gornjiIgrac);
        kontrolerCardTest.setDonjiIgrac(donjiIgrac);
        kontrolerCardTest.setIgracNaPotezu(igracNaPotezu);
        if (igracNaPotezu == "donjiIgrac") {
            k.setIsDisabled(false);
        } else {
            k.setIsDisabled(true);
        }
        k.setPokaziKartu(true);
        kontrolerCardTest.prikazikartu(k, toi);
        fxml.postaviIzvuceneKarteDonjiIgrac(kontrolerCardTest);
    }

    private void setZlatnikeGornjegIgraca(Karta k, TransferObjekatIgra toi, Igrac gornjiIgrac, Igrac donjiIgrac, String igracNaPotezu) throws IOException {
        CardComponent kontrolerCardTest = new CardComponent();
        kontrolerCardTest.setGuiKontroler(this);
        kontrolerCardTest.setGornjiIgrac(gornjiIgrac);
        kontrolerCardTest.setDonjiIgrac(donjiIgrac);
        kontrolerCardTest.setIgracNaPotezu(igracNaPotezu);
        k.setPokaziKartu(true);
        k.setIsDisabled(true);
        kontrolerCardTest.setDisable(true);
        kontrolerCardTest.prikazikartu(k, toi);
        fxml.dodajZlatnikGornjiIgrac(kontrolerCardTest);
    }

    private void setVitezoveGornjegIgraca(Karta k, TransferObjekatIgra toi, Igrac gornjiIgrac, Igrac donjiIgrac, String igracNaPotezu) throws IOException {
        CardComponent kontrolerCardTest = new CardComponent();
        kontrolerCardTest.setGuiKontroler(this);
        kontrolerCardTest.setGornjiIgrac(gornjiIgrac);
        kontrolerCardTest.setDonjiIgrac(donjiIgrac);
        kontrolerCardTest.setIgracNaPotezu(igracNaPotezu);
        k.setPokaziKartu(true);
        k.setIsDisabled(true);
        kontrolerCardTest.setDisable(true);
        kontrolerCardTest.prikazikartu(k, toi);
        fxml.dodajVitezaGornjiIgrac(kontrolerCardTest);
    }

    private void setZlatnikeDonjegIgraca(Karta k, TransferObjekatIgra toi, Igrac gornjiIgrac, Igrac donjiIgrac, String drugiIgrac) throws IOException {
        CardComponent kontrolerCardTest = new CardComponent();
        kontrolerCardTest.setGuiKontroler(this);
        kontrolerCardTest.setGornjiIgrac(gornjiIgrac);
        kontrolerCardTest.setDonjiIgrac(donjiIgrac);
        k.setPokaziKartu(true);
        kontrolerCardTest.prikazikartu(k, toi);
        fxml.dodajZlatnikDonjiIgrac(kontrolerCardTest);
    }

    private void setVitezoveDonjegIgraca(Karta k, TransferObjekatIgra toi, Igrac gornjiIgrac, Igrac donjiIgrac, String drugiIgrac) throws IOException {
        CardComponent kontrolerCardTest = new CardComponent();
        kontrolerCardTest.setGuiKontroler(this);
        kontrolerCardTest.setGornjiIgrac(gornjiIgrac);
        kontrolerCardTest.setDonjiIgrac(donjiIgrac);
        k.setPokaziKartu(true);
        kontrolerCardTest.prikazikartu(k, toi);
        fxml.dodajVitezaDonjiIgrac(kontrolerCardTest);
    }

    public void setIgracNaPotezuIfazaPotezaIndikatore (Igrac igracNaPotezu, Igrac igracGore, Faza fazaPoteza) {
        if (igracNaPotezu.vratiKorisnickoIme().equals(igracGore.vratiKorisnickoIme())) {
            postaviLabeluIgracNaPotezuGore("NA POTEZU");
            postaviLabeluFazaPotezaGore(fazaPoteza.toString());
            postaviLabeluIgracNaPotezuDole("");
            postaviLabeluFazaPotezaDole("");
        } else {
            postaviLabeluIgracNaPotezuGore("");
            postaviLabeluFazaPotezaGore("");
            postaviLabeluIgracNaPotezuDole("NA POTEZU");
            postaviLabeluFazaPotezaDole(fazaPoteza.toString());
        }
    }
}
