package tiralabra.tietorakenteet.verkko;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.tietorakenteet.verkko.Solmu;

/**
 *
 * @author Arto
 */
public class SolmuTest {
    private Solmu solmu1;
    private Solmu solmu2;
    
    public SolmuTest() {
    }
    
    
    @Before
    public void setUp() {
        solmu1 = new Solmu(2,2);
        solmu2 = new Solmu(5,6);
    }
    
    @Test
    public void laskeEtaisyysOikein(){
        assertTrue(solmu1.laskeEtaisyys(solmu2)>4.99999);
        assertTrue(solmu1.laskeEtaisyys(solmu2)<5.00001);
        assertTrue(solmu2.laskeEtaisyys(solmu1)>4.99999);
        assertTrue(solmu2.laskeEtaisyys(solmu1)<5.00001);
    }
    

}
