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
        if (toi.igra != null && toi.brojigraca == 1) {
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
        } else {
            fxml.postaviImeGornjegIgraca("Čeka se igrač...");
        }
    }

    @Override
    public void setToi(TransferObjekatIgrac toi) throws IOException {
        vm.setToi(toi);
        if (toi.nazivOperacije.equals("kreirajIgraca")) {
            if (toi.brojigraca == 2) {
                Igrac prviIgrac = toi.prviIgrac;
                Igrac drugiIgrac = toi.drugiIgrac;
                if (!drugiIgrac.vratiKorisnickoIme().equals(toi.igr.vratiKorisnickoIme())) {
                    // ne treba update za vec ulogovanog - drugog igraca
                    fxml.postaviImeDonjegIgraca(prviIgrac.vratiKorisnickoIme());
                    fxml.postaviImeGornjegIgraca(drugiIgrac.vratiKorisnickoIme());
                    setIgracNaPotezuIfazaPotezaIndikatore(toi.igracNaPotezu, toi.drugiIgrac, toi.fazaPoteza);
                    List<Karta> rukaGornjiIgrac = toi.rukaDrugogIgraca;
                    for (Karta k : rukaGornjiIgrac) {
                        setRukuGornjegIgraca(k, toi, toi.drugiIgrac, toi.prviIgrac);
                    }
                    List<Karta> rukaDonjiIgrac = toi.rukaPrvogIgraca;
                    for (Karta k : rukaDonjiIgrac) {
                        setRukuDonjegIgraca(k, toi, toi.drugiIgrac, toi.prviIgrac);
                    }
                } else {
                    fxml.postaviImeDonjegIgraca(drugiIgrac.vratiKorisnickoIme());
                    fxml.postaviImeGornjegIgraca(prviIgrac.vratiKorisnickoIme());
                    setIgracNaPotezuIfazaPotezaIndikatore(toi.igracNaPotezu, toi.prviIgrac, toi.fazaPoteza);
                    List<Karta> rukaGornjiIgrac = toi.rukaPrvogIgraca;
                    for (Karta k : rukaGornjiIgrac) {
                        setRukuGornjegIgraca(k, toi, toi.prviIgrac, toi.drugiIgrac);
                    }
                    List<Karta> rukaDonjiIgrac = toi.rukaDrugogIgraca;
                    for (Karta k : rukaDonjiIgrac) {
                        setRukuDonjegIgraca(k, toi, toi.prviIgrac, toi.drugiIgrac);
                    }
                }
            } else {
                fxml.postaviImeGornjegIgraca("Čeka se igrač...");
            }
        }

        if (toi.nazivOperacije.equals("odigrajZlatnika")) {
            Igrac stariIgrac = toi.igra.getIgraci().get(0);
            vm.getToi().igr = stariIgrac;
            fxml.postaviImeGornjegIgraca(toi.igra.getIgraci().get(1).vratiKorisnickoIme());
            setIgracNaPotezuIfazaPotezaIndikatore(toi.igra.getIgracNaPotezu(), toi.igra.getIgraci().get(1), toi.igra.vratiFazuPoteza());
            List<Karta> rukaGornjiIgrac = toi.igra.getIgraci().get(1).vratiRuku();
            for (Karta k : rukaGornjiIgrac) {
                setRukuGornjegIgraca(k, toi, toi.igra.getIgraci().get(1), toi.igra.getIgraci().get(0));
            }
            List<Karta> rukaDonjiIgrac = toi.igra.getIgraci().get(0).vratiRuku();
            for (Karta k : rukaDonjiIgrac) {
                setRukuDonjegIgraca(k, toi, toi.igra.getIgraci().get(0), toi.igra.getIgraci().get(1));
            }
            List<Karta> zlatniciGornjiIgrac = toi.igra.getIgraci().get(1).vratiTalon().getRedZlatnika();
            for (Karta k : zlatniciGornjiIgrac) {
                // postavi zlatnike red gornjem igracu
                setZlatnikeGornjegIgraca(k, toi, toi.igra.getIgraci().get(0), toi.igra.getIgraci().get(1));
            }
            List<Karta> vitezoviGornjiIgrac = toi.igra.getIgraci().get(1).vratiTalon().getRedVitezova();
            for (Karta k : vitezoviGornjiIgrac) {
                // postavi viteove red gornjem igracu
                setVitezoveGornjegIgraca(k, toi, toi.igra.getIgraci().get(0), toi.igra.getIgraci().get(1));
            }
            List<Karta> zlatniciDonjiIgrac = stariIgrac.vratiTalon().getRedZlatnika();
            for (Karta k : zlatniciGornjiIgrac) {
                // postavi zlatnike red gornjem igracu
                setZlatnikeDonjegIgraca(k, toi, toi.igra.getIgraci().get(0), toi.igra.getIgraci().get(1));
            }
            List<Karta> vitezoviDonjiIgrac = stariIgrac.vratiTalon().getRedVitezova();
            for (Karta k : vitezoviGornjiIgrac) {
                // postavi viteove red gornjem igracu
                setVitezoveDonjegIgraca(k, toi, toi.igra.getIgraci().get(0), toi.igra.getIgraci().get(1));
            }

        }
    }

    @Override
    public ViewManager getVm() {
        return vm;
    }

    private void setRukuGornjegIgraca(Karta k, TransferObjekatIgrac toi, Igrac gornjiIgrac, Igrac donjiIgrac) throws IOException {
        CardTestComponent kontrolerCardTest = new CardTestComponent();
        kontrolerCardTest.setGuiKontroler(this);
        kontrolerCardTest.setGornjiIgrac(gornjiIgrac);
        kontrolerCardTest.setDonjiIgrac(donjiIgrac);
        k.setPokaziKartu(false);
        kontrolerCardTest.prikazikartu(k, toi);
        fxml.postaviIzvuceneKarteGornjiIgrac(kontrolerCardTest);
    }

    private void setRukuDonjegIgraca(Karta k, TransferObjekatIgrac toi, Igrac gornjiIgrac, Igrac donjiIgrac) throws IOException {
        CardTestComponent kontrolerCardTest = new CardTestComponent();
        kontrolerCardTest.setGuiKontroler(this);
        kontrolerCardTest.setGornjiIgrac(gornjiIgrac);
        kontrolerCardTest.setDonjiIgrac(donjiIgrac);
        k.setIsDisabled(false);
        kontrolerCardTest.prikazikartu(k, toi);
        fxml.postaviIzvuceneKarteDonjiIgrac(kontrolerCardTest);
    }

    private void setZlatnikeGornjegIgraca(Karta k, TransferObjekatIgrac toi, Igrac gornjiIgrac, Igrac donjiIgrac) throws IOException {
        CardTestComponent kontrolerCardTest = new CardTestComponent();
        kontrolerCardTest.setGuiKontroler(this);
        kontrolerCardTest.setGornjiIgrac(gornjiIgrac);
        kontrolerCardTest.setDonjiIgrac(donjiIgrac);
        k.setPokaziKartu(false);
        kontrolerCardTest.prikazikartu(k, toi);
        fxml.dodajZlatnikGornjiIgrac(kontrolerCardTest);
    }

    private void setVitezoveGornjegIgraca(Karta k, TransferObjekatIgrac toi, Igrac gornjiIgrac, Igrac donjiIgrac) throws IOException {
        CardTestComponent kontrolerCardTest = new CardTestComponent();
        kontrolerCardTest.setGuiKontroler(this);
        kontrolerCardTest.setGornjiIgrac(gornjiIgrac);
        kontrolerCardTest.setDonjiIgrac(donjiIgrac);
        k.setPokaziKartu(false);
        kontrolerCardTest.prikazikartu(k, toi);
        fxml.dodajVitezaGornjiIgrac(kontrolerCardTest);
    }

    private void setZlatnikeDonjegIgraca(Karta k, TransferObjekatIgrac toi, Igrac gornjiIgrac, Igrac donjiIgrac) throws IOException {
        CardTestComponent kontrolerCardTest = new CardTestComponent();
        kontrolerCardTest.setGuiKontroler(this);
        kontrolerCardTest.setGornjiIgrac(gornjiIgrac);
        kontrolerCardTest.setDonjiIgrac(donjiIgrac);
        k.setPokaziKartu(false);
        kontrolerCardTest.prikazikartu(k, toi);
        fxml.dodajZlatnikDonjiIgrac(kontrolerCardTest);
    }

    private void setVitezoveDonjegIgraca(Karta k, TransferObjekatIgrac toi, Igrac gornjiIgrac, Igrac donjiIgrac) throws IOException {
        CardTestComponent kontrolerCardTest = new CardTestComponent();
        kontrolerCardTest.setGuiKontroler(this);
        kontrolerCardTest.setGornjiIgrac(gornjiIgrac);
        kontrolerCardTest.setDonjiIgrac(donjiIgrac);
        k.setPokaziKartu(false);
        kontrolerCardTest.prikazikartu(k, toi);
        fxml.dodajZlatnikDonjiIgrac(kontrolerCardTest);
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
