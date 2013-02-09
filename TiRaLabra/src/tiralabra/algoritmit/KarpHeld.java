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
 * Karp-Held heuristiikka on pienimpiin virittäviin l-puihin perustuva
 * heuristiikka. Se löytää optimaalisen reitin melko usein, mutta ei kuitenkaan
 * aina. Mikäli se ei löydä optimaalista reittiä, se pystyy antamaan hyvin
 * lähellä totuutta olevan arvion lyhimmän reitin pituudesta.
 *
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

    /**
     * 1. Asetetaan β(v) ← 0 kaikille pisteille v.
     *
     * 2. Asetetaanα′(u,v)←α(u,v)+β(u)+β(v)kaikilleviivoille(u,v).
     *
     * 3. Etsitään minimaalinen virittävä 1-puu S′ valepainoja α′(u,v) käyttäen.
     * Jos tällaista ei löydy, ei myöskään Hamiltonin piiriä löydy ja voidaan
     * lopettaa.
     *
     * 4. Jos S′ on piiri, niin tulostetaan minimaalinen Hamiltonin piiri H = S′
     * ja lopetetaan.
     *
     * 5. Jos S′ ei ole piiri ja S′:sta laskettu alaraja on kasvanut K
     * iteraatiokierroksen aikana, niin asetetaan β(v) ← β(v) + dS′ (v) − 2
     * jokaiselle pisteelle v ja mennään kohtaan 2. (K on etukäteen kiinnitetty
     * iteraatiokierrosten maksimimäärä.)
     *
     * 6. Jos S′:sta laskettu alaraja ei ole kasvanut K:n iteraatiokierroksen
     * aikana, lopetetaan ja tulostetaan ko. alaraja.
     *
     * Palauttaa true jos lyhin reitti löytyy, muuten false.
     *
     * @return
     */
    public boolean etsiLyhinReitti() {
        int n = 0;
        double alaraja = 0;
        while (n < 100000) { // Testataan, onko alaraja kasvanut K iteraatiokierroksen aikana (K=1000000)
            paivitaValepainot(); //2
            Prim prim = new Prim(valepainot); //3
            asteet = new int[solmut.length];
            virittavaPuu = prim.lTree(0);
            paivitaAsteet();
            double uusiAlaraja = laskeAlaraja();
            if (onkoPiiri()) { //4
                rakennaPino();
                valmis = true;
                return true;
            } else if (alaraja < uusiAlaraja) {
                alaraja = uusiAlaraja;
                paivitaSolmupainot();
                n = 0;
            } else { //5
                paivitaSolmupainot();
                n++;
            }
        }
        minimi = alaraja;
        return false;
    }

    // Päivittää laskennalliset solmupainot.
    private void paivitaSolmupainot() {
        for (int i = 0; i < solmupainot.length; i++) {
            solmupainot[i] = solmupainot[i] + asteet[i] - 2;
        }
    }

    // Rakentaa lyhimmästä reitistä pinon ja tallentaa sen lyhinReitti-muuttujaan. Näin tuloste saadaan samalla tavalla kuin muista algoritmeista.
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

    // laskee ja päivittää alarajan lyhimmän reitin pituudelle.
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

    // laskee onko virittävä l-puu piiri vai ei (virittävä l-puu on piiri täsmälleen silloin jos kaikkien solmujen asteluku on 2)
    private boolean onkoPiiri() {
        for (int i = 0; i < asteet.length; i++) {
            if (asteet[i] != 2) {
                return false;
            }
        }
        return true;
    }
// Päivittää kaarien valepainot

    private void paivitaValepainot() {
        for (int i = 0; i < valepainot.length; i++) {
            for (int j = 0; j < valepainot.length; j++) {
                valepainot[i][j] = this.verkko[i][j] + solmupainot[i] + solmupainot[j];
            }
        }
    }
//päivittää solmujen asteet virittävässä l-puussa.

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

    /**
     *
     * @return
     */
    public boolean[][] getVirittavaPuu() {
        return virittavaPuu;
    }

    /**
     *
     * @return
     */
    public boolean isValmis() {
        return valmis;
    }

    /**
     *
     * @return
     */
    public double getMinimi() {
        return minimi;
    }
}
