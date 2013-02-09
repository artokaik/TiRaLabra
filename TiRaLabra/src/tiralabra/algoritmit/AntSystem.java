/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.algoritmit;

import java.util.Random;
import tiralabra.tietorakenteet.pino.Pino;
import tiralabra.tietorakenteet.verkko.XYKoordinaatti;
import tiralabra.tietorakenteet.verkko.XYVerkko;

/**
 *
 * @author Arto
 */
public class AntSystem extends ReitinEtsija {

    private Random arpoja;
    private double[][] feromoni;
    private double alpha;
    private double beta;
    private double q;

    /**
     *
     * @param verkko Parametri verkko on verkko, josta lyhin reitti etsitään
     * @param alpha Parametri alpha määrittää feromonin merkityksen kun arvotaan muurahaisen käyttämää reittiä
     * @param beta Parametri beta määrittää verkon kaarten painojen merkityksen kun arvotaan muurahaisen käyttämää reittiä
     * @param c Parametri c on feromonin alkuarvo
     * @param q
     */
    public AntSystem(XYVerkko verkko, double alpha, double beta, double c, double q) {
        super(verkko);
        arpoja = new Random();
        feromoni = new double[verkko.getVerkko().length][verkko.getVerkko().length];
        for (int i = 0; i < solmut.length; i++) {
            for (int j = 0; j < solmut.length; j++) {
                feromoni[i][j] = c;
            }
        }
        this.alpha = alpha;
        this.beta = beta;
        this.q = q;
    }

    /**
     *
     * @param verkko
     */
    public AntSystem(XYVerkko verkko) {
        super(verkko);
        arpoja = new Random();
        feromoni = new double[verkko.getVerkko().length][verkko.getVerkko().length];
        for (int i = 0; i < solmut.length; i++) {
            for (int j = 0; j < solmut.length; j++) {
                feromoni[i][j] = 0.01;
            }
        }
        this.alpha = 1;
        this.beta = 3;
        this.q = 1000;
    }

    /**
     *
     * @return
     */
    public boolean etsiLyhinReitti() {
        for (int i = 0; i < verkko.length*10; i++) {
            for (int j = 0; j < kayty.length; j++) {
                kayty[j] = false;
                for (int k = 0; k < verkko.length; k++) {
                    feromoni[j][k] *= 0.90;
                }
            }
            muurahainen(0);
        }
        return true;
    }

    /**
     *
     * @param alku
     */
    public void muurahainen(int alku) {
        Pino<Integer> reitti = new Pino<Integer>();
        double pituus = 0;
        int i = alku;
        reitti.push(i);
        kayty[i] = true;
        double[] p = new double[verkko.length];

        while (!ollaankoReitinLopussa()) {
            double totalP = laskeTodennakoisyydet(p, i);
            int j = arvoSeuraava(totalP, p);
            reitti.push(j);
            kayty[j] = true;
            pituus += verkko[i][j];
            i = j;
        }
        pituus += verkko[i][alku];
        if (pituus < this.lyhimmanReitinPituus) {
            lyhimmanReitinPituus = pituus;
            lyhinReitti = reitti.clone();
        }
        i = alku;
        while (!reitti.empty()) {
            int j = reitti.pop();
            feromoni[i][j] += q / pituus;
            i = j;
        }
    }

    /**
     *
     * @param p
     * @param i
     * @return
     */
    public double laskeTodennakoisyydet(double[] p, int i) {
        double totalP = 0;
        for (int j = 0; j < verkko.length; j++) {
            if (!kayty[j]) {
                p[j] = Math.pow(feromoni[i][j], alpha) * Math.pow(1 / verkko[i][j], beta);
                totalP += p[j];
            } else {
                p[j] = 0;
            }
        }
        return totalP;
    }

    /**
     *
     * @param totalP
     * @param p
     * @return
     */
    public int arvoSeuraava(double totalP, double[] p) {
        double arvottu = arpoja.nextDouble() * totalP;
        int j = -1;
        while (arvottu > 0) {
            j++;
            arvottu -= p[j];
        }
        return j;
    }
}
