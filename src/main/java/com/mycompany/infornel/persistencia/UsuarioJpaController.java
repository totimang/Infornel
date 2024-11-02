package com.mycompany.infornel.persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.infornel.logica.Rol;
import com.mycompany.infornel.logica.FechaHora;
import com.mycompany.infornel.logica.Usuario;
import com.mycompany.infornel.persistencia.exceptions.NonexistentEntityException;
import com.mycompany.infornel.persistencia.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public UsuarioJpaController() {
        emf = Persistence.createEntityManagerFactory("loginPU");
    }
    
    public void create(Usuario usuario) throws PreexistingEntityException, Exception {
        if (usuario.getFechasHora() == null) {
            usuario.setFechasHora(new ArrayList<FechaHora>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rol unRol = usuario.getUnRol();
            if (unRol != null) {
                unRol = em.getReference(unRol.getClass(), unRol.getId());
                usuario.setUnRol(unRol);
            }
            List<FechaHora> attachedFechasHora = new ArrayList<FechaHora>();
            for (FechaHora fechasHoraFechaHoraToAttach : usuario.getFechasHora()) {
                fechasHoraFechaHoraToAttach = em.getReference(fechasHoraFechaHoraToAttach.getClass(), fechasHoraFechaHoraToAttach.getId());
                attachedFechasHora.add(fechasHoraFechaHoraToAttach);
            }
            usuario.setFechasHora(attachedFechasHora);
            em.persist(usuario);
            if (unRol != null) {
                unRol.getListaUsuarios().add(usuario);
                unRol = em.merge(unRol);
            }
            for (FechaHora fechasHoraFechaHora : usuario.getFechasHora()) {
                Usuario oldUsuarioOfFechasHoraFechaHora = fechasHoraFechaHora.getUsuario();
                fechasHoraFechaHora.setUsuario(usuario);
                fechasHoraFechaHora = em.merge(fechasHoraFechaHora);
                if (oldUsuarioOfFechasHoraFechaHora != null) {
                    oldUsuarioOfFechasHoraFechaHora.getFechasHora().remove(fechasHoraFechaHora);
                    oldUsuarioOfFechasHoraFechaHora = em.merge(oldUsuarioOfFechasHoraFechaHora);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuario(usuario.getId()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getId());
            Rol unRolOld = persistentUsuario.getUnRol();
            Rol unRolNew = usuario.getUnRol();
            List<FechaHora> fechasHoraOld = persistentUsuario.getFechasHora();
            List<FechaHora> fechasHoraNew = usuario.getFechasHora();
            if (unRolNew != null) {
                unRolNew = em.getReference(unRolNew.getClass(), unRolNew.getId());
                usuario.setUnRol(unRolNew);
            }
            List<FechaHora> attachedFechasHoraNew = new ArrayList<FechaHora>();
            for (FechaHora fechasHoraNewFechaHoraToAttach : fechasHoraNew) {
                fechasHoraNewFechaHoraToAttach = em.getReference(fechasHoraNewFechaHoraToAttach.getClass(), fechasHoraNewFechaHoraToAttach.getId());
                attachedFechasHoraNew.add(fechasHoraNewFechaHoraToAttach);
            }
            fechasHoraNew = attachedFechasHoraNew;
            usuario.setFechasHora(fechasHoraNew);
            usuario = em.merge(usuario);
            if (unRolOld != null && !unRolOld.equals(unRolNew)) {
                unRolOld.getListaUsuarios().remove(usuario);
                unRolOld = em.merge(unRolOld);
            }
            if (unRolNew != null && !unRolNew.equals(unRolOld)) {
                unRolNew.getListaUsuarios().add(usuario);
                unRolNew = em.merge(unRolNew);
            }
            for (FechaHora fechasHoraOldFechaHora : fechasHoraOld) {
                if (!fechasHoraNew.contains(fechasHoraOldFechaHora)) {
                    fechasHoraOldFechaHora.setUsuario(null);
                    fechasHoraOldFechaHora = em.merge(fechasHoraOldFechaHora);
                }
            }
            for (FechaHora fechasHoraNewFechaHora : fechasHoraNew) {
                if (!fechasHoraOld.contains(fechasHoraNewFechaHora)) {
                    Usuario oldUsuarioOfFechasHoraNewFechaHora = fechasHoraNewFechaHora.getUsuario();
                    fechasHoraNewFechaHora.setUsuario(usuario);
                    fechasHoraNewFechaHora = em.merge(fechasHoraNewFechaHora);
                    if (oldUsuarioOfFechasHoraNewFechaHora != null && !oldUsuarioOfFechasHoraNewFechaHora.equals(usuario)) {
                        oldUsuarioOfFechasHoraNewFechaHora.getFechasHora().remove(fechasHoraNewFechaHora);
                        oldUsuarioOfFechasHoraNewFechaHora = em.merge(oldUsuarioOfFechasHoraNewFechaHora);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = usuario.getId();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            Rol unRol = usuario.getUnRol();
            if (unRol != null) {
                unRol.getListaUsuarios().remove(usuario);
                unRol = em.merge(unRol);
            }
            List<FechaHora> fechasHora = usuario.getFechasHora();
            for (FechaHora fechasHoraFechaHora : fechasHora) {
                fechasHoraFechaHora.setUsuario(null);
                fechasHoraFechaHora = em.merge(fechasHoraFechaHora);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }    
}