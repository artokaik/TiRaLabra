/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.algoritmit;

import tiralabra.tietorakenteet.pino.Pino;
import tiralabra.tietorakenteet.verkko.XYVerkko;

/**
 * BranchAndBound etsii lyhimmän reitin hieman nopeammin kuin BruteForce, mutta
 * kuitenkin hitaasti. Saa parametrinaan verkon, josta lyhin reitti etsitään.
 *
 * @author Arto
 */
public class BranchAndBound2 extends ReitinEtsija {

    private double[] maksimiMatka;

    /**
     *
     * @param xYVerkko
     */
    public BranchAndBound2(XYVerkko xYVerkko) {
        super(xYVerkko);
        maksimiMatka = new double[solmut.length];
        laskeMaksimiMatkat();

    }

    public void laskeMaksimiMatkat() {
        for (int i = 0; i < kaaret.length; i++) {
            for (int j = i + 1; j < kaaret.length; j++) {
                int k = maksimiMatka.length - 1;
                while (maksimiMatka[k] > kaaret[i][j]) {
                    if (k < maksimiMatka.length-1) {
                        maksimiMatka[k + 1] = maksimiMatka[k];
                    }
                    maksimiMatka[k] = kaaret[i][j];
                }
            }
        }
        for (int i = 1; i < maksimiMatka.length; i++) {
            maksimiMatka[i]+=maksimiMatka[i-1];      
        }
    }

    /**
     * Metodi käy rekursion avulla järjestyksessä läpi kaikki mahdolliset
     * reitit, mutta lopettaa reitin tutkimisen aina jos se on pidempi kuin
     * lyhin jo löytynyt reitti.
     */
    public void etsiLyhinReitti() {
        kayty[0] = true;
        Pino<Integer> reitti = new Pino<Integer>();
        reitti.push(0);
        etsiReittia(0, 0, 0, reitti);
    }

    /**
     *
     * Kutsuu lyhimmän reitin päivittävää metodia jos ollaan käyty kaikissa
     * solmuissa. Muussa tapauksessa kutsuu meneSeuraavaanSolmuun-metodia
     * vuorollaan kaikilla käymättömillä solmuilla.
     *
     * @param lahtoSolmu
     * @param pituus
     * @param reitti
     */
    public void etsiReittia(int lahtoSolmu,int solmuja, double pituus, Pino<Integer> reitti) {
        if (this.ollaankoReitinLopussa()) {
            pituus += kaaret[lahtoSolmu][0];
            paivitaLyhinReitti(reitti, pituus);
            return;
        }
        for (int i = 0; i < kaaret.length; i++) {
            if (!kayty[i]) {
                meneSeuraavaanSolmuun(lahtoSolmu, i, solmuja+1, reitti, pituus);
            }
        }
    }

    /**
     * Lisää parametrina annetun solmun reittiin ja mikäli reitti tähän asti on
     * lyhyempi kuin lyhin jo löydetty, kutsuu etsiReittia-metodia asettaen
     * parametrina annetun maalisolmun seuraavaksi lähtösolmuksi.
     *
     * @param lahto
     * @param seuraava
     * @param reitti
     * @param pituus
     */
    public void meneSeuraavaanSolmuun(int lahto, int seuraava, int solmuja, Pino<Integer> reitti, double pituus) {
        reitti.push(seuraava);
        pituus += kaaret[lahto][seuraava];
        kayty[seuraava] = true;
        if (pituus + maksimiMatka[solmut.length-solmuja] < lyhimmanReitinPituus) {
            etsiReittia(seuraava, solmuja, pituus, reitti);
        }
        kayty[seuraava] = false;
        pituus -= kaaret[lahto][seuraava];
        reitti.pop();
    }
}
