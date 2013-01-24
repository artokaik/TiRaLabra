/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.algoritmit.apu;

/**
 *  Prim-luokka kuvaa Primin algoritmia, joka löytää verkosta pienimmän virittävän puun.
 * 
 * double[][] 'verkko' kuvaa verkkoa, josta virittävä puu lasketaan
 * 
 * double[] 'distance' kertoo kunkin solmun lyhimmän etäisyyden jo lasketusta puusta
 * 
 * int[] parent kertoo solmun vanhemman tämänhetkisessä puussa
 * 
 * boolean[][] puu kertoo mitkä verkon kaaret ovat mukana virittävässä puussa
 * 
 * int solmuja kertoo kuinka monta solmua virittävään puuhun on jo lisätty
 * 
 * @author Arto
 */
public class Prim {

    double[][] verkko;
    double[] distance;
    int[] parent;
    boolean[][] puu;
    boolean[] puussa;
    int solmuja;

    /**
     * Prim-luokan konstruktori saa syötteenä taulukkomuodossa esitetyn painotetun verkon ja alustaa luokkamuuttujat vastaamaan verkon kokoa.
     * @param verkko
     */
    public Prim(double[][] verkko) {
        distance = new double[verkko.length];
        parent = new int[verkko.length];
        puu = new boolean[verkko.length][verkko.length];
        puussa = new boolean[verkko.length];
        this.verkko = verkko;
        solmuja = 0;
    }

    /**
     *
     * normalPrim() -metodi palauttaa lyhimmän virittävän puun boolean[][] taulukkona. 
     * 
     * @return
     */
    
    public boolean[][] normalPrim() {
        return normalPrim(0);
    }
    
    private boolean[][] normalPrim(int alku) {
        puussa[alku] = true;
        solmuja++;
        distance[alku] = 0;
        for (int i = 0; i < verkko.length; i++) {
            if (!puussa[i]) {
                distance[i] = verkko[alku][i];
                parent[i] = alku;
            }
        }
        while (solmuja < verkko.length) {
            double lahinDistance = Double.MAX_VALUE;
            int lahin = Integer.MAX_VALUE;
            for (int i = 0; i < verkko.length; i++) {
                if (distance[i] < lahinDistance && !puussa[i]) {
                    lahin = i;
                    lahinDistance = distance[i];
                }
            }
            puu[lahin][parent[lahin]] = true;
            puussa[lahin] = true;
            solmuja++;
            for (int i = 0; i < verkko.length; i++) {
                if (!puussa[i] && distance[i] > verkko[lahin][i]) {
                    distance[i] = verkko[lahin][i];
                    parent[i] = lahin;
                }
            }
        }
        return puu;
    }

    /**
     * lTree(int alku) -metodi palauttaa pienimmän virittävän l-puun, eli pienimmän verkon, joka yhdistää kaikki solmut ja jossa on täsmälleen yksi sykli. Parametrina annettava numero kuvaa solmua, joka on syklissä ja jonka asteluku on 2. 
     * @param alku
     * @return
     */
    public boolean[][] lTree(int alku) {
        puussa[alku] = true;
        solmuja++;
        int lahin = Integer.MAX_VALUE;
        double lahinDistance = Double.MAX_VALUE;
        for (int i = 0; i < verkko.length; i++) {
            if (!puussa[i] && verkko[alku][i] < lahinDistance) {
                lahinDistance = verkko[alku][i];
                lahin = i;
            }
        }
        puussa[lahin]=true;
        puu[alku][lahin]=true;
        
       int lahin2 = Integer.MAX_VALUE;
        double lahinDistance2 = Double.MAX_VALUE;
        for (int i = 0; i < verkko.length; i++) {
            if (!puussa[i] && verkko[i][alku] < lahinDistance2) {
                lahinDistance2 = verkko[i][alku];
                lahin2 = i;
            }
        }
        puu[lahin2][alku]=true;
        
        normalPrim(lahin);
        
        return puu;
    }
}
