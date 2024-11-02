package com.mycompany.infornel.logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class FechaHora implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String horaActual;
    private String entradaSalida;
    private String fecha;
    private String dia;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;    //Muchas fechas y horas pertenecen a un usuario.

    public FechaHora() {
    }

    public FechaHora(int id, String horaActual, String entradaSalida,
            String fecha, String dia, Usuario usuario) {
        this.id = id;
        this.horaActual = horaActual;
        this.entradaSalida = entradaSalida;
        this.fecha = fecha;
        this.dia = dia;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoraActual() {
        return horaActual;
    }

    public void setHoraActual(String horaActual) {
        this.horaActual = horaActual;
    }

    public String getEntradaSalida() {
        return entradaSalida;
    }

    public void setEntradaSalida(String entradaSalida) {
        this.entradaSalida = entradaSalida;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }                    
}