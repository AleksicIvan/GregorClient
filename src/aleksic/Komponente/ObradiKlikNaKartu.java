package aleksic.Komponente;


public abstract class ObradiKlikNaKartu<T> {
    CardTestComponent komponenta;
    public ObradiKlikNaKartu(CardTestComponent komponenta) {
        this.komponenta = komponenta;
        obradi((T) komponenta.trenutnaKarta);
    }
    abstract void obradi(T trenutnaKarta);
}
