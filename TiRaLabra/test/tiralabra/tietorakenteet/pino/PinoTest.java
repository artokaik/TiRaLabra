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
    public void stringPinoAluksiTyhjä() {
        assertTrue(stringPino.empty());
    }

    @Test
    public void stringPinoPalauttaaAluksiNull() {
        assertEquals(null, stringPino.pop());
    }

    @Test
    public void stringPinoPushToimii() {
        stringPino.push("toimii");
        assertFalse(stringPino.empty());
        assertEquals("toimii", stringPino.pop());
    }

    @Test
    public void stringPinoPushPopPopToimii() {
        stringPino.push("toimii");
        assertEquals("toimii", stringPino.pop());
        assertEquals(null, stringPino.pop());
        assertTrue(stringPino.empty());
    }

    @Test
    public void stringPinoKlooniToimiiTyhjalla() {
        Pino<String> uusiPino = stringPino.clone();
        assertTrue(uusiPino.empty());
        assertEquals(null, uusiPino.pop());
    }

    @Test
    public void stringPinoKlooniToimiiYhdella() {
        stringPino.push("eka");
        Pino<String> uusiPino = stringPino.clone();
        assertFalse(uusiPino.empty());
        assertEquals("eka", uusiPino.pop());
        assertTrue(uusiPino.empty());
        assertFalse(stringPino.empty());
        assertEquals("eka", stringPino.pop());
    }
    
    @Test
    public void stringPinoKlooniToimiiUsealla() {
        stringPino.push("eka");
        stringPino.push("toka");
        stringPino.push("kolmas");
        Pino<String> uusiPino = stringPino.clone();
        assertFalse(uusiPino.empty());
        assertEquals("kolmas", uusiPino.pop());
        assertEquals("toka", uusiPino.pop());
        assertFalse(uusiPino.empty());
        assertEquals("eka", uusiPino.pop());
        assertTrue(uusiPino.empty());
        
        assertFalse(stringPino.empty());
        assertEquals("kolmas", stringPino.pop());
        assertEquals("toka", stringPino.pop());
        assertFalse(stringPino.empty());
        assertEquals("eka", stringPino.pop());
        assertTrue(stringPino.empty());
    }
}
