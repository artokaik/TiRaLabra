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
public class BranchAndBound extends ReitinEtsija {

    /**
     *
     * @param xYVerkko
     */
    public BranchAndBound(XYVerkko xYVerkko) {
        super(xYVerkko);


    }

    /**
     * Metodi käy rekursion avulla (etsiReittia kutsuu meneSeuraavaanSolmuun ja päinvastoin) läpi kaikki mahdolliset
     * reitit, mutta lopettaa reitin tutkimisen aina jos se on pidempi kuin
     * lyhin jo löytynyt reitti.
     */
    public void etsiLyhinReitti() {
        kayty[0] = true;
        Pino<Integer> reitti = new Pino<Integer>();
        reitti.push(0);
        etsiReittia(0, 0, reitti);
    }

    
    /**
     * 
     * Kutsuu lyhimmän reitin päivittävää metodia jos ollaan käyty kaikissa solmuissa. Muussa tapauksessa kutsuu meneSeuraavaanSolmuun-metodia vuorollaan kaikilla käymättömillä solmuilla.
     *
     * @param lahtoSolmu
     * @param pituus
     * @param reitti
     */
    public void etsiReittia(int lahtoSolmu, double pituus, Pino<Integer> reitti) {
        if (this.ollaankoReitinLopussa()) {
            pituus += kaaret[lahtoSolmu][0];
            paivitaLyhinReitti(reitti, pituus);
            return;
        }
        for (int i = 0; i < kaaret.length; i++) {
            if (!kayty[i]) {
                meneSeuraavaanSolmuun(lahtoSolmu, i, reitti, pituus);
            }
        }
    }

    /**
     * Lisää parametrina annetun solmun reittiin ja mikäli reitti tähän asti on lyhyempi kuin lyhin jo löydetty, kutsuu etsiReittia-metodia asettaen parametrina annetun maalisolmun seuraavaksi lähtösolmuksi.
     *
     * @param lahto 
     * @param seuraava
     * @param reitti
     * @param pituus
     */
    public void meneSeuraavaanSolmuun(int lahto, int seuraava, Pino<Integer> reitti, double pituus) {
        reitti.push(seuraava);
        pituus += kaaret[lahto][seuraava];
        kayty[seuraava] = true;
        if (pituus < lyhimmanReitinPituus) {
            etsiReittia(seuraava, pituus, reitti);
        }
        kayty[seuraava] = false;
        pituus -= kaaret[lahto][seuraava];
        reitti.pop();
    }
}
