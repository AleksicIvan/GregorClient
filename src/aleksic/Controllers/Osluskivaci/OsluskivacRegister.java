package aleksic.Controllers.Osluskivaci;

import aleksic.Controllers.KontrolerGUILogin;
import javafx.event.Event;
import javafx.event.EventHandler;

import java.io.IOException;

public class OsluskivacRegister implements EventHandler {
    KontrolerGUILogin loginController;

    public OsluskivacRegister(KontrolerGUILogin loginController) {
        this.loginController = loginController;
    }

    @Override
    public void handle(Event event) {
        try {
            loginController.onRegisterAction();
        } catch (IOException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
