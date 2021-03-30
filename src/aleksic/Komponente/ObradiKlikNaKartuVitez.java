package aleksic.Komponente;

import aleksic.DomenskiObjekat.Faza;
import aleksic.DomenskiObjekat.Vitez;


public class ObradiKlikNaKartuVitez extends ObradiKlikNaKartu<Vitez> {
    public ObradiKlikNaKartuVitez(CardComponent komponenta) {
        super(komponenta);
    }

    @Override
    public void obradi(Vitez trenutnaKarta) {
        komponenta.guiKontroler.getTransferObjekatIgra().kliknutiVItezovi.add(trenutnaKarta);
        if (komponenta.guiKontroler.getTransferObjekatIgra().fazaPoteza.equals(Faza.IZBACI_VITEZA)) {
            System.out.println("Faza poteza je IZBACI_VITEZA");
            komponenta.guiKontroler.getFxml().oduzmiVitezaIzRukeDonjegIgraca(komponenta);
            komponenta.guiKontroler.getFxml().dodajVitezaDonjiIgrac(komponenta);
            komponenta.guiKontroler.getTransferObjekatIgra().odigranaKarta = trenutnaKarta;
        }


        if (komponenta.guiKontroler.getTransferObjekatIgra().fazaPoteza.equals(Faza.NAPAD)) {
            System.out.println("Faza poteza je NAPAD");
            komponenta.guiKontroler.getFxml().oduzmiVitezaIzRedaVitezovaDonjegIgraca(komponenta);
            komponenta.guiKontroler.getFxml().dodajVitezaUNapadDonjiIgrac(komponenta);
            komponenta.guiKontroler.getTransferObjekatIgra().odigranaKarta = trenutnaKarta;
        }

        if (komponenta.guiKontroler.getTransferObjekatIgra().fazaPoteza.equals(Faza.ODBRANA)) {
            System.out.println("Faza poteza je ODBRANA");
            komponenta.guiKontroler.getFxml().oduzmiVitezaIzRedaVitezovaDonjegIgraca(komponenta);
            komponenta.guiKontroler.getFxml().dodajVitezaUNapadDonjiIgrac(komponenta);
            komponenta.guiKontroler.getTransferObjekatIgra().odigranaKarta = trenutnaKarta;
        }
    }
}
