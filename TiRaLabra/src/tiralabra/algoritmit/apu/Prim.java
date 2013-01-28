/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.algoritmit.apu;

/**
 * Prim-luokka kuvaa Primin algoritmia, joka löytää verkosta pienimmän
 * virittävän puun.
 *
 * double[][] 'verkko' kuvaa verkkoa, josta virittävä puu lasketaan
 *
 * double[] 'distance' kertoo kunkin solmun lyhimmän etäisyyden jo lasketusta
 * puusta
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
     * Prim-luokan konstruktori saa syötteenä taulukkomuodossa esitetyn
     * painotetun verkon ja alustaa luokkamuuttujat vastaamaan verkon kokoa.
     *
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
     * normalPrim() -metodi palauttaa lyhimmän virittävän puun boolean[][]
     * taulukkona.
     *
     * @return
     */
    public boolean[][] normalPrim() {
        return normalPrim(0);
    }

    private boolean[][] normalPrim(int alku) {
        primAlustus(alku);
        while (solmuja < verkko.length) {
            int lahin = haeLahin();
            lisaaPuuhun(lahin);
            paivitaEtaisyysArviot(lahin);
        }
        return puu;
    }
    
    /**
     *
     * primAlustus() lisää virittävään puuhun ensimmäisen solmun ja päivittää smuiden solmujen etäisyysarvion.
     *
     */
    private void primAlustus(int alku) {
        puussa[alku] = true;
        solmuja++;
        distance[alku] = 0;
        for (int i = 0; i < verkko.length; i++) {
            if (!puussa[i]) {
                distance[i] = verkko[alku][i];
                parent[i] = alku;
            }
        }
    }
    /**
     *
     * haeLahin() palauttaa solmun, jonka etäisyys valmiiseen puuhun on pienin..
     *
     */
    private int haeLahin() {
        double lahinDistance = Double.MAX_VALUE;
        int lahin = Integer.MAX_VALUE;
        for (int i = 0; i < verkko.length; i++) {
            if (distance[i] < lahinDistance && !puussa[i]) {
                lahin = i;
                lahinDistance = distance[i];
            }
        }
        return lahin;
    }
    /**
     *
     * lisaaPuuhun(int solmu) lisää parametrina annetun solmun virittävään puuhun.
     *
     */
    
    private void lisaaPuuhun(int solmu) {
        puu[solmu][parent[solmu]] = true;
        puussa[solmu] = true;
        solmuja++;
    }

     /**
     *
     * paivitaEtaisyysarviot(int solmu) käy läpi solmujen etäisyydet parametrina annettuun solmuun. Mikäli tarkasteltava solmu ei ole puussa ja sen etäisyysarvio on suurempi kuin sen etäisyys parametrina annettuun solmuun, etäisyysarvio päivitetään ja parametrina annettu solmu asetetaan sen vanhemmaksi. 
     *
     */
    private void paivitaEtaisyysArviot(int solmu) {
        for (int i = 0; i < verkko.length; i++) {
            if (!puussa[i] && distance[i] > verkko[solmu][i]) {
                distance[i] = verkko[solmu][i];
                parent[i] = solmu;
            }
        }
    }

    /**
     * lTree(int alku) -metodi palauttaa pienimmän virittävän l-puun, eli
     * pienimmän verkon, joka yhdistää kaikki solmut ja jossa on täsmälleen yksi
     * sykli. Parametrina annettava numero kuvaa solmua, joka on syklissä ja
     * jonka asteluku on 2.
     *
     * @param alku
     * @return
     */
    public boolean[][] lTree(int alku) {
        puussa[alku] = true;
        solmuja++;
        int lahin = haeLahin(alku);
        puussa[lahin] = true;
        puu[alku][lahin] = true;
        
        int lahin2 = haeLahin(alku);
        puu[lahin2][alku] = true;
        normalPrim(lahin);

        return puu;
    }
    
     /**
     *
     * haeLahin(int solmu) palauttaa parametrina annettua solmua lähinnä olevan solmun, joka ei vielä ole virittävässä puussa.
     *
     */

    private int haeLahin(int solmu) {
        int lahin = Integer.MAX_VALUE;
        double lahinDistance2 = Double.MAX_VALUE;
        for (int i = 0;
                i < verkko.length;
                i++) {
            if (!puussa[i] && verkko[i][solmu] < lahinDistance2) {
                lahinDistance2 = verkko[i][solmu];
                lahin = i;
            }
        }
        return lahin;
    }
}
