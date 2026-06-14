package com.biblioteca.gemer.DTO;

import com.biblioteca.gemer.Enums.EstadoEnum;
import com.biblioteca.gemer.Enums.PlataformaEnum;

public class JuegosDTO {

    private String nombre;
    private String imagenUrl;
    private PlataformaEnum plataforma;
    private EstadoEnum estado = EstadoEnum.OBTENIDO;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public PlataformaEnum getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(PlataformaEnum plataforma) {
        this.plataforma = plataforma;
    }

    public EstadoEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoEnum estado) {
        this.estado = estado;
    }
}
