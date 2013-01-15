/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.tietorakenteet.verkko;

import java.util.Random;

/**
 *
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
     *
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
     *
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
