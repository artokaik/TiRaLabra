/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.tietorakenteet.verkko;

import java.util.Random;

/**
 * XY-koordinaatti on piste xy-koordinaatistossa. Muuttujina x:n ja y:n arvot. 
 * @author Arto
 */
public class XYKoordinaatti {

    private double x;
    private double y;

    /**
     *
     * @param x
     * @param y
     */
    public XYKoordinaatti(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Arpoo uuden koordinaatin väliltä 0-maxX,0-maxY.
     * @param maxX
     * @param maxY
     * @return
     */
    public static XYKoordinaatti arvoUusi(double maxX, double maxY) {
        Random random = new Random();
        double x = random.nextDouble() * maxX;
        double y = random.nextDouble() * maxY;
        return new XYKoordinaatti(x, y);
    }
    
    /**
     * Laskee koordinaatin etäisyyden parametrina annettuun koordinaattiin.
     * @param node
     * @return
     */
    public double laskeEtaisyys(XYKoordinaatti node){
        double x1 = this.getX();
        double y1 = this.getY();
        double x2 = node.getX();
        double y2 = node.getY();
        return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
    }

    /**
     *
     * @return
     */
    public double getX() {
        return x;
    }

    /**
     *
     * @return
     */
    public double getY() {
        return y;
    }
    
}
