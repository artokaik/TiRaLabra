/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import tiralabra.algoritmit.AinaLahimpaan;
import tiralabra.algoritmit.BranchAndBound;
import tiralabra.algoritmit.BruteForce;
import tiralabra.algoritmit.SatunnainenReitti;
import tiralabra.tietorakenteet.verkko.XYVerkko;

/**
 *
 * @author Arto
 */
public class TiRaLabra {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        long alku;
        long loppu;

        XYVerkko verkko = XYVerkko.arvoUusi(12, 10, 10);
        
//        BruteForce laskija = new BruteForce(verkko);
//        alku = System.currentTimeMillis();
//        laskija.etsiLyhinReitti();
//        loppu = System.currentTimeMillis();
//        System.out.println("BruteForce:");
//        System.out.println("reitti: " + laskija.getLyhinReitti());
//        System.out.println("reitin pituus: " + laskija.getLyhimmanReitinPituus());
//        System.out.println(loppu - alku);
//        System.out.println("");
        
        BranchAndBound BAB = new BranchAndBound(verkko);
        alku = System.currentTimeMillis();
        BAB.etsiLyhinReitti();
        loppu = System.currentTimeMillis();
        System.out.println("Branch and bound:");
        System.out.println("reitti: " + BAB.getLyhinReitti());
        System.out.println("reitin pituus: " +BAB.getLyhimmanReitinPituus());
        System.out.println(loppu-alku);
        System.out.println("");

        AinaLahimpaan lahin = new AinaLahimpaan(verkko);
        alku = System.currentTimeMillis();
        lahin.etsiLyhinReitti();
        loppu = System.currentTimeMillis();
        System.out.println("Aina lähimpään seuraavaksi:");
        System.out.println("reitti: " + lahin.getLyhinReitti());
        System.out.println("reitin pituus: " + lahin.getLyhimmanReitinPituus());
        System.out.println(loppu-alku);
        System.out.println("");
        
        SatunnainenReitti satunnainen = new SatunnainenReitti(verkko);
        alku = System.currentTimeMillis();
        satunnainen.etsiLyhinReitti();
        loppu = System.currentTimeMillis();
        System.out.println("Satunnainen reitti:");
        System.out.println("reitti: " + satunnainen.getLyhinReitti());
        System.out.println("reitin pituus: " + satunnainen.getLyhimmanReitinPituus());
        System.out.println(loppu-alku);

    }
}
