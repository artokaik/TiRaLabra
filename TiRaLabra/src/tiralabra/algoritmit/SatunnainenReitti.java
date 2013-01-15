/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.algoritmit;

import tiralabra.tietorakenteet.verkko.XYVerkko;

/**
 * SatunnainenReitti on algoritmi, joka käy solmut läpi numerojärjestyksessä ja tallettaa tämän reitin pituuden. Reitti on satunnainen jos solmut ovat syötteessä satunnaisessa järjestyksessä. Aikavaatimus O(n).
 * @author Arto
 */
public class SatunnainenReitti extends ReitinEtsija{
    
    
    public SatunnainenReitti(XYVerkko xyverkko){
        super(xyverkko);
    }
    
    public void etsiLyhinReitti(){
        lyhimmanReitinPituus=0;
        lyhinReitti.push(0);
        for (int i = 1; i < verkko.length; i++) {
            lyhinReitti.push(i);
            this.lyhimmanReitinPituus+=verkko[i-1][i];            
        }
        this.lyhimmanReitinPituus+=verkko[verkko.length-1][0];
    }
}