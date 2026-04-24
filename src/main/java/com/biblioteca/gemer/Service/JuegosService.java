package com.biblioteca.gemer.Service;

import com.biblioteca.gemer.Exceptions.JuegosExceptions;
import com.biblioteca.gemer.Model.Juegos;
import com.biblioteca.gemer.Repository.JuegosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class JuegosService {

    private final JuegosRepository repository;

    @Autowired
    public JuegosService(JuegosRepository repository) {
        this.repository = repository;
    }

    public List<Juegos> getAllGames() {
        return repository.findAll();
    }

    public Juegos getIdGame(Long id) {
        Optional<Juegos> result = repository.findById(id);

        if (result.isEmpty()) {
            throw new JuegosExceptions("ID not found or not exists");
        }

        return result.get();
    }

    public Juegos saveGame(Juegos juegos) {
        if (Objects.nonNull(juegos.getId())) {
            throw new JuegosExceptions("Id Duplicated");
        }

        return repository.save(juegos);
    }

    public void deleteGame(Long id) {
        if (getIdGame(id) == null) {
            throw new JuegosExceptions("ID not found or not exists");
        }

        repository.deleteById(id);
    }


}
