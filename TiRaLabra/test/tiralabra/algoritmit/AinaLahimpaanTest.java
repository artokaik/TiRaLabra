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
public class AinaLahimpaanTest {
    
    public AinaLahimpaanTest() {
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
    public void AinaLahimpaanAntaaSamanTaiVahanHuonommanTuloksenKuinBranchAndBound10satunnaisellaVerkolla() {
        for (int i = 0; i < 10; i++) {
            XYVerkko verkko = new XYVerkko(10, 100, 100);
            BranchAndBound bab = new BranchAndBound(verkko);
            AinaLahimpaan al = new AinaLahimpaan(verkko);
            bab.etsiLyhinReitti();
            al.etsiLyhinReitti();
            assertTrue((bab.lyhimmanReitinPituus-al.lyhimmanReitinPituus) < 0.001);
            assertTrue((bab.lyhimmanReitinPituus*1.5-al.lyhimmanReitinPituus) >0);
        }
    }
    
    @Test
    public void AinaLahimpaanPalauttaaKierroksen10satunnaisellaVerkolla() {
        for (int i = 0; i < 10; i++) {
            XYVerkko verkko = new XYVerkko(30, 100, 100);
            AinaLahimpaan al = new AinaLahimpaan(verkko);
            al.etsiLyhinReitti();
            assertTrue(onKierros(al.getLyhinReitti(),al.solmut.length));
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
