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
public class Solmu {

    private double x;
    private double y;

    public Solmu(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Solmu arvoUusi(double maxX, double maxY) {
        Random random = new Random();
        double x = random.nextDouble() * maxX;
        double y = random.nextDouble() * maxY;
        return new Solmu(x, y);
    }
    
    public double laskeEtaisyys(Solmu node){
        double x1 = this.getX();
        double y1 = this.getY();
        double x2 = node.getX();
        double y2 = node.getY();
        return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
}
