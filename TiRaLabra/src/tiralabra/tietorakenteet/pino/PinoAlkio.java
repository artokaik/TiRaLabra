/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.tietorakenteet.pino;

/**
 *
 * @author Arto
 */
public class PinoAlkio<S> {
    private S sisalto;
    private PinoAlkio<S> seuraava;
    
    protected PinoAlkio(S sisalto){
        this.sisalto = sisalto;
        seuraava = null;
    }

    protected void setSisalto(S sisalto) {
        this.sisalto = sisalto;
    }

    protected void setSeuraava(PinoAlkio<S> seuraava) {
        this.seuraava = seuraava;
    }

    protected S getSisalto() {
        return sisalto;
    }

    protected PinoAlkio<S> getSeuraava() {
        return seuraava;
    }
}
