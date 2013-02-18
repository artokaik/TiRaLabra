/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.algoritmit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.tietorakenteet.pino.Pino;
import tiralabra.tietorakenteet.verkko.XYVerkko;

/**
 *
 * @author Arto
 */
public class KarpHeldTest {

    public KarpHeldTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void KarpHelhAntaaSamanTuloksenKuinBranchAndBound10satunnaisellaVerkollaJosReittiLöytyy() {
        for (int i = 0; i < 10; i++) {
            XYVerkko verkko = new XYVerkko(10, 100, 100);
            BranchAndBound bab = new BranchAndBound(verkko);
            KarpHeld kh = new KarpHeld(verkko);
            bab.etsiLyhinReitti();
            kh.etsiLyhinReitti();
            if (kh.isValmis()) {
                assertTrue(Math.abs(bab.lyhimmanReitinPituus - kh.lyhimmanReitinPituus) < 0.001);
            } else {
                assertTrue(Math.abs(kh.getMinimi()) <= bab.getLyhimmanReitinPituus());
            }
        }
    }

    @Test
    public void KarpHeldPalauttaaKierroksen10satunnaisellaVerkollaJosValmis() {
        for (int i = 0; i < 10; i++) {
            XYVerkko verkko = new XYVerkko(i * 2 + 10, 100, 100);
            KarpHeld kh = new KarpHeld(verkko);
            kh.etsiLyhinReitti();
            if (kh.isValmis()) {
                assertTrue(onKierros(kh.getLyhinReitti(), kh.solmut.length));
            }
        }
    }

    public boolean onKierros(Pino<Integer> kierros, int verkonKoko) {
        boolean[] mukana = new boolean[verkonKoko];
        for (int i = 0; i < verkonKoko; i++) {
            Integer x = kierros.pop();
            if (x == null) {
                return false;
            }
            if (mukana[x]) {
                return false;
            }
            mukana[x] = true;
        }
        if (kierros.pop() == null) {
            return true;
        }
        return false;
    }
}
