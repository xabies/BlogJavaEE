/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.estebanxabi.blogjavaee.facade;

import es.estebanxabi.blogjavaee.entity.Categoria;
import es.estebanxabi.blogjavaee.entity.Post;
import es.estebanxabi.blogjavaee.entity.Post_;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author xabi
 */
@Stateless
public class PostFacade extends AbstractFacade<Post> {

    @Override
    public List<Post> findRange(int[] range) {

        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();

        // Los ordenamos por fecha
        Root<Post> post = cq.from(Post.class);
        cq.select(post);
        cq.orderBy(getEntityManager().getCriteriaBuilder().desc(post.get(Post_.fecha)));

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();

    }

    
    public List<Post> findPostBycategory(Categoria categoria) {

        TypedQuery<Post> q = em.createNamedQuery("Post.findByCategoria", Post.class);
        q.setParameter("categoria", categoria);
        return q.getResultList();

    }
    
    
    public List<Post> findPostBycategory(Categoria categoria, int page) {

        TypedQuery<Post> q = em.createNamedQuery("Post.findByCategoria", Post.class);
        q.setParameter("categoria", categoria);
        q.setMaxResults(5);
        q.setFirstResult(5*page);
        return q.getResultList();

    }

    @PersistenceContext(unitName = "BlogJavaEEPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PostFacade() {
        super(Post.class);
    }
    
    
    public List<Post> findPostByPage(int page) {

        TypedQuery<Post> q = em.createNamedQuery("Post.findAllOrdenados", Post.class);
        q.setMaxResults(5);
        q.setFirstResult(5*page);
        
        return q.getResultList();

    }

}
