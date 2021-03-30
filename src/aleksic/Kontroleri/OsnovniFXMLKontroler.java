package aleksic.Kontroleri;

import aleksic.Pogledi.ViewManager;

public class OsnovniFXMLKontroler {
    protected ViewManager viewManager;
    private String nazivFxml;

    public OsnovniFXMLKontroler(ViewManager viewManager, String nazivFxml) {
        this.viewManager = viewManager;
        this.nazivFxml = nazivFxml;
    }

    public ViewManager getViewManager() {
        return viewManager;
    }

    public String getNazivFxml() {
        return nazivFxml;
    }

    public String getFxmlName() {
        return nazivFxml;
    }
}
