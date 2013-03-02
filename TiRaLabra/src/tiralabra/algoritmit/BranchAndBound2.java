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
 * Lopettaa etsimisen mikäli reitin pituus plus teoreettinen jäljellä oleva
 * minimimatka on pidempi kuin lyhin jo löytynyt reitti.
 *
 * @author Arto
 */
public class BranchAndBound2 extends ReitinEtsija {

    private double[] minimiMatka;

    /**
     *
     * @param xYVerkko
     */
    public BranchAndBound2(XYVerkko xYVerkko) {
        super(xYVerkko);
        minimiMatka = new double[solmut.length];
        laskeMinimiMatkat();

    }

    /**
     *
     * Laskee teoreettisen minimimatkan maaliin jos reitistä puuttuu vielä n
     * kaarta. minimiMatka[i] on i+1 lyhimmän kaaren yhteenlasketut pituudet.
     *
     */
    public void laskeMinimiMatkat() {
        for (int i = 0; i < kaaret.length; i++) {
            for (int j = i + 1; j < kaaret.length; j++) {
                int k = minimiMatka.length - 1;
                while (minimiMatka[k] > kaaret[i][j]) {
                    if (k < minimiMatka.length - 1) {
                        minimiMatka[k + 1] = minimiMatka[k];
                    }
                    minimiMatka[k] = kaaret[i][j];
                }
            }
        }
        for (int i = 1; i < minimiMatka.length; i++) {
            minimiMatka[i] += minimiMatka[i - 1];
        }
    }

    /**
     * Metodi käy rekursion avulla (etsiReittia kutsuu meneSeuraavaanSolmuun ja
     * päinvastoin) läpi kaikki mahdolliset reitit, mutta lopettaa reitin
     * tutkimisen aina jos se on pidempi kuin lyhin jo löytynyt reitti.
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
     * @param lahtoSolmu Solmu jossa ollaan tällä hetkellä
     * @param solmuja Solmujen määrä reitissä
     * @param reitti Reitti tähän asti
     * @param pituus Tähän astisen reitin pituus
     */
    public void etsiReittia(int lahtoSolmu, int solmuja, double pituus, Pino<Integer> reitti) {
        if (this.ollaankoReitinLopussa()) {
            pituus += kaaret[lahtoSolmu][0];
            paivitaLyhinReitti(reitti, pituus);
            return;
        }
        for (int i = 0; i < kaaret.length; i++) {
            if (!kayty[i]) {
                meneSeuraavaanSolmuun(lahtoSolmu, i, solmuja + 1, reitti, pituus);
            }
        }
    }

    /**
     * Lisää parametrina annetun solmun reittiin ja mikäli reitti tähän asti on
     * lyhyempi kuin lyhin jo löydetty, kutsuu etsiReittia-metodia asettaen
     * parametrina annetun maalisolmun seuraavaksi lähtösolmuksi. Mikäli reitin
     * pituus plus teoreettinen jäljellä oleva minimimatka on pidempi kuin lyhin
     * jo löytynyt reitti, lopetetaan etsintä tämän reitin osalta.
     *
     * @param lahto Solmu jossa ollaan tällä hetkellä
     * @param seuraava Seuraava solmu
     * @param solmuja Solmujen määrä reitissä
     * @param reitti Reitti tähän asti
     * @param pituus Tähän astisen reitin pituus
     */
    public void meneSeuraavaanSolmuun(int lahto, int seuraava, int solmuja, Pino<Integer> reitti, double pituus) {
        reitti.push(seuraava);
        pituus += kaaret[lahto][seuraava];
        kayty[seuraava] = true;
        if (pituus + minimiMatka[solmut.length - solmuja] < lyhimmanReitinPituus) {
            etsiReittia(seuraava, solmuja, pituus, reitti);
        }
        kayty[seuraava] = false;
        pituus -= kaaret[lahto][seuraava];
        reitti.pop();
    }
}
