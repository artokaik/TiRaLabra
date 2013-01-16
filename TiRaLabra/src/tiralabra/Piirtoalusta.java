/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tiralabra.algoritmit.ReitinEtsija;
import tiralabra.tietorakenteet.pino.Pino;
import tiralabra.tietorakenteet.verkko.XYKoordinaatti;

/**
 *
 * @author Arto
 */
public class Piirtoalusta extends JPanel {
    private ReitinEtsija hakija;
    private int leveys;
    private int korkeus;
    

    public Piirtoalusta(ReitinEtsija etsija, int leveys, int korkeus) {
        this.hakija = etsija;
        this.leveys=leveys;
        this.korkeus=korkeus+50;
        super.setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        for (int i = 0; i < hakija.getSolmut().length; i++) {
            XYKoordinaatti solmu = hakija.getSolmut()[i];
            graphics.fillOval((int)(solmu.getX())-2, (int)(solmu.getY())-2, 4, 4);          
        }
        Pino<Integer> reitti = hakija.getLyhinReitti().clone();
        XYKoordinaatti a = hakija.getSolmut()[reitti.pop()];
        XYKoordinaatti alku=a;
        while(!reitti.empty()){
            XYKoordinaatti b = hakija.getSolmut()[reitti.pop()];
            graphics.drawLine((int)(a.getX()), (int)(a.getY()), (int)(b.getX()), (int)(b.getY()));
            a=b;
        }
        graphics.drawLine((int)(a.getX()), (int)(a.getY()), (int)(alku.getX()), (int)(alku.getY()));
        JLabel teksti = new JLabel("Reitin pituus: " + (int) hakija.getLyhimmanReitinPituus());
        
        this.add(teksti);
        
    }
}
