/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
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
public class Kayttis implements Runnable {

    private JFrame frame;

    public Kayttis() {
    }

    @Override
    public void run() {
        frame = new JFrame("reitti");
        frame.setPreferredSize(new Dimension(1600, 800));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public void luoKomponentit(Container container) {
        container.setLayout(new GridLayout(2, 4));
        int leveys = 1350;
        int korkeus = 750;
        XYVerkko verkko = XYVerkko.arvoUusi(200, leveys, korkeus);

        AntSystem hakija1 = new AntSystem(verkko);
        hakija1.etsiLyhinReitti();
        container.add(new Piirtoalusta(hakija1, leveys, korkeus));

//        AntSystem hakija2 = new AntSystem(verkko);
//        hakija2.etsiLyhinReitti();
//        container.add(new Piirtoalusta(hakija2, leveys, korkeus));
//
//        AntSystem hakija3 = new AntSystem(verkko);
//        hakija3.etsiLyhinReitti();
//        container.add(new Piirtoalusta(hakija3, leveys, korkeus));
//
//        AntSystem hakija4 = new AntSystem(verkko);
//        hakija4.etsiLyhinReitti();
//        container.add(new Piirtoalusta(hakija4, leveys, korkeus));
//
//        AntSystem hakija5 = new AntSystem(verkko);
//        hakija5.etsiLyhinReitti();
//        container.add(new Piirtoalusta(hakija5, leveys, korkeus));
//
//        AntSystem hakija6 = new AntSystem(verkko);
//        hakija6.etsiLyhinReitti();
//        container.add(new Piirtoalusta(hakija6, leveys, korkeus));
//
//        AntSystem hakija7 = new AntSystem(verkko);
//        hakija7.etsiLyhinReitti();
//        container.add(new Piirtoalusta(hakija7, leveys, korkeus));
//        
//                AntSystem hakija8 = new AntSystem(verkko);
//        hakija8.etsiLyhinReitti();
//        container.add(new Piirtoalusta(hakija8, leveys, korkeus));

//        AinaLahimpaan hakija3 = new AinaLahimpaan(verkko);
//        hakija3.etsiLyhinReitti();
//        container.add(new Piirtoalusta(hakija3, leveys, korkeus));

//        KarpHeld hakija2 = new KarpHeld(verkko);
//        hakija2.etsiLyhinReitti();        
//        if(hakija2.isValmis()){
//            container.add(new Piirtoalusta(hakija2, leveys, korkeus));
//        }else{
//            container.add(new PiirtoalustaPrim(hakija2, leveys, korkeus));
//            String teksti = "Minimi: "+(int)hakija2.getMinimi();
//            container.add(new JLabel(teksti));
//        }

    }
}
