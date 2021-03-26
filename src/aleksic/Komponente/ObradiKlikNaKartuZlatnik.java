package aleksic.Komponente;


import aleksic.DomenskiObjekat.Faza;
import aleksic.DomenskiObjekat.Zlatnik;


public class ObradiKlikNaKartuZlatnik extends ObradiKlikNaKartu<Zlatnik> {
    public ObradiKlikNaKartuZlatnik(CardTestComponent komponenta) {
        super(komponenta);
    }

    @Override
    public void obradi(Zlatnik trenutnaKarta) {
        komponenta.guiKontroler.getTransferObjekatIgra().kliknutiZlatnici.add(trenutnaKarta);
        if (komponenta.guiKontroler.getTransferObjekatIgra().fazaPoteza.equals(Faza.IZBACI_ZLATNIK)) {
            komponenta.guiKontroler.getFxml().oduzmiZlatnikIzRukeDonjegIgraca(komponenta);
            komponenta.guiKontroler.getFxml().dodajZlatnikDonjiIgrac(komponenta);
            komponenta.guiKontroler.getTransferObjekatIgra().odigranaKarta = trenutnaKarta;
        }
        if (komponenta.guiKontroler.getTransferObjekatIgra().fazaPoteza.equals(Faza.PLATI)) {
            komponenta.getStyleClass().removeAll();
            komponenta.getStyleClass().add("iskoriscena-karta");
            komponenta.guiKontroler.getTransferObjekatIgra().odigranaKarta = trenutnaKarta;
        }
    }
}
