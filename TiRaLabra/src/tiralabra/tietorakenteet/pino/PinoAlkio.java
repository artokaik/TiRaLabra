/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.tietorakenteet.pino;

/**
 *
 * @param <S> 
 * @author Arto
 */
public class PinoAlkio<S> {
    private S sisalto;
    private PinoAlkio<S> seuraava;
    
    /**
     *
     * @param sisalto
     */
    protected PinoAlkio(S sisalto){
        this.sisalto = sisalto;
        seuraava = null;
    }

    /**
     *
     * @param sisalto
     */
    protected void setSisalto(S sisalto) {
        this.sisalto = sisalto;
    }

    /**
     *
     * @param seuraava
     */
    protected void setSeuraava(PinoAlkio<S> seuraava) {
        this.seuraava = seuraava;
    }

    /**
     *
     * @return
     */
    protected S getSisalto() {
        return sisalto;
    }

    /**
     *
     * @return
     */
    protected PinoAlkio<S> getSeuraava() {
        return seuraava;
    }
}
