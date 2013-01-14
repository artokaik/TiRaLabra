/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.algoritmit;

import tiralabra.tietorakenteet.verkko.Verkko;

/**
 *
 * @author Arto
 */
public class AinaLahimpaan extends ReitinEtsija{

    

    public AinaLahimpaan(Verkko verkko) {
        super(verkko);
        this.lyhimmanReitinPituus=0;
    }

    
    public void aloitaEtsiminen(int alku){
        int x = alku;
        kayty[alku]=true;
        lyhinReitti.push(x);
        while(!this.ollaankoReitinLopussa()){
            int y = etsiLahinKaymaton(x);
            lyhinReitti.push(y);
            this.lyhimmanReitinPituus += verkko[x][y];
            kayty[y]=true;
            x=y;
        }
        lyhimmanReitinPituus += verkko[x][alku];
    }

    private int etsiLahinKaymaton(int solmu) {
        double pieninValimatka = Double.MAX_VALUE;
        int lahin = Integer.MAX_VALUE;
        for (int i = 0; i < verkko.length; i++) {
            if (!kayty[i] && verkko[solmu][i] < pieninValimatka) {
                pieninValimatka = verkko[solmu][i];
                lahin = i;
            }
        }      
        return lahin;
    }
    
    
}
