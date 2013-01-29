/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.algoritmit;

import tiralabra.tietorakenteet.pino.Pino;
import tiralabra.tietorakenteet.verkko.XYVerkko;

/**
 * BranchAndBOund etsii lyhimmän reitin hieman nopeammin kuin BruteForce, mutta
 * kuitenkin hitaasti. Saa parametrinaan verkon, josta lyhin reitti etsitään.
 *
 * @author Arto
 */
public class BranchAndBound extends ReitinEtsija {

    private int[][] lahimmat;

    /**
     *
     * @param xYVerkko
     */
    public BranchAndBound(XYVerkko xYVerkko) {
        super(xYVerkko);
        lahimmat = new int[verkko.length][verkko.length];

    }

    /**
     * Metodi käy rekursion avulla järjestyksessä läpi kaikki mahdolliset
     * reitit, mutta lopettaa reitin tutkimisen aina jos se on pidempi kuin
     * lyhin jo löytynyt reitti.
     */
    public void etsiLyhinReitti() {

        etsiReittia(0, 0, new Pino<Integer>());
    }

    //Etsii lyhimmän reitin aloittaen parametrina annetusta solmusta. Alussa matka=0 ja reitti on tyhjä pino, mutta molemmat päivittyvät myöhemmin kun metodi kutsuu itseään rekursiivisesti.
    private void etsiReittia(int lahtoSolmu, double matka, Pino<Integer> reitti) {
        reitti.push(lahtoSolmu);
        kayty[lahtoSolmu] = true;
        if (this.ollaankoReitinLopussa()) {
            matka += verkko[lahtoSolmu][0];
            if (matka < lyhimmanReitinPituus) {
                lyhimmanReitinPituus = matka;
                lyhinReitti = (Pino<Integer>) reitti.clone();
            }
        } else {

            for (int i = 0; i < verkko.length; i++) {
                if (!kayty[i]) {
                    matka += verkko[lahtoSolmu][i];
                    if (matka < lyhimmanReitinPituus) {
                        kayty[i] = true;
                        etsiReittia(i, matka, reitti);
                        kayty[i] = false;
                    }
                    matka -= verkko[lahtoSolmu][i];

                }
            }

        }

        reitti.pop();

    }
}
