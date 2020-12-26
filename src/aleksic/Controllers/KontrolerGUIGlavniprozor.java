package aleksic.Controllers;

import aleksic.Models.Igrac;
import aleksic.Models.Karta;
import aleksic.Niti.OsluskivanjeObavestenja;
import aleksic.Servis.Faza;
import aleksic.Servis.FazePoteza;
import aleksic.TransferObjekat.TransferObjekatIgrac;
import aleksic.Views.CardTestComponent;
import aleksic.Views.ViewManager;

import java.io.IOException;
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

    @Override
    public ViewManager getVm() {
        return vm;
    }

    @Override
    public void setToi(TransferObjekatIgrac toi) throws IOException {
        vm.setToi(toi);
        if (toi.nazivOperacije.equals("kreirajIgraca")) {
            System.out.println("IGRAC NA POTEZU JE setToi update: " + toi.igra.getIgracNaPotezu());
            Igrac stariIgrac = toi.igra.getIgraci().get(0);
            vm.getToi().igr = stariIgrac;
            if (toi.igra.getIgraci().size() == 2) {
                fxml.postaviImeGornjegIgraca(toi.igra.getIgraci().get(1).vratiKorisnickoIme());
                setIgracNaPotezuIfazaPotezaIndikatore(toi.igra.getIgracNaPotezu(), toi.igra.getIgraci().get(1), toi.igra.vratiFazuPoteza());
                List<Karta> rukaGornjiIgrac = toi.igra.getIgraci().get(1).vratiRuku();
                for (Karta k : rukaGornjiIgrac) {
                    setRukuGornjegIgraca(k, toi);
                }
                List<Karta> rukaDonjiIgrac = toi.igra.getIgraci().get(0).vratiRuku();
                for (Karta k : rukaDonjiIgrac) {
                    setRukuDonjegIgraca(k, toi);
                }
            } else {
                fxml.postaviImeGornjegIgraca("Čeka se igrač...");
            }
        }
    }

    private void setRukuGornjegIgraca(Karta k, TransferObjekatIgrac toi) throws IOException {
        System.out.println(k.toString());
        CardTestComponent kontrolerCardTest = new CardTestComponent();
        k.setPokaziKartu(false);
        kontrolerCardTest.prikazikartu(k, toi);
        fxml.postaviIzvuceneKarteGornjiIgrac(kontrolerCardTest);
    }

    private void setRukuDonjegIgraca(Karta k, TransferObjekatIgrac toi) throws IOException {
        System.out.println(k.toString());
        CardTestComponent kontrolerCardTest = new CardTestComponent();
        k.setIsDisabled(false);
        kontrolerCardTest.prikazikartu(k, toi);
        fxml.postaviIzvuceneKarteDonjiIgrac(kontrolerCardTest);
    }

    private void setIgracNaPotezuIfazaPotezaIndikatore (Igrac igracNaPotezu, Igrac igracGore, FazePoteza fazaPoteza) {
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


    public KontrolerGUIGlavniprozor(FXMLGlavniProzorDocumentController fxmlGlavniProzorDocumentController, ViewManager viewManager) throws IOException {
        this.fxml = fxmlGlavniProzorDocumentController;
        this.vm = viewManager;
        TransferObjekatIgrac toi = viewManager.getToi();
        this.soketK = viewManager.getSoketK();
        fxml.postaviImeDonjegIgraca(toi.igr.vratiKorisnickoIme());
        OsluskivanjeObavestenja osluskivacObavestenja = OsluskivanjeObavestenja.getInstance();
        osluskivacObavestenja.setOpstiGUIKontroler(this);
        if (toi.igra.getIgraci().size() == 2) {
            Igrac ulogovaniIgrac = toi.igr;
            Igrac stariIgrac = toi.igra.getIgraci().get(0);
            Igrac igracNaPotezu = toi.igra.getIgracNaPotezu();

            postaviImeGornjegIgraca(stariIgrac.vratiKorisnickoIme());
            postaviImeDonjegIgraca(ulogovaniIgrac.vratiKorisnickoIme());
            setIgracNaPotezuIfazaPotezaIndikatore(igracNaPotezu, stariIgrac, toi.igra.vratiFazuPoteza());

            List<Karta> rukaGornjiIgrac = stariIgrac.vratiRuku();
            for (Karta k : rukaGornjiIgrac) {
                setRukuGornjegIgraca(k, toi);
            }
            List<Karta> rukaDonjiIgrac = ulogovaniIgrac.vratiRuku();
            for (Karta k : rukaDonjiIgrac) {
                setRukuDonjegIgraca(k, toi);
            }
        } else {
            fxml.postaviImeGornjegIgraca("Čeka se igrač...");
        }
    }
}
