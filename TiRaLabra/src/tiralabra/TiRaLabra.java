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
import tiralabra.algoritmit.ReitinEtsija;
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

        Kayttis kayttis = new Kayttis();
        kayttis.run();
        
// Loput on koodia jolla testailin eri algoritmeja.
        
//        for (int j = 1; j < 5; j++) {
//            int v = 30 * j;
//
//
//            File tiedosto = new File("AntSystem" + v + ".csv");
//            try {
//                PrintWriter kirjoittaja = new PrintWriter(tiedosto);
//
//

//                long alku;
//                long loppu;
//
//
//                for (int i = 0; i < 100; i++) {
//                    System.out.println(v + " " + i);
//                    XYVerkko verkko = new XYVerkko(v, 100000, 100000);
//
//
//                    alku = System.currentTimeMillis();
//                    KarpHeld karpheld = new KarpHeld(verkko, 4);
//                    karpheld.etsiLyhinReitti();
//                    loppu = System.currentTimeMillis();
//
//                    kirjoittaja.print((int) karpheld.getLyhimmanReitinPituus() + ";" + (int) karpheld.getMinimi() + ";" + (loppu - alku) + ";");
//
//                    alku = System.currentTimeMillis();
//                    KarpHeld karpheld1 = new KarpHeld(verkko, 16);
//                    karpheld1.etsiLyhinReitti();
//                    loppu = System.currentTimeMillis();
//
//                    kirjoittaja.print((int) karpheld1.getLyhimmanReitinPituus() + ";" + (int) karpheld1.getMinimi() + ";" + (loppu - alku) + ";");
//
//                    alku = System.currentTimeMillis();
//                    KarpHeld karpheld2 = new KarpHeld(verkko, 256);
//                    karpheld2.etsiLyhinReitti();
//                    loppu = System.currentTimeMillis();
//
//                    kirjoittaja.print((int) karpheld2.getLyhimmanReitinPituus() + ";" + (int) karpheld2.getMinimi() + ";" + (loppu - alku) + ";");
//
//                    alku = System.currentTimeMillis();
//                    KarpHeld karpheld4 = new KarpHeld(verkko, 30);
//                    karpheld4.etsiLyhinReitti();
//                    loppu = System.currentTimeMillis();
//                    System.out.println(loppu - alku);
//
//                    kirjoittaja.print((int) karpheld4.getLyhimmanReitinPituus() + ";" + (int) karpheld4.getMinimi() + ";" + (loppu - alku) + ";");

//                    alku = System.currentTimeMillis();
//                    AinaLahimpaan al = new AinaLahimpaan(verkko);
//                    al.etsiLyhinReitti();
//                    loppu = System.currentTimeMillis();
//                    System.out.println(loppu - alku);
//
//
//                    kirjoittaja.print((int) al.getLyhimmanReitinPituus() + ";" + (loppu - alku) + ";");
//
//                    double alpha = 1;
//                    double beta = 4;
//                    double c = 0.01;
//                    double q = 1;
//                    int n = v * 10;
//
//                    alku = System.currentTimeMillis();
//                    AntSystem as = new AntSystem(verkko, alpha, beta, c, q, n);
//                    as.etsiLyhinReitti();
//                    loppu = System.currentTimeMillis();
//                    System.out.println(loppu - alku);
//
//                    kirjoittaja.print((int) as.getLyhimmanReitinPituus() + ";" + (loppu - alku) + ";");
//
//                    q = 10;
//
//                    alku = System.currentTimeMillis();
//                    AntSystem as0 = new AntSystem(verkko, alpha, beta, c, q, n);
//                    as.etsiLyhinReitti();
//                    loppu = System.currentTimeMillis();
//                    System.out.println(loppu - alku);
//
//                    kirjoittaja.print((int) as0.getLyhimmanReitinPituus() + ";" + (loppu - alku) + ";");
//
//                    q = 100;
//
//                    alku = System.currentTimeMillis();
//                    AntSystem as1 = new AntSystem(verkko, alpha, beta, c, q, n);
//                    as1.etsiLyhinReitti();
//                    loppu = System.currentTimeMillis();
//                    System.out.println(loppu - alku);
//
//                    kirjoittaja.print((int) as1.getLyhimmanReitinPituus() + ";" + (loppu - alku) + ";");
//
//                    q = 1000;
//
//
//                }
//                kirjoittaja.close();
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//        }
    }
    


}
