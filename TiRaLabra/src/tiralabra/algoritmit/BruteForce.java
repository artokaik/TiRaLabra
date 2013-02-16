/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.algoritmit;

import tiralabra.tietorakenteet.pino.Pino;
import tiralabra.tietorakenteet.verkko.XYVerkko;

/**
 * Brute force käy kaikki mahdolliset reitit läpi ja tallettaa lyhimmän reitin muistiin. Saa parametrinaan reitin, josta lyhin reitti etsitään.
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
     *
     * Etsii lyhimmän reitin verkosta, lähtee liikkeelle solmusta 0.
     * 
     */
    public void etsiLyhinReitti() {
        etsiReittia(0, 0, new Pino<Integer>());
    }
    // Oletus, että alussa lähdettiin solmusta 0 (Lähtösolmulla ei bruteforcessa ole väliä koska lopussa palataan aina lähtösolmuun)

    private void etsiReittia(int lahtoSolmu, double matka, Pino<Integer> reitti) {
        reitti.push(lahtoSolmu);
        kayty[lahtoSolmu] = true;
        for (int i = 0; i < kaaret.length; i++) {
            if (!kayty[i]) {
                matka += kaaret[lahtoSolmu][i];
                kayty[i] = true;
                etsiReittia(i, matka, reitti);
                kayty[i] = false;
                matka -= kaaret[lahtoSolmu][i];
            }
        }
        if (this.ollaankoReitinLopussa()) {
            paivitaLyhin(lahtoSolmu, matka, reitti);
        }
        reitti.pop();
    }
    

    private void paivitaLyhin(int lahtoSolmu, double matka, Pino<Integer> reitti) {
        matka += kaaret[lahtoSolmu][0];
        if (matka < lyhimmanReitinPituus) {
            lyhimmanReitinPituus = matka;
            lyhinReitti = (Pino<Integer>) reitti.clone();
        }
    }
}
