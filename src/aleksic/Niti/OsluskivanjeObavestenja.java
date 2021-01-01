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
//                toi = (TransferObjekatIgrac) new ObjectInputStream(opstiGUIKontroler.getSoketK().getInputStream()).readObject();
                toi = (TransferObjekatIgrac) SocketSingleton.getInstance().getIn().readObject();
//                toi = (TransferObjekatIgrac) opstiGUIKontroler.getVm().getIn().readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

//                izvrsi(gto);
            Platform.runLater(() -> {
                System.out.println("Provera nit promena..." + toi.poruka);
                System.out.println("toi.indeks..." + toi.indeks);
                if (toi.prviIgrac != null) {
                    System.out.println("toi.prviigrac..." + toi.prviIgrac.vratiKorisnickoIme());
                }
                if (toi.drugiIgrac != null) {
                    System.out.println("toi.drugiigrac..." + toi.drugiIgrac.vratiKorisnickoIme());
                }
                if (toi.brojigraca != null) {
                    System.out.println("toi.listaIgraca.sz..." + toi.brojigraca);
                }
                try {
                    opstiGUIKontroler.setToi(toi);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
