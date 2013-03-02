/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.algoritmit;

import tiralabra.tietorakenteet.pino.Pino;
import tiralabra.tietorakenteet.verkko.XYVerkko;

/**
 * Brute force käy kaikki mahdolliset reitit läpi ja tallettaa lyhimmän reitin
 * muistiin. Saa parametrinaan reitin, josta lyhin reitti etsitään.
 *
 * @author Arto
 */
public class BruteForce extends ReitinEtsija {

    /**
     *
     * @param verkko
     */
    public BruteForce(XYVerkko verkko) {
        super(verkko);

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
        etsiReittia(0, 0, reitti);
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
     * Lisää parametrina annetun solmun reittiin kutsuu etsiReittia-metodia asettaen
     * parametrina annetun maalisolmun seuraavaksi lähtösolmuksi.
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
        etsiReittia(seuraava, pituus, reitti);
        kayty[seuraava] = false;
        pituus -= kaaret[lahto][seuraava];
        reitti.pop();
    }
//    /**
//     *
//     * Etsii lyhimmän reitin verkosta, lähtee liikkeelle solmusta 0.
//     * 
//     */
//    public void etsiLyhinReitti() {
//        
//        etsiReittia(0, 0, new Pino<Integer>());
//    }
//    // Oletus, että alussa lähdettiin solmusta 0 (Lähtösolmulla ei bruteforcessa ole väliä koska lopussa palataan aina lähtösolmuun)
//
//    private void etsiReittia(int lahtoSolmu, double matka, Pino<Integer> reitti) {
//        reitti.push(lahtoSolmu);
//        kayty[lahtoSolmu] = true;
//        for (int i = 0; i < kaaret.length; i++) {
//            if (!kayty[i]) {
//                matka += kaaret[lahtoSolmu][i];
//                kayty[i] = true;
//                etsiReittia(i, matka, reitti);
//                kayty[i] = false;
//                matka -= kaaret[lahtoSolmu][i];
//            }
//        }
//        if (this.ollaankoReitinLopussa()) {
//            paivitaLyhin(lahtoSolmu, matka, reitti);
//        }
//        reitti.pop();
//    }
//    
//
//    private void paivitaLyhin(int lahtoSolmu, double matka, Pino<Integer> reitti) {
//        matka += kaaret[lahtoSolmu][0];
//        if (matka < lyhimmanReitinPituus) {
//            lyhimmanReitinPituus = matka;
//            lyhinReitti = (Pino<Integer>) reitti.clone();
//        }
//    }
}
