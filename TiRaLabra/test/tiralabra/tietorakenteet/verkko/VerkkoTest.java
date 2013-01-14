package tiralabra.tietorakenteet.verkko;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Arto
 */
public class VerkkoTest {
    private Verkko arvottuVerkko;
    private int arvottujenSolmujenMaara;
    private Verkko rakennettuVerkko;
    private int rakennetunVerkonKoko;
    
    public VerkkoTest() {
    }
    

    
    @Before
    public void setUp() {
        arvottujenSolmujenMaara = 10;
        arvottuVerkko = Verkko.arvoUusi(arvottujenSolmujenMaara, 10, 10);
        rakennetunVerkonKoko = 10;
        rakennettuVerkko = new Verkko(rakennetunVerkonKoko);
        
    }
    
   @Test
   public void verkkoSymmetrinen(){
       for (int i = 0; i < arvottuVerkko.getVerkko().length; i++) {
           for (int j = 0; j < arvottuVerkko.getVerkko().length; j++) {
               assertTrue(arvottuVerkko.getVerkko()[i][j]-arvottuVerkko.getVerkko()[j][i]<0.00001);
               assertTrue(arvottuVerkko.getVerkko()[i][j]-arvottuVerkko.getVerkko()[j][i]>-0.00001); 
           }          
       }
   }
   
   @Test
   public void kaikkiSolmutMukana(){
       assertEquals(arvottuVerkko.getVerkko().length, arvottuVerkko.getSolmut().length);
   }
   
   @Test 
   public void kaikkiVerkkotaulukonRivitYhtaPitkiaKuinVerkkoTaulukko(){
       for (int i = 0; i < arvottuVerkko.getVerkko().length; i++) {
           assertEquals(arvottuVerkko.getVerkko().length, arvottuVerkko.getVerkko()[i].length);           
       }  
   }
   @Test
   public void verkkoOikeanKokoinen(){
       assertEquals(arvottujenSolmujenMaara, arvottuVerkko.getVerkko().length);
   }
   
   @Test
   public void verkkoonTallennettuSuorinReitti(){
       for (int i = 0; i < arvottuVerkko.getVerkko().length; i++) {
           for (int j = 0; j < arvottuVerkko.getVerkko().length; j++) {
               for (int k = 0; k < arvottuVerkko.getVerkko().length; k++) {
                   assertTrue(arvottuVerkko.getVerkko()[i][j]+arvottuVerkko.getVerkko()[j][k]>=arvottuVerkko.getVerkko()[i][k]);
               }
           }
       }
   }
   
   @Test
   public void verkonRakennusToimii(){
       assertFalse(rakennettuVerkko.onkoTaysi());
       assertTrue(rakennettuVerkko.lisaaSolmu(new Solmu(0,0)));
       for (int i = 1; i < rakennetunVerkonKoko; i++) {
           assertTrue(rakennettuVerkko.lisaaSolmu(new Solmu(i,i)));           
       }
       assertFalse(rakennettuVerkko.lisaaSolmu(new Solmu(3,4)));
       assertTrue(rakennettuVerkko.onkoTaysi());   
   }
}
