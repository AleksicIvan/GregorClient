package aleksic.Views;

import aleksic.Controllers.BaseController;
import aleksic.Controllers.FXMLGlavniProzorDocumentController;
import aleksic.Controllers.FXMLLoginController;
import aleksic.DomenskiObjekat.Igrac;
import aleksic.Servis.SocketSingleton;
import aleksic.TransferObjekat.TransferObjekatIgra;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ViewManager {
    protected Socket soketK;
    ObjectOutputStream out;
    ObjectInputStream in;
    TransferObjekatIgra toi = null;
    Stage currentStage;
    Scene currentScene;
    Igrac trenutnoUlogovaniIgrac = null;

    public ObjectOutputStream getOut() {
        return out;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public ViewManager() throws IOException {
        soketK = SocketSingleton.getInstance().getSoketK();
        in = SocketSingleton.getInstance().getIn();
        out = SocketSingleton.getInstance().getOut();
        out = SocketSingleton.getInstance().getOut();
        toi = new TransferObjekatIgra();
        toi.kliknutiZlatnici = new ArrayList<>();
        toi.kliknutiVItezovi = new ArrayList<>();
        pozivSO("init");
    }

    public void pozivSO(String nazivSO) {
        toi.nazivOperacije = nazivSO;

        try {
            System.out.println("Saljem TOI");
            out.reset();
            out.writeObject(toi);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
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

    public Socket getSoketK() {
        return soketK;
    }

    public TransferObjekatIgra getToi() {
        return toi;
    }

    public void setToi(TransferObjekatIgra toi) {
        this.toi = toi;
    }

    public void prikaziLogin() {
        System.out.println("show login window called");
        BaseController loginController = new FXMLLoginController(soketK, this, "FXMLLogin.fxml");
        initializeStage(loginController);
    }

    public void prikaziMain() {
        System.out.println("show main window called");

        BaseController glavniController = new FXMLGlavniProzorDocumentController(soketK,this, "FXMLGlavniProzor.fxml");
        initializeStage(glavniController);
    }

    private void initializeStage (BaseController baseController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(baseController.getFxmlName()));
        fxmlLoader.setController(baseController);
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
