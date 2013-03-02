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
public class AntSystemTest {
    
    public AntSystemTest() {
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
    public void AntSystemAntaaSamanTaiVahanHuonommanTuloksenKuinBranchAndBound10satunnaisellaVerkolla() {
        for (int i = 0; i < 10; i++) {
            XYVerkko verkko = new XYVerkko(9, 100, 100);
            BranchAndBound bab = new BranchAndBound(verkko);
            AntSystem as = new AntSystem(verkko);
            bab.etsiLyhinReitti();
            as.etsiLyhinReitti();
            assertTrue((bab.lyhimmanReitinPituus-as.lyhimmanReitinPituus) < 0.001);
            assertTrue((bab.lyhimmanReitinPituus*1.5-as.lyhimmanReitinPituus) >0);
        }
    }
    
    @Test
    public void AntSystemPalauttaaKierroksen10satunnaisellaVerkolla() {
        for (int i = 0; i < 10; i++) {
            XYVerkko verkko = new XYVerkko(25, 100, 100);
            AntSystem as = new AntSystem(verkko);
            as.etsiLyhinReitti();
            assertTrue(onKierros(as.getLyhinReitti(),as.solmut.length));
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
