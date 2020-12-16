package aleksic.Controllers;

import aleksic.Models.Karta;
import aleksic.Niti.OsluskivanjeObavestenja;
import aleksic.TransferObjekat.TransferObjekatIgrac;
import aleksic.Views.CardTestComponent;
import aleksic.Views.ViewManager;

import java.io.IOException;
import java.util.ArrayList;
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

    @Override
    public ViewManager getVm() {
        return vm;
    }

    @Override
    public void setToi(TransferObjekatIgrac toi) {
        vm.setToi(toi);
        if (toi.nazivOperacije.equals("kreirajIgraca")) {
            if (toi.igra.getIgraci().size() == 2) {
                if (toi.igra.getIgraci().get(0).vratiKorisnickoIme() == toi.igr.vratiKorisnickoIme()) {
                    fxml.postaviImeGornjegIgraca(toi.igra.getIgraci().get(0).vratiKorisnickoIme());
                } else {
                    fxml.postaviImeGornjegIgraca(toi.igra.getIgraci().get(1).vratiKorisnickoIme());
                }
            }
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
            if (toi.igra.getIgraci().get(0).vratiKorisnickoIme() == toi.igr.vratiKorisnickoIme()) {
                fxml.postaviImeGornjegIgraca(toi.igra.getIgraci().get(1).vratiKorisnickoIme());
                List ruka = toi.igra.getIgraci().get(1).vratiRuku();
                for (Object k : ruka) {
                    System.out.println(k.toString());
                    CardTestComponent kontrolerCardTest = new CardTestComponent();
                    fxml.dodajKartuDonjiIgracruka(kontrolerCardTest);
                }
            } else {
                fxml.postaviImeGornjegIgraca(toi.igra.getIgraci().get(0).vratiKorisnickoIme());
                List ruka = toi.igra.getIgraci().get(0).vratiRuku();
                for (Object k : ruka) {
                    System.out.println(k.toString());
                    CardTestComponent kontrolerCardTest = new CardTestComponent();
                    fxml.dodajKartuDonjiIgracruka(kontrolerCardTest);
                }
            }
        } else {
            fxml.postaviImeGornjegIgraca("Čeka se igrač...");
        }
    }
}
