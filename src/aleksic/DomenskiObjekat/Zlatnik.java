package aleksic.DomenskiObjekat;

public class Zlatnik extends Karta {
    private static final long serialVersionUID = 6529685098267757690L;
    public Zlatnik(Integer id, Integer napad, Integer odbrana, Integer cena, TipKarte tip, boolean iskoriscena) {
        super(id, napad, odbrana, cena, tip, iskoriscena);
    }

    public Zlatnik(Integer id, TipKarte tip) {
        super(id, tip);
    }

    public Zlatnik(Integer id) {
        super(id);
    }
}
