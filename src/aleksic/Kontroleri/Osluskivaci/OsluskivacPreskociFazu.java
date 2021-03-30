package aleksic.Kontroleri.Osluskivaci;

import aleksic.Kontroleri.KontrolerGUIGlavniprozor;
import javafx.event.Event;
import javafx.event.EventHandler;


public class OsluskivacPreskociFazu implements EventHandler {
    KontrolerGUIGlavniprozor guiKontroler;

    public OsluskivacPreskociFazu(KontrolerGUIGlavniprozor guiKontroler) {
        this.guiKontroler = guiKontroler;
    }

    @Override
    public void handle(Event event) {
        guiKontroler.onPreskociFazu();
    }
}
