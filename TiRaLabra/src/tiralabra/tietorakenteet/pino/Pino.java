/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.tietorakenteet.pino;

/**
 *
 * @author Arto
 */
public class Pino<S> {

    private PinoAlkio eka;
    private int koko;

    public Pino() {
        eka = null;
        koko = 0;
    }

    public void push(S sisalto) {
           PinoAlkio uusiAlkio = new PinoAlkio<S>(sisalto);
           uusiAlkio.setSeuraava(eka);
           eka = uusiAlkio;
           koko++;
    }
    
    public S pop(){
        if(empty()){
            return null;
        }
        S palautus = (S) eka.getSisalto();
        eka = eka.getSeuraava();
        koko--;
        return palautus;
    }
    
    public boolean empty(){
        if(koko==0){
            return true;
        }
        return false;
    }
}
