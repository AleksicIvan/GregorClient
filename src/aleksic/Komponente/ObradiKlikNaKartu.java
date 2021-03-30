package aleksic.Komponente;


public abstract class ObradiKlikNaKartu<T> {
    CardComponent komponenta;
    public ObradiKlikNaKartu(CardComponent komponenta) {
        this.komponenta = komponenta;
        obradi((T) komponenta.trenutnaKarta);
    }
    abstract void obradi(T trenutnaKarta);
}
