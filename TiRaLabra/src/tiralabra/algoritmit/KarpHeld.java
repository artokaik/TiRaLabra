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
 * Muuttuja int[] solmupainot
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
    private int iteraatiot;

    /**
     *
     * @param verkko
     */
    public KarpHeld(XYVerkko verkko) {
        this(verkko,100000);
    }
        public KarpHeld(XYVerkko verkko, int iteraatiot) {
        super(verkko);
        solmupainot = new int[solmut.length];
        valepainot = new double[solmut.length][solmut.length];
        valmis = false;
        minimi = 0;
        this.iteraatiot = iteraatiot;
    }

    /**
     * 1. Asetetaan β(v) ← 0 kaikille pisteille v (β on solmupaino).
     *
     * 2. Asetetaan α′(u,v)←α(u,v)+β(u)+β(v)kaikille viivoille(u,v) (α on valepaino) .
     *
     * 3. Etsitään minimaalinen virittävä 1-puu S valepainoja α′(u,v) käyttäen.
     * Jos tällaista ei löydy, ei myöskään Hamiltonin piiriä löydy ja voidaan
     * lopettaa.
     *
     * 4. Jos S′ on piiri, niin tallennetaan minimaalinen Hamiltonin piiri H = S′
     * ja lopetetaan.
     *
     * 5. Jos S′ ei ole piiri ja S′:sta laskettu alaraja on kasvanut K
     * iteraatiokierroksen aikana, niin asetetaan β(v) ← β(v) + dS′ (v) − 2
     * jokaiselle pisteelle v ja mennään kohtaan 2. (K on etukäteen kiinnitetty
     * iteraatiokierrosten maksimimäärä, dS(v) on solmun asteluku virittävässä l-puussa S.)
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
        while (n < iteraatiot) {
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
    public void paivitaSolmupainot() {
        for (int i = 0; i < solmupainot.length; i++) {
            solmupainot[i] = solmupainot[i] + asteet[i] - 2;
        }
    }

    // Rakentaa lyhimmästä reitistä pinon ja tallentaa sen lyhinReitti-muuttujaan. Näin tuloste saadaan samalla tavalla kuin muista algoritmeista.
    public void rakennaPino() {
        this.lyhimmanReitinPituus = 0;
        int v = 0;
        lyhinReitti.push(v);
        int n = 1;
        while (n < solmut.length) {
            for (int i = 0; i < kaaret.length; i++) {
                if (virittavaPuu[v][i] || virittavaPuu[i][v]) {
                    virittavaPuu[i][v] = false;
                    virittavaPuu[v][i] = false;
                    lyhinReitti.push(i);
                    this.lyhimmanReitinPituus += kaaret[v][i];
                    v = i;
                    break;
                }
            }
            n++;
        }
        this.lyhimmanReitinPituus += kaaret[v][0];
    }

    // laskee ja päivittää alarajan lyhimmän reitin pituudelle.
    public double laskeAlaraja() {
        double alaraja = 0;
        for (int i = 0; i < kaaret.length; i++) {
            for (int j = 0; j < kaaret.length; j++) {
                if (virittavaPuu[i][j]) {
                    alaraja += kaaret[i][j];
                }
            }
        }
        for (int i = 0; i < asteet.length; i++) {
            alaraja += (asteet[i] - 2) * solmupainot[i];
        }
        return alaraja;
    }

    // laskee onko virittävä l-puu piiri vai ei (virittävä l-puu on piiri täsmälleen silloin jos kaikkien solmujen asteluku on 2)
    public boolean onkoPiiri() {
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
                valepainot[i][j] = this.kaaret[i][j] + solmupainot[i] + solmupainot[j];
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
