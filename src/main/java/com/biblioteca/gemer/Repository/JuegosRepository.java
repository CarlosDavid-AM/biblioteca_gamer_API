package com.biblioteca.gemer.Repository;

import com.biblioteca.gemer.Enums.EstadoEnum;
import com.biblioteca.gemer.Enums.PlataformaEnum;
import com.biblioteca.gemer.Model.Juegos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JuegosRepository extends JpaRepository<Juegos, Long> {
    List<Juegos> findByEstado(EstadoEnum estado);
    List<Juegos> findByPlataforma(PlataformaEnum plataforma);
    List<Juegos> findByEstadoAndPlataforma(EstadoEnum estado, PlataformaEnum plataforma);
}
