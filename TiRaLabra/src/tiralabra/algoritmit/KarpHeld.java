/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.algoritmit;

import tiralabra.algoritmit.apu.Prim;
import tiralabra.tietorakenteet.pino.Pino;
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

    
    /**
     *
     * @param verkko
     */
    public KarpHeld(XYVerkko verkko){
        super(verkko);
        solmupainot = new int[solmut.length];
        valepainot = new double[solmut.length][solmut.length];
        for (int i = 0; i < valepainot.length; i++) {
            for (int j = 0; j < valepainot.length; j++) {
                valepainot[i][j]=this.verkko[i][j]+solmupainot[i]+solmupainot[j];             
            }         
        }
        Prim prim = new Prim(valepainot);
        virittavaPuu=prim.lTree(0);
            
    }   
    
    /**
     *
     */
    public void etsiLyhinReitti(){
        etsiReittia(0, 0, new Pino<Integer>());
    }
    // 
    
    private void etsiReittia(int lahtoSolmu, double matka, Pino<Integer> reitti){
    }

    public boolean[][] getVirittavaPuu() {
        return virittavaPuu;
    }
    
}
