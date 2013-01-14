/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import tiralabra.algoritmit.AinaLahimpaan;
import tiralabra.algoritmit.BranchAndBound;
import tiralabra.tietorakenteet.verkko.Verkko;
import tiralabra.algoritmit.BruteForce;

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

        Verkko verkko = Verkko.arvoUusi(12, 10, 10);
        BruteForce laskija = new BruteForce(verkko);
        long alku = System.currentTimeMillis();
        laskija.etsiLyhinReitti();
        long loppu = System.currentTimeMillis();
        System.out.println("BruteForce:");
        System.out.println("reitti: " + laskija.getLyhinReitti());
        System.out.println("reitin pituus: " + laskija.getLyhimmanReitinPituus());
        System.out.println(loppu - alku);
        System.out.println("");
        
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
        lahin.aloitaEtsiminen(0);
        loppu = System.currentTimeMillis();
        System.out.println("Aina lähimpään seuraavaksi:");
        System.out.println("reitti: " + lahin.getLyhinReitti());
        System.out.println("reitin pituus: " + lahin.getLyhimmanReitinPituus());
        System.out.println(loppu-alku);

    }
}
