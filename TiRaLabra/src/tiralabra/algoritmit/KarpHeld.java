/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.algoritmit;

import tiralabra.algoritmit.apu.Prim;
import tiralabra.tietorakenteet.pino.Pino;
import tiralabra.tietorakenteet.verkko.XYKoordinaatti;
import tiralabra.tietorakenteet.verkko.XYVerkko;

/**
 *
 * @author Arto
 */
public class KarpHeld extends ReitinEtsija {

    private int[] solmupainot;
    private int[] asteet;
    private boolean[][] virittavaPuu;
    private double[][] valepainot;
    private double minimi;
    private boolean valmis;

    /**
     *
     * @param verkko
     */
    public KarpHeld(XYVerkko verkko) {
        super(verkko);
        solmupainot = new int[solmut.length];
        valepainot = new double[solmut.length][solmut.length];
        valmis = false;
        minimi = 0;
    }

    public boolean etsiLyhinReitti() {
        int n = 0;
        double alaraja = 0;
        while (n < 1000000) {
            paivitaValepainot();
            Prim prim = new Prim(valepainot);
            asteet = new int[solmut.length];
            virittavaPuu = prim.lTree(0);
            paivitaAsteet();
            double uusiAlaraja = laskeAlaraja();
            if (onkoPiiri()) {
                rakennaPino();
                valmis = true;
                return true;
            } else if (alaraja < uusiAlaraja) {
                alaraja = uusiAlaraja;
                n = 0;
            } else {
                paivitaSolmupainot();
                n++;
            }
        }
        minimi = alaraja;
        return false;
    }

    private void paivitaSolmupainot() {
        for (int i = 0; i < solmupainot.length; i++) {
            solmupainot[i] = solmupainot[i] + asteet[i] - 2;
        }
    }

    private void rakennaPino() {
        this.lyhimmanReitinPituus = 0;
        int v = 0;
        lyhinReitti.push(v);
        int n = 1;
        while (n < solmut.length) {
            for (int i = 0; i < verkko.length; i++) {
                if (virittavaPuu[v][i] || virittavaPuu[i][v]) {
                    virittavaPuu[i][v] = false;
                    virittavaPuu[v][i] = false;
                    lyhinReitti.push(i);
                    this.lyhimmanReitinPituus += verkko[v][i];
                    v = i;
                    break;
                }
            }
            n++;
        }
        this.lyhimmanReitinPituus += verkko[v][0];
    }

    private double laskeAlaraja() {
        double alaraja = 0;
        for (int i = 0; i < verkko.length; i++) {
            for (int j = 0; j < verkko.length; j++) {
                if (virittavaPuu[i][j]) {
                    alaraja += verkko[i][j];
                }
            }
        }
        for (int i = 0; i < asteet.length; i++) {
            alaraja += (asteet[i] - 2) * solmupainot[i];
        }
        return alaraja;
    }

    private boolean onkoPiiri() {
        for (int i = 0; i < asteet.length; i++) {
            if (asteet[i] != 2) {
                return false;
            }
        }
        return true;
    }

    private void paivitaValepainot() {
        for (int i = 0; i < valepainot.length; i++) {
            for (int j = 0; j < valepainot.length; j++) {
                valepainot[i][j] = this.verkko[i][j] + solmupainot[i] + solmupainot[j];
            }
        }
    }

    private void paivitaAsteet() {
        for (int i = 0; i < solmut.length; i++) {
            for (int j = 0; j < solmut.length; j++) {
                if (virittavaPuu[i][j]) {
                    asteet[i]++;
                    asteet[j]++;
                }
            }
        }
    }

    public boolean[][] getVirittavaPuu() {
        return virittavaPuu;
    }

    public boolean isValmis() {
        return valmis;
    }

    public double getMinimi() {
        return minimi;
    }
}
