package aleksic.Komponente;

import aleksic.DomenskiObjekat.Faza;
import aleksic.DomenskiObjekat.Igrac;
import aleksic.DomenskiObjekat.Vitez;
import aleksic.TransferObjekat.TransferObjekatIgra;


public class ObradiKlikNaKartuVitez extends ObradiKlikNaKartu<Vitez> {
    public ObradiKlikNaKartuVitez(CardComponent komponenta) {
        super(komponenta);
    }

    @Override
    public void obradi(Vitez trenutnaKarta) {
        TransferObjekatIgra transferObjekat = komponenta.guiKontroler.getTransferObjekatIgra();
        if (transferObjekat.fazaPoteza.equals(Faza.IZBACI_VITEZA)) {
            System.out.println("Faza poteza je IZBACI_VITEZA");
            Igrac igracNaPotezu = transferObjekat.igracNaPotezu.vratiKorisnickoIme().equals(komponenta.guiKontroler.getTransferObjekatIgra().prviIgrac.vratiKorisnickoIme())
                    ? transferObjekat.prviIgrac
                    : transferObjekat.drugiIgrac;
            if (igracNaPotezu.vratiTalon().getRedZlatnika().size() != transferObjekat.kliknutiVItezovi.size()) {
                transferObjekat.kliknutiVItezovi.add(trenutnaKarta);
                komponenta.guiKontroler.getFxml().oduzmiVitezaIzRukeDonjegIgraca(komponenta);
                komponenta.guiKontroler.getFxml().dodajVitezaDonjiIgrac(komponenta);
                transferObjekat.odigranaKarta = trenutnaKarta;
            } else {
                for (int i = 0; i < komponenta.guiKontroler.getFxml().getDonjiIgracRuka().getChildren().size(); i++) {
                    komponenta.guiKontroler.getFxml().getDonjiIgracRuka().getChildren().get(i).setDisable(true);
                }
            }

        }


        if (transferObjekat.fazaPoteza.equals(Faza.NAPAD)) {
            System.out.println("Faza poteza je NAPAD");
            transferObjekat.kliknutiVItezovi.add(trenutnaKarta);
            komponenta.guiKontroler.getFxml().oduzmiVitezaIzRedaVitezovaDonjegIgraca(komponenta);
            komponenta.guiKontroler.getFxml().dodajVitezaUNapadDonjiIgrac(komponenta);
            transferObjekat.odigranaKarta = trenutnaKarta;
        }

        if (transferObjekat.fazaPoteza.equals(Faza.ODBRANA)) {
            System.out.println("Faza poteza je ODBRANA");
            transferObjekat.kliknutiVItezovi.add(trenutnaKarta);
            komponenta.guiKontroler.getFxml().oduzmiVitezaIzRedaVitezovaDonjegIgraca(komponenta);
            komponenta.guiKontroler.getFxml().dodajVitezaUNapadDonjiIgrac(komponenta);
            transferObjekat.odigranaKarta = trenutnaKarta;
        }
    }
}
