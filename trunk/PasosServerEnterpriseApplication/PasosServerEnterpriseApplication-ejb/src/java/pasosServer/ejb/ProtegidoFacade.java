/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pasosServer.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pasosServer.model.Protegido;

/**
 *
 * @author Juan Antonio
 */
@Stateless
public class ProtegidoFacade extends AbstractFacade<Protegido> implements ProtegidoFacadeRemote {
    @PersistenceContext(unitName = "PasosServerEnterpriseApplication-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProtegidoFacade() {
        super(Protegido.class);
    }
    
    
    @Override
    public Protegido findProtegidoByNombreAndApellidos(String nombre, String apellidos){
        return (Protegido) em.createQuery("select p from Protegido p where p.nombre=:nombre and p.apellidos=:apellidos")
                .setParameter("nombre", nombre)
                .setParameter("apellidos", apellidos)
                .getSingleResult();
    }
    
}