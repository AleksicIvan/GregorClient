package aleksic.Views;

import aleksic.Controllers.BaseController;
import aleksic.Controllers.FXMLGlavniProzorDocumentController;
import aleksic.Controllers.FXMLLoginController;
import aleksic.TransferObjekat.TransferObjekatIgrac;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ViewManager {
    protected Socket soketK;
    ObjectOutputStream out;
    ObjectInputStream in;
    TransferObjekatIgrac toi = null;
    Stage currentStage;
    Scene currentScene;

    public ObjectOutputStream getOut() {
        return out;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public ViewManager(Socket soketK) throws IOException {
        toi = new TransferObjekatIgrac();
        this.soketK = soketK;
        pozivSO("init");
    }

    public void pozivSO(String nazivSO) {
        toi.nazivOperacije = nazivSO;

        try {
            out = new ObjectOutputStream(soketK.getOutputStream());
            in =  new ObjectInputStream(soketK.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            out.writeObject(toi);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            setToi((TransferObjekatIgrac) in.readObject());
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
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

    public TransferObjekatIgrac getToi() {
        return toi;
    }

    public void setToi(TransferObjekatIgrac toi) {
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
        stage.show();
    }

    public void zatvoriPozornicu(Stage stageToClose) {
        stageToClose.close();
    }
}
