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
 * AntSystem simuloi muurahaisten toimintaa etsiessään lyhintä Hamiltonin
 * kierrosta verkosta. Yksi "muurahainen" kerrallaan lähtee etsimään lyhintä
 * reittiä. "Muurahainen" valitsee seuraavan käymättömän solmun satunnaisesti
 * siten, että se painottaa lähimpiä solmuja ja toisaalta aiempien muurahaisten
 * käyttämiä reittejä (feromoni-taulukko). Kun muurahainen pääsee reitin
 * loppuun, reitin pituus lasketaan ja käytetyn reitin kaarien feromoni-arvot
 * kasvavat sitä enemmän, mitä lyhyempi reitti oli. Tämän jälkeen matkaan lähtee
 * uusi muurahainen. Tätä toistetaan n kertaa.
 *
 * @author Arto
 */
public class AntSystem extends ReitinEtsija {

    private Random arpoja;
    private double[][] feromoni;
    private double alpha;
    private double beta;
    private double q;
    private int n;

    /**
     *
     * @param verkko Parametri verkko on verkko, josta lyhin reitti etsitään
     * @param alpha Parametri alpha määrittää feromonin merkityksen kun arvotaan
     * muurahaisen käyttämää reittiä
     * @param beta Parametri beta määrittää verkon kaarten painojen merkityksen
     * kun arvotaan muurahaisen käyttämää reittiä
     * @param c Parametri c on feromonin alkuarvo kullakin kaarella (mieluiten
     * hyvin pieni positiivinen luku)
     * @param q Parametri q on muurahaisen reitilleen jättämän feromonin määrä.
     * @param n Parametri n on lähetettävien muurahaisten määrä.
     */
    public AntSystem(XYVerkko verkko, double alpha, double beta, double c, double q, int n) {
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
        this.n = n;
    }

    /**
     * Konstruktori kohtuullisen hyvillä oletusparametreilla.
     *
     * @param verkko
     */
    public AntSystem(XYVerkko verkko) {
        this(verkko, 1, 4, 0.01, 1000, verkko.getVerkko().length * 10);
    }

    /**
     *
     * Etsii lyhyen reitin ja palauttaa true.
     *
     * @return Palauttaa true joka tapauksessa.
     */
    public boolean etsiLyhinReitti() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < kayty.length; j++) {
                kayty[j] = false;
                for (int k = 0; k < kaaret.length; k++) {
                    feromoni[j][k] *= 0.95;
                }
            }
            muurahainen(0);
        }
        return true;
    }

    /**
     *
     * Etsii satunnaisen reitin ja jos se on lyhyempi kuin lyhin löydetty,
     * päivittää lyhimmän reitin. Päivittää lopuksi feromonin määrän kaarilla.
     *
     * @param alku Solmu, josta lähdetään liikkeelle.
     */
    private void muurahainen(int alku) {
        Pino<Integer> reitti = new Pino<Integer>();
        double pituus = 0;
        int i = alku;
        reitti.push(i);
        kayty[i] = true;
        while (!ollaankoReitinLopussa()) {
            int j = arvoSeuraava(i);
            reitti.push(j);
            kayty[j] = true;
            pituus += kaaret[i][j];
            i = j;
        }
        pituus += kaaret[i][alku];
        paivitaLyhinReitti(reitti, pituus);
        paivitaFeromonit(reitti, alku, pituus);
    }



    /**
     *
     * Päivittää feromonin määrän kaarilla
     *
     * @param reitti Reitti, jonka kaarille feromoni päivitetään
     * @param lahto Reitin ensimmäinen solmu.
     * @param pituus Reitin pituus.
     */
    
    public void paivitaFeromonit(Pino<Integer> reitti, int lahto, double pituus) {
        while (!reitti.empty()) {
            int tulo = reitti.pop();
            feromoni[lahto][tulo] += q / pituus;
            lahto = tulo;
        }
    }

    /**
     *
     * Arpoo seuraavan solmun.
     *
     * @param lahto Sen solmun indeksi, jota seuraava solmu haetaan.
     * @return Palauttaa arvotun solmun.
     */
    public int arvoSeuraava(int lahto) {
        double[] p = new double[kaaret.length];
        double totalP = laskeTodennakoisyydet(p, lahto);
        double arvottu = arpoja.nextDouble() * totalP;
        int j = -1;
        while (arvottu > 0) {
            j++;
            arvottu -= p[j];
        }
        return j;
    }

    /**
     *
     * Laskee painot, jotka määrittävät todennäköisyyden, jolla kukin solmu
     * valitaan seuraavaksi.
     *
     * @param p Taulukko painoista, jotka vastaavat todennäköisyyttä, jolla
     * muurahainen menee seuraaviin solmuihin (p[i] > 0, voi periaatteessa
     * kasvaa rajatta)
     * @param i Lähtösolmu.
     * @return Palauttaa taulukon p arvojen summan.
     */
    private double laskeTodennakoisyydet(double[] p, int i) {
        double totalP = 0;
        for (int j = 0; j < kaaret.length; j++) {
            if (!kayty[j]) {
                p[j] = Math.pow(feromoni[i][j], alpha) * Math.pow(1 / kaaret[i][j], beta);
                totalP += p[j];
            } else {
                p[j] = 0;
            }
        }
        return totalP;
    }
}
