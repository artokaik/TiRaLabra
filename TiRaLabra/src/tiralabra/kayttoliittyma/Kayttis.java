/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import tiralabra.algoritmit.AinaLahimpaan;
import tiralabra.algoritmit.AntSystem;
import tiralabra.algoritmit.BranchAndBound;
import tiralabra.algoritmit.BranchAndBound2;
import tiralabra.algoritmit.BruteForce;
import tiralabra.algoritmit.KarpHeld;
import tiralabra.tietorakenteet.verkko.XYVerkko;

/**
 *
 * @author Arto
 */
public class Kayttis implements Runnable {

    private JFrame frame;

    public Kayttis() {
    }

    @Override
    public void run() {
        frame = new JFrame("reitti");
        frame.setPreferredSize(new Dimension(1500, 800));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public void luoKomponentit(Container container) {
        container.setLayout(new GridLayout(2, 3));

        int leveys = 300;
        int korkeus = 300;
        XYVerkko verkko = new XYVerkko(12, leveys, korkeus);

        long alku;
        long loppu;

        alku = System.currentTimeMillis();
        BruteForce hakija1 = new BruteForce(verkko);
        hakija1.etsiLyhinReitti();
        loppu = System.currentTimeMillis();
        container.add(new EtsinnanTulos(hakija1, leveys, korkeus, loppu - alku));

        alku = System.currentTimeMillis();
        BranchAndBound hakija2 = new BranchAndBound(verkko);
        hakija2.etsiLyhinReitti();
        loppu = System.currentTimeMillis();
        container.add(new EtsinnanTulos(hakija2, leveys, korkeus, loppu - alku));
        
        alku = System.currentTimeMillis();
        BranchAndBound2 hakija3 = new BranchAndBound2(verkko);
        hakija3.etsiLyhinReitti();
        loppu = System.currentTimeMillis();
        container.add(new EtsinnanTulos(hakija3, leveys, korkeus, loppu - alku));

        alku = System.currentTimeMillis();
        AinaLahimpaan hakija4 = new AinaLahimpaan(verkko);
        hakija4.etsiLyhinReitti();
        loppu = System.currentTimeMillis();
        container.add(new EtsinnanTulos(hakija4, leveys, korkeus, loppu - alku));

        alku = System.currentTimeMillis();
        AntSystem hakija5 = new AntSystem(verkko);
        hakija5.etsiLyhinReitti();
        loppu = System.currentTimeMillis();
        container.add(new EtsinnanTulos(hakija5, leveys, korkeus, loppu - alku));

        alku = System.currentTimeMillis();
        KarpHeld hakija6 = new KarpHeld(verkko);
        hakija6.etsiLyhinReitti();
        loppu = System.currentTimeMillis();
        container.add(new EtsinnanTulos(hakija6, leveys, korkeus, loppu - alku));



    }
}
