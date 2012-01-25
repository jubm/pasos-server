/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pasosServer.jspbeans;

import java.awt.Image;
import pasosServer.model.Protegido;

/**
 *
 * @author Juan Antonio
 */
public class ProtegidoInfoBean {
    private Protegido protegido;
    private Image foto;
    
    public ProtegidoInfoBean(){
        
    }

    /**
     * @return the protegido
     */
    public Protegido getProtegido() {
        return protegido;
    }

    /**
     * @param protegido the protegido to set
     */
    public void setProtegido(Protegido protegido) {
        this.protegido = protegido;
    }

    /**
     * @return the foto
     */
    public Image getFoto() {
        return foto;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(Image foto) {
        this.foto = foto;
    }

}
