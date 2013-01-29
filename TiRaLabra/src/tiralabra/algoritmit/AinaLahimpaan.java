/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.algoritmit;

import tiralabra.tietorakenteet.pino.Pino;
import tiralabra.tietorakenteet.verkko.XYVerkko;

/**
 * AinaLahimpaan on ajassa n^3 toimiva algoritmi. Se käy verkon läpi n kertaa aloittaen kerran jokaisesta solmusta. Jokaisella läpikäynnillä se menee aina solmusta lähimpään viereiseen solmuun kunnes verkko on käyty läpi. Tämän jälkeen se tallettaa reitin ja sen pituuden jos se on lyhyempi kuin tähän astisista reiteistä paras.
 * 
 * Jos metodin etsiLyhinReitti() sijaan kutsutaan metodia aloitaEtsiminen(int alku), toimii algoritmi ajassa n^2, mutta ratkaisu on keskimäärin huonompi (eikä koskaan parempi).
 * @author Arto
 */
public class AinaLahimpaan extends ReitinEtsija{
  

    /**
     * 
     * @param verkko
     */
    public AinaLahimpaan(XYVerkko verkko) {
        super(verkko);

    }
    
    /**
     * 
     */
    public void etsiLyhinReitti(){
        for (int i = 0; i < verkko.length; i++) {
            aloitaEtsiminen(i);           
        }
    }

    
    /**
     * Aloittaa parametrina annetusta solmusta ja menee aina lähimpään solmuun kunnes kaikissa solmuissa on käyty. Jos tämä reitti on lyhyempi kuin lyhin tähän mennessä löydetty, metodi päivittää lyhimmän reitin. Rungon aikavaatimus O(n) (while-loopissa käydään läpi kaikki solmut), ja while loopin sisällä olevan hakumetodin aikavaatimus myös O(n). Näin ollen koko metodin aikavaatimus on O(n^2) 
     * @param alku
     */
    public void aloitaEtsiminen(int alku){
        int x = alku;
        kayty[alku]=true;
        Pino<Integer> lyhytReitti = new Pino<Integer>();
        double lyhyenReitinPituus = 0;
        lyhytReitti.push(x);
        while(!this.ollaankoReitinLopussa()){
            int y = etsiLahinKaymaton(x);
            lyhytReitti.push(y);
            lyhyenReitinPituus += verkko[x][y];
            kayty[y]=true;
            x=y;
        }
        lyhyenReitinPituus += verkko[x][alku];
        if(lyhyenReitinPituus<this.lyhimmanReitinPituus){
            this.lyhimmanReitinPituus=lyhyenReitinPituus;
            this.lyhinReitti=lyhytReitti;
        }
        for (int i = 0; i < kayty.length; i++) {
            kayty[i]=false;         
        }
    }
    
   /**
     * Etsi parametrina annetun solmun lähimmän sellaisen naapurin, jossa ei ole vielä käyty. Aikavaatimus O(n).
     * @param solmu
     */
    
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
