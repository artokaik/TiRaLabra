/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.tietorakenteet.verkko;

import tiralabra.tietorakenteet.verkko.Solmu;

/**
 *
 * @author Arto
 */
public class Verkko {

    private double[][] verkko;
    private Solmu[] solmut;

    public Verkko(int n) {
        verkko = new double[n][n];
        solmut = new Solmu[n];
    }

    public static Verkko arvoUusi(int solmujenMaara, double maxX, double maxY) {
        Verkko uusiVerkko = new Verkko(solmujenMaara);
        for (int i = 0; i < solmujenMaara; i++) {
            uusiVerkko.solmut[i] = Solmu.arvoUusi(maxX, maxY);
        }
        for (int i = 0; i < solmujenMaara; i++) {
            for (int j = 0; j < solmujenMaara; j++) {
                uusiVerkko.verkko[i][j] = uusiVerkko.solmut[i].laskeEtaisyys(uusiVerkko.solmut[j]);
            }
        }
        return uusiVerkko;
    }

    public double[][] getVerkko() {
        return verkko;
    }

    public Solmu[] getSolmut() {
        return solmut;
    }

    @Override
    public String toString() {
        String tuloste = "";
        return "";

    }
    
    
}
