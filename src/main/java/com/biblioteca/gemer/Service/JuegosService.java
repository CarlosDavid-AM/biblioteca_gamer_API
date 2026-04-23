package com.biblioteca.gemer.Service;

import com.biblioteca.gemer.Model.Juegos;
import com.biblioteca.gemer.Repository.JuegosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JuegosService {

    private JuegosRepository repository;

    @Autowired
    public JuegosService(JuegosRepository repository) {
        this.repository = repository;
    }

    public List<Juegos> getAllGames() {
        return repository.findAll();
    }

    public Juegos saveGame(Juegos juegos) {
        return repository.save(juegos);
    }
}
