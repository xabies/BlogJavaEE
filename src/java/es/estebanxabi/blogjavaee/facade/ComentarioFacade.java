/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.estebanxabi.blogjavaee.facade;

import es.estebanxabi.blogjavaee.entity.Comentario;
import es.estebanxabi.blogjavaee.entity.Post;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author xabi
 */
@Stateless
public class ComentarioFacade extends AbstractFacade<Comentario> {

    @PersistenceContext(unitName = "BlogJavaEEPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComentarioFacade() {
        super(Comentario.class);
    }

    public List<Comentario> getComentariosPost(Post post) {

        List<Comentario> result = em.createNamedQuery("Comentario.findByPostId").setParameter("postId", post).getResultList();

        return result;

    }

}
