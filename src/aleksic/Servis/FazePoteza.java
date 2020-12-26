package aleksic.Servis;

import java.io.Serializable;

public class FazePoteza implements Serializable {
    Faza faza = null;

    public FazePoteza(Faza faza) {
        this.faza = faza;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        switch (faza.toString()) {
            case "IZBACI_ZLATNIK":
                return "Odigraj zlatnik";
            case "ODIGRAJ_VITEZA":
                return "Odigraj viteza";
            case "NAPAD":
                return "Napadaj";
            case "ODBRANA":
                return "Brani se";
            default:
                return "";
        }
    }
}
