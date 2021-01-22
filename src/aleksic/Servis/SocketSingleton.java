package aleksic.Servis;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketSingleton {
    private static SocketSingleton instance;
    private Socket soketK;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private SocketSingleton() throws IOException {
        soketK = new Socket("127.0.0.1", 9001);
        out = new ObjectOutputStream(soketK.getOutputStream());
        in = new ObjectInputStream(soketK.getInputStream());
    }

    public static SocketSingleton getInstance() throws IOException {
        if (instance == null) {
            instance = new SocketSingleton();
        }
        return instance;
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
