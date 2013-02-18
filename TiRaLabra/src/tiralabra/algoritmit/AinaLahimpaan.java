/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.algoritmit;

import tiralabra.tietorakenteet.pino.Pino;
import tiralabra.tietorakenteet.verkko.XYVerkko;

/**
 * AinaLahimpaan on ajassa n^3 toimiva algoritmi. Se käy verkon läpi n kertaa
 * aloittaen kerran jokaisesta solmusta. Jokaisella läpikäynnillä se menee aina
 * solmusta lähimpään viereiseen solmuun kunnes verkko on käyty läpi. Tämän
 * jälkeen se tallettaa reitin ja sen pituuden jos se on lyhyempi kuin tähän
 * astisista reiteistä paras.
 *
 * Jos metodin etsiLyhinReitti() sijaan kutsutaan metodia aloitaEtsiminen(int
 * alku), toimii algoritmi ajassa n^2, mutta ratkaisu on keskimäärin huonompi
 * (eikä koskaan parempi).
 *
 * @author Arto
 */
public class AinaLahimpaan extends ReitinEtsija {

    /**
     *
     * @param verkko Verkko, josta lyhin Hamiltonin kierros etsitään
     */
    public AinaLahimpaan(XYVerkko verkko) {
        super(verkko);

    }

    /**
     * Etsii lyhimmän reitin aloittaen vuorollaan jokaisesta solmusta.
     */
    public void etsiLyhinReitti() {
        for (int i = 0; i < kaaret.length; i++) {
            kayty[i]=true;
            aloitaEtsiminen(i);
            for (int j = 0; j < kayty.length; j++) {
                kayty[j] = false;
            }
        }
    }

    /**
     * Aloittaa parametrina annetusta solmusta ja menee aina lähimpään solmuun
     * kunnes kaikissa solmuissa on käyty. Jos tämä reitti on lyhyempi kuin
     * lyhin tähän mennessä löydetty, metodi päivittää lyhimmän reitin. Rungon
     * aikavaatimus O(n) (while-loopissa käydään läpi kaikki solmut), ja while
     * loopin sisällä olevan hakumetodin aikavaatimus myös O(n). Näin ollen koko
     * metodin aikavaatimus on O(n^2)
     *
     * @param alku Sen solmun indeksi, josta reitin etsiminen aloitetaan
     */
    public void aloitaEtsiminen(int alku) {
        int x = alku;
        Pino<Integer> reitti = new Pino<Integer>();
        double reitinPituus = 0;
        reitti.push(x);
        while (!this.ollaankoReitinLopussa()) {
            int y = etsiLahinKaymaton(x);
            reitinPituus+=lisaaReittiin(reitti,x,y);
            x = y;
        }
        reitinPituus += kaaret[x][alku];
        paivitaLyhinReitti(reitti, reitinPituus);
    }
    
    /**
     * Lisää reittiin uuden solmun ja merkkaa uuden solmun käydyksi.
     * 
     * @param reitti Pino, johon on kerätty reitti tähän asti.
     * @param lahto Sen solmun indeksi, josta kaari lähtee
     * @param uusi Sen solmun indeksi, johon mennään seuraavaksi
     * @return Palauttaa reittiin lisätyn kaaren pituuden.
     */
    public double lisaaReittiin(Pino<Integer> reitti, int lahto, int uusi){
            reitti.push(uusi);
            double kaarenPituus = kaaret[lahto][uusi];
            kayty[uusi] = true;
            return kaarenPituus;
    }

    /**
     * Etsi parametrina annetun solmun lähimmän sellaisen naapurin, jossa ei ole
     * vielä käyty. Aikavaatimus O(n).
     *
     * @param solmu on solmu, jonka lähin käymätön naapuri etsitään
     * @return palauttaa numeron, joka vastaa lähintä käymätöntä solmua 
     */
    public int etsiLahinKaymaton(int solmu) {
        double pieninValimatka = Double.MAX_VALUE;
        int lahin = Integer.MAX_VALUE;
        for (int i = 0; i < kaaret.length; i++) {
            if (!kayty[i] && kaaret[solmu][i] < pieninValimatka) {
                pieninValimatka = kaaret[solmu][i];
                lahin = i;
            }
        }
        return lahin;
    }

}
