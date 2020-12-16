package aleksic.Controllers;

import aleksic.Servis.Igra;
import aleksic.Views.ViewManager;

import java.net.Socket;

public abstract class BaseController {
    private Socket soketK;
    protected ViewManager viewManager;
    private String nazivFxml;
    public String poruka;

    public BaseController(Socket soketK, ViewManager viewManager, String nazivFxml) {
        this.viewManager = viewManager;
        this.nazivFxml = nazivFxml;
        this.soketK = soketK;
        this.poruka = "";
    }

    public Socket getSoketK() {
        return soketK;
    }

    public ViewManager getViewManager() {
        return viewManager;
    }

    public String getNazivFxml() {
        return nazivFxml;
    }

    public String getPoruka() {
        return poruka;
    }

    public String getFxmlName() {
        return nazivFxml;
    }

    public abstract String poruka(String poruka);
}
