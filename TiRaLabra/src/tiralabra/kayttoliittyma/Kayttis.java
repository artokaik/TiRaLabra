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
import tiralabra.algoritmit.BranchAndBound;
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
        XYVerkko verkko = XYVerkko.arvoUusi(40, leveys, korkeus);
        
        AinaLahimpaan hakija1 = new AinaLahimpaan(verkko);
        hakija1.etsiLyhinReitti();
        container.add(new Piirtoalusta(hakija1, leveys, korkeus));
        
        KarpHeld hakija2 = new KarpHeld(verkko);
        hakija2.etsiLyhinReitti();        
        if(hakija2.isValmis()){
            container.add(new Piirtoalusta(hakija2, leveys, korkeus));
        }else{
            String teksti = "Minimi: "+(int)hakija2.getMinimi();
            container.add(new JLabel(teksti));
        }
        
    }
}
