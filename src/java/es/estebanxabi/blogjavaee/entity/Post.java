/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.estebanxabi.blogjavaee.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author xabi
 */
@Entity
@Table(name = "POST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Post.findAll", query = "SELECT p FROM Post p"),
    @NamedQuery(name = "Post.findAllOrdenados", query = "SELECT p FROM Post p ORDER BY p.fecha DESC"),
    @NamedQuery(name = "Post.findByPostId", query = "SELECT p FROM Post p WHERE p.postId = :postId"),
    @NamedQuery(name = "Post.findByPage", query = "SELECT p FROM Post p ORDER BY p.fecha"),
    @NamedQuery(name = "Post.findByTitulo", query = "SELECT p FROM Post p WHERE p.titulo = :titulo"),
    @NamedQuery(name = "Post.findByImagen", query = "SELECT p FROM Post p WHERE p.imagen = :imagen"),
    @NamedQuery(name = "Post.findByContenido", query = "SELECT p FROM Post p WHERE p.contenido = :contenido"),
    @NamedQuery(name = "Post.findByCategoria", query = "SELECT p FROM Post p WHERE p.categoriaId = :categoria ORDER BY p.fecha DESC"),
    @NamedQuery(name = "Post.findByFecha", query = "SELECT p FROM Post p WHERE p.fecha = :fecha")})
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "POST_ID")
    private Integer postId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TITULO")
    private String titulo;
    @Size(max = 100)
    @Column(name = "IMAGEN")
    private String imagen;
    @Size(max = 5000)
    @Column(name = "CONTENIDO")
    private String contenido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "CATEGORIA_ID", referencedColumnName = "CATEGORIA_ID")
    @ManyToOne
    private Categoria categoriaId;
    @OneToMany(mappedBy = "postId")
    private Collection<Comentario> comentarioCollection;

    public Post() {
    }

    public Post(Integer postId) {
        this.postId = postId;
    }

    public Post(Integer postId, String titulo, Date fecha) {
        this.postId = postId;
        this.titulo = titulo;
        this.fecha = fecha;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Categoria getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Categoria categoriaId) {
        this.categoriaId = categoriaId;
    }

    @XmlTransient
    public Collection<Comentario> getComentarioCollection() {
        return comentarioCollection;
    }

    public void setComentarioCollection(Collection<Comentario> comentarioCollection) {
        this.comentarioCollection = comentarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (postId != null ? postId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Post)) {
            return false;
        }
        Post other = (Post) object;
        if ((this.postId == null && other.postId != null) || (this.postId != null && !this.postId.equals(other.postId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.estebanxabi.blogjavaee.entity.Post[ postId=" + postId + " ]";
    }

}
