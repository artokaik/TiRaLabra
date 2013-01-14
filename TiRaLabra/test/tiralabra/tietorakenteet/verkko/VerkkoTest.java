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
    private int solmujenMaara;
    
    public VerkkoTest() {
    }
    

    
    @Before
    public void setUp() {
        solmujenMaara = 10;
        arvottuVerkko = Verkko.arvoUusi(solmujenMaara, 10, 10);
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
       assertEquals(solmujenMaara, arvottuVerkko.getVerkko().length);
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
}
