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
            uusiVerkko.lisaaSolmu(Solmu.arvoUusi(maxX, maxY));
        }
        return uusiVerkko;
    }
    
    public boolean lisaaSolmu(Solmu solmu){
        for (int i = 0; i < solmut.length; i++) {
            if(solmut[i]==null){
                return lisaaSolmu(solmu, i);
            }          
        }
        return false;
    }
    
    public boolean lisaaSolmu(Solmu solmu, int indeksi){
        if(indeksi>=solmut.length || indeksi<0){
            return false;
        }
        solmut[indeksi]=solmu;
        for (int i = 0; i < solmut.length; i++) {
            if(solmut[i]!=null){
                verkko[indeksi][i]=solmu.laskeEtaisyys(solmut[i]);
                verkko[i][indeksi]=solmu.laskeEtaisyys(solmut[i]);
            }
        }
        return true;
    }
    
    public boolean onkoTaysi(){
        for (int i = 0; i < solmut.length; i++) {
            if(solmut[i]==null){
                return false;
            }          
        }
        return true;
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
