package aleksic.Niti;

import aleksic.Kontroleri.OpstiGUIKontroler;
import aleksic.Servis.SocketSingleton;
import aleksic.TransferObjekat.TransferObjekatIgra;
import javafx.application.Platform;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class OsluskivanjeObavestenja extends Thread {
    static OsluskivanjeObavestenja instance;
    OpstiGUIKontroler opstiGUIKontroler;
    Socket socket;
    ObjectInputStream in;
    TransferObjekatIgra toi;

    private OsluskivanjeObavestenja() {
        start();
    }

    public static synchronized OsluskivanjeObavestenja getInstance() throws IOException {
        if (instance == null) {
            instance = new OsluskivanjeObavestenja();
        }
        return instance;
    }

    public void setOpstiGUIKontroler(OpstiGUIKontroler opstiGUIKontroler) {
        this.opstiGUIKontroler = opstiGUIKontroler;
    }


    @Override
    public void run() {
        while (true) {
            try {
                toi = (TransferObjekatIgra) SocketSingleton.getInstance().getIn().readObject();
                System.out.println("toi poruka " + toi.poruka);
            } catch (IOException e) {
                System.out.println("pb 1");
                try {
                    opstiGUIKontroler.getSoketK().close();
                    return;
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.println("pb 2");
                e.printStackTrace();
            }
            Platform.runLater(() -> {
                try {
                    if (toi.nazivOperacije.equals("odustanak")) return;
                    opstiGUIKontroler.setToi(toi);
                } catch (IOException e) {
                    System.out.println("pb 3");

                    e.printStackTrace();
                }
            });
        }
    }
}
