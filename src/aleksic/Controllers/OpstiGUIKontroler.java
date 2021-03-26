package aleksic.Controllers;

import aleksic.Servis.SocketSingleton;
import aleksic.TransferObjekat.TransferObjekatIgra;
import aleksic.Views.ViewManager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

abstract public class OpstiGUIKontroler {
    protected Socket soketK;
    ObjectOutputStream out;
    ObjectInputStream in;
    ViewManager vm;
    TransferObjekatIgra toi;

    public OpstiGUIKontroler() throws IOException {
        soketK = SocketSingleton.getInstance().getSoketK();
        in = SocketSingleton.getInstance().getIn();
        out = SocketSingleton.getInstance().getOut();
    }

    public abstract void pozivSO(String nazivSO);

    public abstract ViewManager getVm();

    public abstract void setToi(TransferObjekatIgra toi) throws IOException;

    public TransferObjekatIgra getTransferObjekatIgra() {
        return toi;
    }

    public void setTransferObjekatIgra(TransferObjekatIgra transferObjekatIgra) {
        this.toi = transferObjekatIgra;
        this.toi.kliknutiZlatnici = new ArrayList<>();
        this.toi.kliknutiVItezovi = new ArrayList<>();
    }

    public Socket getSoketK() {
        return soketK;
    }

    public void setSoketK(Socket soketK) {
        this.soketK = soketK;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public void setIn(ObjectInputStream in) {
        this.in = in;
    }
}
