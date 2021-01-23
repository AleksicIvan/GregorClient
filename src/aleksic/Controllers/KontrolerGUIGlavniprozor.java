package aleksic.Controllers;

import aleksic.Models.Igrac;
import aleksic.Models.Karta;
import aleksic.Niti.OsluskivanjeObavestenja;
import aleksic.Servis.Faza;
import aleksic.Servis.Igra;
import aleksic.TransferObjekat.TransferObjekatIgrac;
import aleksic.Komponente.CardTestComponent;
import aleksic.Views.ViewManager;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

public class KontrolerGUIGlavniprozor extends OpstoGUIKontroler {
    FXMLGlavniProzorDocumentController fxml;
    ViewManager vm;
    String poruka = "";

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public String getPoruka() {
        return poruka;
    }

    public FXMLGlavniProzorDocumentController getFxml() {
        return fxml;
    }

    public void postaviImeGornjegIgraca (String imeGornjegIgraca) {
        this.fxml.postaviImeGornjegIgraca(imeGornjegIgraca);
    }

    public void postaviImeDonjegIgraca (String imeDonjegIgraca) {
        this.fxml.postaviImeDonjegIgraca(imeDonjegIgraca);
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
        TransferObjekatIgrac toi = viewManager.getToi();
        fxml.postaviImeDonjegIgraca(toi.igr.vratiKorisnickoIme());
        if (toi.igra != null && toi.igra.getIgraci().size() == 0) {
            fxml.postaviImeGornjegIgraca("Čeka se igrač...");
        }

        if (toi.igra != null && toi.igra.getIgraci().size() == 1) {
            Igrac ulogovaniIgrac = toi.igr;
            Igrac stariIgrac = toi.igra.getIgraci().get(0);
//            Igrac igracNaPotezu = toi.igra.getIgracNaPotezu();

            postaviImeGornjegIgraca(stariIgrac.vratiKorisnickoIme());
            postaviImeDonjegIgraca(ulogovaniIgrac.vratiKorisnickoIme());
//            setIgracNaPotezuIfazaPotezaIndikatore(toi.igracNaPotezu, stariIgrac, toi.igra.vratiFazuPoteza());

//            List<Karta> rukaGornjiIgrac = stariIgrac.vratiRuku();
//            for (Karta k : rukaGornjiIgrac) {
//                setRukuGornjegIgraca(k, toi, stariIgrac, ulogovaniIgrac);
//            }
//            List<Karta> rukaDonjiIgrac = ulogovaniIgrac.vratiRuku();
//            for (Karta k : rukaDonjiIgrac) {
//                setRukuDonjegIgraca(k, toi, ulogovaniIgrac, stariIgrac);
//            }
        }
    }

    @Override
    public void setToi(TransferObjekatIgrac toi) throws IOException {
        vm.setToi(toi);
        System.out.println("set toi je pozvan");
        if (toi.nazivOperacije.equals("kreirajIgraca") && toi.brojigraca == 1) {
            System.out.println("kreirajIgraca nazivoperacije nedna igrac");
            fxml.postaviImeGornjegIgraca("Čeka se igrač...");
            return;
        }
        System.out.println("naziv sistemske operacije: " + toi.nazivOperacije);

        System.out.println("pre reseta");
        fxml.resetujRukuGornjegIgraca();
        fxml.resetujNapadDonjegIgraca();
        fxml.resetRedZlatnikaDonjiIgrac();
        fxml.resetRedvitezovaDonjiIgrac();

        fxml.resetujRukuDonjegIgraca();
        fxml.resetRedZlatnikaGornjiIgrac();
        fxml.resetRedVitezovaGornjiIgrac();
        fxml.resetujNapadGornjegIgraca();
        System.out.println("posle reseta");

        if (vm.getTrenutnoUlogovaniIgrac().vratiKorisnickoIme().equals(toi.prviIgrac.vratiKorisnickoIme())) {
            System.out.println("prvi je na potezu pa je drugi igrac gore");
            // prvi je na potezu pa je drugi igrac gore
            fxml.postaviImeGornjegIgraca(toi.drugiIgrac.vratiKorisnickoIme());
            fxml.postaviImeDonjegIgraca(toi.prviIgrac.vratiKorisnickoIme());
            List<Karta> rukaGornjiIgrac = toi.rukaDrugogIgraca;
            System.out.println("rukaGornjiIgrac: " + rukaGornjiIgrac.size());
            for (Karta k : rukaGornjiIgrac) {
                setRukuGornjegIgraca(k, toi, toi.drugiIgrac, toi.prviIgrac, "prviIgrac");
            }
            List<Karta> zlatniciGornjiRed = toi.talonDrugogIgraca.getRedZlatnika();
            System.out.println("zlatniciGornjiRed: " + zlatniciGornjiRed.size());
            for (Karta k : zlatniciGornjiRed) {
                setZlatnikeGornjegIgraca(k, toi, toi.drugiIgrac, toi.prviIgrac, "prviIgrac");
            }
            List<Karta> vitezoviGornjired = toi.talonDrugogIgraca.getRedVitezova();
            for (Karta k : vitezoviGornjired) {
                System.out.println("vitezoviGornjired: " + vitezoviGornjired.size());
                setVitezoveGornjegIgraca(k, toi, toi.drugiIgrac, toi.prviIgrac, "prviIgrac");
            }
            List<Karta> redNapadGornjegIgraca = toi.talonDrugogIgraca.getRedNapad();
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
            List<Karta> zlatniciDonjiRed = toi.talonPrvogIgraca.getRedZlatnika();
            System.out.println("zlatniciDonjiRed: " + zlatniciDonjiRed.size());
            for (Karta k : zlatniciDonjiRed) {
                setZlatnikeDonjegIgraca(k, toi, toi.prviIgrac, toi.drugiIgrac, "prviIgrac");
            }
            List<Karta> vitezoviDonjired = toi.talonPrvogIgraca.getRedVitezova();
            System.out.println("vitezoviDonjired: " + vitezoviDonjired.size());
            for (Karta k : vitezoviDonjired) {
                setVitezoveDonjegIgraca(k, toi, toi.prviIgrac, toi.drugiIgrac, "prviIgrac");
            }
            List<Karta> redNapadDonjegIgraca = toi.talonPrvogIgraca.getRedNapad();
            for (Karta k : redNapadDonjegIgraca) {
                setRedNapadDonjiIgrac(k, toi, toi.prviIgrac, toi.drugiIgrac, "prviIgrac");
            }
            setIgracNaPotezuIfazaPotezaIndikatore(toi.igracNaPotezu, toi.drugiIgrac, toi.fazaPoteza);
        } else {
            System.out.println("drugi je na potezu pa je prvi igrac gore");
            // drugi je na potezu pa je prvi igrac gore
            fxml.postaviImeGornjegIgraca(toi.prviIgrac.vratiKorisnickoIme());
            fxml.postaviImeDonjegIgraca(toi.drugiIgrac.vratiKorisnickoIme());
            List<Karta> rukaGornjiIgrac = toi.rukaPrvogIgraca;
            System.out.println("rukaGornjiIgrac: " + rukaGornjiIgrac.size());
            for (Karta k : rukaGornjiIgrac) {
                setRukuGornjegIgraca(k, toi, toi.prviIgrac, toi.drugiIgrac, "drugiIgrac");
            }
            List<Karta> zlatniciGornjiRed = toi.talonPrvogIgraca.getRedZlatnika();
            System.out.println("zlatniciGornjiRed: " + zlatniciGornjiRed.size());
            for (Karta k : zlatniciGornjiRed) {
                setZlatnikeGornjegIgraca(k, toi, toi.prviIgrac, toi.drugiIgrac, "prviIgrac");
            }
            List<Karta> vitezoviGornjired = toi.talonPrvogIgraca.getRedVitezova();
            System.out.println("vitezoviGornjired: " + vitezoviGornjired.size());
            for (Karta k : vitezoviGornjired) {
                setVitezoveGornjegIgraca(k, toi, toi.prviIgrac, toi.drugiIgrac, "drugiIgrac");
            }
            List<Karta> redNapadGornjegIgraca = toi.talonPrvogIgraca.getRedNapad();
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
            List<Karta> zlatniciDonjiRed = toi.talonDrugogIgraca.getRedZlatnika();
            System.out.println("zlatniciDonjiRed: " + zlatniciDonjiRed.size());
            for (Karta k : zlatniciDonjiRed) {
                setZlatnikeDonjegIgraca(k, toi, toi.drugiIgrac, toi.prviIgrac, "prviIgrac");
            }
            List<Karta> vitezoviDonjired = toi.talonDrugogIgraca.getRedVitezova();
            System.out.println("vitezoviDonjired: " + vitezoviDonjired.size());
            for (Karta k : vitezoviDonjired) {
                setVitezoveDonjegIgraca(k, toi, toi.drugiIgrac, toi.prviIgrac, "drugiIgrac");
            }
            List<Karta> redNapadDonjegIgraca = toi.talonDrugogIgraca.getRedNapad();
            for (Karta k : redNapadDonjegIgraca) {
                setRedNapadDonjiIgrac(k, toi, toi.drugiIgrac, toi.prviIgrac, "drugiIgrac");
            }
            setIgracNaPotezuIfazaPotezaIndikatore(toi.igracNaPotezu, toi.prviIgrac, toi.fazaPoteza);
        }

        System.out.println("setToi izvrsen");



//        if (toi.nazivOperacije.equals("odigrajZlatnik")) {
//            System.out.println("izbacen je zlatnik");
//            if (toi.igracNaPotezu.vratiKorisnickoIme().equals(toi.prviIgrac.vratiKorisnickoIme())) {
//                // prvi
//                setIgracNaPotezuIfazaPotezaIndikatore(toi.igracNaPotezu, toi.prviIgrac, toi.fazaPoteza);
//                List<Karta> zlatniciGornjiIgrac = toi.talonPrvogIgraca.getRedZlatnika();
//                for (Karta k : zlatniciGornjiIgrac) {
//                    // postavi zlatnike red gornjem igracu
//
//                    setZlatnikeGornjegIgraca(k, toi, toi.drugiIgrac, toi.prviIgrac, "prviIgrac");
//                }
//            } else {
//                // drugi
//                setIgracNaPotezuIfazaPotezaIndikatore(toi.igracNaPotezu, toi.drugiIgrac, toi.fazaPoteza);
//                List<Karta> zlatniciGornjiIgrac = toi.talonDrugogIgraca.getRedZlatnika();
//                for (Karta k : zlatniciGornjiIgrac) {
//                    // postavi zlatnike red gornjem igracu
//                    setZlatnikeGornjegIgraca(k, toi, toi.drugiIgrac, toi.prviIgrac, "drugiIgrac");
//                }
//            }
//        }

//        if (toi.nazivOperacije.equals("plati")) {
//            System.out.println("placen je zlatnik");
//            fxml.resetujRukuGornjegIgraca();
//            fxml.resetRedZlatnikaGornjiIgrac();
//
//            if (toi.igracNaPotezu.vratiKorisnickoIme().equals(toi.prviIgrac.vratiKorisnickoIme())) {
//                // prvi
//                setIgracNaPotezuIfazaPotezaIndikatore(toi.igracNaPotezu, toi.prviIgrac, toi.fazaPoteza);
//                List<Karta> zlatniciGornjiIgrac = toi.talonPrvogIgraca.getRedZlatnika();
//                for (Karta k : zlatniciGornjiIgrac) {
//                    // postavi zlatnike red gornjem igracu
//
//                    setZlatnikeGornjegIgraca(k, toi, toi.drugiIgrac, toi.prviIgrac, "prviIgrac");
//                }
//            } else {
//                // drugi
//                setIgracNaPotezuIfazaPotezaIndikatore(toi.igracNaPotezu, toi.drugiIgrac, toi.fazaPoteza);
//                List<Karta> zlatniciGornjiIgrac = toi.talonDrugogIgraca.getRedZlatnika();
//                for (Karta k : zlatniciGornjiIgrac) {
//                    // postavi zlatnike red gornjem igracu
//                    setZlatnikeGornjegIgraca(k, toi, toi.drugiIgrac, toi.prviIgrac, "drugiIgrac");
//                }
//            }
//        }

//        if (toi.nazivOperacije.equals("izbaciViteza")) {
//            System.out.println("Vitez je izbacen");
//            fxml.resetRedVitezovaGornjiIgrac();
//            if (this.getVm().getTrenutnoUlogovaniIgrac().vratiKorisnickoIme().equals(toi.prviIgrac.vratiKorisnickoIme())) {
//                List<Karta> vitezoviGornjired = toi.talonDrugogIgraca.getRedVitezova();
//                for (Karta k : vitezoviGornjired) {
//                    // postavi zlatnike red gornjem igracu
//                    setVitezoveGornjegIgraca(k, toi, toi.drugiIgrac, toi.prviIgrac, "prviIgrac");
//                }
//                List<Karta> rukaGornjiIgrac = toi.rukaDrugogIgraca;
//                for (Karta k : rukaGornjiIgrac) {
//                    setRukuGornjegIgraca(k, toi, toi.drugiIgrac, toi.prviIgrac, "prviIgrac");
//                }
//                setIgracNaPotezuIfazaPotezaIndikatore(toi.igracNaPotezu, toi.drugiIgrac, toi.fazaPoteza);
//            } else {
//                List<Karta> vitezoviGornjired = toi.talonPrvogIgraca.getRedVitezova();
//                for (Karta k : vitezoviGornjired) {
//                    // postavi zlatnike red gornjem igracu
//                    setVitezoveGornjegIgraca(k, toi, toi.drugiIgrac, toi.prviIgrac, "prviIgrac");
//                }
//                List<Karta> rukaGornjiIgrac = toi.rukaPrvogIgraca;
//                for (Karta k : rukaGornjiIgrac) {
//                    setRukuGornjegIgraca(k, toi, toi.drugiIgrac, toi.prviIgrac, "prviIgrac");
//                }
//                setIgracNaPotezuIfazaPotezaIndikatore(toi.igracNaPotezu, toi.prviIgrac, toi.fazaPoteza);
//            }
//        }

//        if (toi.nazivOperacije.equals("napad")) {
//            System.out.println("Faza je napad");
//            fxml.resetujRukuGornjegIgraca();
//            fxml.resetRedVitezovaGornjiIgrac();
//            if (toi.igracNaPotezu.vratiKorisnickoIme().equals(toi.prviIgrac.vratiKorisnickoIme())) {
//                List<Karta> vitezoviGornjired = toi.talonPrvogIgraca.getRedVitezova();
//                for (Karta k : vitezoviGornjired) {
//                    // postavi zlatnike red gornjem igracu
//                    setVitezoveGornjegIgraca(k, toi, toi.prviIgrac, toi.drugiIgrac, "prviIgrac");
//                }
//                List<Karta> rukaGornjiIgrac = toi.rukaPrvogIgraca;
//                for (Karta k : rukaGornjiIgrac) {
//                    setRukuGornjegIgraca(k, toi, toi.prviIgrac, toi.drugiIgrac, "prviIgrac");
//                }
//                List<Karta> redNapadGornjegIgraca = toi.talonPrvogIgraca.getRedNapad();
//                for (Karta k : redNapadGornjegIgraca) {
//                    setRedNapadGornjiIgrac(k, toi, toi.prviIgrac, toi.drugiIgrac, "prviIgrac");
//                }
//                this.getVm().getToi().igracNaPotezu = this.getVm().getToi().drugiIgrac;
//                this.getVm().getToi().fazaPoteza = Faza.ODBRANA;
//                setIgracNaPotezuIfazaPotezaIndikatore(toi.igracNaPotezu, toi.prviIgrac, toi.fazaPoteza);
//            } else {
//                List<Karta> vitezoviGornjired = toi.talonDrugogIgraca.getRedVitezova();
//                for (Karta k : vitezoviGornjired) {
//                    // postavi zlatnike red gornjem igracu
//                    setVitezoveGornjegIgraca(k, toi, toi.prviIgrac, toi.drugiIgrac, "drugiIgrac");
//                }
//                List<Karta> rukaGornjiIgrac = toi.rukaDrugogIgraca;
//                for (Karta k : rukaGornjiIgrac) {
//                    setRukuGornjegIgraca(k, toi, toi.prviIgrac, toi.drugiIgrac, "drugiIgrac");
//                }
//                List<Karta> redNapadGornjegIgraca = toi.talonDrugogIgraca.getRedNapad();
//                for (Karta k : redNapadGornjegIgraca) {
//                    setRedNapadGornjiIgrac(k, toi, toi.prviIgrac, toi.drugiIgrac, "drugiIgrac");
//                }
//                this.getVm().getToi().igracNaPotezu = this.getVm().getToi().prviIgrac;
//                this.getVm().getToi().fazaPoteza = Faza.ODBRANA;
//                setIgracNaPotezuIfazaPotezaIndikatore(toi.igracNaPotezu, toi.drugiIgrac, toi.fazaPoteza);
//            }
//        }

//        if (toi.nazivOperacije.equals("odbrana")) {
//            System.out.println("Faza je odbrana SET TOI");
//            System.out.println("igrac na potezu SET TOI" + toi.igracNaPotezu.vratiKorisnickoIme());
////            fxml.resetujRukuGornjegIgraca();
////            fxml.resetRedVitezovaGornjiIgrac();
////            if (toi.igracNaPotezu.vratiKorisnickoIme().equals(toi.prviIgrac.vratiKorisnickoIme())) {
////                System.out.println("toi.prviIgrac.vratiKorisnickoIme() SET TOI" + toi.prviIgrac.vratiKorisnickoIme());
////                List<Karta> vitezoviGornjired = toi.talonDrugogIgraca.getRedVitezova();
////                for (Karta k : vitezoviGornjired) {
////                    setVitezoveGornjegIgraca(k, toi, toi.prviIgrac, toi.drugiIgrac, "prviIgrac");
////                }
////            } else {
////                System.out.println("toi.drugiIfgrac.vratiKorisnickoIme() SET TOI" + toi.drugiIgrac.vratiKorisnickoIme());
////                List<Karta> vitezoviGornjired = toi.talonPrvogIgraca.getRedVitezova();
////                for (Karta k : vitezoviGornjired) {
////                    setVitezoveGornjegIgraca(k, toi, toi.drugiIgrac, toi.prviIgrac, "drugiIgrac");
////                }
////            }
//
//        }
    }

    private void setRedNapadGornjiIgrac(Karta k, TransferObjekatIgrac toi, Igrac gornjiIgrac, Igrac donjiIgrac, String igracNaPotezu) throws IOException {
        CardTestComponent kontrolerCardTest = new CardTestComponent();
        kontrolerCardTest.setGuiKontroler(this);
        kontrolerCardTest.setGornjiIgrac(gornjiIgrac);
        kontrolerCardTest.setDonjiIgrac(donjiIgrac);
        kontrolerCardTest.setIgracNaPotezu(igracNaPotezu);
        k.setPokaziKartu(true);
        kontrolerCardTest.prikazikartu(k, toi);
        fxml.dodajVitezaUNapadGornjiIgrac(kontrolerCardTest);
    }

    private void setRedNapadDonjiIgrac(Karta k, TransferObjekatIgrac toi, Igrac gornjiIgrac, Igrac donjiIgrac, String igracNaPotezu) throws IOException {
        CardTestComponent kontrolerCardTest = new CardTestComponent();
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

    private void setRukuGornjegIgraca(Karta k, TransferObjekatIgrac toi, Igrac gornjiIgrac, Igrac donjiIgrac, String igracNaPotezu) throws IOException {
        CardTestComponent kontrolerCardTest = new CardTestComponent();
        kontrolerCardTest.setGuiKontroler(this);
        kontrolerCardTest.setGornjiIgrac(gornjiIgrac);
        kontrolerCardTest.setDonjiIgrac(donjiIgrac);
        kontrolerCardTest.setIgracNaPotezu(igracNaPotezu);
        k.setPokaziKartu(false);
        kontrolerCardTest.prikazikartu(k, toi);
        fxml.postaviIzvuceneKarteGornjiIgrac(kontrolerCardTest);
    }

    private void setRukuDonjegIgraca(Karta k, TransferObjekatIgrac toi, Igrac gornjiIgrac, Igrac donjiIgrac, String igracNaPotezu) throws IOException {
        CardTestComponent kontrolerCardTest = new CardTestComponent();
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

    private void setZlatnikeGornjegIgraca(Karta k, TransferObjekatIgrac toi, Igrac gornjiIgrac, Igrac donjiIgrac, String igracNaPotezu) throws IOException {
        CardTestComponent kontrolerCardTest = new CardTestComponent();
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

    private void setVitezoveGornjegIgraca(Karta k, TransferObjekatIgrac toi, Igrac gornjiIgrac, Igrac donjiIgrac, String igracNaPotezu) throws IOException {
        CardTestComponent kontrolerCardTest = new CardTestComponent();
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

    private void setZlatnikeDonjegIgraca(Karta k, TransferObjekatIgrac toi, Igrac gornjiIgrac, Igrac donjiIgrac, String drugiIgrac) throws IOException {
        CardTestComponent kontrolerCardTest = new CardTestComponent();
        kontrolerCardTest.setGuiKontroler(this);
        kontrolerCardTest.setGornjiIgrac(gornjiIgrac);
        kontrolerCardTest.setDonjiIgrac(donjiIgrac);
        k.setPokaziKartu(true);
        kontrolerCardTest.prikazikartu(k, toi);
        fxml.dodajZlatnikDonjiIgrac(kontrolerCardTest);
    }

    private void setVitezoveDonjegIgraca(Karta k, TransferObjekatIgrac toi, Igrac gornjiIgrac, Igrac donjiIgrac, String drugiIgrac) throws IOException {
        CardTestComponent kontrolerCardTest = new CardTestComponent();
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
