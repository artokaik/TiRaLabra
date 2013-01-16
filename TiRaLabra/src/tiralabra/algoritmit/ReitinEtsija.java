/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.algoritmit;

import tiralabra.tietorakenteet.pino.Pino;
import tiralabra.tietorakenteet.verkko.XYKoordinaatti;
import tiralabra.tietorakenteet.verkko.XYVerkko;

/**
 *
 * @author Arto
 */
public abstract class ReitinEtsija {

    /**
     *
     */
    protected double[][] verkko;
    /**
     *
     */
    protected XYKoordinaatti[] solmut;
    /**
     *
     */
    protected double lyhimmanReitinPituus;
    /**
     *
     */
    protected Pino<Integer> lyhinReitti;
    /**
     *
     */
    protected boolean[] kayty;

    /**
     *
     * @param verkko
     */
    public ReitinEtsija(XYVerkko verkko) {
        this.verkko = verkko.getVerkko();
        lyhimmanReitinPituus = Double.MAX_VALUE;
        lyhinReitti = new Pino<Integer>();
        this.kayty = new boolean[verkko.getSolmut().length];
        this.solmut = verkko.getSolmut();
    }

    /**
     *
     * @return
     */
    protected boolean ollaankoReitinLopussa() {
        for (int i = 0; i < kayty.length; i++) {
            if (!kayty[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @return
     */
    public double[][] getVerkko() {
        return verkko;
    }

    /**
     *
     * @return
     */
    public XYKoordinaatti[] getSolmut() {
        return solmut;
    }

    /**
     *
     * @return
     */
    public double getLyhimmanReitinPituus() {
        return lyhimmanReitinPituus;
    }

    /**
     *
     * @return
     */
    public Pino<Integer> getLyhinReitti() {
        return lyhinReitti;
    }
}
