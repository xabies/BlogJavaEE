/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.estebanxabi.blogjavaee.facade;

import es.estebanxabi.blogjavaee.entity.Categoria;
import es.estebanxabi.blogjavaee.entity.Post;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author xabi
 */
@Stateless
public class CategoriaFacade extends AbstractFacade<Categoria> {

    @PersistenceContext(unitName = "BlogJavaEEPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriaFacade() {
        super(Categoria.class);
    }

    public Categoria findCategory(String name) {
        Query q = em.createNamedQuery("Categoria.findByNombrecategoria").setParameter("nombrecategoria", name);

        
        return (Categoria) q.getSingleResult();

    }
}
