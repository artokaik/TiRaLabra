/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.tietorakenteet.pino;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import tiralabra.tietorakenteet.pino.Pino;
import tiralabra.tietorakenteet.pino.PinoAlkio;

/**
 *
 * @author Arto
 */
public class PinoTest {
    private Pino<String> stringPino;
    private Pino<Integer> numeroPino;
    
    public PinoTest() {
    }
    

    @Before
    public void setUp() {
        stringPino = new Pino<String>();
        numeroPino = new Pino<Integer>();
    }
    
    @Test
    public void stringPinoAluksiTyhjä(){
        assertTrue(stringPino.empty());
    }
    
    @Test
    public void stringPinoPalauttaaAluksiNull(){
        assertEquals(null, stringPino.pop());
    }
    
    @Test
    public void stringPinoPushToimii(){
        stringPino.push("toimii");
        assertFalse(stringPino.empty());
        assertEquals("toimii", stringPino.pop());
    }
       

}
