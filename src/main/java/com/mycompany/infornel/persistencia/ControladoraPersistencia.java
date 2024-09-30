package com.mycompany.infornel.persistencia;
import com.mycompany.infornel.logica.FechaHora;
import com.mycompany.infornel.logica.Rol;
import com.mycompany.infornel.logica.Usuario;
import com.mycompany.infornel.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladoraPersistencia {
    
    UsuarioJpaController usuJpa = new UsuarioJpaController();
    RolJpaController rolJpa = new RolJpaController();
    FechaHoraJpaController fechaHoraJpa = new  FechaHoraJpaController ();

    public List<Usuario> traerUsuarios() {
        
        List<Usuario> listaUsuario = usuJpa.findUsuarioEntities(); //Me trae todos los usuarios de la base de datos
        return listaUsuario;
        
    }

    public List<Rol> traerRoles() {
        
        //SELECT * FROM Roles
        return rolJpa.findRolEntities();
                        
    }
    
    public List<FechaHora> traerFechaHora(){
        List<FechaHora> listaFechaHora = fechaHoraJpa.findFechaHoraEntities();
        return listaFechaHora;
    }

    public void crearUsuario(Usuario usu) {
        
        try {
            usuJpa.create(usu);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void borrarUsuario(int id_usuario) {
        
        try {
            usuJpa.destroy(id_usuario);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public Usuario traerUsuario(int id_usuario) {
        
        return usuJpa.findUsuario(id_usuario);
        
    }

    public void editarUsuario(Usuario usu) {
        
        try {
            usuJpa.edit(usu);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void crearHoraEntrada(FechaHora fecHor) {
        try {
            fechaHoraJpa.create(fecHor);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
