/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.tietorakenteet.verkko;

import tiralabra.tietorakenteet.verkko.XYKoordinaatti;

/**
 * Luokka XYVerkko kuvaa verkkoa, jossa on n määrä solmuja. Solmut ovat pisteitä xy-koordinaatistossa.
 * @author Arto
 */
public class XYVerkko {

    private double[][] verkko;
    private XYKoordinaatti[] solmut;

    /**
     *
     * @param n
     */
    public XYVerkko(int n) {
        verkko = new double[n][n];
        solmut = new XYKoordinaatti[n];
    }

    /**
     * 
     * @param solmut
     */
    public XYVerkko(XYKoordinaatti[] solmut) {
        this.solmut = solmut;
        this.verkko = new double[solmut.length][solmut.length];
        for (int i = 0; i < solmut.length; i++) {
            for (int j = 0; j < solmut.length; j++) {
                verkko[j][i] = solmut[j].laskeEtaisyys(solmut[i]);
                verkko[i][j] = solmut[i].laskeEtaisyys(solmut[j]);
            }
        }
    }

    /**
     * Arpoo uuden verkon, jossa on parametrina annettu määrä solmuja. Solmujen koordinaatit ovat väliläl 0-maxX,0-maxY.
     * @param solmujenMaara
     * @param maxX
     * @param maxY
     * @return
     */
    public static XYVerkko arvoUusi(int solmujenMaara, double maxX, double maxY) {
        XYVerkko uusiVerkko = new XYVerkko(solmujenMaara);
        for (int i = 0; i < solmujenMaara; i++) {
            uusiVerkko.lisaaSolmu(XYKoordinaatti.arvoUusi(maxX, maxY));
        }
        return uusiVerkko;
    }

    /**
     * Lisää parametrina annetun solmun verkkoon, mikäli verkossa on vielä tyhjiä paikkoja.
     * @param solmu
     * @return
     */
    public boolean lisaaSolmu(XYKoordinaatti solmu) {
        for (int i = 0; i < solmut.length; i++) {
            if (solmut[i] == null) {
                return lisaaSolmu(solmu, i);
            }
        }
        return false;
    }

    /**
     * Lisää parametrina annetun solmun parametrina annettuun kohtaan solmulistassa ja päivittää etäisyydet muihin solmuihin. Palauttaa true jos onnistui, muuten false
     * @param solmu
     * @param indeksi
     * @return
     */
    public boolean lisaaSolmu(XYKoordinaatti solmu, int indeksi) {
        if (indeksi >= solmut.length || indeksi < 0 || solmu == null) {
            return false;
        }
        solmut[indeksi] = solmu;
        for (int i = 0; i < solmut.length; i++) {
            if (solmut[i] != null) {
                verkko[indeksi][i] = solmu.laskeEtaisyys(solmut[i]);
                verkko[i][indeksi] = solmu.laskeEtaisyys(solmut[i]);
            }
        }
        return true;
    }

    /**
     *
     * @return
     */
    public boolean onkoTaysi() {
        for (int i = 0; i < solmut.length; i++) {
            if (solmut[i] == null) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @return
     */
    public double[][] getVerkko() {
        return verkko;
    }

    /**
     *
     * @return
     */
    public XYKoordinaatti[] getSolmut() {
        return solmut;
    }

    @Override
    public String toString() {
        String tuloste = "";
        return "";

    }
}
