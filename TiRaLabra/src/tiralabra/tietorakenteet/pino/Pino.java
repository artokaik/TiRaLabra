/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.tietorakenteet.pino;

/**
 * Pino on yksinkertainen tietorakenne, johon voi tallettaa mitä tahansa olioita. Pinoon voi ajassa O(1) lisätä uuden olion (push) tai ottaa pois sinne viimeksi lisätyn objektin (pop). Jokainen olio on tallennettu omaan pinoalkioonsa ja Pino tuntee päällimmäisen pinoalkion (oliomuuttuja "eka").
 * @param <S> 
 * @author Arto
 */
public class Pino<S> {

    private PinoAlkio eka;
    
    /**
     *
     */
    public Pino() {
        eka = null;
    }

    /**
     * Lisää alkion pinon päälle.
     * @param sisalto
     */
    public void push(S sisalto) {
        PinoAlkio uusiAlkio = new PinoAlkio<S>(sisalto);
        uusiAlkio.setSeuraava(eka);
        eka = uusiAlkio;
    }

    /**
     * Palauttaa pinon päällimmäisen alkion.
     * @return
     */
    public S pop() {
        if (empty()) {
            return null;
        }
        S palautus = (S) eka.getSisalto();
        eka = eka.getSeuraava();
        return palautus;
    }

    /**
     * Palauttaa true jos Pino on tyhjä, muuten false;
     * @return
     */
    public boolean empty() {
        if (eka == null) {
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
        while (x.getSeuraava() != null) {
            PinoAlkio z = new PinoAlkio(x.getSeuraava().getSisalto());
            y.setSeuraava(z);
            x = x.getSeuraava();
            y = z;
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
