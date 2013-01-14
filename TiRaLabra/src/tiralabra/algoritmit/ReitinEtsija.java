/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.algoritmit;

import tiralabra.tietorakenteet.pino.Pino;
import tiralabra.tietorakenteet.verkko.Verkko;

/**
 *
 * @author Arto
 */
public abstract class ReitinEtsija {

    protected double[][] verkko;
    protected double lyhimmanReitinPituus;
    protected Pino<Integer> lyhinReitti;
    protected boolean[] kayty;

    public ReitinEtsija(Verkko verkko) {
        this.verkko = verkko.getVerkko();
        lyhimmanReitinPituus = Double.MAX_VALUE;
        lyhinReitti = new Pino<Integer>();
        this.kayty = new boolean[verkko.getSolmut().length];
    }

    protected boolean ollaankoReitinLopussa() {
        for (int i = 0; i < kayty.length; i++) {
            if (!kayty[i]) {
                return false;
            }
        }
        return true;
    }

    public double getLyhimmanReitinPituus() {
        return lyhimmanReitinPituus;
    }

    public Pino<Integer> getLyhinReitti() {
        return lyhinReitti;
    }
}
