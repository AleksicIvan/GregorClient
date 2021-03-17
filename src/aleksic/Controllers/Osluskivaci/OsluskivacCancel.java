package aleksic.Controllers.Osluskivaci;

import aleksic.Controllers.KontrolerGUILogin;
import javafx.event.Event;
import javafx.event.EventHandler;

import java.io.IOException;

public class OsluskivacCancel implements EventHandler {
    KontrolerGUILogin loginController;

    public OsluskivacCancel(KontrolerGUILogin loginController) {
        this.loginController = loginController;
    }

    @Override
    public void handle(Event event) {
        try {
            loginController.onCancelAction();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}