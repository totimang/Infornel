package com.mycompany.infornel.persistencia;

import com.mycompany.infornel.logica.FechaHora;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.infornel.logica.Usuario;
import com.mycompany.infornel.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FechaHoraJpaController implements Serializable {

    public FechaHoraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public FechaHoraJpaController() {
        emf = Persistence.createEntityManagerFactory("loginPU");
    }
    
    public void create(FechaHora fechaHora) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario = fechaHora.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getId());
                fechaHora.setUsuario(usuario);
            }
            em.persist(fechaHora);
            if (usuario != null) {
                usuario.getFechasHora().add(fechaHora);
                usuario = em.merge(usuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FechaHora fechaHora) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FechaHora persistentFechaHora = em.find(FechaHora.class, fechaHora.getId());
            Usuario usuarioOld = persistentFechaHora.getUsuario();
            Usuario usuarioNew = fechaHora.getUsuario();
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getId());
                fechaHora.setUsuario(usuarioNew);
            }
            fechaHora = em.merge(fechaHora);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getFechasHora().remove(fechaHora);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getFechasHora().add(fechaHora);
                usuarioNew = em.merge(usuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = fechaHora.getId();
                if (findFechaHora(id) == null) {
                    throw new NonexistentEntityException("The fechaHora with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FechaHora fechaHora;
            try {
                fechaHora = em.getReference(FechaHora.class, id);
                fechaHora.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fechaHora with id " + id + " no longer exists.", enfe);
            }
            Usuario usuario = fechaHora.getUsuario();
            if (usuario != null) {
                usuario.getFechasHora().remove(fechaHora);
                usuario = em.merge(usuario);
            }
            em.remove(fechaHora);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FechaHora> findFechaHoraEntities() {
        return findFechaHoraEntities(true, -1, -1);
    }

    public List<FechaHora> findFechaHoraEntities(int maxResults, int firstResult) {
        return findFechaHoraEntities(false, maxResults, firstResult);
    }

    private List<FechaHora> findFechaHoraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FechaHora.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public FechaHora findFechaHora(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FechaHora.class, id);
        } finally {
            em.close();
        }
    }

    public int getFechaHoraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FechaHora> rt = cq.from(FechaHora.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }    
}