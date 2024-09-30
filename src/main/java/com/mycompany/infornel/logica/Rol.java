package com.mycompany.infornel.logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
/*mport javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;*/
import javax.persistence.Id;
import javax.persistence.OneToMany; //Uno a muchos

@Entity
public class Rol implements Serializable {
    
    //Definimos los atributos:
    @Id //Definimos Id como clave primaria
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String nombreRol;
    private String descripcion;
    
    @OneToMany (mappedBy="unRol") //Acá le digo con quién voy a mapear del lado de usuario, con el objeto unRol
    private List<Usuario> listaUsuarios;

    //Constructor vacío:
    public Rol() {
    }

    //Constructor con parametros

    public Rol(int id, String nombreRol, String descripcion, List<Usuario> listaUsuarios) {
        this.id = id;
        this.nombreRol = nombreRol;
        this.descripcion = descripcion;
        this.listaUsuarios = listaUsuarios;
    }
    
    
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    //Metodos:
    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }           
}
