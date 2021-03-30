package aleksic.Kontroleri.Osluskivaci;

import aleksic.Komponente.CardComponent;
import javafx.event.Event;
import javafx.event.EventHandler;

public class OsluskivacCardTest implements EventHandler {
    CardComponent kontrolerCardTest;

    public OsluskivacCardTest(CardComponent kontrolerCardTest) {
        this.kontrolerCardTest = kontrolerCardTest;
    }

    @Override
    public void handle(Event event) {
        kontrolerCardTest.cardTestOnClick();
    }
}
