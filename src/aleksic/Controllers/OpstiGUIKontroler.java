package aleksic.Controllers;

import aleksic.TransferObjekat.TransferObjekatIgrac;
import aleksic.Views.ViewManager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

abstract public class OpstiGUIKontroler {
    protected Socket soketK;
    ObjectOutputStream out;
    ObjectInputStream in;
    ViewManager vm;

    public abstract ViewManager getVm();

    public abstract void setToi(TransferObjekatIgrac toi) throws IOException;

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
