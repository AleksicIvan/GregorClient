package aleksic.Controllers.Osluskivaci;

import aleksic.Controllers.KontrolerGUILogin;
import javafx.event.Event;
import javafx.event.EventHandler;

import java.io.IOException;

public class OsluskivacLogin implements EventHandler {
    KontrolerGUILogin loginController;

    public OsluskivacLogin(KontrolerGUILogin loginController) {
        this.loginController = loginController;
    }

    @Override
    public void handle(Event event) {
        try {
            loginController.onLoginAction();
        } catch (IOException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
