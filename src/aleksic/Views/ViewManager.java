package aleksic.Views;

import aleksic.Controllers.OsnovniFXMLKontroler;
import aleksic.Controllers.FXMLGlavniProzorDocumentController;
import aleksic.Controllers.FXMLLoginController;
import aleksic.DomenskiObjekat.Igrac;
import aleksic.TransferObjekat.TransferObjekatIgra;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;

public class ViewManager {
    TransferObjekatIgra toi;
    Stage currentStage;
    Scene currentScene;
    Igrac trenutnoUlogovaniIgrac = null;

    public ViewManager() {
        toi = new TransferObjekatIgra();
    }

    public Igrac getTrenutnoUlogovaniIgrac() {
        return trenutnoUlogovaniIgrac;
    }

    public void setTrenutnoUlogovaniIgrac(Igrac trenutnoUlogovaniIgrac) {
        this.trenutnoUlogovaniIgrac = trenutnoUlogovaniIgrac;
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void setCurrentScene(Scene currentScene) {
        this.currentScene = currentScene;
    }

    public Stage getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    public TransferObjekatIgra getToi() {
        return toi;
    }

    public void setToi(TransferObjekatIgra toi) {
        this.toi = toi;
    }

    public void prikaziLogin() {
        System.out.println("show login window called");
        OsnovniFXMLKontroler loginController = new FXMLLoginController(this, "FXMLLogin.fxml");
        initializeStage(loginController);
    }

    public void prikaziMain() {
        System.out.println("show main window called");

        OsnovniFXMLKontroler glavniController = new FXMLGlavniProzorDocumentController(this, "FXMLGlavniProzor.fxml");
        initializeStage(glavniController);
    }

    private void initializeStage (OsnovniFXMLKontroler osnovniFXMLKontroler) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(osnovniFXMLKontroler.getFxmlName()));
        fxmlLoader.setController(osnovniFXMLKontroler);
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene scene = new Scene(parent);
        Stage stage = new Stage();

        setCurrentStage(stage);
        setCurrentScene(scene);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    public void zatvoriPozornicu(Stage stageToClose) {
        stageToClose.close();
    }
}
