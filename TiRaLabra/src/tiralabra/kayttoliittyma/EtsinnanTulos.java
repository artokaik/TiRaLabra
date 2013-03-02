/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import tiralabra.algoritmit.ReitinEtsija;
import tiralabra.tietorakenteet.pino.Pino;
import tiralabra.tietorakenteet.verkko.XYKoordinaatti;

/**
 *
 * @author Arto
 */
public class EtsinnanTulos extends JPanel {

    private long kesto;
    private ReitinEtsija hakija;
    private int leveys;
    private int korkeus;

    public EtsinnanTulos(ReitinEtsija etsija, int leveys, int korkeus, long kesto) {
        this.hakija = etsija;
        this.leveys = leveys;
        this.korkeus = korkeus + 50;
        this.kesto = kesto;
        super.setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.setLayout(new BorderLayout());
        this.add(new JLabel(hakija.getClass().getName()), BorderLayout.NORTH);
        this.add(new Piirtoalusta(hakija, leveys, korkeus), BorderLayout.CENTER);
        JTextArea teksti = new JTextArea("Pituus: " + hakija.getLyhimmanReitinPituus() + "\nKesto: " + kesto);
        teksti.setEditable(false);
        this.add(teksti, BorderLayout.SOUTH);    
    }
}
