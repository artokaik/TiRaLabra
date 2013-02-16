/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.algoritmit;

import tiralabra.tietorakenteet.pino.Pino;
import tiralabra.tietorakenteet.verkko.XYKoordinaatti;
import tiralabra.tietorakenteet.verkko.XYVerkko;

/**
 * ReitinEtsija on abstrakti luokka, jonka kaikki algoritmit toteuttavat. ReitinEtsijan muuttujista  * @author Arto
 */
public abstract class ReitinEtsija {

    /**
     * kaaret kertoo solmujen väliset etäisyydet 
     */
    protected double[][] kaaret;
    /**
     *solmut on lista solmuista
     */
    protected XYKoordinaatti[] solmut;
    /**
     * lyhimmanReitinPituus on lyhimman löydetyn reitin pituus
     */
    protected double lyhimmanReitinPituus;
    /**
     * lyhinReitti on lyhin löydetty reitti 
     */
    protected Pino<Integer> lyhinReitti;
    /**
     * kayty-lista kertoo onko solmussa jo käyty
     */
    protected boolean[] kayty;

    /**
     *
     * @param verkko
     */
    public ReitinEtsija(XYVerkko verkko) {
        this.kaaret = verkko.getVerkko();
        lyhimmanReitinPituus = Double.MAX_VALUE;
        lyhinReitti = new Pino<Integer>();
        this.kayty = new boolean[verkko.getSolmut().length];
        this.solmut = verkko.getSolmut();
    }

    /**
     * Palauttaa true jos kaikissa solmuissa on käyty. Muuten palauttaa false
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
    public double[][] getKaaret() {
        return kaaret;
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
