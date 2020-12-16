package aleksic.Controllers;

import aleksic.Controllers.Osluskivaci.OsluskivacLogin;
import aleksic.Models.Igrac;
import aleksic.Niti.OsluskivanjeObavestenja;
import aleksic.TransferObjekat.TransferObjekatIgrac;
import aleksic.Views.ViewManager;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class KontrolerGUILogin extends OpstoGUIKontroler {
    FXMLLoginController fxmlLoginController;
    ViewManager vm;
    TransferObjekatIgrac toi;

    public KontrolerGUILogin(FXMLLoginController loginController, ViewManager viewManager) throws IOException {
        fxmlLoginController = loginController;
        this.vm = viewManager;
//        this.toi = viewManager.getToi();
        loginController.login.setOnAction(new OsluskivacLogin(this));
        this.soketK = viewManager.getSoketK();
    }

    public void onLoginAction () throws IOException {
        System.out.println("Login action initiated!");
        Igrac igrac = new Igrac(fxmlLoginController.korisnickoIme.getText(), fxmlLoginController.korisnickaSifra.getText());
        vm.getToi().igr = igrac;
//        fxmlLoginController.gregorIgra.dodajIgraca(igrac);
        vm.pozivSO("kreirajIgraca");
        OsluskivanjeObavestenja osluskivacObavestenja = OsluskivanjeObavestenja.getInstance();
        osluskivacObavestenja.setOpstiGUIKontroler(this);
        vm.prikaziMain();
        vm.zatvoriPozornicu(getLoginStage());
    }

    public Stage getLoginStage () {
        Stage stage = (Stage) fxmlLoginController.login.getScene().getWindow();
        return stage;
    }

    @Override
    public ViewManager getVm() {
        return vm;
    }

    @Override
    public void setToi(TransferObjekatIgrac toi) {
        vm.setToi(toi);
    }
}
