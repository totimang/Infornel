package com.mycompany.infornel.logica;

import com.mycompany.infornel.persistencia.ControladoraPersistencia;
import java.util.List;

public class Controladora {
    
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    public Controladora() {
        controlPersis = new ControladoraPersistencia();
    }    
    
    //Validar el usuario y Dni
    public Usuario validarUsuario(String usuario, String dni) {
        
        // mensaje="";
        Usuario usr = null;
        
        List<Usuario> listaUsuarios = controlPersis.traerUsuarios();
        
        for (Usuario usu : listaUsuarios) {
            
            if (usu.getNombreUsuario().equals(usuario)) { //usamos "equals" 
                                                                //porque comparamos
                                                                //cadenas de caracteres                
                
                if(usu.getDni().equals(dni)) {
                    
                    //mensaje = "Usuario y dni correctos. Bienvenido/a!";
                    usr = usu;
                    return usr;
                }
                else {
                    //mensaje = "Dni incorrecto";
                    usr = null;
                    return usr;
                }
            }
            else {
                //mensaje ="Usuario no encontrado";
                usr = null;
            }
        }        
        return usr;        
    }
    
    //Validar solo Dni:
    public Usuario validarDni(String dni) {
        
        // mensaje="";
        Usuario usr = null;
        
        List<Usuario> listaUsuarios = controlPersis.traerUsuarios();
        
        for (Usuario usu : listaUsuarios) {
            
            if (usu.getDni().equals(dni)) { //usamos "equals" porque 
                                                    //comparamos cadenas de caracteres                
                
                if(usu.getDni().equals(dni)) {
                    
                    //mensaje = "Dni correcto. Bienvenido/a!";
                    usr = usu;
                    return usr;
                }
                else {
                    //mensaje = "Dni incorrecto";
                    usr = null;
                    return usr;
                }
            }
            else {
                //mensaje ="Usuario no encontrado";
                usr = null;
            }
        }        
        return usr;        
    }

    public List<Usuario> traerUsuarios() {
        
        return controlPersis.traerUsuarios();        
    }

    public List<Rol> traerRoles() {
        
        return controlPersis.traerRoles();        
    }
    
    public List<FechaHora> traerFechaHora(){
        
        return controlPersis.traerFechaHora();        
    }
    
    public void crearUsuario(String apellido,String usuario,
            String dniNuevo, String rolRecibido) {
        
        Usuario usu = new Usuario ();
        usu.setApellidoUsuario(apellido);
        usu.setNombreUsuario(usuario);
        usu.setDni(dniNuevo);
        
        Rol rolEncontrado = new Rol();
        rolEncontrado = this.traerRol(rolRecibido);
        
        if (rolEncontrado!=null) {
            usu.setUnRol(rolEncontrado);
        }
        
        int id = this.buscarUltimaIdUsuarios();
        usu.setId(id+1);
        
        controlPersis.crearUsuario(usu);                
    }
    


    private Rol traerRol(String rolRecibido) {
        
        List<Rol> listaRoles = controlPersis.traerRoles();
        
        for (Rol rol:listaRoles) {
            if (rol.getNombreRol().equals(rolRecibido)) {
                return rol;
            }
        }
        return null;
    }

    private int buscarUltimaIdUsuarios() {
        
        List<Usuario> listaUsuarios = this.traerUsuarios();
        
        Usuario usu = listaUsuarios.get(listaUsuarios.size()-1);
        return usu.getId();        
    }

    public void borrarUsuario(int id_usuario) {
        
        controlPersis.borrarUsuario (id_usuario);        
    }

    public Usuario traerUsuario(int id_usuario) {
        
        return controlPersis.traerUsuario(id_usuario);        
    }

    public void editarUsuario(Usuario usu, String apellido,String usuario,
            String dniNuevo, String rolRecibido) {
        
        usu.setApellidoUsuario(apellido);
        usu.setNombreUsuario(usuario);
        usu.setDni(dniNuevo);
        
        Rol rolEncontrado = new Rol();
        rolEncontrado = this.traerRol(rolRecibido);
        
        if (rolEncontrado!=null) {
            usu.setUnRol(rolEncontrado);
        }        
        controlPersis.editarUsuario(usu);        
    }    

    public void crearHoraEntrada(String horaActual, String entradaSalida,
            String fecha, String dia, Usuario usuario) {
        
        FechaHora fecHor = new FechaHora ();
        fecHor.setHoraActual(horaActual);
        fecHor.setEntradaSalida(entradaSalida);
        fecHor.setFecha(fecha);
        fecHor.setDia(dia);
        fecHor.setUsuario(usuario);
        
        controlPersis.crearHoraEntrada(fecHor);        
    }

    public Usuario obtenerUsuarioPorId(int idUsuario) {
        return controlPersis.buscarUsuario(idUsuario);
    }
}