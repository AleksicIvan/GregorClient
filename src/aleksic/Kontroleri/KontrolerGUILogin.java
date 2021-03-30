package aleksic.Kontroleri;

import aleksic.Kontroleri.Osluskivaci.OsluskivacCancel;
import aleksic.Kontroleri.Osluskivaci.OsluskivacLogin;
import aleksic.Kontroleri.Osluskivaci.OsluskivacRegister;
import aleksic.DomenskiObjekat.Igrac;
import aleksic.Niti.OsluskivanjeObavestenja;
import aleksic.TransferObjekat.TransferObjekatIgra;
import aleksic.Pogledi.ViewManager;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Optional;

public class KontrolerGUILogin extends OpstiGUIKontroler {
    FXMLLoginController fxmlLoginController;
    ViewManager vm;

    public KontrolerGUILogin(FXMLLoginController loginController, ViewManager viewManager) throws IOException {
        OsluskivanjeObavestenja osluskivacObavestenja = OsluskivanjeObavestenja.getInstance();
        osluskivacObavestenja.setOpstiGUIKontroler(this);
        loginController.login.setOnAction(new OsluskivacLogin(this));
        loginController.cancel.setOnAction(new OsluskivacCancel(this));
        loginController.register.setOnAction(new OsluskivacRegister(this));
        fxmlLoginController = loginController;
        this.vm = viewManager;
        setTransferObjekatIgra(viewManager.getToi());
        pozivSO("init");
    }



    public void onLoginAction () throws IOException, IllegalAccessException {
        System.out.println("Login action initiated!");
        Igrac igrac = napraviIgraca(fxmlLoginController);
        vm.setTrenutnoUlogovaniIgrac(igrac);
        toi.igr = igrac;
        toi.poruka = "";
        pozivSO("kreirajIgraca");
    }

    public void onCancelAction () throws IOException {
        System.out.println("Cancel action initiated!");
        toi.poruka = "";
        pozivSO("odustanak");
        vm.getCurrentStage().close();
    }

    public void onRegisterAction () throws IOException, IllegalAccessException {
        System.out.println("Register action initiated!");
        toi.poruka = "";
        if (!fxmlLoginController.korisnickoIme.getText().isEmpty() || !fxmlLoginController.korisnickaSifra.getText().isEmpty()) {
            Igrac noviIgrac = napraviIgraca(fxmlLoginController);
            toi.igr = noviIgrac;
            pozivSO("registracija");
        } else {
            Alert neuspesnaRegistracija = new Alert(Alert.AlertType.CONFIRMATION);
            neuspesnaRegistracija.setTitle(null);
            neuspesnaRegistracija.setHeaderText(null);
            neuspesnaRegistracija.setContentText("Morate uneti korisničko ime i šifru!");
            neuspesnaRegistracija.showAndWait();
            neuspesnaRegistracija.close();
        }
    }

    // PRIMER REFLEKSIJE
    public Igrac napraviIgraca (FXMLLoginController fxmlLoginController) throws IllegalAccessException {
        Class cls = fxmlLoginController.getClass();
        Field[] fields = cls.getDeclaredFields();
        String imeIgraca = "";
        String sifraIgraca = "";
        for (Field f : fields) {
            f.setAccessible(true);
            if (f.getType().getName().equals("javafx.scene.control.TextField")) {
                TextField imeTextField = (TextField) f.get(fxmlLoginController);
                imeIgraca = imeTextField.getText();
            }
            if (f.getType().getName().equals("javafx.scene.control.PasswordField")) {
                PasswordField pass = (PasswordField) f.get(fxmlLoginController);
                sifraIgraca = pass.getText();
            }
        }

        return new Igrac(imeIgraca, sifraIgraca);
    }

    @Override
    public void setToi (TransferObjekatIgra toi) {
        vm.setToi(toi);
        setTransferObjekatIgra(toi);
        if (toi.nazivOperacije.equals("kreirajIgraca")) {
            if (toi.poruka.startsWith("Greska!")) {
                fxmlLoginController.getLoginError().setText(toi.poruka);
            } else {
                fxmlLoginController.getLoginError().setText("");
                vm.prikaziMain();
                vm.zatvoriPozornicu(getLoginStage());
            }
        }

        if (toi.nazivOperacije.equals("registracija")) {
            if (toi.poruka.startsWith("Greska!")) {
                fxmlLoginController.getLoginError().setText(toi.poruka);
                Alert neuspesnaRegistracija = new Alert(Alert.AlertType.CONFIRMATION);
                neuspesnaRegistracija.setTitle(null);
                neuspesnaRegistracija.setHeaderText(null);
                neuspesnaRegistracija.setContentText(toi.poruka);
                neuspesnaRegistracija.showAndWait();
                neuspesnaRegistracija.close();
            } else {
                fxmlLoginController.getLoginError().setText("");
                Alert uspesnaRegistracija = new Alert(Alert.AlertType.CONFIRMATION);
                uspesnaRegistracija.setTitle(null);
                uspesnaRegistracija.setHeaderText(null);
                uspesnaRegistracija.setContentText(toi.poruka);
                Optional<ButtonType> result = uspesnaRegistracija.showAndWait();
                if (result.get() == ButtonType.OK){
                    toi.nazivOperacije = "kreirajIgraca";
                    toi.poruka = "";
                    pozivSO("kreirajIgraca");
                } else {
                    uspesnaRegistracija.close();
                }
                vm.zatvoriPozornicu(getLoginStage());
                vm.prikaziMain();
            }
        }
    }

    public Stage getLoginStage () {
        Stage stage = (Stage) fxmlLoginController.login.getScene().getWindow();
        return stage;
    }

    @Override
    public void pozivSO(String nazivSO) {
        toi.nazivOperacije = nazivSO;

        try {
            System.out.println("Saljem TOI iz Kontroler GUI Logina");
            out.reset();
            out.writeObject(toi);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ViewManager getVm() {
        return vm;
    }
}
