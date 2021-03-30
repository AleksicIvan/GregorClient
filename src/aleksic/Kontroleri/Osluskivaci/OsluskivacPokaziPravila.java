package aleksic.Kontroleri.Osluskivaci;

import aleksic.Kontroleri.KontrolerGUIGlavniprozor;
import javafx.event.Event;
import javafx.event.EventHandler;


public class OsluskivacPokaziPravila implements EventHandler {
    KontrolerGUIGlavniprozor guiKontroler;

    public OsluskivacPokaziPravila(KontrolerGUIGlavniprozor guiKontroler) {
        this.guiKontroler = guiKontroler;
    }

    @Override
    public void handle(Event event) {
        guiKontroler.onPokaziPravila();
    }
}
