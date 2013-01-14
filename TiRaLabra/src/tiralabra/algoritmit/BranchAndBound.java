/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.algoritmit;

import java.util.ArrayList;
import tiralabra.tietorakenteet.pino.Pino;
import tiralabra.tietorakenteet.verkko.Verkko;

/**
 *
 * @author Arto
 */
public class BranchAndBound extends ReitinEtsija {

    public BranchAndBound(Verkko verkko) {
        super(verkko);

    }

    public void etsiLyhinReitti() {
        etsiReittia(0, 0, new Pino<Integer>());
    }
    // Oletus, että alussa lähdettiin solmusta 0 (Lähtösolmulla ei bruteforcessa ole väliä koska lopussa palataan aina lähtösolmuun)

    private void etsiReittia(int lahtoSolmu, double matka, Pino<Integer> reitti) {
        reitti.push(lahtoSolmu);
        kayty[lahtoSolmu] = true;
        for (int i = 0; i < verkko.length; i++) {
            if (!kayty[i]) {
                matka += verkko[lahtoSolmu][i];
                if ( matka < lyhimmanReitinPituus ) {
                    kayty[i] = true;
                    etsiReittia(i, matka, reitti);
                    kayty[i] = false;
                }
                matka -= verkko[lahtoSolmu][i];

            }

        }
        if (this.ollaankoReitinLopussa()) {
            matka += verkko[lahtoSolmu][0];
            if (matka < lyhimmanReitinPituus) {
                lyhimmanReitinPituus = matka;
                lyhinReitti = (Pino<Integer>) reitti.clone();
            }
        }
        reitti.pop();
    }
}
