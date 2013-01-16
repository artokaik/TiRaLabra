/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import tiralabra.algoritmit.AinaLahimpaan;
import tiralabra.algoritmit.BranchAndBound;
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
        frame.setPreferredSize(new Dimension(1200, 800));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public void luoKomponentit(Container container) {
        container.setLayout(new GridLayout(1,2));
        int leveys = 500;
        int korkeus = 500;
        XYVerkko verkko = XYVerkko.arvoUusi(13, leveys, korkeus);
        BranchAndBound hakija1 = new BranchAndBound(verkko);
        hakija1.etsiLyhinReitti();
        AinaLahimpaan hakija2 = new AinaLahimpaan(verkko);
        hakija2.etsiLyhinReitti();
        container.add(new Piirtoalusta(hakija1, leveys, korkeus));
        container.add(new Piirtoalusta(hakija2, leveys, korkeus));
    }
}
