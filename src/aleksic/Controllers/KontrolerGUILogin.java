package aleksic.Controllers;

import aleksic.Controllers.Osluskivaci.OsluskivacCancel;
import aleksic.Controllers.Osluskivaci.OsluskivacLogin;
import aleksic.DomenskiObjekat.Igrac;
import aleksic.Niti.OsluskivanjeObavestenja;
import aleksic.TransferObjekat.TransferObjekatIgra;
import aleksic.Views.ViewManager;
import javafx.stage.Stage;

import java.io.IOException;

public class KontrolerGUILogin extends OpstiGUIKontroler {
    FXMLLoginController fxmlLoginController;
    ViewManager vm;
    TransferObjekatIgra toi;

    public KontrolerGUILogin(FXMLLoginController loginController, ViewManager viewManager) throws IOException {
        OsluskivanjeObavestenja osluskivacObavestenja = OsluskivanjeObavestenja.getInstance();
        osluskivacObavestenja.setOpstiGUIKontroler(this);
        loginController.login.setOnAction(new OsluskivacLogin(this));
        loginController.cancel.setOnAction(new OsluskivacCancel(this));
        fxmlLoginController = loginController;
        this.vm = viewManager;
//        this.toi = viewManager.getToi();
        this.soketK = viewManager.getSoketK();
    }

    public void onLoginAction () throws IOException {
        System.out.println("Login action initiated!");
        Igrac igrac = new Igrac(fxmlLoginController.korisnickoIme.getText(), fxmlLoginController.korisnickaSifra.getText());
        vm.getToi().igr = igrac;
        vm.setTrenutnoUlogovaniIgrac(igrac);
        vm.getToi().poruka = "";
        vm.pozivSO("kreirajIgraca");
    }

    public void onCancelAction () throws IOException {
        System.out.println("Cancel action initiated!");
        vm.getToi().poruka = "";
        vm.pozivSO("odustanak");
        vm.getCurrentStage().close();
    }

    @Override
    public void setToi (TransferObjekatIgra toi) {
        vm.setToi(toi);
        if (toi.nazivOperacije.equals("kreirajIgraca")) {
            if (toi.poruka.startsWith("Greska!")) {
                fxmlLoginController.getLoginError().setText(toi.poruka);
            } else {
                fxmlLoginController.getLoginError().setText("");
                vm.prikaziMain();
                vm.zatvoriPozornicu(getLoginStage());
            }
        }
    }

    public Stage getLoginStage () {
        Stage stage = (Stage) fxmlLoginController.login.getScene().getWindow();
        return stage;
    }

    @Override
    public ViewManager getVm() {
        return vm;
    }
}
