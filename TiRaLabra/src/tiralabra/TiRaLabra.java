/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import java.io.File;
import java.io.PrintWriter;
import tiralabra.kayttoliittyma.Kayttis;
import tiralabra.algoritmit.AinaLahimpaan;
import tiralabra.algoritmit.AntSystem;
import tiralabra.algoritmit.BranchAndBound;
import tiralabra.algoritmit.BruteForce;
import tiralabra.algoritmit.KarpHeld;
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

        File tiedosto = new File("antsystemVSainaLahinvertailu.csv");
        try {
            PrintWriter kirjoittaja = new PrintWriter(tiedosto);



//        Kayttis kayttis = new Kayttis();
//        kayttis.run();
            long alku;
            long loppu;

            for (int i = 0; i < 100; i++) {
                XYVerkko verkko = new XYVerkko(100, 1000, 1000);


//                KarpHeld karpheld = new KarpHeld(verkko);
//                alku = System.currentTimeMillis();
//                karpheld.etsiLyhinReitti();
//                loppu = System.currentTimeMillis();
//                System.out.println("Held-Karp");
////        System.out.println("reitti: " + karpheld.getLyhinReitti());
////        System.out.println("reitin pituus: " + karpheld.getLyhimmanReitinPituus());
//                System.out.println(karpheld.getMinimi());
//                System.out.println(loppu - alku);
//                System.out.println("");
//
//                kirjoittaja.print((int)karpheld.getMinimi() + ";" + (loppu - alku) + ";");

//
//        BranchAndBound BAB = new BranchAndBound(verkko);
//        alku = System.currentTimeMillis();
//        BAB.etsiLyhinReitti();
//        loppu = System.currentTimeMillis();
//        System.out.println("Branch and bound:");
//        System.out.println("reitti: " + BAB.getLyhinReitti());
//        System.out.println("reitin pituus: " + BAB.getLyhimmanReitinPituus());
//        System.out.println(loppu - alku);
//        System.out.println("");
//
                AinaLahimpaan lahin = new AinaLahimpaan(verkko);
                alku = System.currentTimeMillis();
                lahin.etsiLyhinReitti();
                loppu = System.currentTimeMillis();
                System.out.println("Aina lähimpään seuraavaksi:");
                System.out.println("reitti: " + lahin.getLyhinReitti());
                System.out.println("reitin pituus: " + lahin.getLyhimmanReitinPituus());
                System.out.println(loppu - alku);
                System.out.println("");
                
                kirjoittaja.print((int) lahin.getLyhimmanReitinPituus() + ";" + (loppu - alku) + ";");

                double alpha = 1;
                double beta = 4;
                double c = 0.01;
                double q = 100;
                int n = 500;

                AntSystem as = new AntSystem(verkko, alpha, beta, c, q, n);
                alku = System.currentTimeMillis();
                as.etsiLyhinReitti();
                loppu = System.currentTimeMillis();
                System.out.println("Aina lähimpään seuraavaksi:");
                System.out.println("reitti: " + as.getLyhinReitti());
                System.out.println("reitin pituus: " + as.getLyhimmanReitinPituus());
                System.out.println(loppu - alku);
                System.out.println("");

//                kirjoittaja.print((int) as.getLyhimmanReitinPituus() + ";");
//
//                as = new AntSystem(verkko,alpha, beta, c, q, 5000);
//                alku = System.currentTimeMillis();
//                as.etsiLyhinReitti();
//                loppu = System.currentTimeMillis();
//                System.out.println("Aina lähimpään seuraavaksi:");
//                System.out.println("reitti: " + as.getLyhinReitti());
//                System.out.println("reitin pituus: " + as.getLyhimmanReitinPituus());
//                System.out.println(loppu - alku);
//                System.out.println("");

                kirjoittaja.println((int) as.getLyhimmanReitinPituus() + ";" + (loppu - alku));
                //        BruteForce laskija = new BruteForce(verkko);
//        alku = System.currentTimeMillis();
//        laskija.etsiLyhinReitti();
//        loppu = System.currentTimeMillis();
//        System.out.println("BruteForce:");
//        System.out.println("reitti: " + laskija.getLyhinReitti());
//        System.out.println("reitin pituus: " + laskija.getLyhimmanReitinPituus());
//        System.out.println(loppu - alku);
//        System.out.println("");
            }
            kirjoittaja.close();
        } catch (Exception e) {
        }

    }
}
