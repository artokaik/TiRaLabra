/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.tietorakenteet.pino;

/**
 *
 * @param <S> 
 * @author Arto
 */
public class Pino<S> {

    private PinoAlkio eka;
    private int koko;

    /**
     *
     */
    public Pino() {
        eka = null;
        koko = 0;
    }

    /**
     *
     * @param sisalto
     */
    public void push(S sisalto) {
        PinoAlkio uusiAlkio = new PinoAlkio<S>(sisalto);
        uusiAlkio.setSeuraava(eka);
        eka = uusiAlkio;
        koko++;
    }

    /**
     *
     * @return
     */
    public S pop() {
        if (empty()) {
            return null;
        }
        S palautus = (S) eka.getSisalto();
        eka = eka.getSeuraava();
        koko--;
        return palautus;
    }

    /**
     *
     * @return
     */
    public boolean empty() {
        if (koko == 0) {
            return true;
        }
        return false;
    }
    
    @Override
    public Pino<S> clone() {
        if(this.empty()){
            return new Pino<S>();
        }
        PinoAlkio x = this.eka;
        PinoAlkio y = new PinoAlkio(x.getSisalto());
        Pino<S> uusiPino = new Pino<S>();
        uusiPino.eka = y;
        uusiPino.koko++;
        while (x.getSeuraava() != null) {
            PinoAlkio z = new PinoAlkio(x.getSeuraava().getSisalto());
            y.setSeuraava(z);
            x = x.getSeuraava();
            y = z;
            uusiPino.koko++;
        }
        return uusiPino;
    }

    @Override
    public String toString() {
        if (this.empty()) {
            return "[]";
        }
        String tuloste = "[";
        Pino<S> uusiPino = this.clone();
        while (uusiPino.eka.getSeuraava() != null) {
            tuloste += uusiPino.pop() + ", ";
        }
        tuloste += uusiPino.pop() + "]";
        return tuloste;
    }
}
