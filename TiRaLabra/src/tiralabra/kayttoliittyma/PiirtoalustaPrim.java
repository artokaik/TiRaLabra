/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.kayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tiralabra.algoritmit.KarpHeld;
import tiralabra.tietorakenteet.verkko.XYKoordinaatti;

/**
 *
 * @author Arto
 */
public class PiirtoalustaPrim extends JPanel {

    private KarpHeld hakija;
    private int leveys;
    private int korkeus;

    public PiirtoalustaPrim(KarpHeld etsija, int leveys, int korkeus) {
        this.hakija = etsija;
        this.leveys = leveys;
        this.korkeus = korkeus + 50;
        super.setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        for (int i = 0; i < hakija.getSolmut().length; i++) {
            XYKoordinaatti solmu = hakija.getSolmut()[i];
            if(i==0){
            graphics.fillOval((int) (solmu.getX()) - 4, (int) (solmu.getY()) - 4, 8, 8);
            } else {
                graphics.fillOval((int) (solmu.getX()) - 2, (int) (solmu.getY()) - 2, 4, 4);
            }
        }
        
        for (int i = 0; i < hakija.getSolmut().length; i++) {
            for (int j = 0; j < hakija.getSolmut().length; j++) {
                if(hakija.getVirittavaPuu()[i][j]){
                    XYKoordinaatti a = hakija.getSolmut()[i];
                    XYKoordinaatti b = hakija.getSolmut()[j];
                    graphics.drawLine((int) (a.getX()), (int) (a.getY()), (int) (b.getX()), (int) (b.getY()));
                }
            }
            
        }
       

    }
}
