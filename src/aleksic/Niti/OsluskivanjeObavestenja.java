package aleksic.Niti;

import aleksic.Controllers.OpstoGUIKontroler;
import aleksic.Servis.SocketSingleton;
import aleksic.TransferObjekat.TransferObjekatIgrac;
import javafx.application.Platform;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class OsluskivanjeObavestenja extends Thread {
    static OsluskivanjeObavestenja instance;
    OpstoGUIKontroler opstiGUIKontroler;
    Socket socket;
    ObjectInputStream in;
    TransferObjekatIgrac toi;

    private OsluskivanjeObavestenja() {
        start();
    }

    public static synchronized OsluskivanjeObavestenja getInstance() throws IOException {
        if (instance == null) {
            instance = new OsluskivanjeObavestenja();
        }
        return instance;
    }

    public void setOpstiGUIKontroler(OpstoGUIKontroler opstiGUIKontroler) {
        this.opstiGUIKontroler = opstiGUIKontroler;
    }


    @Override
    public void run() {
        while (true) {
            try {
                toi = (TransferObjekatIgrac) SocketSingleton.getInstance().getIn().readObject();
                System.out.println("toi poruka " + toi.poruka);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> {
                try {
                    opstiGUIKontroler.setToi(toi);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
