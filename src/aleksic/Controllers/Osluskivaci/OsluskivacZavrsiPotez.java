package aleksic.Controllers.Osluskivaci;

import aleksic.Controllers.KontrolerGUIGlavniprozor;
import javafx.event.Event;
import javafx.event.EventHandler;


public class OsluskivacZavrsiPotez implements EventHandler {
    KontrolerGUIGlavniprozor guiKontroler;

    public OsluskivacZavrsiPotez(KontrolerGUIGlavniprozor guiKontroler) {
        this.guiKontroler = guiKontroler;
    }

    @Override
    public void handle(Event event) {
        guiKontroler.onZavrsiPotez();
    }
}
