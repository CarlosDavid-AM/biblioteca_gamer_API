package com.biblioteca.gemer.Model;

import com.biblioteca.gemer.Enums.EstadoEnum;
import com.biblioteca.gemer.Enums.PlataformaEnum;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "juegos")
public class Juegos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "imagen_url", length = 500)
    private String imagenUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "plataforma", nullable = false)
    private PlataformaEnum plataforma;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoEnum estado = EstadoEnum.OBTENIDO;

    public Juegos(Long id, String nombre, String imagenUrl, PlataformaEnum plataforma, EstadoEnum estado) {
        this.id = id;
        this.nombre = nombre;
        this.imagenUrl = imagenUrl;
        this.plataforma = plataforma;
        this.estado = estado;
    }

    public Juegos() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Juegos juegos = (Juegos) o;
        return Objects.equals(id, juegos.id) && Objects.equals(nombre, juegos.nombre) && Objects.equals(imagenUrl, juegos.imagenUrl) && plataforma == juegos.plataforma && estado == juegos.estado;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, imagenUrl, plataforma, estado);
    }

    @Override
    public String toString() {
        return "Juegos{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", imagenUrl='" + imagenUrl + '\'' +
                ", plataforma=" + plataforma +
                ", estado=" + estado +
                '}';
    }
}
