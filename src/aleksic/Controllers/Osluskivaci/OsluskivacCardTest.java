package aleksic.Controllers.Osluskivaci;

import aleksic.Komponente.CardTestComponent;
import javafx.event.Event;
import javafx.event.EventHandler;

import java.io.IOException;

public class OsluskivacCardTest implements EventHandler {
    CardTestComponent kontrolerCardTest;

    public OsluskivacCardTest(CardTestComponent kontrolerCardTest) {
        this.kontrolerCardTest = kontrolerCardTest;
    }

    @Override
    public void handle(Event event) {
        try {
            kontrolerCardTest.cardTestOnClick();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
