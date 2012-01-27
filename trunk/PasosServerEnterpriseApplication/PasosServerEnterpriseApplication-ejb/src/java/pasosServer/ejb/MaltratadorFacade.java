/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pasosServer.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pasosServer.model.Maltratador;

/**
 *
 * @author Juan Antonio
 */
@Stateless
public class MaltratadorFacade extends AbstractFacade<Maltratador> implements MaltratadorFacadeRemote {
    @PersistenceContext(unitName = "PasosServerEnterpriseApplication-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public MaltratadorFacade() {
        super(Maltratador.class);
    }
    
   
    @Override
    public Maltratador findByimei(String imei){
        return (Maltratador) em.createQuery("SELECT m FROM Maltratador m WHERE m.imei = :imei")
                .setParameter("imei", imei)
                .getSingleResult();
    }    
}
