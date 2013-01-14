/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import tiralabra.algoritmit.AinaLahimpaan;
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

        Verkko verkko = Verkko.arvoUusi(10, 10, 10);
        BruteForce laskija = new BruteForce(verkko);
        long alku = System.currentTimeMillis();
        laskija.etsiLyhinReitti();
        long loppu = System.currentTimeMillis();
        System.out.println(laskija.getLyhinReitti());
        System.out.println(laskija.getLyhimmanReitinPituus());
        System.out.println(loppu - alku);
        System.out.println("");

        AinaLahimpaan lahin = new AinaLahimpaan(verkko);
        alku = System.currentTimeMillis();
        lahin.aloitaEtsiminen(0);
        loppu = System.currentTimeMillis();
        System.out.println(lahin.getLyhinReitti());
        System.out.println(lahin.getLyhimmanReitinPituus());
        System.out.println(loppu-alku);

    }
}
