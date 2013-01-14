/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.algoritmit;

import tiralabra.algoritmit.ReitinEtsija;
import java.util.ArrayList;
import tiralabra.tietorakenteet.verkko.Verkko;

/**
 *
 * @author Arto
 */
public class BruteForce extends ReitinEtsija {


    
    public BruteForce(Verkko verkko){
        super(verkko);
            
    }   
    
    public void etsiLyhinReitti(){
        etsiReittia(0, 0, new ArrayList<Integer>());
    }
    // Oletus, että alussa lähdettiin solmusta 0 (Lähtösolmulla ei bruteforcessa ole väliä koska lopussa palataan aina lähtösolmuun)
    
    private void etsiReittia(int lahtoSolmu, double matka, ArrayList<Integer> reitti){
        reitti.add(lahtoSolmu);
        kayty[lahtoSolmu]=true;
        for (int i = 0; i < verkko.length; i++) {
            if(!kayty[i]){
                matka += verkko[lahtoSolmu][i];
                kayty[i]=true;
                etsiReittia(i, matka, reitti);
                kayty[i]=false;
                matka -= verkko[lahtoSolmu][i];
            }
            
        }
        if (this.ollaankoReitinLopussa()){
            matka += verkko[lahtoSolmu][0];
            if(matka<lyhimmanReitinPituus){
                lyhimmanReitinPituus = matka;
                lyhinReitti = (ArrayList<Integer>) reitti.clone();
            }
        }
        reitti.remove(reitti.size()-1);       
    }   
    
}
