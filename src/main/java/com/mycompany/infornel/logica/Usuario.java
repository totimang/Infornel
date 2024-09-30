package com.mycompany.infornel.logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
/*mport javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;*/
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne; //Mucho a uno
import javax.persistence.OneToMany;


@Entity //Generamos la libreria de la linea 3, marcamos que "Usuario se va a transformar en tabla"
public class Usuario implements Serializable {
    
    //Atributos:
    @Id //Esta sera la llave primaria                   //A la Id generame el valor de forma automatica,
    //@GeneratedValue(strategy=GenerationType.SEQUENCE)   //siguiendo la estrategia secuencialmente.
    private int id;                                     //Cada vez que se agregue un usuario nuevo, se hara de forma automatica la Id.
    private String apellidoUsuario;
    private String nombreUsuario;
    private String dni;
    
    @ManyToOne
    @JoinColumn(name="fk_rol") //Acá le digo que tiene que tener como llave principal a "id"
    private Rol unRol;  //Utilizando el objeto unRol

    @OneToMany(mappedBy = "usuario")
    private List<FechaHora> fechasHora; //Un usuario puede temr muchas fechas y horas.
    
    //Constructor vacio:
    public Usuario() {
    }

    
    //Constructor con parametros:

    public Usuario(int id, String apellidoUsuario, String nombreUsuario, String dni, Rol unRol, List<FechaHora> fechasHora) {
        this.id = id;
        this.apellidoUsuario = apellidoUsuario;
        this.nombreUsuario = nombreUsuario;
        this.dni = dni;
        this.unRol = unRol;
        this.fechasHora = fechasHora;
    }
        
    
    //Metodos:
    public List<FechaHora> getFechasHora() {
        return fechasHora;
    }

    public void setFechasHora(List<FechaHora> fechasHora) {
        this.fechasHora = fechasHora;
    }

    public Rol getUnRol() {
        return unRol;
    }
    
    public void setUnRol(Rol unRol) {
        this.unRol = unRol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }
    
    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
     
}
